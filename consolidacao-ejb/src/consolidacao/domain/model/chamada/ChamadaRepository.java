package consolidacao.domain.model.chamada;

import javax.ejb.Local;

/**
 * Especifica o repositorio para as Chamadas
 * @author daniel.braz
 */
@Local
public interface ChamadaRepository {

	/**
	 * Realiza a consulta da chamada pelo identificador do SIP
	 * @param idSip Identificar do SIP
	 * @return A chamada cujo identificador eh igual ao parametro idSip. Null caso nenhuma chamada seja encontrada
	 */
	Chamada consultarPorIdSip(String idSip);

	/**
	 * Adiciona uma chamada ao repositorio
	 * @param chamada A Chamada a ser adicionada
	 * @return A chamada adicionada ao repositorio
	 */
	Chamada adicionar(Chamada chamada);

}
