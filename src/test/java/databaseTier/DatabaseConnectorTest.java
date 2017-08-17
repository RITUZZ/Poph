package DatabaseTier;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

import BusinessTier.Login;
import fit.ColumnFixture;
import model.User;
import DatabaseTier.DatabaseManagerSecure;

public class DatabaseConnectorTest extends ColumnFixture {
	private String sLoginName;
	
	@Test
	public void loadConfigFile() {
		Properties configProps = DatabaseManagerSecure.loadConfigFile();
		assertNotNull(configProps);
		assertEquals("test-user", configProps.getProperty("jdbcUsername"));
	}
	
	public void setLoginName(String name) {
		sLoginName = name;
	}

	@Test
	public void testChecklogin() {
		setLoginName("test");
		Login login = new Login();
		DatabaseManagerSecure connector = new DatabaseManagerSecure();
		User validUser = connector.checklogin(sLoginName);
		//login success
		assertTrue(login.verifyLogin(validUser,sLoginName));
		
	}

	@Test
	public void testRegisterUser() {
		fail("not implemented");
		setLoginName("test123");
		DatabaseManagerSecure connector = new DatabaseManagerSecure();
		boolean value = connector.registerUser(sLoginName,"123");
		value = value&&connector.deleteUser(sLoginName);
		assertFalse(value);
	}

	public boolean testConnectionUser() {
		Properties configProps = DatabaseManagerSecure.loadConfigFile();
		//return configProps.getProperty("jdbcUsername");
		return configProps.getProperty("jdbcUsername").equals("test-user");
		
	}

}
