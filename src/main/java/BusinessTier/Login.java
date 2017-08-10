package BusinessTier;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import DatabaseTier.DatabaseConnector;
import model.User;

/**
 * Servlet implementation class login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		// TODO Auto-generated method stub

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("hello from get");
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();


		//		String username = request.getParameter("username");
		//		System.out.println(username);
		//		String password = request.getParameter("password");
		String username = "alison";
		String password= "gradprog2016@07";

		//TODO: Verify username and password
		DatabaseConnector connector = new DatabaseConnector();
		User user = connector.checklogin(username);
		if (HashMaster.hash(password, user.getSalt()).equals(user.getPassword())) {
			System.out.println("LOGIN SUCCESSFUL");
		} else {
			System.out.println("LOGIN FAILED - INVALID USER");
		}
		//boolean isValid = connector.checklogin(username, password);
		//System.out.println("is valid: "+isValid);


	}

	public boolean verifyLogin(User user, String password) {
		if (HashMaster.hash(password, user.getSalt()).equals(user.getPassword())) {
			return true;
		}
		return false;
	}

}
