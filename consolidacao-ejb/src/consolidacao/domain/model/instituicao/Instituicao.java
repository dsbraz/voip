package consolidacao.domain.model.instituicao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Agregado Instituicao
 * @author daniel.braz
 */
@Entity
@Table(name = "instituicao_voip_ativo")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "Consolidacao")
public class Instituicao implements Serializable {

	private static final long serialVersionUID = 1951640240608288049L;

	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "ip_coleta")
	private String ip;

	Instituicao() {
		super();
	}

	/**
	 * Construtor da classe Instituicao
	 * @param id Identificador unico da instituicao
	 */
	public Instituicao(final Long id) {
		this();
		this.id = id;
	}

	/**
	 * @return identificar unico
	 */
	public Long id() {
		return id;
	}

	/**
	 * @return IP
	 */
	public String ip() {
		return ip;
	}

	/**
	 * Valida os campos da entidade
	 */
	@PostLoad
	public void validar() {
		final boolean ok = id != null && ip != null;
		if (!ok) { throw new RuntimeException("O objeto possui valores invalidos"); }
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (!(obj instanceof Instituicao)) { return false; }
		final Instituicao other = (Instituicao) obj;
		if (id == null) {
			if (other.id != null) { return false; }
		} else if (!id.equals(other.id)) { return false; }
		return true;
	}

}
