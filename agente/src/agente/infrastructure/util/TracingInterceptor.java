package agente.infrastructure.util;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/**
 * @author daniel.braz
 */
public class TracingInterceptor implements MethodInterceptor {

	private final Logger log;

	/**
	 * @param logger
	 */
	public TracingInterceptor(final Logger logger) {
		super();
		log = logger;
	}

	/**
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	public Object invoke(final MethodInvocation invocation) throws Throwable {
		log.trace(invocation.getClass().getName() + "." + invocation.getMethod());
		return invocation.proceed();
	}

}
