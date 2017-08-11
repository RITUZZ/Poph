package BusinessTier;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DatabaseTier.DatabaseManagerOriginal;
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
		DatabaseManagerOriginal db = new DatabaseManagerOriginal();
		ArrayList<Instrument> instrumentList = new ArrayList<Instrument>();
		instrumentList = db.getInstrumentTable();
		for(int i=0; i<instrumentList.size();i++){
			System.out.println(instrumentList.get(i));
		}
	}

}
