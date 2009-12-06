package consolidacao.domain.model.chamada;

/**
 * Objeto-Valor TipoChamada
 * @author daniel.braz
 */
public enum TipoChamada {

	/**
	 * SIP
	 */
	SIP,
	/**
	 * H323
	 */
	H323,
	/**
	 * VOIP
	 */
	VOIP,
	/**
	 * PBX
	 */
	PBX,
	/**
	 * RTFC
	 */
	RTFC;

	/**
	 * @param tipo String representando um tipo de chamda
	 * @return O tipo de chamada especificado
	 */
	public static TipoChamada get(final String tipo) {
		TipoChamada result = null;
		if (tipo.equalsIgnoreCase("SIP")) {
			result = TipoChamada.SIP;
		} else if (tipo.equalsIgnoreCase("H323")) {
			result = TipoChamada.H323;
		} else if (tipo.equalsIgnoreCase("VOIP")) {
			result = TipoChamada.VOIP;
		} else if (tipo.equalsIgnoreCase("PBX") || tipo.equalsIgnoreCase("PABX")) {
			result = TipoChamada.PBX;
		} else if (tipo.equalsIgnoreCase("RTFC")) {
			result = TipoChamada.RTFC;
		}
		return result;
	}

}
