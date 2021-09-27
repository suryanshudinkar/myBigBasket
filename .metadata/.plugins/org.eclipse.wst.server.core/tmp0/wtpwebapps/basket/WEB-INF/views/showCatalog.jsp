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

<h1>Catalog</h1>
	<table border="2" width="70%" cellpadding="2">
	<tr><th>Product Name</th><th>Product price</th><th>Add</th></tr>
	<c:forEach var="product" items="${list}">
	<tr>
	<td>${product.productName}</td>
	<td>${product.productPrice}</td>
	
	<td><a href="addProduct/${product.productPrice}/${product.productID}/${product.productName}">Add item to cart</a></td>
	</tr>
	</c:forEach>
	</table>

    <br/>
    
    <form action="calculateBill" method="post">
			 <input type="submit" value="Calculate Bill" /> <br>
	    </form>
</body>
</html>