<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body >
<center>
<h1>Login Page</h1>
</center>

        <center>
            <form name="frmLogin" method="POST" action="login">
<table >
	<tbody>
		
		<tr>
			<td>User Name: </td>
			<td><input type="text" name="user_name" /></td>
		</tr>
		<tr>
			<td>Password: </td>
			<td><input type="password" name="pass_word" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" name="Submit" value="Submit"/></td>
		</tr>
	</tbody>
</table>
</form>
            <h3>Register New User </h3>
        <a href="NewUser.jsp">New User</a>
        </center>

</body>
</html>