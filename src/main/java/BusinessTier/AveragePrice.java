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
import model.AverageInstrumentPrice;

/**
 * Servlet implementation class AveragePrice
 */
public class AveragePrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AveragePrice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatabaseManagerOriginal db = new DatabaseManagerOriginal();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		
		ArrayList<AverageInstrumentPrice> averagePriceList = new ArrayList<AverageInstrumentPrice>();
		List<ObjectNode> answerList = new ArrayList<ObjectNode>();
		averagePriceList = db.getAveragePrices();
		AverageInstrumentPrice averagePrice;
		
		for(int i=0; i<averagePriceList.size();i++){
			averagePrice = averagePriceList.get(i);
	
				ObjectNode node = JsonNodeFactory.instance.objectNode();
				//node.put("id", averagePrice.getId());
				node.put("name", averagePrice.getName());
				node.put("averageBuy", averagePrice.getAverageBuy());
				node.put("averageSell", averagePrice.getAverageSell());
	
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
