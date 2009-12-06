package consolidacao.infrastructure.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.annotation.ejb.Clustered;

import fonernp.aaa.accounting.TracingInterceptor;

/**
 * @author daniel.braz
 */
@Stateless
@Clustered
@Interceptors(TracingInterceptor.class)
public class ConfigManagerImpl implements ConfigManager {

	private EntityManager em;
	private Map<String, String> cache;

	@SuppressWarnings("unchecked")
	private void load() {
		final Query qry = em.createQuery("FROM Config");
		final List<Config> configs = qry.getResultList();
		if (configs != null) {
			cache = new HashMap<String, String>();
			for (final Config c : configs) {
				cache.put(c.getKey(), c.getValue());
			}
		}
	}

	/**
	 * @see fonernp.adesao.infrastructure.config.ConfigManager#getConfig(java.lang.String)
	 */
	public synchronized String getConfig(final String key) throws ConfigException {
		if (cache == null) {
			load();
		}
		final String config = cache.get(key);
		if (config == null) { throw new ConfigException("Nao foi possivel obter a configuracao " + key); }
		return config;
	}

	/**
	 * @param entityManager
	 */
	@PersistenceContext(unitName = "ConsolidacaoPU")
	public void setEntityManager(final EntityManager entityManager) {
		em = entityManager;
	}

}
