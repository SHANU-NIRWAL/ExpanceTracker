<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<title>Dashboarde</title>
</head>
<body style="margin: auto; background-color: #d7d7d9;">
	<%@ include file="part/AdminHeader.jsp"%>
	<%
response.setHeader("Cache-Control", "no-cache,no-store,must-revolidate");
response.setHeader("Progma", "no-cache");
response.setHeader("Expires", "0");
%>
	<h1 style="text-align: center; background-color: black; color: white">List
		of all Users</h1>

	<div style="margin: auto; width: 800px;">
		<table class="table table-light "
			style="box-shadow: 10px 10px 5px grey;">
			<thead>
				<tr class="table-dark">
					<th scope="col">#</th>
					<th scope="col">First Name</th>
					<th scope="col">Last Name</th>
					<th scope="col">Email</th>
					<th scope="col">Phone</th>
					<th scope="col">Action</th>
					<th scope="col">View Expense</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userlist}" var="user">
					<tr>
						<td>${user.userId}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.email}</td>
						<td>${user.phone}</td>
						<td><a
							href="delete/${user.userId}"><i class="material-icons"
								style="color: red">&#xe872;</i></a></td>
						<td><a href="/ExpanseTracker/expenseDetailsADmin/${user.userId}">View</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>