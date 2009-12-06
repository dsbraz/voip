package agente.application.service.impl;

import java.util.List;

import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import agente.application.service.ColetaCdrService;
import agente.domain.model.cdr.Cdr;
import agente.domain.model.cdr.CdrRepository;
import agente.domain.model.coleta.Coleta;
import agente.domain.model.coleta.ColetaRepository;
import agente.infrastructure.util.PersistenceManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * Implementa o servico de aplicacao de coleta dos CDR's
 * @see agente.application.service.ColetaCdrService
 * @author daniel.braz
 */
@Singleton
public class ColetaCdrServiceImpl implements ColetaCdrService {

	private final Logger log;
	private final PersistenceManager pm;
	private final CdrRepository cdrRepository;
	private final ColetaRepository coletaRepository;

	/**
	 * Construtor da classe ColetaCdrServiceImpl
	 * @param log Logger utilizado pela classe
	 * @param persistenceManager PersistenceManager utilizado para controlar as transacoes
	 * @param cdrRepository CdrRepository
	 * @param coletaRepository ColetaRepository
	 */
	@Inject
	public ColetaCdrServiceImpl(final Logger log, final PersistenceManager persistenceManager,
			final CdrRepository cdrRepository, final ColetaRepository coletaRepository) {
		super();
		this.log = log;
		pm = persistenceManager;
		this.cdrRepository = cdrRepository;
		this.coletaRepository = coletaRepository;
	}

	/**
	 * @see agente.application.service.ColetaCdrService#consultarCdrsNaoColetados()
	 */
	public List<Cdr> consultarCdrsNaoColetados() {
		List<Cdr> result = null;
		try {
			pm.openEntityManager();
			result = cdrRepository.consultar();
		} finally {
			pm.closeEntityManager();
		}
		return result;
	}

	/**
	 * @see agente.application.service.ColetaCdrService#confirmarColeta(java.util.List)
	 */
	public void confirmarColeta(final List<Coleta> cdrsColetados) {
		try {
			final EntityTransaction tx = pm.openEntityManager().getTransaction();
			for (final Coleta coleta : cdrsColetados) {
				try {
					tx.begin();
					coletaRepository.adicionar(coleta);
					tx.commit();
				} catch (final Exception e) {
					log.warn("Nao foi possivel confirmar a coleta :: Cdr " + coleta.cdr());
					log.debug(e.getMessage(), e);
					tx.rollback();
				}
			}
		} finally {
			pm.closeEntityManager();
		}
	}
}
