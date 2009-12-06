package consolidacao.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

import consolidacao.infrastructure.config.ConfigManager;
import consolidacao.infrastructure.config.ConfigManagerImpl;
import consolidacao.web.util.EjbJobInvoker;
import consolidacao.web.util.ServiceLocator;


/**
 * Realiza o boot da aplicacao carregando todas as configuracoes e demais recursos necessarios ao
 * correto funcionamendo do sistema
 * @see javax.servlet.http.HttpServlet
 * @author daniel.braz
 */
public class BootstrapServlet extends HttpServlet {

	private static final long serialVersionUID = 4893239667355061124L;

	/**
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		boot();
	}

	/**
	 * Realiza o boot do sistema, iniciando todos os recursos necessarios para a execucao do mesmo.
	 */
	public synchronized void boot() {
		System.out.println("**************************************************");
		System.out.println("* :.::.::.: Consolidacao VoIP :.::.:.:");
		System.out.println("* Iniciando aplicacao...");
		try {
			loadConfigs();
			scheduleJobs();
		} catch (final Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		System.out.println("* Aplicacao iniciada.");
		System.out.println("**************************************************");
	}

	private void loadConfigs() throws Exception {
		try {
			System.out.print("* Carregando configuracoes...");
			final String userhome = System.getProperty("user.home");
			System.setProperty("javax.net.ssl.keyStore", userhome + "/.consolidacao_voip/consolidacao.keystore");
			System.setProperty("javax.net.ssl.trustStore", userhome
					+ "/.consolidacao_voip/consolidacao.truststore");
			System.setProperty("javax.net.ssl.keyStorePassword", "password");
			System.out.println("pronto.");
		} catch (final Exception e) {
			System.out.println("falhou.");
			throw e;
		}
	}

	private void scheduleJobs() throws Exception {
		try {
			System.out.print("* Agendando Jobs...");
			final SchedulerFactory sf = new StdSchedulerFactory();
			final Scheduler scheduler = sf.getScheduler();
			scheduleConsolidacaoNacionalJob(scheduler);
			scheduler.start();
			System.out.println("pronto.");
		} catch (final Exception e) {
			System.out.println("falhou.");
			throw e;
		}
	}

	private void scheduleConsolidacaoNacionalJob(final Scheduler scheduler) throws Exception {
		assert scheduler != null;
		final JobDetail jd = scheduler.getJobDetail("consolidacaoJob", "consolidacaoGroup");
		if (jd == null) {
			final JobDetail detail = new JobDetail("consolidacaoJob", "consolidacaoGroup",
					EjbJobInvoker.class);
			detail.getJobDataMap().put("ejbName", "ConsolidacaoNacionalServiceImpl");
			detail.getJobDataMap().put("method", "consolidarChamadas");
			final ConfigManager config = ServiceLocator.getLocalBean(ConfigManagerImpl.class);
			final String cronStartTime = config
					.getConfig(ConfigManager.CRON_CONSOLIDAR_CHAMADAS_START_TIME);
			final int cronStartHour = Integer.parseInt(cronStartTime.substring(0, 2));
			final int cronStartMinute = Integer.parseInt(cronStartTime.substring(3));
			final Trigger trigger = TriggerUtils.makeDailyTrigger("consolidarChamadasLocaisTrg",
					cronStartHour, cronStartMinute);
			scheduler.scheduleJob(detail, trigger);
		}
	}

}
