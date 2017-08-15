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
		
		DatabaseManagerOriginal db = new DatabaseManagerOriginal();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		
//HttpHelper helper = new HttpHelper();
//		
//		String responseString = helper.getResponseString(request);
//		System.out.println("response is "+responseString);
//		Map<String,Object> map = mapper.readValue(responseString, Map.class);
		
		ArrayList<Deal> instrumentDetailList = new ArrayList<Deal>();
		List<ObjectNode> answerList = new ArrayList<ObjectNode>();
//		instrumentDetailList = db.getInstumentDetails((String)map.get("instrument"), (String)map.get("dealType"));
		instrumentDetailList = db.getInstumentDetails("Eclipse", "B");
		Deal deal;
		
		for(int i=0; i<instrumentDetailList.size(); i++){
			deal = instrumentDetailList.get(i);
	
				ObjectNode node = JsonNodeFactory.instance.objectNode();
				node.put("instrumentName", deal.getInstrumentName());
				node.put("dealTime", deal.getTime().toString());
				node.put("dealQuantity", deal.getQuantity());
				node.put("dealAmount", deal.getAmount());
				
	
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}