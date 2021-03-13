<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>
<body>
<jsp:include page="admin-header.jsp" ></jsp:include>
<a href="edit-product.jsp">Add Product</a><br>
<br>
<br>
<table border=1 cellspacing=2 cellpadding=4>
 	<tr>
 		<td><b>Product Id</b></td>
 		<td><b>Product Name</b></td>
 		<td><b>Price</b></td>
 		
 	</tr>
 	<c:forEach items="${list}" var="item">
 	  	<tr>
 	  		<td>${item.productId }</td>
	 		<td>${item.name }</td>
 			<td>${item.price }</td>
 	  		<td>
 	  			<a href="deleteproduct?id=${item.productId}">Delete</a>
 	  		</td>
 	  	</tr>
 	  </c:forEach>
</table> 	

</body>
</html>