package BusinessTier;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import DatabaseTier.DatabaseManagerOriginal;
import model.Deal;
import model.RealisedProfitLoss;

/**
 * Servlet implementation class RealisedProfitLossTable
 */
public class RealisedProfitLossTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RealisedProfitLossTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DatabaseManagerOriginal db = Login.db;
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();

		ArrayList<RealisedProfitLoss> realisedList = db.getRealisedProfitLoss();
		List<ObjectNode> answerList = new ArrayList<ObjectNode>();
		RealisedProfitLoss rpl;

		for (int i = 0; i < realisedList.size(); i++) {
			rpl = realisedList.get(i);
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			node.put("instrument_name", rpl.getInstrumentName().toString());
			node.put("counterparty_name", rpl.getCounterparty());
			node.put("profit", rpl.getRealisedProfit());

			answerList.add(node);

		}
		mapper.writeValueAsString(answerList);
		ObjectNode responseNode = JsonNodeFactory.instance.objectNode();

		// hardcoded true for now
		responseNode.put("status", true);

		ArrayNode array = mapper.valueToTree(answerList);
		responseNode.put("answer", array);
		String jsonInString = mapper.writeValueAsString(responseNode);
		out.println(jsonInString);
//		System.out.println(jsonInString);
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
