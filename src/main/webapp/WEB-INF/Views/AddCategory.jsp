<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<style type="text/css">
.right {
	display: block;
	margin-left: auto;
	margin-right: auto;
	width: 80%;
	background-color: "yellow";
	border-style: outset;
	margin-top: 10px;
	margin-bottom: 25px;
}

h1 {
	text-align: center;
}

.parent {
	align-items: center;
	display: flex;
	justify-content: center;
}

.child {
	height: 100px;
	width: 200px;
}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color:#d7d7d9">
<%@ include file="part/AdminHeader.jsp" %>
<h1 class="center" style="text-align: center;background-color: black;color:white">Add Role</h1>
	<form class="row g-5" action="categoryInset" method="post"
		modelAttribute="category">
		
		<div class="right  p-3 mb-5 bg-body rounded parent row-g-3 "
			style="align-content: center; margin-top:50px;box-shadow: 10px 10px 5px grey;width:500px;height:200px">


			<div class="col-auto child">
				<label for="account" class="form-label"><h5>ADD Roll:</h5></label> <input
					type="text" class="form-control" id="accountS" name="role" style="box-shadow: rgb(204, 219, 232) 3px 3px 6px 0px inset, rgba(255, 255, 255, 0.5) -3px -3px 6px 1px inset;">
				<br>
				<div class="d-grid gap-2 d-md-flex justify-content-md-center">
					<input type="submit" class="btn btn-warning" value="Add Role" style="box-shadow: rgba(0, 0, 0, 0.3) 0px 19px 38px, rgba(0, 0, 0, 0.22) 0px 15px 12px;"/>
				</div>
			</div>

		</div>

	</form>
	<hr>
	
	<div style="margin:auto;width:400px;">
		<table class="table table-info " style=" box-shadow: 10px 10px 5px grey;">
			<thead>
				<tr class="table-dark">

					<th scope="col">Id</th>
					<th scope="col">Role</th>


				</tr>
			</thead>
			<tbody>
				<c:forEach items="${role}" var="rl">
					<tr>

						<td>${rl.roleId}</td>
						<td>${rl.role}</td>


					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>