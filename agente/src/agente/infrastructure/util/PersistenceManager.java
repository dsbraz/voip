package agente.infrastructure.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

/**
 * Gerenciador dos objetos de persistencia
 * @author daniel.braz
 */
public class PersistenceManager {

	private final EntityManagerFactory entityManagerFactory;
	private static final ThreadLocal<EntityManager> session = new ThreadLocal<EntityManager>();

	/**
	 * Construtor da classe PersistenceManager
	 * @param persistenceUnitName Nome da Persistence Unit do JPA
	 */
	public PersistenceManager(final String persistenceUnitName) {
		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
	}

	/**
	 * @return A EntityTransaction associada ao EntityManager do JPA associado a thread corrente
	 */
	public EntityTransaction getTransaction() {
		return getEntityManager().getTransaction();
	}

	/**
	 * Realiza o flush no Entity Manager do JPA
	 */
	public PersistenceManager flushSession() {
		getEntityManager().flush();
		return this;
	}

	/**
	 * @return O Entity Manager do JPA associado a thread corrente
	 */
	public EntityManager getEntityManager() {
		return session.get();
	}

	public PersistenceManager openEntityManager() {
		if (session.get() == null || !session.get().isOpen()) {
			session.set(entityManagerFactory.createEntityManager());
			session.get().setFlushMode(FlushModeType.AUTO);
		}
		return this;
	}

	/**
	 * Fecha o Entity Manager do JPA e libera a associada a thread corrente
	 */
	public PersistenceManager closeEntityManager() {
		if (session.get() != null && session.get().isOpen()) {
			session.get().close();
		}
		session.set(null);
		return this;
	}
}
