package lucasjc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Main application class
 */
public class Main {

	/**
	 * Start entire application
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		int port = 8081;
		System.out.println("Server starting...");
		Server server = new Server(port);
		ServletContextHandler handler = new ServletContextHandler(server, "/", true, false);
		List<String> currentMessages = new ArrayList<>();
		Servlet servlet = new MessagingServlet(currentMessages);
		handler.addServlet(new ServletHolder(servlet), "/messages");
		server.start();
		System.out.println("Server listening at " + port);
	}
}
