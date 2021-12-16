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
        <h1>Welcome home ${loggedUser.firstName} ${loggedUser.lastName}!</h1>
        <table class="table table-dark">
        	<thead>
        		<tr>
        			<th>Id</th>
        			<th>Title</th>
        			<th>Author</th>
        			<th>Posted by</th>
        			<th>Actions</th>
        		</tr>
        	</thead>
        	<tbody>
        		<c:forEach var="i" items="${books}">
	        	<tr>
	        		<td><c:out value="${i.id}"></c:out></td>
	        		<td><a href="/book/${i.id}"><c:out value="${i.title}"></c:out></a></td>
	        		<td><c:out value="${i.author}"></c:out></td>
	        		<td><c:out value="${i.reader.firstName}"></c:out></td>
	        		<td>
	        		<!-- Lines 46-51 check if the user is in session so they can edit or delete! -->
	        		<c:choose>
	        		<c:when test="${i.reader.id == loggedUser.id}">
		        		<a href="/edit/${i.id}">Edit</a> |
						<a href="/delete/${i.id}">Delete</a>
					</c:when>
					</c:choose>
					</td>
	        	</tr>
	        	</c:forEach>
	        	
        	</tbody>
        </table> 
        <a href="/books/new" class="btn btn-info">Add New Book</a>
        <a href="/logout" class="btn btn-info">Logout</a>
    </div> <!-- End of Container -->
</body>
</html>