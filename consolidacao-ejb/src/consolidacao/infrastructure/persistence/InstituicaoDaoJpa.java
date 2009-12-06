package consolidacao.infrastructure.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.Clustered;

import consolidacao.domain.model.instituicao.Instituicao;
import consolidacao.domain.model.instituicao.InstituicaoRepository;

import fonernp.aaa.accounting.TracingInterceptor;

/**
 * Implementa o repositorio para as instituicoes
 * @author daniel.braz
 */
@Clustered
@Stateless
@Interceptors(TracingInterceptor.class)
public class InstituicaoDaoJpa implements InstituicaoRepository {

	private EntityManager em;

	/**
	 * @see consolidacao.domain.model.instituicao.InstituicaoRepository#consultar(java.lang.Long)
	 */
	public Instituicao consultar(final Long id) {
		return em.find(Instituicao.class, id);
	}

	/**
	 * @see consolidacao.domain.model.instituicao.InstituicaoRepository#consultar()
	 */
	@SuppressWarnings("unchecked")
	public List<Instituicao> consultar() {
		final Query qry = em.createQuery("FROM Instituicao");
		// qry.setHint("org.hibernate.cacheable", "true");
		return qry.getResultList();
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
