package consolidacao.infrastructure.consolidacao;

import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.rmi.ssl.SslRMIClientSocketFactory;

import consolidacao.domain.model.chamada.Cdr;
import consolidacao.domain.model.instituicao.Instituicao;

import fonernp.agente.remoting.rmi.dto.CdrTO;
import fonernp.agente.remoting.rmi.facade.ColetaCdrFacade;

/**
 * Implementa um proxy para acesso aos servicos remotos das instituicoes
 * @author daniel.braz
 */
public class InstituicaoProxy {

	private static final String AGENTE_COLETA_CDR_FACADE = "ColetaCdrFacade";
	private final ColetaCdrFacade coletaCdrFacade;
	private final Instituicao instituicao;

	/**
	 * Construtor da classe InstituicaoProxy
	 * @param instituicao A instituicao para qual o proxy devera "apontar"
	 * @throws RemoteException Caso ocorra algum erro interno no servidor remoto
	 * @throws NotBoundException Caso o servidor remoto nao seja encontrado ou nao possa processar a requisicao
	 */
	public InstituicaoProxy(final Instituicao instituicao) throws RemoteException, NotBoundException {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		final String addr = instituicao.ip() != null ? instituicao.ip() : "localhost";
		final Registry registry = LocateRegistry.getRegistry(addr, 2100, new SslRMIClientSocketFactory());
		coletaCdrFacade = (ColetaCdrFacade) registry.lookup(AGENTE_COLETA_CDR_FACADE);
		this.instituicao = instituicao;
	}

	/**
	 * Efetua a consulta remota dos CDR's ainda nao coletado da instituicao
	 * @return Uma lista com os CDR's ainda nao coletados
	 * @throws RemoteException Caso ocorra algum erro interno no servidor remoto
	 */
	public List<Cdr> consultarCdrsNaoColetados() throws RemoteException {
		List<Cdr> result = null;
		final List<CdrTO> coleta = coletaCdrFacade.consultarCdrsNaoColetados();
		result = new ArrayList<Cdr>();
		for (final CdrTO cdr : coleta) {
			result.add(cdrtoToCdr(cdr));
		}
		return result;
	}

	/**
	 * Confirma a coleta e o processamento dos CDR's
	 * @param cdrs A lista dos CDR's coletados e processados
	 * @throws RemoteException Caso ocorra alguma excecao interna no servidor remoto
	 */
	public void confirmarColetaCdrs(final List<Cdr> cdrs) throws RemoteException {
		if (!cdrs.isEmpty()) {
			final List<CdrTO> cdrsColetados = new ArrayList<CdrTO>();
			for (final Cdr cdr : cdrs) {
				cdrsColetados.add(cdrToCdrto(cdr));
			}
			coletaCdrFacade.confirmarColeta(cdrsColetados);
		}
	}

	private CdrTO cdrToCdrto(final Cdr cdr) {
		assert cdr != null;
		return new CdrTO(cdr.id(), cdr.registrado());
	}

	private Cdr cdrtoToCdr(final CdrTO cdrTO) {
		assert cdrTO != null;
		final Long id = cdrTO.id;
		final String h323ConfId = cdrTO.h323ConfId;
		final String sipCallId = cdrTO.sipCallId;
		final Long instOrigem = cdrTO.instOrigem;
		final Long instDestino = cdrTO.instDestino;
		final String dddOrigem = cdrTO.dddOrigem;
		final String dddDestino = cdrTO.dddDestino;
		final String tipoOrigem = cdrTO.tipoOrigem;
		final String tipoDestino = cdrTO.tipoDestino;
		final Date horaLinhaDisponivel = cdrTO.horaSetup;
		final Date horaInicioChamada = cdrTO.horaConnect;
		final Date horaTerminoChamada = cdrTO.horaDisconnect;
		final Boolean chamadaAtendida = cdrTO.atendida;
		final Boolean terminoNormal = cdrTO.terminoNormal;
		final String motivoSip = cdrTO.motivoSip;
		final String motivoAsterisk = cdrTO.motivoAsterisk;
		final Long duracaoChamada = cdrTO.duracao != null ? new Long(cdrTO.duracao.getTime()) : null;
		final boolean coletadoNaOrigem = instOrigem.equals(instituicao.id());
		return new Cdr(id, h323ConfId, sipCallId, instOrigem, instDestino, dddOrigem, dddDestino, tipoOrigem,
				tipoDestino, horaLinhaDisponivel, horaInicioChamada, horaTerminoChamada, duracaoChamada,
				chamadaAtendida, terminoNormal, motivoSip, motivoAsterisk, coletadoNaOrigem, false);
	}

}
