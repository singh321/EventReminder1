<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="JavaScript" type="text/javascript" src="datetimepicker.js">

//Script by Denis Gritcyuk: tspicker@yahoo.com
//Submitted to JavaScript Kit (http://javascriptkit.com)
//Visit http://javascriptkit.com for this script

</script>
<title>Edit</title>
</head>
<body >
<% String id = request.getParameter("id");
System.out.println("this is JSP: "+id);
		request.setAttribute("id", id);
		%>
		<% String name = request.getParameter("name");
System.out.println("this is JSP: "+name);
		request.setAttribute("name", name);
		%>
		<% String dob = request.getParameter("dob");
System.out.println("this is JSP: "+dob);
		request.setAttribute("dob", dob);
		%>
		<%String ann = request.getParameter("ann");
System.out.println("this is JSP: "+ann);
		request.setAttribute("ann", ann);
		%>
        
        <center>
<form action="Edit" method="POST">

    <p>Employee Name:</p>
    <p><input type="text" name="emp" value="${name}"></p>
    <input type="hidden" name="emailId1" id="emailId1" value="${id}">

    <p>Birth Date: (dd-mm-yyyy)</p>
    <p><input id="demo1" type="text" name="dob" value="${dob}">
    
<a href="javascript:NewCal('demo1','DDMMYYYY')">
<img src="cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>


    </p>
    <p>Anniversary Date: (dd-mm-yyyy)</p>
    <p><input id="demo2" type="text" name="ann" value="${ann}">
    <a href="javascript:NewCal('demo2','DDMMYYYY')">
<img src="cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>
</p>
    <p><input type="submit" value="submit details"></p>
    </center>

</form>


</body>
</html>