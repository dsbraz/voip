package consolidacao.web.util;

import java.lang.reflect.Method;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Invocador de EJB's como JOB's do Quartz
 * @see org.quartz.Job
 * @author daniel.braz
 */
public class EjbJobInvoker implements Job {

	/**
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(final JobExecutionContext context) throws JobExecutionException {
		try {
			final JobDataMap jdm = context.getJobDetail().getJobDataMap();
			final String name = (String) jdm.get("ejbName");
			final String method = (String) jdm.get("method");
			final Object[] args = (Object[]) jdm.get("args");
			final Object ejb = ServiceLocator.getLocalBean(name);
			final Method ejbMethod = ejb.getClass().getMethod(method);
			ejbMethod.invoke(ejb, args);
		} catch (final Exception e) {
			throw new JobExecutionException(e);
		}
	}

}
