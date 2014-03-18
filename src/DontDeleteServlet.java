

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DontDeleteServlet
 */
@WebServlet("/DontDeleteServlet")
public class DontDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_PAGE = "Table.jsp";
	
   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			response.sendRedirect(VIEW_PAGE);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
