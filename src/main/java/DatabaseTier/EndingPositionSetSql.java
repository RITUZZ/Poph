package DatabaseTier;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EndingPositionSetSql extends EndingPositionSet {
	ResultSet rs;

	public EndingPositionSetSql(DatabaseManagerOriginalBase databaseManagerOriginalBase) {

		try{
			Statement statement = databaseManagerOriginalBase.connection().createStatement();
			rs = statement.executeQuery("select counterparty.counterparty_name, "+
					"instrument.instrument_name, "+
					"deal.deal_type, " +
					"sum(deal.deal_quantity*deal.deal_amount) AS Total "+
					"from deal "+
					"inner join instrument on deal.deal_instrument_id = instrument.instrument_id "+
					"inner join counterparty on deal.deal_counterparty_id = counterparty.counterparty_id "+
					"group by counterparty.counterparty_name, instrument.instrument_id, deal.deal_type, instrument.instrument_name "+
					"order by deal.deal_type, counterparty.counterparty_name;");
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean next() {
		try {
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public String getString(int i) {
		try {
			return rs.getString(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public BigDecimal getBigDecimal(int i) {
		try {
			return rs.getBigDecimal(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
