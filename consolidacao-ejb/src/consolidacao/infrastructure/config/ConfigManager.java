package consolidacao.infrastructure.config;

import javax.ejb.Local;

/**
 * @author daniel.braz
 */
@Local
public interface ConfigManager {

	/**
	 * CRON_CONSOLIDAR_CHAMADAS_START_TIME
	 */
	public static final String CRON_CONSOLIDAR_CHAMADAS_START_TIME = "consolidacao.timer.consolidar_chamadas.start_time";

	/**
	 * @param key Nome da configuracao
	 * @throws ConfigException Exception caso a configuracao nao seja encontrada
	 * @return O nome da configuracao
	 */
	String getConfig(String key) throws ConfigException;

}
