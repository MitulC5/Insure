<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
<style>
	<%@ include file="../styles/signup.css"%>
</style>
</head>
<body>
	<h1 class="header">Login Page</h1>
	<div class="container">
		<form method="post" action="../Register" class="loginContainer">
			<div class="holder"><label>UserName: </label><input type="text" name="username"></div>
			<div class="holder"><label>Password: </label><input type="password" name="password"></div>
			<div class="holder"><label>PhoneNo: </label><input type="text" name="phoneNo"></div>
			<div class="holder"><label>Email: </label><input type="text" name="email"></div>
			<div class="holder"><label>FirstName: </label><input type="text" name="fname"></div>
			<div class="holder"><label>LastName: </label><input type="text" name="lname"></div>
			<div class="holder"><label>Address: </label><input type="text" name="address"></div>
			<div class="holder btn"><input class="subtn" type="submit" name="submit" value="Register"></div>
		</form>
	</div>
</body>
</html>