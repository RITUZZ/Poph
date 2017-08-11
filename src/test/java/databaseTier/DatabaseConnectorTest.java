package databaseTier;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;
import fit.ColumnFixture;

import DatabaseTier.DatabaseConnector;

public class DatabaseConnectorTest extends ColumnFixture {
	
	@Test
	public void loadConfigFile() {
		Properties configProps = DatabaseConnector.loadConfigFile();
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


	public String testConnectionUser() {
		Properties configProps = DatabaseConnector.loadConfigFile();
		return configProps.getProperty("jdbcUsername");//"test-user"
		
	}

}
