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
<title>Insert title here</title>
</head>
<body >

<center>
<h1>Welcome to Birthday/Anniversary Notification Portal</h1>
</center>
<br/><br/>
<p align="right">
        <a align="right"href="Logout.jsp">Logout</a>
        <br>
        <br>
        <a align="right"href="Table.jsp">View Table</a>
        
        
        </p>
        
        <center>
<form action="Home" method="POST">

    <p>Employee Name:</p>
    <p><input type="text" name="emp" value=""></p>

    <p>Employee E-mail:</p>
    <p><input type="text" name="mail" value=""></p>
    
    <p>Birth Date: (dd-mm-yyyy)</p>
    <p><input id="demo1" type="text" name="dob" value="">
<a href="javascript:NewCal('demo1','ddmmyyyy')">
<img src="cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>

    </p>
    <p>Anniversary Date: (dd-mm-yyyy)</p>
    <p><input id="demo2" type="text" name="ann" value="">
    <a href="javascript:NewCal('demo2','ddmmyyyy')">
<img src="cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>
</p>
    <p><input type="submit" value="submit details"></p>
    </center>

</form>


</body>
</html>