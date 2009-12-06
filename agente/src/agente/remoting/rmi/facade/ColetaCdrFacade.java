package agente.remoting.rmi.facade;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import agente.remoting.rmi.dto.CdrTO;


/**
 * Especifica o facade remoto para a coleta dos CDR's
 * @author daniel.braz
 */
public interface ColetaCdrFacade extends Remote {

	/**
	 * Consulta os CDR's ainda nao coletados pelo servidor
	 * @return Uma lista com os TO's dos CDR's ainda nao coletados
	 * @throws RemoteException Caso ocorra alguma falha na consulta
	 */
	List<CdrTO> consultarCdrsNaoColetados() throws RemoteException;

	/**
	 * Confirma a coleta dos CDR's
	 * @param cdrsColetados Lista com os TO's dos CDR's coletados
	 * @throws RemoteException Caso ocorra alguma falha na confirmacao
	 */
	void confirmarColeta(List<CdrTO> cdrsColetados) throws RemoteException;
}
