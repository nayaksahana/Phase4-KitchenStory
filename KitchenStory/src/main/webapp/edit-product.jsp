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

<%
if (request.getParameter("error") != null)
	out.print(request.getParameter("error"));
%>

<form name=frmProduct method=post action="adminaddproduct">
<table border=1 cellspacing=2 cellpadding=4>
<tr>
 		<td width=25%>Product ID*</td>
 		<td><input name=id  maxlength=100 ></td>
 	</tr>
 	<tr>
 		<td width=25%>Product name*</td>
 		<td><input name=name  maxlength=100 ></td>
 	</tr>
 	<tr>
 		<td width=25%>Price*</td>
 		<td><input name=price type="numeric"  maxlength=6 ></td>
 	</tr>
 	
 	</tr>
 	
 	<tr>
 		<td colspan=2>
 			<button>Save</button>
 		</td>
 	</tr>
 </table>
</form>


</body>
</html>