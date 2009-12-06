package agente.remoting.rmi.facade.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import agente.application.service.ColetaCdrService;
import agente.domain.model.cdr.Cdr;
import agente.domain.model.coleta.Coleta;
import agente.remoting.rmi.dto.CdrTO;
import agente.remoting.rmi.facade.ColetaCdrFacade;

import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * Implementa o facade remoto para a coleta dos CDR's
 * @see agente.remoting.rmi.facade.ColetaCdrFacade
 * @author daniel.braz
 */
@Singleton
public class ColetaCdrFacadeImpl implements ColetaCdrFacade {

	private final Logger log;
	private final ColetaCdrService coletaCdrService;

	/**
	 * Construtor da classe ColetaCdrFacadeImpl
	 * @param log O logger a ser utilizado pela classe
	 * @param coletaCdrService Servico de aplicacao ColetaCdrService
	 */
	@Inject
	public ColetaCdrFacadeImpl(final Logger log, final ColetaCdrService coletaCdrService) {
		super();
		this.log = log;
		this.coletaCdrService = coletaCdrService;
	}

	/**
	 * @see agente.remoting.rmi.facade.ColetaCdrFacade#consultarCdrsNaoColetados()
	 */
	public List<CdrTO> consultarCdrsNaoColetados() throws RemoteException {
		final List<CdrTO> result = new ArrayList<CdrTO>();
		try {
			final List<Cdr> cdrs = coletaCdrService.consultarCdrsNaoColetados();
			for (final Cdr cdr : cdrs) {
				final CdrTO cdrTO = new CdrTO(cdr.id(), cdr.h323ConfId(), cdr.sipCallId(), cdr
						.instOrigem(), cdr.instDestino(), cdr.dddOrigem(), cdr.dddDestino(), cdr
						.tipoOrigem(), cdr.tipoDestino(), cdr.horaSetup(), cdr.horaConnect(), cdr
						.horaDisconnect(), cdr.duracao(), cdr.atendida(), cdr.terminoNormal(), cdr
						.motivoSip(), cdr.motivoAsterisk(), false);
				result.add(cdrTO);
			}
		} catch (final Exception e) {
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage());
		}
		return result;
	}

	/**
	 * @see agente.remoting.rmi.facade.ColetaCdrFacade#confirmarColeta(java.util.List)
	 */
	public void confirmarColeta(final List<CdrTO> cdrsColetados) throws RemoteException {
		try {
			final List<Coleta> coletas = new ArrayList<Coleta>();
			for (final CdrTO cdrTO : cdrsColetados) {
				coletas.add(new Coleta(cdrTO.id, new Boolean(cdrTO.registrado), new Date()));
			}
			coletaCdrService.confirmarColeta(coletas);
		} catch (final Exception e) {
			log.error(e.getMessage(), e);
			throw new RemoteException(e.getMessage());
		}
	}
}
