package consolidacao.domain.model.instituicao;

import java.util.List;

import javax.ejb.Local;

/**
 * Especifica o repositorio para as instituicoes
 * @author daniel.braz
 */
@Local
public interface InstituicaoRepository {

	/**
	 * Consulta uma instituicao pelo seu identificador unico
	 * @param id Identificar unico da instituicao
	 * @return A Instituicao que possui o identificar, null caso nenhuma instituicao seja encontrada
	 */
	Instituicao consultar(Long id);

	/**
	 * Consulta todas as instituicoes ativas no servico VoIP
	 * @return Uma lista com todas as instituicoes ativas no servico VoIP
	 */
	List<Instituicao> consultar();

}
