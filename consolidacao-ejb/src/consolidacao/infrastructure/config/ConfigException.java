package consolidacao.infrastructure.config;

/**
 * @author erick.couto
 */
public class ConfigException extends Exception {

	private static final long serialVersionUID = -791033221575669618L;

	/**
	 * @param thw
	 */
	public ConfigException(final Throwable thw) {
		super(thw);
	}

	/**
	 * @param message
	 */
	public ConfigException(final String message) {
		super(message);
	}

}
