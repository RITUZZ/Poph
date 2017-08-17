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
import fit.ColumnFixture;
import model.User;

public class DatabaseManagerSecure extends ColumnFixture {

	private Connection connection;
	static Properties configProps;

	public static void main(String[] args) {
		DatabaseManagerSecure d = new DatabaseManagerSecure();
		System.out.println(d.checklogin("test").getSalt());
	}

	public DatabaseManagerSecure() {

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
			configProps.load(DatabaseManagerSecure.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return configProps;
	}

	public boolean connectDatabase() {

		try {
			System.out.println("CONNECTING");

			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(new StringBuilder().append(configProps.getProperty("jdbcHost")).append(configProps.getProperty("jdbcNameSecure")).toString(), configProps.getProperty("jdbcUsername"), configProps.getProperty("jdbcPassword"));
			System.out.println("CONNECTED");

			System.out.println(checklogin("test") != null ? "Database Test: Success" : "Database Test: Fail");

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public void disconnectDatabase() {
		try {
			if(!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			int rs = ps.executeUpdate();
			return rs>0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteUser(String user) {
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("DELETE userSecure WHERE user_id = ?");
			ps.setString(1, user);
			int rs = ps.executeUpdate();
			return rs>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


}

