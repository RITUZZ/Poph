package BusinessTier;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

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

/**
 * Servlet implementation class DealTable
 */
public class DealTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DealTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatabaseManagerOriginal db = Login.db;
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		
		ArrayList<Deal> dealList = new ArrayList<Deal>();
		List<ObjectNode> answerList = new ArrayList<ObjectNode>();
		
		int limit = Integer.parseInt(request.getParameter("limit"));
		int offset = Integer.parseInt(request.getParameter("offset"));
		
		dealList = db.getDealTable(dealList,offset,limit);
		Deal deal;
		
		for(int i=0; i<dealList.size();i++){
			deal = dealList.get(i);
			//System.out.println(deal);
				ObjectNode node = JsonNodeFactory.instance.objectNode();
				//node.put("id",deal.getId());
				node.put("instrumentname", deal.getInstrumentName());
				node.put("counterpartyname", deal.getCounterpartyName());
				node.put("time", deal.getTime().toString());
				//node.put("counterpartyid",deal.getCounterpartyId());
				//node.put("instrumentid", deal.getInstrumentId());
				node.put("type", deal.getType());
				node.put("amount", deal.getAmount());
				node.put("quantity", deal.getQuantity());
				answerList.add(node);
			
		}
		mapper.writeValueAsString(answerList);
		ObjectNode responseNode = JsonNodeFactory.instance.objectNode();
		
		//hardcoded true for now
		responseNode.put("status", true);
		
		
		ArrayNode array = mapper.valueToTree(answerList);
		responseNode.put("answer", array);
		String jsonInString = mapper.writeValueAsString(responseNode);
		out.println(jsonInString);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
