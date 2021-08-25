<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.card {
	margin: auto;
	flex: display;
	width: 250px;
	height: 250px;
	background-color: yellow;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body style="background-color: #d7d7d9">
	<%@ include file="part/Header.jsp"%>
	<div class="row"
		style="margin: auto; flex: display; position: fixed; top: 50%; left: 50%; // width: 30em; // height: 18em; margin-top: -9em; /*set to a negative number 1/2 of your height*/ margin-left: -35em; /*set to a negative number 1/2 of your width*/ // border: 1px solid #ccc; // background-color: #f3f3f3;">
		<div class="card"
			style="background-color: #c5edfc; box-shadow: 10px 10px 5px grey; border-style: outset;">
			<div class="card-header">Payee</div>
			<div class="card-body">
				<h5 class="card-title">Add/Delete/Edit Payee Details</h5>
				<p class="card-text">You Have All access To perform These Action</p>
				<a href="#" class="btn btn-danger" data-toggle="modal"
					data-target="#modalContactForm"
					style="box-shadow: 10px 10px 5px grey">Click me</a>
			</div>
		</div>
		<div class="card"
			style="margin-left: 20px; background-color: #fcddc5; box-shadow: 10px 10px 5px grey; border-style: outset;">
			<div class="card-header">Category</div>
			<div class="card-body">
				<h5 class="card-title">Add/Delete/Edit Category Details</h5>
				<p class="card-text">You Have All access To perform These Action</p>
				<a href="#" class="btn btn-warning"
					style="box-shadow: 10px 10px 5px grey" data-toggle="modal"
					data-target="#orangeModalSubscription">Click me</a>
			</div>
		</div>
		<div class="card"
			style="margin-left: 20px; background-color: #dafc90; box-shadow: 10px 10px 5px grey; border-style: outset;">
			<div class="card-header">Subcategory</div>
			<div class="card-body">
				<h5 class="card-title">Add/Delete/Edit Subcategory Details</h5>
				<p class="card-text">You Have All access To perform These Action</p>
				<a href="#" class="btn btn-primary"
					style="box-shadow: 10px 10px 5px grey" data-toggle="modal"
					data-target="#modalContactForm1">Click me</a>
			</div>
		</div>
	</div>


	<!--/////////////////Model payee////////////////////  -->
	<div class="modal fade" id="modalContactForm" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h4 class="modal-title w-100 font-weight-bold">Payee</h4>

				</div>
				<form action="addPayee" mathod="post" modelAttribute="payee">
					<div class="modal-body mx-3">
						<div class="md-form mb-5">
							<i class="fas fa-envelope prefix grey-text"></i>Payee <input
								type="Payee" id="payee" class="form-control validate"
								name="payeename"> <label data-error="wrong"
								data-success="right" for="defaultForm-email"></label>
						</div>

						<div class=" d-flex justify-content-center">
							<button class="btn btn-warning"
								style="box-shadow: 10px 10px 5px grey">Add Payee</button>
						</div>

					</div>
				</form>
				<table class="table table-info "
					style="box-shadow: 10px 10px 5px grey;">
					<thead>
						<tr class="table-dark">

							<th scope="col">Payee Id</th>
							<th scope="col">Payee Name</th>
							<th scope="col">Action</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pBean}" var="pb">
							<tr>

								<td>${pb.payeeId}</td>
								<td>${pb.payeename}</td>

								<td><a href="editPayee/${pb.payeeId}"><i
										class="material-icons">&#xe22b;</i></a>&nbsp; <a
									href="deletePayee/${pb.payeeId}"><i class="material-icons"
										style="color: red">&#xe872;</i></a></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
	</div>

	<!-- ///////////////////model category//////////////////////// -->
	<div class="modal fade" id="orangeModalSubscription" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-notify modal-warning" role="document">
			<!--Content-->
			<div class="modal-content">
				<!--Header-->
				<div class="modal-header text-center">
					<h4 class="modal-title white-text w-100 font-weight-bold py-2">Category</h4>

				</div>

				<!--Body-->
				<form action="addCat" method="post" modelAttribute="category">
					<div class="modal-body">
						<div class="md-form mb-5">
							<label for="exampleDataList"
								class="form-label form-label-margin-bottom"><h5>Select
									Payee</h5></label> <input action="test2" class="form-control"
								list="datalistOptions" id="exampleDataList"
								placeholder="Type to search..." name="payeeName">

							<datalist id="datalistOptions">
								<c:forEach items="${pBean}" var="payedata">
									<option value="${payedata.payeename}"></option>
								</c:forEach>

							</datalist>
						</div>

						<div class="md-form">
							<i class="fas fa-envelope prefix grey-text"></i> <input
								type="text" id="form2" class="form-control " name="catName">
							ADD Category </label>
						</div>
						<div class="modal-footer justify-content-center">
							<Button class="btn btn-warning waves-effect"
								style="box-shadow: 10px 10px 5px grey">
								Add <i class="fas fa-paper-plane-o ml-1"></i>
							</Button>
						</div>
					</div>
				</form>
				<!--Footer-->

				<table class="table table-info "
					style="box-shadow: 10px 10px 5px grey;">
					<thead>
						<tr class="table-dark">

							<th scope="col">Category Name</th>
							<th scope="col">Payee Name</th>
							<th scope="col">Action</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cBean}" var="cb">
							<tr>
								
								<td>${cb.catName}</td>
								<td>${cb.payeeName}</td>

								<td><a href="editCat/${cb.catId}"><i
										class="material-icons">&#xe22b;</i></a>&nbsp; <a
									href="deleteCat/${cb.catId}"><i class="material-icons"
										style="color: red">&#xe872;</i></a></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
			<!--/.Content-->
		</div>
	</div>

	<!--/////////////////////////////////model sub category//////////////////////////////////  -->
	<div class="modal fade" id="modalContactForm1" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h4 class="modal-title w-100 font-weight-bold">Sub Category</h4>

				</div>
				<form name="addSubCat" action="addSub" method="post"
					modelAttribute="category">
					>
					<div class="modal-body mx-3">
						<div class="md-form mb-5">
							<label for="exampleDataList"
								class="form-label form-label-margin-bottom"><h5>Enter
									Payee</h5></label> <input action="test2" class="form-control"
								list="datalistOptions" id="exampleDataList"
								placeholder="Type to search..." name="payeeName"
								onchange='onInputPayee()'>

							<datalist id="datalistOptions">
								<c:forEach items="${pBean}" var="payedata">
									<option value="${payedata.payeename}"></option>
								</c:forEach>

							</datalist>
						</div>

						<div class="md-form mb-5">
							<label for="Category" class="form-label"><h5>Select
									Category</h5></label><input type="text" class="form-control" name="catName"
								list="categorydatalist" onchange='onInput(this)'
								id="categoryinput">
							<datalist id="categorydatalist">
								<!--<c:forEach items="${category}" var="catdata">
					<option value="${catdata.catName}"></option>
				</c:forEach>-->
							</datalist>
						</div>

						<div class="md-form mb-5">
							<i class="fas fa-tag prefix grey-text"></i> <input type="text"
								id="form32" class="form-control validate" name="subCatName">
							<label data-error="wrong" data-success="right" for="form32">Sub
								Category</label>
						</div>

						<div class="modal-footer d-flex justify-content-center">
							<button class="btn btn-warning"
								style="box-shadow: 10px 10px 5px grey">
								Add Subcategory <i class="fas fa-paper-plane-o ml-1"></i>
							</button>
						</div>

					</div>
				</form>




				<table class="table table-info "
					style="box-shadow: 10px 10px 5px grey;">
					<thead>
						<tr class="table-dark">

							<th scope="col">Sub Category Name</th>
							<th scope="col">Category Name</th>
							<th scope="col">Payee Name</th>
							<th scope="col">Action</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sBean}" var="sb">
							<tr>
							
								<td>${sb.subCatName}</td>
								<td>${sb.catName}</td>
								<td>${sb.payeeName}</td>

								<td><a href="editSubCat/${sb.id}"><i
										class="material-icons">&#xe22b;</i></a>&nbsp; <a
									href="deleteSubCat/${sb.id}"><i
										class="material-icons" style="color: red">&#xe872;</i></a></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>


			</div>
		</div>
	</div>
	<!-- /////////////////js ajax function/////////////////////////////////////////// -->

	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript">
		function onInputPayee() {
			var val = document.addSubCat.payeeName.value;
			var xhttp = new XMLHttpRequest();
			console.log(val);
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					console.log(xhttp.response);
					myFunction(this);
				}
			};
			xhttp.open("GET", "/ExpanseTracker/payeecategory/" + val, true);
			xhttp.send();

		}
		function myFunction(xml) {
			var x, i, xmlDoc, txt;
			var list = [];
			xmlDoc = xml.responseXML;
			console.log("xmlDoc");
			console.log(xmlDoc);
			txt = "";
			x = xmlDoc.getElementsByTagName("catName");
			console.log(x.length)
			for (i = 0; i < x.length; i++) {
				/// txt += x[i].childNodes[0].nodeValue + "<br>";
				console.log("test");
				console.log(x[i].childNodes[0].nodeValue);
				list.push(x[i].childNodes[0].nodeValue);
			}
			console.log(list);
			var list2 = document.getElementById('categorydatalist');

			list.forEach(function(item) {
				var option = document.createElement('option');
				option.value = item;
				list2.appendChild(option);
			});
		}
	</script>

</body>
</html>