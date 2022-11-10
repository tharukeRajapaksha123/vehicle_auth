<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
 <link rel="stylesheet" href="css/login.css">
</head>
<body>
<input type = "hidden" id="status" value = "<%= request.getAttribute("status")  %>">
<div class= "body">
		<div class="glass-card">
			<h1>Let's Start</h1>
			<form action="RegistrataionServerlet" method="post">
					<label for="username">Username</label>
    				<input type="text" id="usrname" name="username" required>
    				<label for="email">Mobile Number</label>
    				<input type="text" id="usrname" maxlength = "10" name="mobile_number" required>
				<label for="email">Email</label>
    				<input type="text" id="usrname" name="email" required>
				<label for="psw">Password</label>
  					<input type="password" id="psw" name="psw" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required>
  					<input type="submit" value="Submit">
			</form>
			<p>Already have an account ? <div class="wrapper"><a href="index.jsp">Login here</a></div></p>
		</div>
	</div>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	<script type="text/javascript">
	var status = document.getElementById("status").value;
	console.log("states iss  " + status)
	if(status == "sucess"){
		swal("Congrats","Account created successfully","success")
	}
	</script>
</body>
</html>