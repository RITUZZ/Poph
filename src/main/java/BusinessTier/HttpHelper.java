package BusinessTier;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class HttpHelper {

	protected String getResponseString(HttpServletRequest request) throws ServletException, IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		try {
			String line;
		
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				sb.append(line).append('\n');
			}
		} finally {
			br.close();
		}
		String responseString = sb.toString();
		return responseString;
	}
	
}
