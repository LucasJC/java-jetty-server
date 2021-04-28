package lucasjc;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet for messaging operations
 */
public class MessagingServlet extends HttpServlet {
	
	/**
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Gson GSON = new Gson();
	
	private final List<String> messages;

	/**
	 * @param messages
	 */
	public MessagingServlet(List<String> messages) {
		super();
		this.messages = messages;
	}

	/**
	 * HTTP GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println(GSON.toJson(messages));
	}
	
	/**
	 * HTTP POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String rawBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		@SuppressWarnings("unchecked")
		Map<String, Object> body = GSON.fromJson(rawBody, Map.class);
		if (null != body && body.containsKey("message")) {
			this.messages.add((String) body.get("message"));
			response.setStatus(HttpServletResponse.SC_CREATED);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		response.setContentType("application/json");
	}
}
