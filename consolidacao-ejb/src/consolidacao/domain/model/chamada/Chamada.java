package consolidacao.domain.model.chamada;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import consolidacao.domain.model.instituicao.Instituicao;


/**
 * Agregado Chamada
 * @author daniel.braz
 */
@Entity
@Table(name = "chamada_voip")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "Consolidacao")
public class Chamada implements Serializable {

	private static final long serialVersionUID = -2054834074550931873L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "id_sip")
	private String idSip;
	@ManyToOne
	@JoinColumn(name = "instituicao_origem")
	private Instituicao origem;
	@ManyToOne
	@JoinColumn(name = "instituicao_destino")
	private Instituicao destino;
	@Column(name = "ddd_origem")
	private String dddOrigem;
	@Column(name = "ddd_destino")
	private String dddDestino;
	@Column(name = "duracao")
	private Long duracao;
	@Column(name = "atendida")
	private Boolean atendida;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "detalhe_origem")
	private DetalheChamada detalheOrigem;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "detalhe_destino")
	private DetalheChamada detalheDestino;

	Chamada() {
		super();
	}

	/**
	 * @param id Identificador unico da chamada
	 * @param idSip Identificar unico gerado pelo SIP
	 * @param origem Instituicao de origem
	 * @param destino Instituicao de destino
	 * @param dddOrigem DDD de origem
	 * @param dddDestino DDD de destino
	 * @param duracao Tempo de duracao da chamada
	 */
	public Chamada(final Long id, final String idSip, final Instituicao origem, final Instituicao destino,
			final String dddOrigem, final String dddDestino, final Long duracao) {
		this();
		this.id = id;
		this.idSip = idSip;
		this.origem = origem;
		this.destino = destino;
		this.dddOrigem = dddOrigem;
		this.dddDestino = dddDestino;
		this.duracao = duracao;
	}

	/**
	 * @return the id
	 */
	public Long id() {
		return id;
	}

	/**
	 * @return the idSip
	 */
	public String idSip() {
		return idSip;
	}

	/**
	 * @return the origem
	 */
	public Instituicao origem() {
		return origem;
	}

	/**
	 * @return the destino
	 */
	public Instituicao destino() {
		return destino;
	}

	/**
	 * @return the duracao
	 */
	public Long duracao() {
		return duracao;
	}

	/**
	 * @return the atendida
	 */
	public Boolean atendida() {
		return atendida;
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
	 * @return the horaLinhaDisponivel
	 */
	public Date horaLinhaDisponivelOrigem() {
		Date result = null;
		if (detalheOrigem != null) {
			result = detalheOrigem.horaLinhaDisponivel();
		}
		return result;
	}

	/**
	 * @return the horaInicio
	 */
	public Date horaInicioOrigem() {
		Date result = null;
		if (detalheOrigem != null) {
			result = detalheOrigem.horaInicio();
		}
		return result;
	}

	/**
	 * @return the horaTermino
	 */
	public Date horaTerminoOrigem() {
		Date result = null;
		if (detalheOrigem != null) {
			result = detalheOrigem.horaTermino();
		}
		return result;
	}

	/**
	 * @return the terminoNormal
	 */
	public Boolean terminoNormalOrigem() {
		Boolean result = null;
		if (detalheOrigem != null) {
			result = detalheOrigem.terminoNormal();
		}
		return result;
	}

	/**
	 * @return the motivoTerminoSip
	 */
	public String motivoTerminoSipOrigem() {
		String result = null;
		if (detalheOrigem != null) {
			result = detalheOrigem.motivoTerminoSip();
		}
		return result;
	}

	/**
	 * @return the motivoTerminoAsterisk
	 */
	public String motivoTerminoAsteriskOrigem() {
		String result = null;
		if (detalheOrigem != null) {
			result = detalheOrigem.motivoTerminoAsterisk();
		}
		return result;
	}

	/**
	 * @return the horaLinhaDisponivel
	 */
	public Date horaLinhaDisponivelDestino() {
		Date result = null;
		if (detalheDestino != null) {
			result = detalheDestino.horaLinhaDisponivel();
		}
		return result;
	}

	/**
	 * @return the horaInicio
	 */
	public Date horaInicioDestino() {
		Date result = null;
		if (detalheDestino != null) {
			result = detalheDestino.horaInicio();
		}
		return result;
	}

	/**
	 * @return the horaTermino
	 */
	public Date horaTerminoDestino() {
		Date result = null;
		if (detalheDestino != null) {
			result = detalheDestino.horaTermino();
		}
		return result;
	}

	/**
	 * @return the terminoNormal
	 */
	public Boolean terminoNormalDestino() {
		Boolean result = null;
		if (detalheDestino != null) {
			result = detalheDestino.terminoNormal();
		}
		return result;
	}

	/**
	 * @return the motivoTerminoSip
	 */
	public String motivoTerminoSipDestino() {
		String result = null;
		if (detalheDestino != null) {
			result = detalheDestino.motivoTerminoSip();
		}
		return result;
	}

	/**
	 * @return the motivoTerminoAsterisk
	 */
	public String motivoTerminoAsteriskDestino() {
		String result = null;
		if (detalheDestino != null) {
			result = detalheDestino.motivoTerminoAsterisk();
		}
		return result;
	}

	/**
	 * @return True se chamada ja foi consolidade
	 */
	public boolean consolidada() {
		return possuiDetalheOrigem() && possuiDetalheDestino();
	}

	/**
	 * @return True se a chamada possui os detalhes da origem
	 */
	public boolean possuiDetalheOrigem() {
		return detalheOrigem != null;
	}

	/**
	 * @return True se chamada possui os detalhes do destino
	 */
	public boolean possuiDetalheDestino() {
		return detalheDestino != null;
	}

	/**
	 * Valida os campos da entidade
	 */
	@PrePersist
	@PreUpdate
	@PostLoad
	public void validar() {
		final boolean ok = (origem != null || destino != null) && (detalheOrigem != null || detalheDestino != null);
		if (!ok) { throw new RuntimeException("O objeto possui valores invalidos"); }
	}

	/**
	 * Registra os detalhes da chamada informados pela instituicao de origem
	 * @param chamadaAtendida Chamada foi atendida?
	 * @param tipo Tipo da chamada
	 * @param horaLinhaDisponivel Hora em que a linha ficou disponivel
	 * @param horaInicio Hora de inicio da chamada
	 * @param horaTermino Hora de termino da chamada
	 * @param terminoNormal O termino da chamada foi normal?
	 * @param motivoTerminoSip Motivo de termino informado pelo SIP
	 * @param motivoTerminoAsterisk Motivo de termino informado pelo Asteriks
	 */
	public void registrarDetalheOrigem(final Boolean chamadaAtendida, final TipoChamada tipo,
			final Date horaLinhaDisponivel, final Date horaInicio, final Date horaTermino, final Boolean terminoNormal,
			final String motivoTerminoSip, final String motivoTerminoAsterisk) {
		if (possuiDetalheOrigem()) { throw new RuntimeException("A chamada ja possui os detalhes da origem."); }
		atendida = chamadaAtendida;
		detalheOrigem = new DetalheChamada(null, tipo, horaLinhaDisponivel, horaInicio, horaTermino, terminoNormal,
				motivoTerminoSip, motivoTerminoAsterisk);
	}

	/**
	 * Registra os detalhes da chamada informados pela instituicao de destino
	 * @param tipo Tipo da chamada
	 * @param horaLinhaDisponivel Hora em que a linha ficou disponivel
	 * @param horaInicio Hora de inicio da chamada
	 * @param horaTermino Hora de termino da chamada
	 * @param terminoNormal O termino da chamada foi normal?
	 * @param motivoTerminoSip Motivo de termino informado pelo SIP
	 * @param motivoTerminoAsterisk Motivo de termino informado pelo Asteriks
	 */
	public void registrarDetalheDestino(final TipoChamada tipo, final Date horaLinhaDisponivel, final Date horaInicio,
			final Date horaTermino, final Boolean terminoNormal, final String motivoTerminoSip,
			final String motivoTerminoAsterisk) {
		if (possuiDetalheDestino()) { throw new RuntimeException("A chamada ja possui os detalhes do destino."); }
		atendida = Boolean.TRUE;
		detalheDestino = new DetalheChamada(null, tipo, horaLinhaDisponivel, horaInicio, horaTermino, terminoNormal,
				motivoTerminoSip, motivoTerminoAsterisk);
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
		if (!(obj instanceof Chamada)) { return false; }
		final Chamada other = (Chamada) obj;
		if (id == null) {
			if (other.id != null) { return false; }
		} else if (!id.equals(other.id)) { return false; }
		return true;
	}

}
