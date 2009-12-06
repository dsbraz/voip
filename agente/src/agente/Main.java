package agente;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

import org.apache.log4j.Logger;

import agente.infrastructure.util.ObjectFactory;
import agente.remoting.rmi.facade.ColetaCdrFacade;


/**
 * Classe de entrada (main) do programa.<br>
 * Responsavel por iniciar o servidor RMI
 * @author daniel.braz
 */
public class Main {

	private static final Logger log = ObjectFactory.getInstance(Logger.class);

	/**
	 * Inicia o servidor RMI e registra os servicos
	 * @param args
	 */
	public static void main(final String[] args) {
		log.trace("agente.Main.main");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		System.out.println("************************************************");
		System.out.println("* :.::.::.: Agente VoIP :.::.:.:");
		System.out.println("* Iniciando aplicacao...");
		try {
			loadConfigs();
			final Registry server = startServer();
			bindServices(server);
		} catch (final Exception e) {
			log.error(e.getMessage(), e);
			System.exit(-1);
		}
		System.out.println("* Aplicacao iniciada.");
		System.out.println("************************************************");
	}

	private static void loadConfigs() throws Exception {
		log.trace("agente.Main.loadConfigs");
		try {
			System.out.print("* Carregando configuracoes...");
			final String userhome = System.getProperty("user.home");
			System.setProperty("javax.net.ssl.keyStore", userhome + "/.agente_voip/agente.keystore");
			System.setProperty("javax.net.ssl.trustStore", userhome
					+ "/.agente_voip/agente.truststore");
			System.setProperty("javax.net.ssl.keyStorePassword", "password");
			System.out.println("pronto.");
		} catch (final Exception e) {
			System.out.println("falhou.");
			throw e;
		}
	}

	private static Registry startServer() throws Exception {
		log.trace("agente.Main.startServer");
		Registry result = null;
		try {
			System.out.print("* Iniciando servidor RMI...");
			result = LocateRegistry.createRegistry(2100, new SslRMIClientSocketFactory(),
					new SslRMIServerSocketFactory());
			System.out.println("pronto.");
		} catch (final Exception e) {
			System.out.println("falhou.");
			throw e;
		}
		return result;
	}

	private static void bindServices(final Registry server) throws Exception {
		log.trace("agente.Main.bindServices");
		try {
			System.out.print("* Registrando servicos...");
			final ColetaCdrFacade facade = ObjectFactory.getInstance(ColetaCdrFacade.class);
			final ColetaCdrFacade stub = (ColetaCdrFacade) UnicastRemoteObject.exportObject(facade,
					2101);
			server.rebind("ColetaCdrFacade", stub);
			System.out.println("pronto.");
		} catch (final Exception e) {
			System.out.println("falhou.");
			throw e;
		}
	}

}
