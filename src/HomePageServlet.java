import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mysql.jdbc.*;
 
public class  HomePageServlet extends HttpServlet {
	boolean isUserCreated=false;
	private static final String THANKYOU_PAGE = "ThankYou.jsp";
	private static final String HOME_PAGE = "Home.jsp";
	private static final String ERROR_PAGE = "ErrorValidation.jsp";
	private static final String ERROR_PAGE1 = "ErrorDuplicateMailID.jsp";
protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
	System.out.println("inside home servlet");
 
String empname=request.getParameter("emp");
String empmail=request.getParameter("mail");
String birth=request.getParameter("dob");
String anni=request.getParameter("ann");

if(empname =="" || empmail=="" || (birth=="" &&anni=="")){
	 response.sendRedirect(ERROR_PAGE);
}
else{
	SimpleDateFormat outputFormat = (SimpleDateFormat) new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat inputFormat = (SimpleDateFormat) new SimpleDateFormat("dd-MM-yyyy");
	//String inputText = "2012-11-17";
	Date date = null;
	try {
		date = ((java.text.DateFormat) inputFormat).parse(birth);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	String birth1 = ((java.text.DateFormat) outputFormat).format(date);
	System.out.println(birth1);
	System.out.println(birth);


	SimpleDateFormat outputFormat1 = (SimpleDateFormat) new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat inputFormat1 = (SimpleDateFormat) new SimpleDateFormat("dd-MM-yyyy");

	//String inputText = "2012-11-17";
	Date date1 = null;
	String anni1 = null;
	if(anni!=""){
	try {
		date1 = ((java.text.DateFormat) inputFormat1).parse(anni);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	}
	anni1 = ((java.text.DateFormat) outputFormat1).format(date1);
	System.out.println(anni1);
	System.out.println(anni);
	}

System.out.println("name is" +empname);
System.out.println("\n and email is" +empmail);
System.out.println("\n and DOB is"+birth1);
 System.out.println("\n and Anniversary is"+anni1);
String db="db1";        
  ResultSet rs;
 
try {
  
  Context context = new InitialContext();
	DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/rem_datasource");
	Connection conn = (Connection) dataSource.getConnection(); 
	
  //Add the data into the database
  String sql = "insert into emp values (?,?,?,?)";
  PreparedStatement prep = conn.prepareStatement(sql);
  //Setting the values which we got from JSP form
  prep.setString(1, empname);
  prep.setString(2, empmail);
  prep.setString(3, birth1);
  prep.setString(4, anni1);
  //statement = (Statement) connection.createStatement();
  System.out.println("SQL" + sql);
  prep.executeUpdate();
  isUserCreated=true;
  if(isUserCreated){
		response.sendRedirect(THANKYOU_PAGE);
	} else {
		response.sendRedirect(HOME_PAGE);
	}
  prep.close();
    }catch(Exception E){
//Any Exceptions will be caught here
System.out.println("The error is=="+E.getMessage());
response.sendRedirect(ERROR_PAGE1);
}

finally{
}
}
}
}