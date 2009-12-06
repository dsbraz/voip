package consolidacao.domain.model.chamada;

import java.util.Date;

/**
 * Objeto-Valor CDR
 * @author daniel.braz
 */
public class Cdr {

	private Long id;
	private String h323ConfId;
	private String sipCallId;
	private Long instituicaoOrigem;
	private Long instituicaoDestino;
	private String dddOrigem;
	private String dddDestino;
	private String tipoOrigem;
	private String tipoDestino;
	private Date horaLinhaDisponivel;
	private Date horaInicioChamada;
	private Date horaTerminoChamada;
	private Long duracaoChamada;
	private Boolean chamadaAtendida;
	private Boolean terminoNormal;
	private String motivoSip;
	private String motivoAsterisk;
	private boolean coletadoNaOrigem;
	private boolean registrado;

	Cdr() {
		super();
	}

	/**
	 * Construtor do Objeto-Valor CDR
	 * @param id Identificador unico do CDR
	 * @param h323ConfId H323 Conf Id
	 * @param sipCallId SIP Call Id
	 * @param instituicaoOrigem Instituicao de origem
	 * @param instituicaoDestino Instituicao de destino
	 * @param dddOrigem DDD de origem
	 * @param dddDestino DDD de destino
	 * @param tipoOrigem Tipo de origem
	 * @param tipoDestino Tipo de destino
	 * @param horaLinhaDisponivel Hora que obtencao da linha
	 * @param horaInicioChamada Hora de inicio da chamada
	 * @param horaTerminoChamada Hora de termino da chamada
	 * @param duracaoChamada Duracao da chamada
	 * @param chamadaAtendida Chamada atendida?
	 * @param terminoNormal Termino da chamada foi normal?
	 * @param motivoSip Motivo de termino da chamada informado pelo SIP
	 * @param motivoAsterisk Motivo termino da chamada informado pelo Asterisk
	 * @param coletadoNaOrigem O CDR foi coleta na instituicao de origem da chamada?
	 * @param registrado O status do processamento do CDR
	 */
	public Cdr(final Long id, final String h323ConfId, final String sipCallId, final Long instituicaoOrigem,
			final Long instituicaoDestino, final String dddOrigem, final String dddDestino, final String tipoOrigem,
			final String tipoDestino, final Date horaLinhaDisponivel, final Date horaInicioChamada,
			final Date horaTerminoChamada, final Long duracaoChamada, final Boolean chamadaAtendida,
			final Boolean terminoNormal, final String motivoSip, final String motivoAsterisk,
			final boolean coletadoNaOrigem, final boolean registrado) {
		this();
		this.id = id;
		this.h323ConfId = h323ConfId;
		this.sipCallId = sipCallId;
		this.instituicaoOrigem = instituicaoOrigem;
		this.instituicaoDestino = instituicaoDestino;
		this.dddOrigem = dddOrigem;
		this.dddDestino = dddDestino;
		this.tipoOrigem = tipoOrigem;
		this.tipoDestino = tipoDestino;
		this.horaLinhaDisponivel = horaLinhaDisponivel;
		this.horaInicioChamada = horaInicioChamada;
		this.horaTerminoChamada = horaTerminoChamada;
		this.duracaoChamada = duracaoChamada;
		this.chamadaAtendida = chamadaAtendida;
		this.terminoNormal = terminoNormal;
		this.motivoSip = motivoSip;
		this.motivoAsterisk = motivoAsterisk;
		this.coletadoNaOrigem = coletadoNaOrigem;
		this.registrado = registrado;
	}

	/**
	 * @return the id
	 */
	public Long id() {
		return id;
	}

	/**
	 * @return the h323ConfId
	 */
	public String h323ConfId() {
		return h323ConfId;
	}

	/**
	 * @return the sipCallId
	 */
	public String sipCallId() {
		return sipCallId;
	}

	/**
	 * @return the instituicaoOrigem
	 */
	public Long instituicaoOrigem() {
		return instituicaoOrigem;
	}

	/**
	 * @return the instituicaoDestino
	 */
	public Long instituicaoDestino() {
		return instituicaoDestino;
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
	 * @return the horaLinhaDisponivel
	 */
	public Date horaLinhaDisponivel() {
		return horaLinhaDisponivel;
	}

	/**
	 * @return the horaInicioChamada
	 */
	public Date horaInicioChamada() {
		return horaInicioChamada;
	}

	/**
	 * @return the horaTerminoChamada
	 */
	public Date horaTerminoChamada() {
		return horaTerminoChamada;
	}

	/**
	 * @return the duracaoChamada
	 */
	public Long duracaoChamada() {
		return duracaoChamada;
	}

	/**
	 * @return the chamadaAtendida
	 */
	public Boolean chamadaAtendida() {
		return chamadaAtendida;
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
	 * @return the coletadoNaOrigem
	 */
	public boolean coletadoNaOrigem() {
		return coletadoNaOrigem;
	}

	/**
	 * @return the registrado
	 */
	public boolean registrado() {
		return registrado;
	}

	/**
	 *
	 */
	public void registradoComSucesso() {
		registrado = true;
	}

}
