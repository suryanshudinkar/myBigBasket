<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>

.all {
  margin: auto;
  width: 90%;
  border: 3px solid #73AD21;
  padding: 10px;
  text-align:center;
}


</style>

</head>

    <body>
    <div class="all">
        <h1>Welcome to Big Basket</h1>
        <hr>
        
        <form action="login" method="post">
			 <input type="submit" value="Login" />
	    </form>
	    
	    <br>
	    
	    <form action="signup" method="post">
			 <input type="submit" value="Signup" /> <br>
	    </form>
	    </div>
	    
	     
    </body>
</html>
