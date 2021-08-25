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

<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script src="https://code.jquery.com/jquery-3.3.1.js"
	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
	crossorigin="anonymous">
	<script>
	$(function() {
		$("#header").load("home.html");
		// $("#footer").load("footer.html"); 
	});
</script>
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
</style>
<title>Insert title here</title>
</head>
<body style="background-color: #d7d7d9">
	<%@ include file="part/Header.jsp"%>
	<div id="header">
		<h1 class="center"
			style="text-align: center; background-color: black; color: white">Add
			Account</h1>
	</div>
	<form class="row g-3 " action="accountinsert" method="post"
		modelAttribute="account">
		<div class="right  p-3 mb-5 bg-body rounded"
			style="box-shadow: 10px 10px 5px grey; border-style: outset; width: 700px;">
			<div class="col px-md-5">
				<div class="col-auto">
					<label for="account" class="form-label">Account Name:</label> <input
						type="text" class="form-control" id="accountS" name="accountName">
				</div>

				<div class="col-auto">
					<label for="inputPassword4" class="form-label">Balance</label> <input
						type="text" class="form-control" id="inputPassword4"
						name="balance">
				</div>
				<div class="row">
					<div class="col">
						<label for="inputState" class="form-label">Account Type<br>
							<select name="accountType" class="form-control">
								<c:forEach items="${AccountType}" var="accountType">
									<option value="${accountType.accountTypeId}"
										<c:if test="${accountType.accountTypeId eq selectedCatId}">selected="selected"</c:if>>${accountType.typeName}</option>
								</c:forEach>
						</select></label>
					</div>
					<div class="col">
						<label for="inputAddress" class="form-label">Time <input
							type="text" id="currentTime" class="form-control"> <script>
								var today = new Date();
								var time = today.getHours() + ":"
										+ today.getMinutes();

								document.getElementById("currentTime").value = time;
							</script></label>
					</div>
					<div class="col">
						<label for="inputAddress2" class="form-label"></label>Current
						Date: <input type="text" id="currentDate" class="form-control">
						<script>
							var today = new Date();
							var date = today.getFullYear() + '-'
									+ (today.getMonth() + 1) + '-'
									+ today.getDate();
							document.getElementById("currentDate").value = date;
						</script>
					</div>

				</div>

				<div class="d-grid gap-2 d-md-flex justify-content-md-center">
					<button type="submit" class="btn btn-warning"
						style="margin-bottom: 25px; margin-top: 20px;">
						Add Account<br>
					</button>

				</div>
			</div>
		</div>
	</form>
	<hr>
	<div style="margin: auto; width: 800px;">
		<table class="table table-info "
			style="box-shadow: 10px 10px 5px grey;">
			<thead>
				<tr class="table-dark">

					<th scope="col">Account Name</th>
					<th scope="col">Balance</th>
					<th scope="col">Account Type</th>
					<th scope="col">Created At</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${useraccountdetails}" var="useraccountdetail">
					<tr>
						
						<td>${useraccountdetail.accountName}</td>
						<td>${useraccountdetail.balance}</td>
						<td>${useraccountdetail.accountTypename }</td>
						<td>${useraccountdetail.createdAt}</td>
						<td><a href="editAccount/${useraccountdetail.accountId}"><i
								class="material-icons">&#xe22b;</i></a>&nbsp; <a
							href="deleteAccount/${useraccountdetail.accountId}"><i
								class="material-icons" style="color: red">&#xe872;</i></a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>