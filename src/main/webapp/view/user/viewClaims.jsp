<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
	<%@ include file="../../styles/userclaims.css"%>
</style>
<meta charset="ISO-8859-1">
<title>User Claims</title>
</head>
<%
String allClaims = (String) request.getAttribute("allClaims");
String username = "";
String password = "";
Cookie ck[]=request.getCookies();  
for(int i=0;i<ck.length;i++){ 
	if(ck[i].getName().equals("username")){
		username = ck[i].getValue();
	}
	if(ck[i].getName().equals("password")){
		password = ck[i].getValue();
	}
}  
%>
<body>
<div class="container">
	<div class="listOfProducts">
		<h3>List of Claims</h3>
		<%
		String message = (String) request.getAttribute("message");
		if(message != null && !message.equals("")){
			String msg =  message.contains("Error:") ? "error" : "success";
		%>
		<p class="message <%= msg %>" ><%= message %></p>
		<%
		}
		%>
		<%=allClaims%>
	</div>
	<div class="bckHolder">
		<form action="Login" method="post">
			<input type="hidden" name="username" value="<%=username%>"/>
			<input type="hidden" name="password" value="<%=password%>"/>
			<input type="submit" class="backbtn" value="Back to Main Page" />
		</form>
	</div>
</div>
</body>
</html>