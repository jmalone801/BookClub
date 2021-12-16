<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Book</title>
  <!-- Bootstrap -->
  <link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
		<div class="box">
            <div class="boxContent">
	        <h1>Insert New Book</h1>
                <form:form action="/createbook" method="post" modelAttribute="newBook">
                	<form:hidden path="reader" value="${userId}"/>
                    <div class="form-group">
                        <label>Title:</label>
                        <form:input path="title" class="form-control" />
                        <form:errors path="title" class="text-danger" />
                    </div>
                    <div class="form-group">
                        <label>Author:</label>
                        <form:input path="author" class="form-control" />
                        <form:errors path="author" class="text-danger" />
                    </div>
                    <div class="form-group">
                        <label>My Thoughts:</label>
                        <form:input path="thoughts" class="form-control" />
                        <form:errors path="thoughts" class="text-danger" />
                    </div>
                    <input type="submit" value="Submit" class="btn btn-success"/><br><br>
                </form:form>
            <a href="/logout" class="btn btn-info">Logout</a>
        	<a href="/dashboard" class="btn btn-info">Back to Dashboard</a>
            </div>
        </div>
    </div> <!-- End of Container -->
</body>
</html>