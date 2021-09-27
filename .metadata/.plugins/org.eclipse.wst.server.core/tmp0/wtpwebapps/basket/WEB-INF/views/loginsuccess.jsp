<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Login successful</h1>

		<form action="showCatalog" method="post">
			<input type="submit" value="Show Catalog" /> <br>
		</form>	
		
		<form action="home" method="post">
			<input type="submit" value="Logout" /> <br>
		</form>	
		
		<form action="viewPrevousOrders" method="post">
			<input type="submit" value="View previous orders" /> <br>
		</form>	

</body>
</html>