package DatabaseTier;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import model.Deal;

public class DatabaseManagerSecureTest {
	
	static DatabaseManagerOriginal o;

	@BeforeClass
	public static void connectDatabase() {
		o = new DatabaseManagerOriginal();
	}
	
	
	@Test
	public void testTables() {
		assertNotNull(o.getTables());
	}
	
	@Test
	public void testDealTable() {
		assertNotNull(o.getDealTable(new ArrayList<Deal>(), 0, 100));
	}
	
	@Test
	public void testCounterPartyTable() {
		assertNotNull(o.getCounterpartyTable());
	}
	
	@Test
	public void testInstrument() {
		assertNotNull(o.getInstrumentTable());
	}
	
	@Test
	public void testEnding() {
		assertNotNull(o.getEndingPositions());
	}
	
	@Test
	public void testAveragePrices() {
		assertNotNull(o.getAveragePrices());
	}
	
	@Test
	public void testInstrumentDetails() {
		assertNotNull(o.getInstrumentDetails("Eclipse", "B"));
	}
	
	@Test
	public void testRealisedProfit() {
		assertNotNull(o.getCounterpartyTable());
	}

}
