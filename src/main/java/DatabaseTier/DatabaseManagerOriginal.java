package DatabaseTier;

import java.io.IOException;
import java.math.BigDecimal;
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

		ArrayList<Deal> s = d.getDealTable(new ArrayList<Deal>(), 0, 100);
		for (Deal sd : s) {
			System.out.println(sd.getId() + "   " + sd.getTime());
		}
		//		ArrayList<AverageInstrumentPrice> s = d.getAveragePrices();
		//		for (AverageInstrumentPrice sd : s) {
		//			System.out.println(sd.getId() + "    " + sd.getAverageBuy() + "   " + sd.getAverageSell());
		//		}
		//		ArrayList<EndPosition> s = d.getEndingPositions();
		//		for (EndPosition sd : s) {
		//			System.out.println(sd.getDealCounterpart() + "    " + sd.getInstrumentName() + "   " + sd.getBought() + "   " + sd.getSold() + "   " + sd.getTotal());
		//		}
//		ArrayList<Deal> s = d.getInstumentDetails("Eclipse", "B");
//		for (Deal sd : s) {
//			System.out.println(sd.getInstrumentName() + "   " + sd.getTime() + "    " + sd.getAmount() + "    " + sd.getQuantity());
//		}
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

	public ArrayList<Deal> getDealTable(ArrayList<Deal> deals, int offset, int limit) {

		try {

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM deal LIMIT ? OFFSET ?");
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				deals.add(new Deal(null, rs.getInt(1), rs.getTimestamp(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getBigDecimal(6), rs.getInt(7)));
			}
			return deals;

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

	public ArrayList<EndPosition> getEndingPositions() {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select deal.deal_counterparty_id, "+
					"instrument.instrument_name, "+
					"deal.deal_type, " +
					"sum(deal.deal_quantity*deal.deal_amount) AS Total "+
					"from deal "+
					"inner join instrument on deal.deal_instrument_id = instrument.instrument_id "+
					"group by deal.deal_counterparty_id, instrument.instrument_id, deal.deal_type, instrument.instrument_name "+
					"order by deal.deal_type, deal.deal_counterparty_id;");
			ArrayList<EndPosition> results = new ArrayList<EndPosition>();

			while (rs.next()) {

				int dealer = rs.getInt(1);
				String instrument = rs.getString(2);
				String dealType = rs.getString(3);

				if (dealType.equalsIgnoreCase("b")) {
					results.add(new EndPosition(dealer, instrument, rs.getBigDecimal(4), null, null));
				} else {
					boolean found = false;
					for (EndPosition ep : results) {
						if (ep.getDealCounterpart() == dealer && ep.getInstrumentName().equals(instrument)) {
							ep.setSold(rs.getBigDecimal(4));
							ep.setTotal(ep.getBought().subtract(ep.getSold()));
							found = true;
							break;
						}
					}
					if (!found) {
						results.add(new EndPosition(dealer, instrument, null, rs.getBigDecimal(3), null));
					}
				}

			}

			//			while (rs.next()) {
			//				EndPosition ep = new EndPosition(rs.getInt(1), rs.getString(2));
			//				ep.setBought(rs.getBigDecimal(3));
			//				results.add(ep);
			//			}	
			//
			//			rs = statement.executeQuery("select deal.deal_counterparty_id, "+
			//					"instrument.instrument_name, "+
			//					"sum(deal.deal_quantity*deal.deal_amount) AS Total "+
			//					"from deal "+
			//					"inner join instrument on deal.deal_instrument_id = instrument.instrument_id "+
			//					"where deal.deal_type = 'S' "+
			//					"group by deal.deal_counterparty_id, instrument.instrument_id, instrument.instrument_name;");
			//
			//			while (rs.next()) {
			//				String instrumentName = rs.getString(2);
			//				int counterpart = rs.getInt(1);
			//				boolean found = false;
			//				for (EndPosition ep : results) {
			//					if (ep.getInstrumentName().equals(instrumentName) && ep.getDealCounterpart() == counterpart) {
			//						ep.setSold(rs.getBigDecimal(3));
			//						ep.setTotal(ep.getBought().subtract(rs.getBigDecimal(3)));
			//						found = true;
			//						break;
			//					}
			//				}
			//				if (!found) {
			//					EndPosition ep = new EndPosition(rs.getInt(1), rs.getString(2));
			//					ep.setBought(new BigDecimal(0));
			//					ep.setSold(rs.getBigDecimal(3));
			//					ep.setTotal(ep.getBought().subtract(ep.getSold()));
			//					results.add(ep);
			//				}
			//			}

			return results;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<AverageInstrumentPrice> getAveragePrices() {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select deal_instrument_id, "+
					"deal_type, "+
					"avg(deal_amount) "+
					"from deal "+
					"group by deal_instrument_id, deal_type " +
					"order by deal_type, deal_instrument_id;");
			ArrayList<AverageInstrumentPrice> results = new ArrayList<AverageInstrumentPrice>();

			while (rs.next()) {

				int id = rs.getInt(1);
				String dealType = rs.getString(2);

				if (dealType.equalsIgnoreCase("b")) {
					results.add(new AverageInstrumentPrice(id, rs.getBigDecimal(3), null));

				} else {	

					boolean found = false;
					for (AverageInstrumentPrice aip : results) {
						if (aip.getId() == id) {
							aip.setAverageSell(rs.getBigDecimal(3));
							found = true;
							break;
						}
					}
					if (!found) {
						results.add(new AverageInstrumentPrice(id, null, rs.getBigDecimal(3)));
					}

				}
			}

			return results;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public ArrayList<Deal> getInstumentDetails(String instrument, String dealType) {
		//time, quant, price, type
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT instrument.instrument_name, "+
					"deal.deal_time, "+
					"deal.deal_quantity, "+
					"deal.deal_amount "+
					"FROM deal "+
					"INNER JOIN instrument ON deal.deal_instrument_id = instrument.instrument_id "+
					"WHERE deal.deal_type = ? AND instrument.instrument_name = ?;");
			ps.setString(1, dealType);
			ps.setString(2, instrument);
			ResultSet rs = ps.executeQuery();
			ArrayList<Deal> results = new ArrayList<Deal>();

			while (rs.next()) {
				results.add(new Deal(rs.getString(1), 0, rs.getTimestamp(2), 0, 0, null, rs.getBigDecimal(4), rs.getInt(3)));
			}
			
			return results;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void parseTime() {

	}

}
