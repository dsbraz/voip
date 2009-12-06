package agente.application.service;

import java.util.List;

import agente.domain.model.cdr.Cdr;
import agente.domain.model.coleta.Coleta;


/**
 * Especifica o servico de aplicacao de coleta dos CDR's
 * @author daniel.braz
 */
public interface ColetaCdrService {

	/**
	 * Consulta todos os CDR's ainda nao coletados pelo servidor
	 * @return Uma lista com os CDR's ainda nao coletados
	 */
	List<Cdr> consultarCdrsNaoColetados();

	/**
	 * Confirma a coleta dos CDR's
	 * @param cdrsColetados Lista das coletas
	 */
	void confirmarColeta(List<Coleta> cdrsColetados);
}
