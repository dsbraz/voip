package consolidacao.infrastructure.config;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author daniel.braz
 */
@Entity
@Table(name = "app_config_voip")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "Adesao")
public class Config implements Serializable {

	private static final long serialVersionUID = 2985585982396930216L;

	@Id
	@Column(name = "config_key")
	private String key;
	@Column(name = "config_value")
	private String value;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

}
