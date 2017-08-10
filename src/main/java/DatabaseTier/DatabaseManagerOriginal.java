package DatabaseTier;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseManagerOriginal {

	static Properties configProps;
	private Connection connection;

	public static void main(String[] args) {
		DatabaseManagerOriginal d = new DatabaseManagerOriginal();
		ArrayList<String> s = d.getTables();
		for (String sd : s) {
			System.out.println(sd);
		}
	}

	public DatabaseManagerOriginal() {

		try {

			loadConfigFile();
			connectDatabase();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Properties loadConfigFile(){
		try {
			configProps = new Properties();
			configProps.load(DatabaseConnector.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return configProps;
	}

	public boolean connectDatabase() {

		try {
			System.out.println("CONNECTING");

			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(new StringBuilder().append(configProps.getProperty("jdbcHost")).append(configProps.getProperty("jdbcName")).toString(), configProps.getProperty("jdbcUsername"), configProps.getProperty("jdbcPassword"));
			System.out.println("CONNECTED");

			System.out.println(checklogin("test") != null ? "Database Test: Success" : "Database Test: Fail");

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public ArrayList<String> getTables() {

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SHOW TABLES");
			ArrayList<String> tables = new ArrayList<String>();

			while (rs.next()) {
				rs.getString(1);
			}

			return tables;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
