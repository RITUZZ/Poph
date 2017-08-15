package BusinessTier;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import model.AverageInstrumentPrice;
import model.Counterparty;
import model.Deal;
import model.EndPosition;
import model.Instrument;

/**
 * Servlet implementation class Tables
 */
public class Tables extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tables() {
        super();
    }
    
    public void getDealTable(int limit, int offset, DatabaseManagerOriginal db, ObjectMapper mapper, PrintWriter out) throws JsonGenerationException, JsonMappingException, IOException{
    	
    }
    
    
    public void getCounterpartyTable(DatabaseManagerOriginal db, ObjectMapper mapper, PrintWriter out) throws JsonGenerationException, JsonMappingException, IOException{
    	
    }
    
    public void getEndingPositions(DatabaseManagerOriginal db, ObjectMapper mapper, PrintWriter out) throws JsonGenerationException, JsonMappingException, IOException{
    	ArrayList<EndPosition> endingPositionsList = new ArrayList<EndPosition>();
		List<ObjectNode> answerList = new ArrayList<ObjectNode>();
		endingPositionsList = db.getEndingPositions();
		EndPosition endPosition;
		
		for(int i=0; i<endingPositionsList.size();i++){
			endPosition = endingPositionsList.get(i);
			
				ObjectNode node = JsonNodeFactory.instance.objectNode();
				node.put("dealCounterpart", endPosition.getDealCounterpart());
				
				node.put("instrumentName", endPosition.getInstrumentName());
				node.put("bought", endPosition.getBought());
				node.put("sold", endPosition.getSold());
				node.put("total", endPosition.getTotal());
	
				answerList.add(node);
			
		}
		mapper.writeValueAsString(answerList);
		ObjectNode responseNode = JsonNodeFactory.instance.objectNode();
		
		//hardcoded true for now
		responseNode.put("status", true);
		
		responseNode.put("tabletype", "endingpositions");
		
		ArrayNode array = mapper.valueToTree(answerList);
		responseNode.put("answer", array);
		String jsonInString = mapper.writeValueAsString(responseNode);
		out.println(jsonInString);
    }
    
    public void getAveragePrices(DatabaseManagerOriginal db, ObjectMapper mapper, PrintWriter out) throws JsonGenerationException, JsonMappingException, IOException{
    	ArrayList<AverageInstrumentPrice> averagePriceList = new ArrayList<AverageInstrumentPrice>();
		List<ObjectNode> answerList = new ArrayList<ObjectNode>();
		averagePriceList = db.getAveragePrices();
		AverageInstrumentPrice averagePrice;
		
		for(int i=0; i<averagePriceList.size();i++){
			averagePrice = averagePriceList.get(i);
	
				ObjectNode node = JsonNodeFactory.instance.objectNode();
				node.put("id", averagePrice.getId());
				node.put("averageBuy", averagePrice.getAverageBuy());
				node.put("averageSell", averagePrice.getAverageSell());
	
				answerList.add(node);
			
		}
		mapper.writeValueAsString(answerList);
		ObjectNode responseNode = JsonNodeFactory.instance.objectNode();
		
		//hardcoded true for now
		responseNode.put("status", true);
		
		responseNode.put("tabletype", "averageprices");
		
		ArrayNode array = mapper.valueToTree(answerList);
		responseNode.put("answer", array);
		String jsonInString = mapper.writeValueAsString(responseNode);
		out.println(jsonInString);
    	
    	
    }
    
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO: change status to be based on whether user is logged in
		//response.setContentType("application/json");
		DatabaseManagerOriginal db = new DatabaseManagerOriginal();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
	
		//calling method to get Deal table
//		HttpHelper helper = new HttpHelper();
//		String responseString = helper.getResponseString(request);
//		System.out.println("response is "+responseString);
//		Map<String,Object> map = mapper.readValue(responseString, Map.class);
//		int limit = (Integer) map.get("limit");
//		int offset = (Integer) map.get("offset");
		
//		int limit = 10;
//		int offset = 0;
//		getDealTable(limit, offset, db,mapper,out);
		
		//calling method to get Ending Positions
//		getEndingPositions(db,mapper,out);
		
		//calling method to get Average Prices
		//getAveragePrices(db,mapper,out);
		
		out.close();
		
		
	}

}
