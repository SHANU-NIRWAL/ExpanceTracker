<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
<style>
body {
	background: #424242;
	/*background: linear-gradient(#424242, #212121);*/
	background-image:
		url("https://c4.wallpaperflare.com/wallpaper/444/479/808/flowers-yellow-flowers-black-background-digital-art-wallpaper-preview.jpg");
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
}
</style>
<body>

	<div class="container">
		<hr>


		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<header class="card-header">

						<h4 class="card-title mt-2">Sign up</h4>
					</header>
					<article class="card-body">
						<form action="saveuser" method="post" modelAttribute="user" name="reg"
							class="needs-validation" novalidate>
							<div class="form-row">
								<div class="col form-group">
									<label>First name </label> <input type="text"
										class="form-control" placeholder="" name="firstName"
										required="">
									<div class="valid-feedback">Looks good!</div>
								</div>
								<!-- form-group end.// -->
								<div class="col form-group">
									<label>Last name</label> <input type="text"
										class="form-control" placeholder=" " name="lastName"
										required="">
									<div class="valid-feedback">Looks good!</div>
								</div>
								<!-- form-group end.// -->
							</div>
							<!-- form-row end.// -->
							<div class="form-group">
								<label>Email address</label> <input type="email"
									class="form-control" placeholder="" name="email" id="email"
									aria-describedby="inputGroupPrepend" required> <small
									class="form-text text-muted">We'll never share your
									email with anyone else.</small>
								<div class="invalid-feedback">Please choose a username.</div>
							</div>
							<span id="emailError" style="color:red"></span>
							<!-- form-group end.// -->
							<!-- form-group end.// -->
							<div class="form-row">
								<div class="form-group col-md-12">
									<label>Phone</label> <input type="text" class="form-control"
										name="phone" pattern=".{10}"required="">
								
								</div>
								<!-- form-group end.// -->
							</div>
							<div class="form-group">
								<label>Create password</label> <input class="form-control"
									type="password" name="password"required="">
							</div>
							<!-- form-group end.// -->
							<div class="form-group">
								<button type="submit" class="btn btn-primary btn-block">
									Register</button>
							</div>
							<!-- form-group// -->
							<small class="text-muted">By clicking the 'Sign Up'
								button, you confirm that you accept our <br> Terms of use
								and Privacy Policy.
							</small>
						</form>
					</article>
					<!-- card-body end .// -->
					<div class="border-top card-body text-center">
						Have an account? <a href="login">Log In</a>
					</div>
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
	<script>
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict';
			window.addEventListener('load',
					function() {
						// Fetch all the forms we want to apply custom Bootstrap validation styles to
						var forms = document
								.getElementsByClassName('needs-validation');
						// Loop over them and prevent submission
						var validation = Array.prototype.filter.call(forms,
								function(form) {
									form.addEventListener('submit', function(
											event) {
										if (form.checkValidity() === false) {
											event.preventDefault();
											event.stopPropagation();
										}
										form.classList.add('was-validated');
									}, false);
								});
					}, false);
		})();
	</script>
	<script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>
		<script type="text/javascript">
		
		function checkEmail() {
			let email = document.reg.email.value;
			console.log(email);
			$.get("checkemail/" + email, function(data) {
				console.log(data);
				
				
				
				
				if (data) {
				 	$("#emailError").text("*Email Already In Use");
				}else{
					$("#emailError").text("");
				}
			}).fail(function(err) {
				console.log("error"+err);
			});
		}
		
		$(document).ready(function() {
			$("#email").blur(function() {
				checkEmail();
			})
		})
		</script>
</body>
</html>