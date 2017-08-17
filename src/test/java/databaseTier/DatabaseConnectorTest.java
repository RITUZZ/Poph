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
		DatabaseManagerSecure connector = new DatabaseManagerSecure();
		//login success
		assertEquals("tPL+/JAKJH5M0lLmeiibqbxd5/+sGwJQXkMlpm9LxDs=", connector.checklogin("test").getSalt());
		
	}

	public boolean testConnectionUser() {
		Properties configProps = DatabaseManagerSecure.loadConfigFile();
		//return configProps.getProperty("jdbcUsername");
		return configProps.getProperty("jdbcUsername").equals("test-user");
		
	}

}
