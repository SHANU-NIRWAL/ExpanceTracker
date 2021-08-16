<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"  isELIgnored="false"%>
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
<body>
	<form class="row g-5">
		<h1 class="center">Add Account</h1>
		<div class="right shadow-lg p-3 mb-5 bg-body rounded parent row-g-3 "
			style="align-content: center;">


			<div class="col-auto child">
				<label for="account" class="form-label"><h5>ADD
						Account Type:</h5></label> <input type="text" class="form-control" id="accountS"
					name="AccountType"> <br>
				<div class="d-grid gap-2 d-md-flex justify-content-md-center">
					<button type="button" class="btn btn-warning">Warning</button>
				</div>
			</div>

		</div>

	</form>
	<hr>
<table class="table">
  <thead>
    <tr>
  
      <th scope="col">Id</th>
      <th scope="col">Account Type</th>
     
     
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${list}" var="list">
    <tr>
   
      <td>${list.accountTypeId}</td>
      <td>${list.typeName}</td>
     
    
    </tr>
</c:forEach>
  </tbody>
</table>
</body>
</html>