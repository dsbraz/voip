package agente.infrastructure.util;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Instancia um objeto gerenciado pelo Google-Guice
 * @author daniel.braz
 */
public class ObjectFactory {

	private static final Injector injector = Guice.createInjector(new AgenteGuiceModule());

	/**
	 * Cria a instancia do objeto utilizando o injector do guice
	 * @param <T> Tipo informado pelo param clazz
	 * @param clazz Classe utilizada para instanciar o objeto
	 * @return Uma instancia do tipo informado em clazz
	 */
	public static <T extends Object> T getInstance(final Class<T> clazz) {
		return injector.getInstance(clazz);
	}

}
