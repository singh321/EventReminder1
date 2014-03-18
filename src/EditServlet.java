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
 
public class  EditServlet extends HttpServlet {
	int isUserCreated=0;
	private static final String VIEW_PAGE = "Table.jsp";
	private static final String EDIT_PAGE = "Edit.jsp";
	private static final String ERROR_PAGE = "ErrorValidation.jsp";
	private static final String ERROR_PAGE1 = "ErrorDuplicateMailID.jsp";
protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
	System.out.println("inside home servlet");
 
String empname=request.getParameter("emp");

String empmail=request.getParameter("emailId1");

System.out.println("email id is : "+empmail);



String birth=request.getParameter("dob");
String anni=request.getParameter("ann");

if(empname =="" || (birth=="" &&anni=="")){
	 response.sendRedirect(ERROR_PAGE);
}
else{
	String birth1;
	String anni1 = null;
	
	

	
	if(birth.indexOf("-")==4){
		System.out.println("found b date in right format, no need to change it");
		birth1=birth;
	}
	else{
		System.out.println("found b date in wrong format, need to change it");
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
	
	birth1 = ((java.text.DateFormat) outputFormat).format(date);
	System.out.println(birth1);
	System.out.println(birth);
	}

	
	if(anni.indexOf("-")==4){
		System.out.println("found anni date in right format, no need to change it");
		anni1=anni;
	}
	else{
		System.out.println("found anni date in right format, no need to change it");
	SimpleDateFormat outputFormat1 = (SimpleDateFormat) new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat inputFormat1 = (SimpleDateFormat) new SimpleDateFormat("dd-MM-yyyy");

	//String inputText = "2012-11-17";
	Date date1 = null;
	//String anni1 = null;
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
	}

System.out.println("name is" +empname);
System.out.println("\n and email is" +empmail);
System.out.println("\n and DOB is"+birth1);
 System.out.println("\n and Anniversary is"+anni1);
String db="db1";        
  ResultSet rs;

try {
	Statement statement=null;
	ResultSet resultset=null;
  
  Context context = new InitialContext();
	DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/rem_datasource");
	Connection conn = (Connection) dataSource.getConnection(); 
	
  //Add the data into the database
	
	if(anni1=="" || anni1==null){
		String sql = "UPDATE emp SET empname='"+empname+"',dob='"+birth1+"', anniversary="+anni1+" WHERE email='"+empmail+"'";
		 System.out.println("SQL is " + sql);
		  PreparedStatement prep=conn.prepareStatement(sql);
		  System.out.println(prep);
		  isUserCreated=prep.executeUpdate();
		  if(isUserCreated!=0){
				response.sendRedirect(VIEW_PAGE);
			} else {
				response.sendRedirect(EDIT_PAGE);
			}
	}
	else{
		String sql = "UPDATE emp SET empname='"+empname+"',dob='"+birth1+"', anniversary='"+anni1+"' WHERE email='"+empmail+"'";
	
	
  
  System.out.println("SQL is " + sql);
  PreparedStatement prep=conn.prepareStatement(sql);
  System.out.println(prep);
  isUserCreated=prep.executeUpdate();
  
  if(isUserCreated!=0){
		response.sendRedirect(VIEW_PAGE);
	} else {
		response.sendRedirect(EDIT_PAGE);
	}
	}

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