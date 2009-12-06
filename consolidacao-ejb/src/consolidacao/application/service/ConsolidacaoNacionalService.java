package consolidacao.application.service;

import javax.ejb.Local;

/**
 * Especifica o servico de aplicacao para a consolidacao nacional das chamadas
 * @author daniel.braz
 */
@Local
public interface ConsolidacaoNacionalService {

	/**
	 * Realiza a coleta dos CDR's e consolida as chamadas<br>
	 * A execucao da coleta eh assincrona (paralela), sendo usada uma thread por instituicao
	 */
	void consolidarChamadas();

}
