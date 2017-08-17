package BusinessTier;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

import fitnesse.fixtures.TableFixture;
import model.EndPosition;

public class EndingPositionTest extends TableFixture {

	@Test
	public void test() {
		EndingPositionTable table = new EndingPositionTable();
		List<ObjectNode> answerList = table.get_table();
		for (EndPosition sd : answerList) {
			System.out.println(sd.getDealCounterpart() + "    " + sd.getInstrumentName() + "   " + sd.getBought() + "   " + sd.getSold() + "   " + sd.getTotal());
		}
		fail("Not yet implemented");
	}
	
	 protected void doStaticTable(int rows) {
		
	}

}
