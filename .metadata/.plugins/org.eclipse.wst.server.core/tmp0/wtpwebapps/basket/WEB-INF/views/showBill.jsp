<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <h1>Cart</h1>
	<table border="2" width="70%" cellpadding="2">
	<tr><th>Product Name</th><th>Product price</th></tr>
	<c:forEach var="product" items="${cart}">
	<tr>
	<td>${product.productName}</td>
	<td>${product.productPrice}</td>
	
	</tr>
	</c:forEach>
	</table>
    <br>
    <h1>Total Bill=${total}</h1>
    
        <form action="home" method="post">
			 <input type="submit" value="Log out" /> <br>
	    </form> 

	    
</body>
</html>