package DatabaseTier;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import BusinessTier.HashMaster;
import model.User;

public class DatabaseConnector {

	private Connection connection;
	static Properties configProps;

	public static void main(String[] args) {
		DatabaseConnector d = new DatabaseConnector();
		ArrayList<String> s = d.getTables();
		for (String sd : s) {
			System.out.println(sd);
		}
	}

	public DatabaseConnector() {

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

	public User checklogin(String username) {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new User(username, rs.getString("user_pwd"), rs.getString("user_salt"));
			} else {
				System.err.println("Username not found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public boolean registerUser(String user, String password) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO userSecure (user_id, user_pwd) VALUES (?, ?)");
			ps.setString(1, user);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String userResult = rs.getString("user_id");
				if (userResult != null && !userResult.equals("")) {
					return true;
				}
			}

		} catch (SQLException e) {
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

