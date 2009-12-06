package agente.domain.model.cdr;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidade CDR
 * @author daniel.braz
 */
@Entity
@Table(name = "chamadas_nao_coletadas")
public class Cdr implements Serializable {

	private static final long serialVersionUID = -317771278145340836L;

	@Id
	@Column(name = "id_chamada")
	private Long id;
	@Column(name = "h323_confid")
	private String h323ConfId;
	@Column(name = "sip_callid")
	private String sipCallId;
	@Column(name = "inst_origem")
	private Long instOrigem;
	@Column(name = "inst_destino")
	private Long instDestino;
	@Column(name = "ddd_origem")
	private String dddOrigem;
	@Column(name = "ddd_destino")
	private String dddDestino;
	@Column(name = "tipo_origem")
	private String tipoOrigem;
	@Column(name = "tipo_destino")
	private String tipoDestino;
	@Column(name = "hora_setup")
	private Date horaSetup;
	@Column(name = "hora_connect")
	private Date horaConnect;
	@Column(name = "hora_disconnect")
	private Date horaDisconnect;
	@Column(name = "duracao")
	private Date duracao;
	@Column(name = "atendida")
	private Boolean atendida;
	@Column(name = "termino_normal")
	private Boolean terminoNormal;
	@Column(name = "motivo_sip")
	private String motivoSip;
	@Column(name = "motivo_asterisk")
	private String motivoAsterisk;

	protected Cdr() {
		super();
	}

	/**
	 * @return the duracao
	 */
	public Date duracao() {
		return duracao;
	}

	/**
	 * @return the h323Confid
	 */
	public String h323ConfId() {
		return h323ConfId;
	}

	/**
	 * @return the sipCallid
	 */
	public String sipCallId() {
		return sipCallId;
	}

	/**
	 * @return the instOrigem
	 */
	public Long instOrigem() {
		return instOrigem;
	}

	/**
	 * @return the instDestino
	 */
	public Long instDestino() {
		return instDestino;
	}

	/**
	 * @return the dddOrigem
	 */
	public String dddOrigem() {
		return dddOrigem;
	}

	/**
	 * @return the dddDestino
	 */
	public String dddDestino() {
		return dddDestino;
	}

	/**
	 * @return the tipoOrigem
	 */
	public String tipoOrigem() {
		return tipoOrigem;
	}

	/**
	 * @return the tipoDestino
	 */
	public String tipoDestino() {
		return tipoDestino;
	}

	/**
	 * @return the horaSetup
	 */
	public Date horaSetup() {
		return horaSetup;
	}

	/**
	 * @return the horaConnect
	 */
	public Date horaConnect() {
		return horaConnect;
	}

	/**
	 * @return the horaDisconnect
	 */
	public Date horaDisconnect() {
		return horaDisconnect;
	}

	/**
	 * @return the atendida
	 */
	public Boolean atendida() {
		return atendida;
	}

	/**
	 * @return the terminoNormal
	 */
	public Boolean terminoNormal() {
		return terminoNormal;
	}

	/**
	 * @return the motivoSip
	 */
	public String motivoSip() {
		return motivoSip;
	}

	/**
	 * @return the motivoAsterisk
	 */
	public String motivoAsterisk() {
		return motivoAsterisk;
	}

	/**
	 * @return the id
	 */
	public Long id() {
		return id;
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
		if (!(obj instanceof Cdr)) { return false; }
		final Cdr other = (Cdr) obj;
		if (id == null) {
			if (other.id != null) { return false; }
		} else if (!id.equals(other.id)) { return false; }
		return true;
	}

}
