package consolidacao.domain.model.chamada;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Objeto-Valor DetalheChamada
 * @author daniel.braz
 */
@Entity
@Table(name = "detalhe_chamada_voip")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "Consolidacao")
public class DetalheChamada implements Serializable {

	private static final long serialVersionUID = 8922980727012823071L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_chamada")
	private TipoChamada tipo;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "hora_linha_disponivel")
	private Date horaLinhaDisponivel;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "hora_inicio_chamada")
	private Date horaInicio;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "hora_termino_chamado")
	private Date horaTermino;
	@Column(name = "termino_normal")
	private Boolean terminoNormal;
	@Column(name = "motivo_termino_sip")
	private String motivoTerminoSip;
	@Column(name = "motivo_termino_asterisk")
	private String motivoTerminoAsterisk;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_coleta")
	private Date dataColeta;

	DetalheChamada() {
		super();
	}

	/**
	 * Construtor da classe DetalheChamada
	 * @param id Identificador unico do detalhe
	 * @param tipo Tipo de chamada
	 * @param horaLinhaDisponivel Hora em que a linha ficou disponivel
	 * @param horaInicio Hora de inicio da chamada
	 * @param horaTermino Hora de termino da chamada
	 * @param terminoNormal O termino da chamada foi normal?
	 * @param motivoTerminoSip Motivo de termino informado pelo SIP
	 * @param motivoTerminoAsterisk Motivo de termino informado pelo Asteriks
	 */
	public DetalheChamada(final Long id, final TipoChamada tipo, final Date horaLinhaDisponivel, final Date horaInicio,
			final Date horaTermino, final Boolean terminoNormal, final String motivoTerminoSip,
			final String motivoTerminoAsterisk) {
		this();
		this.id = id;
		this.tipo = tipo;
		this.horaLinhaDisponivel = horaLinhaDisponivel;
		this.horaInicio = horaInicio;
		this.horaTermino = horaTermino;
		this.terminoNormal = terminoNormal;
		this.motivoTerminoSip = motivoTerminoSip;
		this.motivoTerminoAsterisk = motivoTerminoAsterisk;
		dataColeta = new Date();
	}

	/**
	 * @return the id
	 */
	public Long id() {
		return id;
	}

	/**
	 * @return the tipo
	 */
	public TipoChamada tipo() {
		return tipo;
	}

	/**
	 * @return the horaLinhaDisponivel
	 */
	public Date horaLinhaDisponivel() {
		return horaLinhaDisponivel;
	}

	/**
	 * @return the horaInicio
	 */
	public Date horaInicio() {
		return horaInicio;
	}

	/**
	 * @return the horaTermino
	 */
	public Date horaTermino() {
		return horaTermino;
	}

	/**
	 * @return the terminoNormal
	 */
	public Boolean terminoNormal() {
		return terminoNormal;
	}

	/**
	 * @return the motivoTerminoSip
	 */
	public String motivoTerminoSip() {
		return motivoTerminoSip;
	}

	/**
	 * @return the motivoTerminoAsterisk
	 */
	public String motivoTerminoAsterisk() {
		return motivoTerminoAsterisk;
	}

	/**
	 * @return the dataColeta
	 */
	public Date dataColeta() {
		return dataColeta;
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
		if (!(obj instanceof DetalheChamada)) { return false; }
		final DetalheChamada other = (DetalheChamada) obj;
		if (id == null) {
			if (other.id != null) { return false; }
		} else if (!id.equals(other.id)) { return false; }
		return true;
	}

}
