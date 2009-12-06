package agente.domain.model.coleta;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidade Coleta
 * @author erick.couto
 */
@Entity
@Table(name = "coleta_chamada")
public class Coleta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "chamada", nullable = false)
	private Long cdr;
	@Column(name = "chamada_registrada", nullable = false)
	private Boolean cdrRegistrado;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_coleta", nullable = false)
	private Date data;

	protected Coleta() {
		super();
	}

	/**
	 * Construtor da classe Coleta
	 * @param cdr Id do CDR coletado
	 * @param cdrRegistrado Status do processamento do CDR
	 * @param data Data da coleta
	 */
	public Coleta(final Long cdr, final Boolean cdrRegistrado, final Date data) {
		this();
		this.cdr = cdr;
		this.data = data;
		this.cdrRegistrado = cdrRegistrado;
	}

	/**
	 * @return id
	 */
	public Long id() {
		return id;
	}

	/**
	 * @return data
	 */
	public Date data() {
		return data;
	}

	/**
	 * @return cdr
	 */
	public Long cdr() {
		return cdr;
	}

	/**
	 * @return the cdrRegistrado
	 */
	public Boolean cdrRegistrado() {
		return cdrRegistrado;
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
		if (!(obj instanceof Coleta)) { return false; }
		final Coleta other = (Coleta) obj;
		if (id == null) {
			if (other.id != null) { return false; }
		} else if (!id.equals(other.id)) { return false; }
		return true;
	}

}
