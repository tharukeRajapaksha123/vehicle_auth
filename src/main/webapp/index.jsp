<%
	if(session.getAttribute("name") != null){
		response.sendRedirect("home.jsp");
	}
%>




<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vehicle Spare Parts System</title>
 <link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div class= "body">
		<div class="glass-card">
			<h1>Welcome Back</h1>
			<form action="LoginServerlet" method="post">
				<label for="email">Email</label>
    				<input type="text" id="usrname" name="email" required>
				<label for="psw">Password</label>
  					<input type="password" id="psw" name="psw" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required>
  					<input type="submit" value="Submit">
			</form>
			<p>Don't have an account ? <div class="wrapper"><a href="register.jsp">Register here</a></div></p>
		</div>
	</div>
</body>
</html>