package BusinessTier;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import DatabaseTier.DatabaseConnector;
import model.User;

public class LoginTest {
	
	private static Login login;
	
	@BeforeClass
	public static void setUp(){
		login = new Login();
	}

	@Test
	public void testGetResponseString() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testVerifyLogin(){
		DatabaseConnector connector = new DatabaseConnector();
		User validUser = connector.checklogin("test");
		//login success
		assertTrue(login.verifyLogin(validUser,"test"));
		//login fail
		assertFalse(login.verifyLogin(validUser,"testtest"));
	}
	
	@Test
	public void testGetPost(){
		fail("Not yet implemented");
	}

}
