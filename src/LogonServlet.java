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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class LogonServlet
 */
public class LogonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String DBNAME = "db1";
    private static final String LOGIN_QUERY = "select * from login where BINARY username=? and BINARY password=?";
    private static final String HOME_PAGE = "Home.jsp";
    private static final String LOGIN_PAGE = "Login.jsp";
    private static final String ERROR_PAGE = "Error.jsp";


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello World");
		
		String strUserName = request.getParameter("user_name");
		String strPassword = request.getParameter("pass_word");
		System.out.println(strUserName);
		System.out.println(strPassword);
		String strErrMsg = null;
		HttpSession session = request.getSession();
		boolean isValidLogon = false;
		try {
			isValidLogon = authenticateLogin(strUserName, strPassword);
			if(isValidLogon) {
				session.setAttribute("userName", strUserName);
			} else {
				strErrMsg = "User name or Password is invalid. Please try again.";
			}
		} catch(Exception e) {
			strErrMsg = "Unable to validate user / password in database";
			
		}
		
		if(isValidLogon) {
			response.sendRedirect(HOME_PAGE);
		} else {
			session.setAttribute("errorMsg", strErrMsg);
			System.out.println("Incorrect user/password");
			response.sendRedirect(ERROR_PAGE);
		}
		
	}
	
	private boolean authenticateLogin(String strUserName, String strPassword) throws Exception {
		boolean isValid = false;
		
		try {
			
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/rem_datasource");
			Connection conn = (Connection) dataSource.getConnection(); 
			PreparedStatement prepStmt = conn.prepareStatement(LOGIN_QUERY);
			prepStmt.setString(1, strUserName);
			prepStmt.setString(2, strPassword);
			ResultSet rs = prepStmt.executeQuery();
			if(rs.next()) {
				System.out.println("User login is valid in DB");
				isValid = true;
			}
		} catch(Exception e) {
			System.out.println("validateLogon: Error while validating password: "+e.getMessage());
			throw e;
		} 
		
		return isValid;
	}
	
	private void closeConnection(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
		} catch(SQLException sqle) {
			System.out.println("Error while closing connection.");
		}
	}
}
