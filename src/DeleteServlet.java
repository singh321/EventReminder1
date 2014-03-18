import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class  DeleteServlet extends HttpServlet {
	int isUserCreated=0;
	private static final String THANKYOU_PAGE = "DeleteThankYou.jsp";
	private static final String ERROR_PAGE = "Error1.jsp";
	
protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{ 
System.out.println("inside delete servlet");
	String empmail2=request.getParameter("emailId2");
	System.out.println("email id is:"+empmail2);
	
String db="db1";         

  ResultSet rs;
 
try {
	Context context = new InitialContext();
	DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/rem_datasource");
	Connection conn = (Connection) dataSource.getConnection(); 
	 System.out.println(conn);
	 String sql = "DELETE from EMP where email='"+empmail2+"'";
	 System.out.println(sql);
	 PreparedStatement prep=conn.prepareStatement(sql);
	 System.out.println(prep);
	 
 
  isUserCreated=prep.executeUpdate();
  if(isUserCreated!=0){
		response.sendRedirect(THANKYOU_PAGE);
	} else {
		response.sendRedirect(ERROR_PAGE);
	}
  prep.close();
    }catch(Exception E){
//Any Exceptions will be caught here
System.out.println("The error is=="+E.getMessage());
}
finally{
}
}
}
