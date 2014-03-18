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

public class  SaveUser extends HttpServlet {
	int isUserCreated=0;
	private static final String THANKYOU_PAGE = "LogonThankYou.jsp";
	private static final String ERROR_PAGE = "LogonError.jsp";
	private static final String ERROR1_PAGE = "ErrorDuplicate.jsp";
protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{ 
 
String username=request.getParameter("user_name");
String password=request.getParameter("pass_word");

System.out.println("The username is" +username);
System.out.println("\n and the password is" +password); 

String db="db1";         

  ResultSet rs;
  if(username=="" || password==""){
		response.sendRedirect(ERROR_PAGE);
	}
  else{
try {
	Context context = new InitialContext();
	DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/rem_datasource");
	Connection conn = (Connection) dataSource.getConnection(); 
	 System.out.println(conn);
	 String sql = "insert into LOGIN values (?,?)";
	 System.out.println(sql);
	 PreparedStatement prep=conn.prepareStatement(sql);
	 System.out.println(prep);
	 
  prep.setString(1, username);
  prep.setString(2, password);
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
response.sendRedirect(ERROR1_PAGE);
}
finally{
}
}
}
}