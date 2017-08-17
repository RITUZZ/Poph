package BusinessTier;

import java.math.BigDecimal;

import DatabaseTier.EndingPositionSet;

public class EndingPositionSetSample extends EndingPositionSet {
	EndingPositionTest endingPositionTest_;
	int index_;

	public EndingPositionSetSample(EndingPositionTest endingPositionTest) {
		endingPositionTest_ = endingPositionTest;
		index_ = 0;
	}

	@Override
	public boolean next() {
		return index_<endingPositionTest_.getRows();
	}

	@Override
	public String getString(int i) {
		// TODO Auto-generated method stub
		return endingPositionTest_.getString(index_,i);
	}

	@Override
	public BigDecimal getBigDecimal(int i) {
		// TODO Auto-generated method stub
		return endingPositionTest_.getBigDecimal(index_,i);
	}

}
