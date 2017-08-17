package DatabaseTier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EndingPositionSetSql extends EndingPositionSet {

	public EndingPositionSetSql(DatabaseManagerOriginal databaseManagerOriginal) {

		try{
			Statement statement = databaseManagerOriginal.connection.createStatement();
			ResultSet rs = statement.executeQuery("select counterparty.counterparty_name, "+
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
}
