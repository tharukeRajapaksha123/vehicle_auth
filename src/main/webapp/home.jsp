<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="com.mysql.jdbc.PreparedStatement"%>
<%@page import="com.mysql.jdbc.Connection"%>

<% 
	//SET Current user values
	String name = ""; 
	String uemail = ""; 
	String  mobileNumber = " ";
	String password = "071656Ad@";
	String email = String.valueOf(session.getAttribute("name"));
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/auth","root","");
	PreparedStatement pst =(PreparedStatement) con.prepareStatement("select * from users where email = ?");
	pst.setString(1, email);
	
	ResultSet rs = pst.executeQuery();
	if(rs.next()){
		System.out.print(rs.getString("name"));
		name = rs.getString("name");
		uemail = rs.getString("email");
		mobileNumber = rs.getString("mobile_number");
		password = rs.getString("password");
	}else{
		System.out.println("result is empty");
	}
	if(name == ""){
		response.sendRedirect("home.jsp");
	}
%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
 <link rel="stylesheet" href="css/home.css">
</head>
<body>
	
	<div class="body">
		<div class="glass-card">
			<h1>User Profile</h1>
			
			<form method="post" action = "UpdateProfileServerlet">
				<label for="name">Name</label>
				<input id="name" name="name" placeholder="name" required = "true" value=<%= name %>>
				<label for="email">Email</label>
				<input id="email" name="email" placeholder="email" required = "true" value=<%= email %>>
				<label for="mobile_number">Mobile Number</label>
				<input id="mobile_number" maxlength="10" name="mobile_number" placeholder="mobile_number" required = "true" value=<%= mobileNumber %>>
				<label for="password">Password</label>
				<input id="password" name="password" placeholder="password" type="password" required = "true" value=<%= password %>>
				<input type="submit" value="Update Profile">
			</form>
			<form action="LogoutServerlet" method="post">
				<button id= "logout-btn" type="submit">Logout</button>
			</form>
			<form action="DeleteUserServerlet" method="post">
				<button id= "delete-btn" type="submit">DELETE</button>
			</form>
		</div>
	</div>
	<script>
	function alertName(){
		alert("Successfully authenticaed");
		} 
	</script>
	<script type="text/javascript"> window.onload = alertName; </script>
</body>
</html>