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
import model.EndPosition;

/**
 * Servlet implementation class EndingPositionTable
 */
public class EndingPositionTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EndingPositionTable() {
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
