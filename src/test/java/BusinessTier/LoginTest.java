package BusinessTier;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import DatabaseTier.DatabaseManagerSecure;
import model.User;

public class LoginTest {
	
	private static Login login;
	
	@BeforeClass
	public static void setUp(){
		login = new Login();
	}
	
	@Test
	public void testVerifyLogin(){
		DatabaseManagerSecure connector = new DatabaseManagerSecure();
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
