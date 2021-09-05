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
<title>Expense Details</title>
<script src="https://code.jquery.com/jquery-3.6.0.slim.js"
	integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY="
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
<script src='//cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js'></script>

<link
	href='//cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css'
	rel='stylesheet'
	integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC'
	crossorigin='anonymous'>
<script>
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0");
	$(document).ready(function() {
		$('#myTable').DataTable()
	})
</script>
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css' />
</head>
<body style="background-color: #d7d7d9">
<%
response.setHeader("Cache-Control", "no-cache,no-store,must-revolidate");
response.setHeader("Progma", "no-cache");
response.setHeader("Expires", "0");
%>

	<c:choose>
		<c:when test="${role==1}">
			<%@ include file="part/AdminHeader.jsp"%>
			<input type="hidden" value=${userId } name="userId">
		</c:when>
		<c:otherwise>
			<%@ include file="part/Header.jsp"%>
		</c:otherwise>
	</c:choose>


	<h1 style="text-align: center; background-color: black; color: white">Total

		Expense</h1>
	<div class="row">
		<div class="col order-first " style="margin-left:300px"><h6 style="margin-left:20px">
			Year</h6> <input type="text" name="datep" id="annee" class="form-control"
				style="width: 200px; margin-left: 10px"><br><h6 style="margin-left:20px"> Month</h6> <input
				type="text" name="datep" id="mois" class="form-control"
				style="width: 200px; margin-left: 10px"> <br>
			<button class=" btn-warning btn btn-theme" type="submit" 
				style="margin-left: 1px; box-shadow: rgba(0, 0, 0, 0.3) 0px 19px 38px, rgba(0, 0, 0, 0.22) 0px 15px 12px; margin-left: 10px">View</button>

		</div>

		<div class="col" style="width: 500px;"></div>

		<div class="col order-last" style="left-margin: 700px;">
			<form action="downloadpdfinbetweendate" method="post"
				style="margin-right: 50px;">
				<label for="date" class="col"><h6>Enter start date:-</h6></label> <input
					type="date" id="date" name="startdate" class="form-control"
					style="width: 150px"> <br><h6 >Enter End date:- </h6><input
					type="date" name="enddate" class="form-control"
					style="margin-right: 50px; width: 150px""><br> <input
					type="submit" value="View" class="btn btn-warning"
					style="margin-left: 1px; box-shadow: rgba(0, 0, 0, 0.3) 0px 19px 38px, rgba(0, 0, 0, 0.22) 0px 15px 12px;" />
			
			<c:choose>
		<c:when test="${role==1}">
			<a href="${userId }">reset</a><br><br>
		</c:when> 
		<c:otherwise>
		<a href="expenseDetails">reset</a><br><br>
		</c:otherwise>
	</c:choose>
			</form>

			
		</div>
	</div>
	</div>

	<div style="margin: auto; width: 800px;" id="tab">
		<table class="table table-info " id="myTable"
			style="box-shadow: 10px 10px 5px grey;">
			<thead>
				<tr class="table-dark">
					<th scope="col">#</th>
					<th scope="col">Payee Name</th>
					<th scope="col">Category</th>
					<th scope="col">Sub Category</th>
					<th scope="col">Amount Debit</th>
					<th scope="col">Account</th>
					<th scope="col">Time</th>
					<th scope="col">Date</th>
					<th scope="col">Description</th>
					<th scope="col">Action</th>
					<!-- <th scope="col">Documents</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${expenseDetails}" var="expensedetails">
					<tr>
						<td>${expensedetails.expenseId}</td>
						<td>${expensedetails.payeeName}</td>
						<td>${expensedetails.categorydatalist}</td>
						<td>${expensedetails.subcategorydatalist}</td>
						<td>${expensedetails.ammount}</td>
						<td>${expensedetails. accountTypebean.name}</td>
						<td>${expensedetails.timeexp}</td>
						<td>${expensedetails.dateexp}</td>
						<td>${expensedetails.description}</td>
						<%-- <td><form action="viewImage" method="post">
								<input type="hidden" value="${expensedetails.image }"
									name="imageurl" /> <input type="submit" value="view" />
							</form></td> --%>
						<td><a href="editExpence/${expensedetails.expenseId}"><i
								class="material-icons">&#xe22b;</i></a>&nbsp; <a
							href="deleteExpence/${expensedetails.expenseId}"><i
								class="material-icons" style="color: red">&#xe872;</i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="button" value="Create PDF" id="btPrint"
			onclick="createPDF()" />
	</div>


	<script>
		$(document).ready(function() {
			var table = $('#myTable').DataTable();
			let now = new Date();
			let year = now.getFullYear();
			let month = ("0" + (now.getMonth() + 1)).slice(-2);
			$('#annee').val(year);
			$('#mois').val(month);

			$(document).on("click", ".btn-theme", function() {
				var year = $('#annee').val();
				var month = $('#mois').val();
				var serachVal = year + "-" + month;
				table.column(7).search(serachVal).draw();
			});
		});

		function createPDF() {
			var sTable = document.getElementById('tab').innerHTML;

			var style = "<style>";
			style = style + "table {width: 100%;font: 17px Calibri;}";
			style = style
					+ "table, th, td {border: solid 1px #DDD; border-collapse: collapse;";
			style = style + "padding: 2px 3px;text-align: center;}";
			style = style + "</style>";

			// CREATE A WINDOW OBJECT.
			var win = window.open('', '', 'height=700,width=700');

			win.document.write('<html><head>');
			win.document.write('<title>Profile</title>'); // <title> FOR PDF HEADER.
			win.document.write(style); // ADD STYLE INSIDE THE HEAD TAG.
			win.document.write('</head>');
			win.document.write('<body>');
			win.document.write(sTable); // THE TABLE CONTENTS INSIDE THE BODY TAG.
			win.document.write('</body></html>');

			win.document.close(); // CLOSE THE CURRENT WINDOW.

			win.print(); // PRINT THE CONTENTS.
		}
	</script>
</body>
</html>