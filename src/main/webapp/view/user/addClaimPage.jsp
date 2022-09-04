<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	<%@ include file="../../styles/registerProduct.css"%>
</style>
</head>
<%
	String regProd = (String) request.getAttribute("regProd");
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
		<h3>List of Registered products</h3>
		<%
		String message = (String) request.getAttribute("message");
		if(message != null && !message.equals("")){
			String msg =  message.contains("Error:") ? "error" : "success";
		%>
		<p class="message <%= msg %>" ><%= message %></p>
		<%
		}
		%>
		<%= regProd %>
		<div class="bckHolder">
			<form action="Login" method="post">
				<input type="hidden" name="username" value="<%=username%>"/>
				<input type="hidden" name="password" value="<%=password%>"/>
				<input type="submit" class="backbtn" value="Back to Main Page" />
			</form>
		</div>
	</div>
</div>
</body>
</html>