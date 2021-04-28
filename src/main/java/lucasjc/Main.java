package lucasjc;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

	public static void main(String[] args) throws Exception {
		int port = 8081;
		System.out.println("Server starting...");
		Server server = new Server(port);
		ServletContextHandler handler = new ServletContextHandler(server, "/", true, false);
		
		List<String> currentMessages = new ArrayList<>();
		
		handler.addServlet(new ServletHolder(new MessagingServlet(currentMessages)), "/messages");
		server.start();
		System.out.println("Server listening at " + port);
	}
}
