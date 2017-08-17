package BusinessTier;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import DatabaseTier.DatabaseManagerOriginal;
import model.Deal;

/**
 * Servlet implementation class Scylla
 */
public class Scylla extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Scylla() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DatabaseManagerOriginal db = Login.db;
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
	
		String name = request.getParameter("id");
		String dealType = request.getParameter("type");
	
		
		ArrayList<Deal> instrumentDetailList = new ArrayList<Deal>();
		List<ObjectNode> answerList = new ArrayList<ObjectNode>();
//		instrumentDetailList = db.getInstumentDetails((String)map.get("instrument"), (String)map.get("dealType"));
		//instrumentDetailList = db.getInstumentDetails("Eclipse", "B");
		instrumentDetailList = db.getInstrumentDetails(name,dealType);
		Deal deal;
		ObjectNode responseNode = JsonNodeFactory.instance.objectNode();
		ObjectNode finalResponse = JsonNodeFactory.instance.objectNode();
		
		for(int i=0; i<instrumentDetailList.size(); i++){
			deal = instrumentDetailList.get(i);
				ObjectNode node = JsonNodeFactory.instance.objectNode();
				node.put("deal_time", deal.getTime().toString());
				node.put("deal_quantity", deal.getQuantity());
				node.put("deal_amount", deal.getAmount());
				
				answerList.add(node);
			
		}
		mapper.writeValueAsString(answerList);
		
		ArrayNode array = mapper.valueToTree(answerList);
		responseNode.put("deals", array);
		responseNode.put("instrument", name);
		//hardcoded true for now
		finalResponse.put("status", true);
		finalResponse.put("answer", responseNode);
		
//		String jsonInString = mapper.writeValueAsString(responseNode);
//		out.println(jsonInString);
//		System.out.println(jsonInString);
		
		String jsonInString = mapper.writeValueAsString(finalResponse);
		out.println(jsonInString);
		System.out.println(jsonInString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
