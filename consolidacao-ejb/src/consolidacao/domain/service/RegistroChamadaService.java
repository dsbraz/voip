package consolidacao.domain.service;

import javax.ejb.Local;

import consolidacao.domain.model.chamada.Cdr;


/**
 * Especifica o servico de dominio Registro de Chamadas
 * @author daniel.braz
 */
@Local
public interface RegistroChamadaService {

	/**
	 * Registra um CDR's como um detalhe de uma chamada.<br>
	 * Se ainda nao houver uma chamada para o identificar SIP especificado pelo CDR, entao uma chamada eh criada antes
	 * do registro do detalhe.
	 * @param cdr O CDR a ser registrado
	 */
	void registrarCdr(Cdr cdr);

}
