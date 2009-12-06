package consolidacao.remoting.facade.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.jboss.annotation.ejb.Clustered;

import consolidacao.application.service.ConsolidacaoNacionalService;
import consolidacao.remoting.facade.ConsolidacaoNacionalFacade;

import fonernp.aaa.accounting.TracingInterceptor;

/**
 * @author daniel.braz
 */
@Stateless
@Clustered
@SOAPBinding(style = Style.RPC)
@Interceptors(TracingInterceptor.class)
@WebService(name = "ConsolidacaoNacionalFacade", serviceName = "ConsolidacaoNacionalService")
public class ConsolidacaoNacionalFacadeImpl implements ConsolidacaoNacionalFacade {

	private ConsolidacaoNacionalService srv;

	/**
	 * @see consolidacao.remoting.facade.ConsolidacaoNacionalFacade#consolidarChamadas()
	 */
	@WebMethod
	public void consolidarChamadas() {
		srv.consolidarChamadas();
	}

	/**
	 * @param service O servico a ser injetado
	 */
	@EJB
	public void setConsolidacaoNacionalService(final ConsolidacaoNacionalService service) {
		srv = service;
	}

}
