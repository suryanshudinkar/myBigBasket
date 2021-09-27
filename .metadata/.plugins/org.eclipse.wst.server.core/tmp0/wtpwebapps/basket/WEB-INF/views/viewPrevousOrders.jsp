<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

		<h1>All orders</h1>
	<table border="2" width="70%" cellpadding="2">
	<tr><th>Product Name</th><th>Product price</th><th>Add</th></tr>
	<c:forEach var="user" items="${list}">
	<tr>
	<td>${user.oID}</td>
	<td>${user.productlist}</td>	
	</tr>
	</c:forEach>
	</table>

    <br/>
    
    <form action="showCatalog" method="post">
			<input type="submit" value="Show Catalog" /> <br>
		</form>	
		
		<form action="home" method="post">
			<input type="submit" value="Logout" /> <br>
		</form>	

</body>
</html>