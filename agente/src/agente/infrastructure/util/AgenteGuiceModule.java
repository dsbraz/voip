package agente.infrastructure.util;

import org.apache.log4j.Logger;

import agente.application.service.ColetaCdrService;
import agente.application.service.impl.ColetaCdrServiceImpl;
import agente.domain.model.cdr.CdrRepository;
import agente.domain.model.coleta.ColetaRepository;
import agente.infrastructure.persistence.CdrDaoJpa;
import agente.infrastructure.persistence.ColetaDaoJpa;
import agente.remoting.rmi.facade.ColetaCdrFacade;
import agente.remoting.rmi.facade.impl.ColetaCdrFacadeImpl;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;


/**
 * Configura o Modulo do Google-Guice
 * @author daniel.braz
 */
public class AgenteGuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		final Logger logger = Logger.getLogger("Agente");
		bindInterceptor(Matchers.inSubpackage("agente"), Matchers.any(),
				new TracingInterceptor(logger));
		bind(Logger.class).toInstance(logger);
		bind(PersistenceManager.class).toInstance(new PersistenceManager("AgentePU"));
		bind(CdrRepository.class).to(CdrDaoJpa.class);
		bind(ColetaRepository.class).to(ColetaDaoJpa.class);
		bind(ColetaCdrService.class).to(ColetaCdrServiceImpl.class);
		bind(ColetaCdrFacade.class).to(ColetaCdrFacadeImpl.class);
	}

}
