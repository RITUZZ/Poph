package DatabaseTier;

import static org.junit.Assert.*;

import org.junit.Test;

public class DatabaseConnectorTest {
	
	
	@Test
	public void loadConfigFile() {
		DatabaseManagerSecure connector = new DatabaseManagerSecure();
		//Properties configProps = connector.loadConfigFile();
		//assertNotNull(configProps);
		//assertEquals("test-user", configProps.getProperty("jdbcUsername"));
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
