package agente.infrastructure.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import agente.domain.model.cdr.Cdr;
import agente.domain.model.cdr.CdrRepository;
import agente.infrastructure.util.PersistenceManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * Implementa o repositorio para os CDR's
 * @see agente.domain.model.cdr.CdrRepository
 * @author daniel.braz
 */
@Singleton
public class CdrDaoJpa implements CdrRepository {

	private final PersistenceManager persistenceManager;

	/**
	 * Construtor da classe CdrDaoJpa
	 * @param persistenceManager PersistenceManager utilizado para acessar o JPA
	 */
	@Inject
	public CdrDaoJpa(final PersistenceManager persistenceManager) {
		this.persistenceManager = persistenceManager;
	}

	/**
	 * @see agente.domain.model.cdr.CdrRepository#consultar()
	 */
	@SuppressWarnings("unchecked")
	public List<Cdr> consultar() {
		final EntityManager em = persistenceManager.getEntityManager();
		final Query qry = em.createQuery("FROM Cdr");
		qry.setMaxResults(15000);
		return qry.getResultList();
	}
}
