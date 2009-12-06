package consolidacao.web.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author daniel.braz
 */
public class ServiceLocator {

	private static InitialContext context;

	/**
	 * @param beanName
	 * @return
	 * @throws NamingException
	 */
	public synchronized static Object getLocalBean(final String beanName) throws NamingException {
		if (context == null) {
			context = new InitialContext();
		}
		return context.lookup("consolidacao/" + beanName + "/local");
	}

	/**
	 * @param <T>
	 * @param beanImpl
	 * @return A instancia do bean
	 * @throws NamingException
	 */
	@SuppressWarnings("unchecked")
	public synchronized static <T> T getLocalBean(final Class<T> beanImpl) throws NamingException {
		if (context == null) {
			context = new InitialContext();
		}
		return (T) context.lookup("consolidacao/" + beanImpl.getSimpleName() + "/local");
	}
}
