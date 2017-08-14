package BusinessTier;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
import model.Counterparty;
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
    
    public void getInstrumentTable(DatabaseManagerOriginal db, ObjectMapper mapper, PrintWriter out) throws JsonGenerationException, JsonMappingException, IOException{
    	ArrayList<Instrument> instrumentList = new ArrayList<Instrument>();
		List<ObjectNode> answerList = new ArrayList<ObjectNode>();
		instrumentList = db.getInstrumentTable();
		Instrument instrument;
		
		for(int i=0; i<instrumentList.size();i++){
			instrument = instrumentList.get(i);
			if (!answerList.contains(instrument.getId())){
				ObjectNode node = JsonNodeFactory.instance.objectNode();
				node.put("id",instrument.getId());
				node.put("name", instrument.getName());
				answerList.add(node);
			}
		}
		mapper.writeValueAsString(answerList);
		ObjectNode responseNode = JsonNodeFactory.instance.objectNode();
		
		//hardcoded true for now
		responseNode.put("status", true);
		
		responseNode.put("tabletype", "instrument");
		
		ArrayNode array = mapper.valueToTree(answerList);
		responseNode.put("answer", array);
		String jsonInString = mapper.writeValueAsString(responseNode);
		out.write(jsonInString);
    }
    
    public void getCounterpartyTable(DatabaseManagerOriginal db, ObjectMapper mapper, PrintWriter out) throws JsonGenerationException, JsonMappingException, IOException{
    	ArrayList<Counterparty> counterpartyList = new ArrayList<Counterparty>();
		List<ObjectNode> answerList = new ArrayList<ObjectNode>();
		counterpartyList = db.getCounterpartyTable();
		Counterparty counterparty;
		
		for(int i=0; i<counterpartyList.size();i++){
			counterparty = counterpartyList.get(i);
			if (!answerList.contains(counterparty.getId())){
				ObjectNode node = JsonNodeFactory.instance.objectNode();
				node.put("id", counterparty.getId());
				node.put("name", counterparty.getName());
				node.put("status", counterparty.getStatus());
				node.put("date", counterparty.getDateRegistered().toString());
				answerList.add(node);
			}
		}
		mapper.writeValueAsString(answerList);
		ObjectNode responseNode = JsonNodeFactory.instance.objectNode();
		
		//hardcoded true for now
		responseNode.put("status", true);
		
		responseNode.put("tabletype", "counterparty");
		
		ArrayNode array = mapper.valueToTree(answerList);
		responseNode.put("answer", array);
		String jsonInString = mapper.writeValueAsString(responseNode);
		out.write(jsonInString);
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
		//TODO: move this to doPost once connected with front-end and change status to be based on whether user is logged in
		
		DatabaseManagerOriginal db = new DatabaseManagerOriginal();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		
		//calling method to get Instrument table
		getInstrumentTable(db, mapper, out);
		
		//calling method to get Counterparty table
		getCounterpartyTable(db,mapper,out);
		
		
	}

}
