package agente.domain.model.coleta;

/**
 * Especifica o repositorio para as coletas
 * @author erick.couto
 */
public interface ColetaRepository {

	/**
	 * Adiciona uma Coleta ao repositorio
	 * @param coleta A Coleta a ser adicionada
	 * @return A coleta adicionada ao repositorio
	 */
	Coleta adicionar(Coleta coleta);

}
