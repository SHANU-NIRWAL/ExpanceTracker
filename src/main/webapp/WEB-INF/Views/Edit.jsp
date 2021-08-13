<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
</head>
<body>

	<div class="container">
		<hr>


		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<header class="card-header">

						<h4 class="card-title mt-2">Edit User</h4>
					</header>
					<article class="card-body">
						<form action="../editUser" method="post" modelAttribute="user">
							<div class="form-row">
								<div class="col form-group">
									<label>First name </label> <input type="text" value="${user.firstName }"
										class="form-control" placeholder="" name="firstName">
								</div>
								<!-- form-group end.// -->
								<div class="col form-group">
									<label>Last name</label> <input type="text" value="${user.lastName }"
										class="form-control" placeholder=" "name="lastName">
								</div>
								<!-- form-group end.// -->
							</div>
							<!-- form-row end.// -->
							<div class="form-group">
								<label>Email address</label> <input type="email" value="${user.email }"
									class="form-control" placeholder="" name="email"> <small
									class="form-text text-muted">We'll never share your
									email with anyone else.</small>
							</div>
							<!-- form-group end.// -->
							<!-- form-group end.// -->
							<div class="form-row">
								<div class="form-group col-md-12">
									<label>Phone</label> <input type="number" class="form-control" value="${user.phone }" name="phone">
								</div>
								<!-- form-group end.// -->
							</div>
							<!-- form-group end.// -->
							<div class="form-group">
								<button type="submit" class="btn btn-primary btn-block">
									Submit</button>
							</div>
							<!-- form-group// -->
							
						</form>
					</article>
					<!-- card-body end .// -->
	
				</div>
				<!-- card.// -->
			</div>
			<!-- col.//-->

		</div>
		<!-- row.//-->


	</div>
	<!--container end.//-->

	<br>
	<br>

</body>
</html>