package DatabaseTier;

import java.math.BigDecimal;

public abstract class EndingPositionSet {

	public abstract boolean next();

	public abstract String getString(int i);

	public abstract BigDecimal getBigDecimal(int i);

}
