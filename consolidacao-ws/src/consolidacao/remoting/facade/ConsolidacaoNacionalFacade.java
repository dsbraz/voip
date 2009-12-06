package consolidacao.remoting.facade;

import javax.ejb.Remote;

/**
 * @author daniel.braz
 */
@Remote
public interface ConsolidacaoNacionalFacade {

	/**
	 * Inicia o processo de consolidacao
	 */
	void consolidarChamadas();

}
