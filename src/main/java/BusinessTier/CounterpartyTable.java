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
import model.Counterparty;

/**
 * Servlet implementation class CounterpartyTable
 */
public class CounterpartyTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CounterpartyTable() {
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
