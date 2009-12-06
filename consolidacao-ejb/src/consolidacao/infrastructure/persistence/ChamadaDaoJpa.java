package consolidacao.infrastructure.persistence;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.Clustered;

import consolidacao.domain.model.chamada.Chamada;
import consolidacao.domain.model.chamada.ChamadaRepository;

import fonernp.aaa.accounting.TracingInterceptor;

/**
 * Implementa um repositorio para as chamadas
 * @see consolidacao.domain.model.chamada.ChamadaRepository
 * @author daniel.braz
 */
@Clustered
@Stateless
@Interceptors(TracingInterceptor.class)
public class ChamadaDaoJpa implements ChamadaRepository {

	private EntityManager em;

	/**
	 * @see consolidacao.domain.model.chamada.ChamadaRepository#consultarPorIdSip(java.lang.String)
	 */
	public Chamada consultarPorIdSip(final String idSip) {
		Chamada chamada = null;
		final StringBuilder query = new StringBuilder();
		query.append(" FROM Chamada AS c ");
		query.append("   LEFT JOIN FETCH c.origem ");
		query.append("   LEFT JOIN FETCH c.destino ");
		query.append("   LEFT JOIN FETCH c.detalheOrigem ");
		query.append("   LEFT JOIN FETCH c.detalheDestino ");
		query.append(" WHERE c.idSip = :idSip ");
		final Query qry = em.createQuery(query.toString());
		// qry.setHint("org.hibernate.cacheable", "true");
		qry.setParameter("idSip", idSip);
		try {
			chamada = (Chamada) qry.getSingleResult();
		} catch (final NoResultException e) {
			//
		}
		return chamada;
	}

	/**
	 * @see consolidacao.domain.model.chamada.ChamadaRepository#adicionar(consolidacao.domain.model.chamada.Chamada)
	 */
	public Chamada adicionar(final Chamada chamada) {
		Chamada result = null;
		if (chamada.id() == null) {
			em.persist(chamada);
			result = chamada;
		} else {
			result = em.merge(chamada);
		}
		return result;
	}

	/**
	 * Injeta o EntityManager
	 * @param entityManager EntityManager a ser injetado
	 */
	@PersistenceContext(unitName = "ConsolidacaoPU")
	public void setEntityManager(final EntityManager entityManager) {
		em = entityManager;
	}

}
