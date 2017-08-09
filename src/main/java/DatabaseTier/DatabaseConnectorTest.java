package DatabaseTier;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

public class DatabaseConnectorTest {
	
	
	@Test
	public void loadConfigFile() {
		DatabaseConnector connector = new DatabaseConnector();
		Properties configProps = connector.loadConfigFile();
		assertNotNull(configProps);
		assertEquals("test-user", configProps.getProperty("jdbcUsername"));
	}
	
	@Test
	public void testChecklogin() {
		fail("Not yet implemented");
		
	}

	@Test
	public void testRegisterUser() {
		fail("Not yet implemented");
	}


}
