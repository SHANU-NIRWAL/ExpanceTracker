<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<!--Made with love by Mutiullah Samim -->

<!--Bootsrap 4 CDN-->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<!--Fontawesome CDN-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!--Custom styles-->
<link rel="stylesheet" type="text/css" href="styles.css">
<style type="text/css">
html {
	height: 100%;
}

body {
	height: 100%;
	margin: 0;
	background-repeat: no-repeat;
	background-attachment: fixed;
}

/* Text Align */
.text-c {
	text-align: center;
}

.text-l {
	text-align: left;
}

.text-r {
	text-align: right
}

.text-j {
	text-align: justify;
}

/* Text Color */
.text-whitesmoke {
	color: whitesmoke
}

.text-darkyellow {
	color: rgba(255, 235, 59, 0.432)
}

/* Lines */
.line-b {
	border-bottom: 1px solid #FFEB3B !important;
}

/* Buttons */
.button {
	border-radius: 3px;
}

.form-button {
	background-color: rgba(255, 235, 59, 0.24);
	border-color: rgba(255, 235, 59, 0.24);
	color: #e6e6e6;
}

.form-button:hover, .form-button:focus, .form-button:active,
	.form-button.active, .form-button:active:focus, .form-button:active:hover,
	.form-button.active:hover, .form-button.active:focus {
	background-color: rgba(255, 235, 59, 0.473);
	border-color: rgba(255, 235, 59, 0.473);
	color: #e6e6e6;
}

.button-l {
	width: 100% !important;
}

/* Margins g(global) - l(left) - r(right) - t(top) - b(bottom) */
.margin-g {
	margin: 15px;
}

.margin-g-sm {
	margin: 10px;
}

.margin-g-md {
	margin: 20px;
}

.margin-g-lg {
	margin: 30px;
}

.margin-l {
	margin-left: 15px;
}

.margin-l-sm {
	margin-left: 10px;
}

.margin-l-md {
	margin-left: 20px;
}

.margin-l-lg {
	margin-left: 30px;
}

.margin-r {
	margin-right: 15px;
}

.margin-r-sm {
	margin-right: 10px;
}

.margin-r-md {
	margin-right: 20px;
}

.margin-r-lg {
	margin-right: 30px;
}

.margin-t {
	margin-top: 15px;
}

.margin-t-sm {
	margin-top: 10px;
}

.margin-t-md {
	margin-top: 20px;
}

.margin-t-lg {
	margin-top: 30px;
}

.margin-b {
	margin-bottom: 15px;
}

.margin-b-sm {
	margin-bottom: 10px;
}

.margin-b-md {
	margin-bottom: 20px;
}

.margin-b-lg {
	margin-bottom: 30px;
}

/* Bootstrap Form Control Extension */
.form-control, .border-line {
	background-color: #5f5f5f;
	background-image: none;
	border: 1px solid #424242;
	border-radius: 1px;
	color: inherit;
	display: block;
	padding: 6px 12px;
	transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s
		ease-in-out 0s;
	width: 100%;
}

.form-control:focus, .border-line:focus {
	border-color: #FFEB3B;
	background-color: #616161;
	color: #e6e6e6;
}

.form-control, .form-control:focus {
	box-shadow: none;
}

.form-control::-webkit-input-placeholder {
	color: #BDBDBD;
}

/* Container */
.container-content {
	background-color: #3a3a3aa2;
	color: inherit;
	padding: 15px 20px 20px 20px;
	border-color: #FFEB3B;
	border-image: none;
	border-style: solid solid none;
	border-width: 1px 0;
}

/* Backgrounds */
.main-bg {
	background: #424242;
	/*background: linear-gradient(#424242, #212121);*/
	background-image:url("https://c4.wallpaperflare.com/wallpaper/444/479/808/flowers-yellow-flowers-black-background-digital-art-wallpaper-preview.jpg");
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
}

/* Login & Register Pages*/
.login-container {
	max-width: 400px;
	z-index: 100;
	margin: 0 auto;
	padding-top: 100px;
}

.login.login-container {
	width: 300px;
}

.wrapper.login-container {
	margin-top: 140px;
}

.logo-badge {
	color: #e6e6e6;
	font-size: 80px;
	font-weight: 800;
	letter-spacing: -10px;
	margin-bottom: 0;
}
</style>
</head>
<body class="main-bg">

<c:if test="${stat==1}">
	<script>
window.alert("login failed");
</script>

	</c:if>

	<div class="login-container text-c animated flipInX">
		<div>
			<h1 class="logo-badge text-whitesmoke">
				<span class="fa fa-user-circle"></span>
			</h1>
		</div>

		<p class="text-whitesmoke">Forgot Password</p>
		<div class="container-content">
			<form class="margin-t" action="sendOtp" method="get">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="email"
						name="email" required="">
				</div>

				<button type="submit" class="form-button button-l margin-b"
					data-toggle="modal" data-target="#myModal">Send Otp</button>


			</form>

		</div>
	</div>
	
		
</body>
</html>



