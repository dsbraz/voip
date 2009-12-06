package agente.infrastructure.persistence;

import javax.persistence.EntityManager;

import agente.domain.model.coleta.Coleta;
import agente.domain.model.coleta.ColetaRepository;
import agente.infrastructure.util.PersistenceManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * Implementa o repositorio para as coletas
 * @see agente.domain.model.coleta.ColetaRepository
 * @author erick.couto
 */
@Singleton
public class ColetaDaoJpa implements ColetaRepository {

	private final PersistenceManager persistenceManager;

	/**
	 * Construtor da classe ColetaDaoJpa
	 * @param persistenceManager PersistenceManager utilizado para acessar o JPA
	 */
	@Inject
	public ColetaDaoJpa(final PersistenceManager persistenceManager) {
		this.persistenceManager = persistenceManager;
	}

	/**
	 * @see agente.domain.model.coleta.ColetaRepository#adicionar(agente.domain.model.coleta.Coleta)
	 */
	public Coleta adicionar(final Coleta coleta) {
		final EntityManager em = persistenceManager.getEntityManager();
		Coleta result = null;
		if (coleta.id() == null) {
			em.persist(coleta);
			result = coleta;
		} else {
			result = em.merge(coleta);
		}
		return result;
	}
}
