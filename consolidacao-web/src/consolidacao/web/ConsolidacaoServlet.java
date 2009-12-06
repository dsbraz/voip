package consolidacao.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import consolidacao.application.service.ConsolidacaoNacionalService;
import consolidacao.application.service.impl.ConsolidacaoNacionalServiceImpl;
import consolidacao.infrastructure.log.LogManager;
import consolidacao.web.util.ServiceLocator;


/**
 * @author daniel.braz
 */
public class ConsolidacaoServlet extends HttpServlet {

	private static final long serialVersionUID = -7325327440343416048L;
	private final Logger log = LogManager.logger;

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			final ConsolidacaoNacionalService srv = ServiceLocator
					.getLocalBean(ConsolidacaoNacionalServiceImpl.class);
			srv.consolidarChamadas();
		} catch (final Exception e) {
			log.error(e.getMessage(), e);
			throw new ServletException(e);
		}
		final RequestDispatcher disp = req.getRequestDispatcher("success.jsp");
		if (disp != null) {
			disp.forward(req, resp);
		}
	}
}
