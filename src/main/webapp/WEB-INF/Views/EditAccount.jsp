<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color: #d7d7d9">
	<%@ include file="part/Header.jsp"%>
	<div id="header">
		<h1 class="center"
			style="text-align: center; background-color: black; color: white">Edit
			Account</h1>
	</div>
	<form class="row g-3 " action="../updateuser"  method="post" >
		<div class="right  p-3 mb-5 bg-body rounded"
			style="box-shadow: 10px 10px 5px grey; border-style: outset; width: 700px; background-color:white;margin:auto;
			">
			<div class="col px-md-5">
				<div class="col-auto">
					<label for="account" class="form-label">Account Name:</label> <input
						type="text" class="form-control" id="accountS" name="accountName" value="${actbn.accountName}">
				</div>

				<div class="col-auto">
					<label for="inputPassword4" class="form-label">Balance</label> <input
						type="text" class="form-control" id="inputPassword4"
						name="balance" value="${actbn.balance}">
				</div>&nbsp;
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

				<div class="col-auto">
					<label for="id" class="form-label"></label> <input
						type="hidden" class="form-control" name="accountId" value="${actbn.accountId}"
						>
				</div>
				</div>

				<div class="d-grid gap-2 d-md-flex justify-content-md-center">
					<button type="submit" class="btn btn-warning"
						style="margin-bottom: 25px; margin-top: 20px;">
						Update Account<br>
					</button>

				</div>
			</div>
		</div>
	</form>

</body>
</html>