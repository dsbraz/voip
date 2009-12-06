package consolidacao.application.service.impl;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;
import org.jboss.annotation.ejb.Clustered;

import consolidacao.application.service.ConsolidacaoNacionalService;
import consolidacao.domain.model.chamada.Cdr;
import consolidacao.domain.model.instituicao.Instituicao;
import consolidacao.domain.model.instituicao.InstituicaoRepository;
import consolidacao.domain.service.RegistroChamadaService;
import consolidacao.infrastructure.consolidacao.InstituicaoProxy;
import consolidacao.infrastructure.log.LogManager;

import fonernp.aaa.accounting.TracingInterceptor;
import fonernp.aaa.authorization.AuthorizationInterceptor;
import fonernp.aaa.authorization.Secure;

/**
 * Implementa o servico de aplicacao para a consolidacao nacional da chamadas
 * @see consolidacao.application.service.ConsolidacaoNacionalService
 * @author daniel.braz
 */
@Stateless
@Clustered
@Interceptors( { TracingInterceptor.class, AuthorizationInterceptor.class })
@TransactionManagement(TransactionManagementType.BEAN)
public class ConsolidacaoNacionalServiceImpl implements ConsolidacaoNacionalService {

	private final Logger log = LogManager.logger;
	private static final Object lock = new Object();
	private UserTransaction userTransaction;
	private InstituicaoRepository instituicaoAtivaRepository;
	private RegistroChamadaService registroChamadaService;

	/**
	 * @see consolidacao.application.service.ConsolidacaoNacionalService#consolidarChamadas()
	 */
	@Secure(rolesAllowed = { "_system_", "admin" })
	public void consolidarChamadas() {
		final ExecutorService threadPool = Executors.newFixedThreadPool(25);
		try {
			final List<Instituicao> instituicoesAtivas = instituicaoAtivaRepository.consultar();
			for (final Instituicao instituicao : instituicoesAtivas) {
				threadPool.execute(new Runnable() {
					public void run() {
						consolidarChamadasLocais(instituicao);
					}
				});
			}
		} finally {
			threadPool.shutdown();
		}
	}

	private void consolidarChamadasLocais(final Instituicao instituicao) {
		assert instituicao != null;
		try {
			final InstituicaoProxy proxy = new InstituicaoProxy(instituicao);
			List<Cdr> cdrsNaoColetados = null;
			while ((cdrsNaoColetados = proxy.consultarCdrsNaoColetados()) != null && !cdrsNaoColetados.isEmpty()) {
				for (final Cdr cdr : cdrsNaoColetados) {
					if (validarCdr(cdr)) {
						synchronized (lock) {
							try {
								userTransaction.begin();
								registroChamadaService.registrarCdr(cdr);
								userTransaction.commit();
								cdr.registradoComSucesso();
							} catch (final Exception e) {
								log.warn("Nao foi possivel registrar o CDR :: IU " + instituicao.id() + " :: CDR "
										+ cdr.id() + " :: " + e.getMessage());
								log.debug(e.getMessage(), e);
								try {
									userTransaction.rollback();
								} catch (final Exception e1) {
									log.warn("Nao foi possivel realizar o rollback da transacao");
									log.debug(e1.getMessage(), e1);
								}
							}
						}
						Thread.yield();
					}
				}
				proxy.confirmarColetaCdrs(cdrsNaoColetados);
			}
		} catch (final NotBoundException e) {
			log.warn("Servidor nao existe ou nao esta disponivel :: IU " + instituicao.id());
			log.debug(e.getMessage(), e);
		} catch (final RemoteException e) {
			log.warn("Nao foi possivel processar a coleta :: IU " + instituicao.id());
			log.debug(e.getMessage(), e);
		}
	}

	private boolean validarCdr(final Cdr cdr) {
		assert cdr != null;
		return cdr.instituicaoOrigem() != null && cdr.instituicaoDestino() != null;
	}

	/**
	 * Injeta a UserTransaction
	 * @param userTransaction UserTransacion a ser injetada
	 */
	@Resource
	public void setUserTransaction(final UserTransaction userTransaction) {
		this.userTransaction = userTransaction;
	}

	/**
	 * Injeta o repositorio das instituicoes
	 * @param repository Repositorio a ser injetado
	 */
	@EJB
	public void setInstituicaoAtivaRepository(final InstituicaoRepository repository) {
		instituicaoAtivaRepository = repository;
	}

	/**
	 * Injeta o servico de dominio RegistroChamadaService
	 * @param service Servico a ser injetado
	 */
	@EJB
	public void setRegistroChamadaService(final RegistroChamadaService service) {
		registroChamadaService = service;
	}

}
