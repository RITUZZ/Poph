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

//		ArrayList<Deal> s = d.getDealTable(new ArrayList<Deal>(), 0, 100);
//		for (Deal sd : s) {
//			System.out.println(sd.getCoutnerpartyName() + ", " + sd.getTime() + ", " + sd.getInstrumentName());
//		}
				ArrayList<AverageInstrumentPrice> s = d.getAveragePrices();
				for (AverageInstrumentPrice sd : s) {

					System.out.println(sd.getName() + "    " + sd.getAverageBuy() + "   " + sd.getAverageSell());

				}
//				ArrayList<EndPosition> s = d.getEndingPositions();
//				for (EndPosition sd : s) {
//					System.out.println(sd.getDealCounterpart() + "    " + sd.getInstrumentName() + "   " + sd.getBought() + "   " + sd.getSold() + "   " + sd.getTotal());
//				}
//				ArrayList<Deal> s = d.getInstumentDetails("Eclipse", "B");
//				for (Deal sd : s) {
//					System.out.println(sd.getInstrumentName() + "   " + sd.getTime() + "    " + sd.getAmount() + "    " + sd.getQuantity());
//				}
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
			connection = DriverManager.getConnection(new StringBuilder().append(configProps.getProperty("jdbcHost")).append(configProps.getProperty("jdbcNameOriginal")).toString(), configProps.getProperty("jdbcUsername"), configProps.getProperty("jdbcPassword"));
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

			PreparedStatement ps = connection.prepareStatement("SELECT instrument.instrument_name, "+
					"counterparty.counterparty_name, "+	
					"deal.deal_time, "+
					"deal.deal_type, "+										
					"deal.deal_amount, "+
					"deal.deal_quantity "+
					"FROM deal "+
					"inner join instrument on instrument.instrument_id = deal.deal_instrument_id "+
					"inner join counterparty on counterparty.counterparty_id = deal.deal_counterparty_id "+
					"LIMIT ? OFFSET ?;");
			ps.setInt(1, limit);
			ps.setInt(2, offset);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				deals.add(new Deal(rs.getString(1), rs.getString(2), rs.getTimestamp(3), rs.getString(4), rs.getBigDecimal(5), rs.getInt(6)));
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
			ResultSet rs = statement.executeQuery("SELECT counterparty_name FROM counterparty");
			ArrayList<Counterparty> results = new ArrayList<Counterparty>();

			while (rs.next()) {
				results.add(new Counterparty(rs.getString(1), null, null));
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
			ResultSet rs = statement.executeQuery("SELECT instrument_name FROM instrument");
			ArrayList<Instrument> results = new ArrayList<Instrument>();

			while (rs.next()) {
				results.add(new Instrument(rs.getString(1)));
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
			ResultSet rs = statement.executeQuery("select counterparty.counterparty_name, "+
					"instrument.instrument_name, "+
					"deal.deal_type, " +
					"sum(deal.deal_quantity*deal.deal_amount) AS Total "+
					"from deal "+
					"inner join instrument on deal.deal_instrument_id = instrument.instrument_id "+
					"inner join counterparty on deal.deal_counterparty_id = counterparty.counterparty_id "+
					"group by counterparty.counterparty_name, instrument.instrument_id, deal.deal_type, instrument.instrument_name "+
					"order by deal.deal_type, counterparty.counterparty_name;");
			ArrayList<EndPosition> results = new ArrayList<EndPosition>();

			while (rs.next()) {

				String dealer = rs.getString(1);
				String instrument = rs.getString(2);
				String dealType = rs.getString(3);

				if (dealType.equalsIgnoreCase("b")) {
					results.add(new EndPosition(dealer, instrument, rs.getBigDecimal(4), null, null));
				} else {
					boolean found = false;
					for (EndPosition ep : results) {
						if (ep.getDealCounterpart().equals(dealer) && ep.getInstrumentName().equals(instrument)) {
							ep.setSold(rs.getBigDecimal(4));
							ep.setTotal(ep.getBought().subtract(ep.getSold()));
							found = true;
							break;
						}
					}
					if (!found) {
						results.add(new EndPosition(dealer, instrument, null, rs.getBigDecimal(4), null));
					}
				}

			}

			return results;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<EndPosition> getEndingPositionsGraphs() {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select counterparty.counterparty_name, "+
					"sum(deal.deal_quantity*deal.deal_amount) AS Total "+
					"from deal "+
					"inner join instrument on deal.deal_instrument_id = instrument.instrument_id "+
					"inner join counterparty on deal.deal_counterparty_id = counterparty.counterparty_id "+
					"group by counterparty.counterparty_name, instrument.instrument_id, deal.deal_type, instrument.instrument_name "+
					"order by deal.deal_type, counterparty.counterparty_name;");
			ArrayList<EndPosition> results = new ArrayList<EndPosition>();

			while (rs.next()) {

				String dealer = rs.getString(1);
				String instrument = rs.getString(2);
				String dealType = rs.getString(3);

				if (dealType.equalsIgnoreCase("b")) {
					results.add(new EndPosition(dealer, instrument, rs.getBigDecimal(4), null, null));
				} else {
					boolean found = false;
					for (EndPosition ep : results) {
						if (ep.getDealCounterpart().equals(dealer) && ep.getInstrumentName().equals(instrument)) {
							ep.setSold(rs.getBigDecimal(4));
							ep.setTotal(ep.getBought().subtract(ep.getSold()));
							found = true;
							break;
						}
					}
					if (!found) {
						results.add(new EndPosition(dealer, instrument, null, rs.getBigDecimal(4), null));
					}
				}

			}

			return results;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<AverageInstrumentPrice> getAveragePrices() {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select instrument.instrument_name, "+
					"deal_type, "+
					"avg(deal_amount) "+
					"from deal "+
					"inner join instrument on instrument.instrument_id = deal.deal_instrument_id "+
					"group by deal_instrument_id, deal_type " +
					"order by deal_type, deal_instrument_id;");
			ArrayList<AverageInstrumentPrice> results = new ArrayList<AverageInstrumentPrice>();

			while (rs.next()) {

				String name = rs.getString(1);
				String dealType = rs.getString(2);

				if (dealType.equalsIgnoreCase("b")) {
					results.add(new AverageInstrumentPrice(name, rs.getBigDecimal(3), null));

				} else {	

					boolean found = false;
					for (AverageInstrumentPrice aip : results) {

						if (aip.getName().equals(name)) {

							aip.setAverageSell(rs.getBigDecimal(3));
							found = true;
							break;
						}
					}
					if (!found) {
						results.add(new AverageInstrumentPrice(name, null, rs.getBigDecimal(3)));
					}

				}
			}

			return results;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public ArrayList<Deal> getInstrumentDetails(String instrument, String dealType) {
		//time, quant, price, type
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT instrument.instrument_name, "+
					"deal.deal_time, "+
					"deal.deal_amount, "+
					"deal.deal_quantity "+
					"FROM deal "+
					"INNER JOIN instrument ON deal.deal_instrument_id = instrument.instrument_id "+
					"WHERE deal.deal_type = ? AND instrument.instrument_name = ?;");
			ps.setString(1, dealType);
			ps.setString(2, instrument);
			ResultSet rs = ps.executeQuery();
			ArrayList<Deal> results = new ArrayList<Deal>();

			while (rs.next()) {
				results.add(new Deal(rs.getString(1), null, rs.getTimestamp(2), null, rs.getBigDecimal(3), rs.getInt(4)));
			}

			return results;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void realisedProfitLoss() {

	}

	public Counterparty searchCounterparty(String counterparty) {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT counterparty_name, "+
					"counterparty_status, "+
					"counterparty_date_registered "+
					"FROM counterparty "+
					"where counterparty_name = ?;");
			ps.setString(1, counterparty);
			ResultSet rs = ps.executeQuery();

			return new Counterparty(rs.getString(1), rs.getString(2), rs.getTimestamp(3));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
