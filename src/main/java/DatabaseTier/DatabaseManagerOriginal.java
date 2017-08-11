package DatabaseTier;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import model.*;

public class DatabaseManagerOriginal {

	static Properties configProps;
	private Connection connection;

	public static void main(String[] args) {
		
		final long startTime = System.currentTimeMillis();
		
		DatabaseManagerOriginal d = new DatabaseManagerOriginal();
		
		final long databaseEndTime = System.currentTimeMillis();
		System.out.println("Database connect time: " + (databaseEndTime - startTime)/1000);
		
		ArrayList<Counterparty> s = d.getCounterpartyTable();
		for (Counterparty sd : s) {
			System.out.println(sd.toString());
		}
		System.out.println(s.size());
		
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime)/1000);
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
			connection = DriverManager.getConnection(new StringBuilder().append(configProps.getProperty("jdbcHostOriginal")).append(configProps.getProperty("jdbcNameOriginal")).toString(), configProps.getProperty("jdbcUsernameOriginal"), configProps.getProperty("jdbcPasswordOriginal"));
			System.out.println("CONNECTED");

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
				tables.add(rs.getString(1));
			}

			return tables;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Deal> getDealTable() {

		try {
			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM deal");
			ArrayList<Deal> results = new ArrayList<Deal>();
			
			while (rs.next()) {
				results.add(new Deal(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getFloat(6), rs.getInt(7)));
			}
			return results;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<Counterparty> getCounterpartyTable() {

		try {
			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM counterparty");
			ArrayList<Counterparty> results = new ArrayList<Counterparty>();
			
			while (rs.next()) {
				results.add(new Counterparty(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4)));
			}
			return results;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<Instrument> getInstrumentTable() {

		try {
			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM instrument");
			ArrayList<Instrument> results = new ArrayList<Instrument>();
			
			while (rs.next()) {
				results.add(new Instrument(rs.getInt(1), rs.getString(2)));
			}
			return results;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
