package agente.domain.model.cdr;

import java.util.List;

/**
 * Especifica o repositorio para os CDR's
 * @author daniel.braz
 */
public interface CdrRepository {

	/**
	 * Realiza a consulta a todos os CDR's
	 * @return Uma lista com todos os CDR's
	 */
	List<Cdr> consultar();
}
