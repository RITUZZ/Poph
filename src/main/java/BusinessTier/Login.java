package BusinessTier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet; 	
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import DatabaseTier.DatabaseManagerOriginal;
import DatabaseTier.DatabaseManagerSecure;

import model.User;

/**
 * Servlet implementation class login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static DatabaseManagerOriginal db = new DatabaseManagerOriginal();
	DatabaseManagerSecure connector;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		HttpHelper helper = new HttpHelper();
		
		String responseString = helper.getResponseString(request);
		
		Map<String,Object> map = mapper.readValue(responseString, Map.class);
		String username = (String) map.get("username");
		String password = (String) map.get("password");
		
		if (connector == null) {
			connector = new DatabaseManagerSecure();
		}
		
		User user = connector.checklogin(username);
		//initialize as false
		boolean status = false;
		ObjectNode node = JsonNodeFactory.instance.objectNode(); // initializing
		
		if (user != null) {
			if (verifyLogin(user, password)) {
				connector.disconnectDatabase();
				connector = null;
				status = true;
				//set session
//				HttpSession session=request.getSession();  
//		        session.setAttribute(username,username);  
			} else {
				
				
			}
			
		} else {
			
		}
		
		node.put("status", status); // building JSON object
		node.put("username", username);
		
		String jsonInString = mapper.writeValueAsString(node);
		
		//send JSON in response
		out.println(jsonInString);
		
		out.close();


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	public boolean verifyLogin(User user, String password) {
		if (HashMaster.hash(password, user.getSalt()).equals(user.getPassword())) {
			return true;
		}
		return false;
	}

}
