<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sporty Shoes - Purchase Confirmation</title>
</head>
<body>

<jsp:include page="customer-header.jsp"></jsp:include>
<br><br>
Your Order of ${cartValue} has been completed successfully.<br><br>
Order Details

<table border=1 cellspacing=2 cellpadding=4>
 	<tr>
 		<td><b>Product</b></td>
 		<td><b>Price</b></td>
 	</tr>
 	<c:forEach items="${cartItems}" var="item">
 	  	<tr>
	 		<td>${item.productName }</td>
 			<td>${item.price }</td>
 	  	</tr>
 	  </c:forEach>
</table> 	

</body>
</html>