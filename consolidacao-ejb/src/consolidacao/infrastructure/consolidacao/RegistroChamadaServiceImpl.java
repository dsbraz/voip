package consolidacao.infrastructure.consolidacao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.jboss.annotation.ejb.Clustered;

import consolidacao.domain.model.chamada.Cdr;
import consolidacao.domain.model.chamada.Chamada;
import consolidacao.domain.model.chamada.ChamadaRepository;
import consolidacao.domain.model.chamada.TipoChamada;
import consolidacao.domain.model.instituicao.Instituicao;
import consolidacao.domain.model.instituicao.InstituicaoRepository;
import consolidacao.domain.service.RegistroChamadaService;

import fonernp.aaa.accounting.TracingInterceptor;

/**
 * Implementa o servico de dominio Registro de Chamadas
 * @see consolidacao.domain.service.RegistroChamadaService
 * @author daniel.braz
 */
@Stateless
@Clustered
@Interceptors(TracingInterceptor.class)
public class RegistroChamadaServiceImpl implements RegistroChamadaService {

	private ChamadaRepository chamadaRepository;
	private InstituicaoRepository instituicaoRepository;

	/**
	 * @see consolidacao.domain.service.RegistroChamadaService#registrarCdr(consolidacao.domain.model.chamada.Cdr)
	 */
	public void registrarCdr(final Cdr cdr) {
		Chamada chamada = chamadaRepository.consultarPorIdSip(cdr.sipCallId());
		if (chamada == null) {
			final Instituicao origem = instituicaoRepository.consultar(cdr.instituicaoOrigem());
			final Instituicao destino = instituicaoRepository.consultar(cdr.instituicaoDestino());
			chamada = new Chamada(null, cdr.sipCallId(), origem, destino, cdr.dddOrigem(), cdr.dddDestino(), cdr
					.duracaoChamada());
		}
		if (cdr.coletadoNaOrigem()) {
			final TipoChamada tipoOrigem = TipoChamada.get(cdr.tipoOrigem());
			chamada.registrarDetalheOrigem(cdr.chamadaAtendida(), tipoOrigem, cdr.horaLinhaDisponivel(), cdr
					.horaInicioChamada(), cdr.horaTerminoChamada(), cdr.terminoNormal(), cdr.motivoSip(), cdr
					.motivoAsterisk());
		} else {
			final TipoChamada tipoDestino = TipoChamada.get(cdr.tipoDestino());
			chamada.registrarDetalheDestino(tipoDestino, cdr.horaLinhaDisponivel(), cdr.horaInicioChamada(), cdr
					.horaTerminoChamada(), cdr.terminoNormal(), cdr.motivoSip(), cdr.motivoAsterisk());
		}
		chamadaRepository.adicionar(chamada);
	}

	/**
	 * Injeta o repositorio de chamada
	 * @param chamadaRepository Repositorio a ser injetado
	 */
	@EJB
	public void setChamadaRepository(final ChamadaRepository chamadaRepository) {
		this.chamadaRepository = chamadaRepository;
	}

	/**
	 * Injeta o repositorio das instituicoes
	 * @param instituicaoRepository Repositorio a ser injetado
	 */
	@EJB
	public void setInstituicaoRepository(final InstituicaoRepository instituicaoRepository) {
		this.instituicaoRepository = instituicaoRepository;
	}

}
