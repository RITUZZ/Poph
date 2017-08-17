package BusinessTier;

import java.math.BigDecimal;
import java.util.List;

import DatabaseTier.DatabaseManagerOriginalBase;
import DatabaseTier.EndingPositionSet;
import fitnesse.fixtures.TableFixture;
import model.EndPosition;

public class EndingPositionTest extends TableFixture {

	private int rows_;
	
	protected void doStaticTable(int rows) {
		setRows(rows);
		DatabaseManagerOriginalBase d = new DatabaseManagerOriginalBase();
		EndingPositionSet rs = new EndingPositionSetSample(this);
		List<EndPosition> answerList = d.getEndingPositionsResults(rs);
		for (EndPosition sd : answerList) {
			System.out.println(sd.getDealCounterpart() + "    " + sd.getInstrumentName() + "   " + sd.getBought() + "   " + sd.getSold() + "   " + sd.getTotal());
		}
	}

	public int getRows() {
		return rows_;
	}

	public void setRows(int rows_) {
		this.rows_ = rows_;
	}

	public String getString(int row, int column) {
		// TODO Auto-generated method stub
		return getText(row, column);
	}

	public BigDecimal getBigDecimal(int row, int column) {
		// TODO Auto-generated method stub
		return BigDecimal.valueOf(getInt(row, column));
	}

}