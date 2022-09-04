<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ABC Insurance Company</title>
<style>
	<%@ include file="../styles/index.css"%>
</style>
</head>
<body>
	<h1 class="header">Login Page</h1>
	<div class="container">
		<form action="../Login" method="post">
			<div class="loginContainer">
				<div class="unCont">
					<label class="un_lbl">Username: </label><input name="username"
						type="text" class="un_ipt" />
				</div>
				<div class="pdCont">
					<label class="pd_lbl">Password: </label><input name="password"
						type="password" class="pd_ipt" />
				</div>
				<div class="actionHol">
					<input class="loginbtn" type="submit" value="Login" /> <a
						class="register" href="signup.jsp">Register</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>