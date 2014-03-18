<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body >
<h2>Data from the employee table</h2>
<%
try {

String connectionURL = "jdbc:mysql://localhost:3306/db1";

Connection connection = null;

Statement statement = null;

ResultSet rs = null;

Class.forName("com.mysql.jdbc.Driver").newInstance();

connection = DriverManager.getConnection(connectionURL, "root", "pass");

statement = connection.createStatement();

String QueryString = "SELECT * from emp";
rs = statement.executeQuery(QueryString);
%>
<TABLE cellpadding="15" border="1">
<th>Emp Name</th><th>Mail ID</th><th>DOB</th><th>Anniversiry</th>
<%
while (rs.next()) {
%>
<TR>
<TD><%=rs.getString(1)%></TD>
<% System.out.println(rs.getString(1));%>

<TD><%=rs.getString(2)%></TD>

<% System.out.println(rs.getString(2));%>
<TD><%=rs.getString(3)%></TD>

<% System.out.println(rs.getString(3));%>
<TD><%=rs.getString(4)%></TD>
<td><a href="Edit.jsp?id=<%=rs.getString(2)%>&name=<%=rs.getString(1)%>&dob=<%=rs.getString(3)%>&ann=<%=rs.getString(4)%>">Edit</a></td>

<% System.out.println(rs.getString(3));%>

<td><a href="ConfirmDelete.jsp?id1=<%=rs.getString(2)%>&name1=<%=rs.getString(1)%>&dob1=<%=rs.getString(3)%>&ann1=<%=rs.getString(4)%>">Delete</a></td>
</TR>
<% } %>
<%

rs.close();
statement.close();
connection.close();
} catch (Exception ex) {
%>
</font>
<font size="+3" color="red"></b>
<%
System.out.println(ex.getMessage());
out.println("Unable to connect to database.");
}
%>
</TABLE><TABLE>
<TR>
<TD><FORM ACTION="Home.jsp" method="get" >
<button type="submit"><-- back</button></TD>
</TR>
</TABLE>
</font>




</body>
</html>