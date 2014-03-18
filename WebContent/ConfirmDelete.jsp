<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete</title>
</head>
<body >
<center>
<h1>Are you want to delete?</h1>
</center>
<% String id1 = request.getParameter("id1");
System.out.println("this is JSP: "+id1);
		request.setAttribute("id1", id1);
		%>
		<% String name = request.getParameter("name1");
System.out.println("this is JSP: "+name);
		request.setAttribute("name", name);
		%>
		<% String dob = request.getParameter("dob1");
System.out.println("this is JSP: "+dob);
		request.setAttribute("dob", dob);
		%>
		<%String ann = request.getParameter("ann1");
System.out.println("this is JSP: "+ann);
		request.setAttribute("ann", ann);
		%>
		<center>
<form action="Table" method="POST">

    <p>Employee Name:</p>
    <p><input type="text" name="emp" value="${name}" readonly></p>
    <input type="hidden" name="emailId2" id="emailId2" value="${id1}">

    <p>Birth Date:</p>
    <p><input id="demo1" type="text" name="dob" value="${dob}" readonly>


    </p>
    <p>Anniversary Date:</p>
    <p><input id="demo2" type="text" name="ann" value="${ann}" readonly>
   
</p>
    <p><input type="submit" value="Yes"></p>
    </center>

</form>
<form action="ConfirmDelete" method="post">
<center>
<p><input type="submit" value="No"></p>
</center>


</form>

</body>
</html>