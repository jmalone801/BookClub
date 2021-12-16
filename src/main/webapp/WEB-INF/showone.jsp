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
<title>Title Here</title>
  <!-- Bootstrap -->
  <link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
    <br><br>
        
        <h1> <span><c:out value="${book.title}"></c:out></span> </h1><br>

        <h3><span class="text-info"><c:out value="${book.reader.firstName}"></c:out></span> read the book 
        <span class="text-info"><c:out value="${book.title}"></c:out></span> by 
        <span class="text-info"><c:out value="${book.author}"></c:out></span>.</h3><br><br>
        
        <h3>Here are <span class="text-info"><c:out value="${book.reader.firstName}"></c:out>'s</span> thoughts:
        <span class="text-info"><c:out value="${book.thoughts}"></c:out></span> </h3><br><br>
        
        <c:choose>
			<c:when test="${book.reader.id == loggedUser.id}">
		         <a href="/edit/${book.id}" class="btn btn-warning">Edit Book</a>
		         <a href="/delete/${book.id}" class="btn btn-danger">Delete</a><br><br>
			</c:when>
		</c:choose>
        <a href="/logout" class="btn btn-info">Logout</a>
        <a href="/dashboard" class="btn btn-info">Back to Dashboard</a>
    </div> <!-- End of Container -->
</body>
</html>