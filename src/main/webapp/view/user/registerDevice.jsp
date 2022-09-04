<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
	<%@ include file="../../styles/userPurchase.css"%>
</style>
<title>Register products</title>
</head>
<%
String username = (String) request.getAttribute("username");
String productId = (String) request.getAttribute("productId");
String password = "";
Cookie ck[]=request.getCookies();  
for(int i=0;i<ck.length;i++){ 
	if(ck[i].getName().equals("password")){
		password = ck[i].getValue();
	}
}  
%>
<body>
	<div class="container">
		<h3>Add Purchased product</h3>
		<form action="UserAddProduct" method="post">
			<input type="hidden" name="username" value="<%=username%>"/>
			<input type="hidden" name="pId" value="<%=productId%>"/>
			<div class="holder serialHolder">
				<label>SerialNo: </label>
				<input type="text" name="serialNo" />
			</div>
			<div class="holder purchaseHolder">
				<label>Purchase Date: </label>
				<input type="date" name="date" />
			</div>
			<input class="btn" type="submit" value="Add" />
		</form>
		<div class="bckHolder">
			<form action="Login" method="post">
				<input type="hidden" name="username" value="<%=username%>"/>
				<input type="hidden" name="password" value="<%=password%>"/>
				<input type="submit" class="backbtn" value="Back to Products" />
			</form>
		</div>
	</div>
</body>
</html>