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
			Payee</h1>
	</div>
	<form class="row g-5" action="../payeeUpdate" method="post"
		modelAttribute="payee" style="">

		<div class="right  p-3 mb-5 bg-body rounded parent row-g-3 "
			style="align-content: center; margin-top: 50px; box-shadow: 10px 10px 5px grey; width: 500px; height: 200px; background-color: white; margin: auto; flex: display; margin-top: 10%;">


			<div class="col-auto child">
				<label for="account" class="form-label"><h5>Edit payee
						Type:</h5></label> <input type="text" class="form-control" id="payeename"
					name="payeename" value="${pbn.payeename}"
					style="box-shadow: rgb(204, 219, 232) 3px 3px 6px 0px inset, rgba(255, 255, 255, 0.5) -3px -3px 6px 1px inset;">
				<br>
				<input type="hidden" name="payeeId" value="${pbn.payeeId}">
				
				<div class="d-grid gap-2 d-md-flex justify-content-md-center">
					<input type="submit" value="Edit Payee"
						class="btn btn-warning"
						style="box-shadow: rgba(0, 0, 0, 0.3) 0px 19px 38px, rgba(0, 0, 0, 0.22) 0px 15px 12px;" />
				</div>
				
			</div>

		</div>

	</form>

</body>
</html>