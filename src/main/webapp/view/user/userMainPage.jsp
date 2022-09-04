<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	<%@ include file="../../styles/usermain.css"%>
</style>
<title>User Main Page</title>
</head>
<%
String allProducts = (String) request.getAttribute("allProducts");
String username = "";
Cookie ck[] = request.getCookies();
for (int i = 0; i < ck.length; i++) {
	if (ck[i].getName().equals("username")) {
		username = ck[i].getValue();
	}
}
%>
<body>
	<div class="container">
		<div class="header">
			<div class="hholder">
				<span class="heading">ABC Company</span> 
				<a href="DeleteCookies"	class="logout">Logout</a>
			</div>
			<p>Here we can register our products, claim for
				repair/replacement</p>
			<span class="addClHol"><a
				href="UserAddClaim?username=<%=username%>">Claim Product</a></span> <span
				class="viewClaims"><a
				href="UserViewClaims?username=<%=username%>">View Claims</a></span>
		</div>
		<div class="listOfProducts">
			<h3>List of products</h3>
			<%=allProducts%>
		</div>
	</div>

</body>
</html>