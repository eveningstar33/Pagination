<%@ page import="servlet.pag.model.Employee"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<%@ page isELIgnored="false" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

		<h1>Page No: ${page}</h1>
		
        <table border='1' cellpadding='4' width='60%'>  
	        <tr>
	        	<th>Id</th>
	        	<th>Name</th>
	        	<th>Salary</th>  
	        <tr>
	        <c:forEach var="tempEmployee" items="${employees}">
	        	<tr>
	        		<td>${tempEmployee.id}</td>
	        		<td>${tempEmployee.name}</td>
	        		<td>${tempEmployee.salary}</td>
	        	</tr>
	        </c:forEach>
        </table>

        <a href="ViewEmployees?page=1">1</a>  
        <a href="ViewEmployees?page=2">2</a>   
        <a href="ViewEmployees?page=3">3</a>

</body>
</html>