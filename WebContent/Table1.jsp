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
/* Create string of connection url within specified format with machine
name, port number and database name. Here machine name id localhost and 
database name is student. */
String connectionURL = "jdbc:mysql://localhost:3306/db1";
// declare a connection by using Connection interface
Connection connection = null;
/* declare object of Statement interface that is used for executing sql 
statements. */
Statement statement = null;
// declare a resultset that uses as a table for output data from tha table.
ResultSet rs = null;
// Load JBBC driver "com.mysql.jdbc.Driver"
Class.forName("com.mysql.jdbc.Driver").newInstance();
/* Create a connection by using getConnection() method that takes parameters 
of string type connection url, user name and password to connect to database.*/
connection = DriverManager.getConnection(connectionURL, "root", "pass");
/* createStatement() is used for create statement object that is used for 
sending sql statements to the specified database. */
statement = connection.createStatement();
// sql query to retrieve values from the secified table.
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
<td><a href="Edit.jsp">Edit</a></td>
<c:forEach var="item" items="${items}">

    	  	<td>
    	  		<input type="checkbox" name="itemId" value="${item.itemId}">
    	  	</td>	
    			
</c:forEach>    

<TD><FORM ACTION="Table" method="post" >
<button type="submit">Delete</Delete></TD>
<% System.out.println(rs.getString(4));%>
</TR>
<% } %>
<%
// close all the connections.
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