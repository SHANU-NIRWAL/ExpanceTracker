<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.bg {
	background-color: white;
	border-style: outset;
	box-shadow: 10px 10px 5px grey;
	width: 600px;
	height: 300;
	margin: auto;
	margin-top: 50px;
}

.mycol {
	margin-left: 150px;
	margin-right: auto;
	display: block;
	margin-top: 15px;
}

.btn {
	border-radius: 4px;
}
</style>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
</head>
<body style="background-color: #d7d7d9">

	<%@ include file="part/Header.jsp"%>
	<h1 style="text-align: center; background-color: black; color: white">View
		Expence Report</h1>
	<div class="bg">
		<div class="mycol">
			<form action="downloadPdfbymonth" method="post" class="form-horizontal">
				<select name="monthName" id="monthName"
					class="form-select form-select-lg mb-3" aria-label=".form-select-lg example">
					<option selected>Select Month</option>
					<option value="01">January</option>
					<option value="02">February</option>
					<option value="03">March</option>
					<option value="04">April</option>
					<option value="05">May</option>
					<option value="06">June</option>
					<option value="07">July</option>
					<option value="08">August</option>
					<option value="09">September</option>
					<option value="10">October</option>
					<option value="11">November</option>
					<option value="12">December</option>
				</select>
				<input type="submit" value="Generate Monthly PDF"
					class="btn btn-warning"
					style="box-shadow: rgba(0, 0, 0, 0.3) 0px 19px 38px, rgba(0, 0, 0, 0.22) 0px 15px 12px;" />
			</form>
		</div>
		<hr>
		<div class="mycol">
			<form action="downloadPdfyerly" method="post">
				<input type=number name="year" placeholder="YEAR"
					 class="form-control"><br>
				 <input type="submit" value="Generate Yearly PDF"
					class="btn btn-warning"
					style="box-shadow: rgba(0, 0, 0, 0.3) 0px 19px 38px, rgba(0, 0, 0, 0.22) 0px 15px 12px;" />
			</form>
		</div>
		<hr>
		<div class="mycol">
			<form action="downloadpdfinbetweendate" method="post" style="margin-right:50px;">
				<label for="date" class="col-sm-6 control-label">Enter start date:-</label> <input type="date" id="date"
					name="startdate" class="form-control"
					>
				<br>Enter End date:- <input type="date" name="enddate"
					 class="form-control" style="margin-right: 50px; box-shadow: rgba(0, 0, 0, 0.17) 0px -23px 25px 0px inset, rgba(0, 0, 0, 0.15) 0px -36px 30px 0px inset, rgba(0, 0, 0, 0.1) 0px -79px 40px 0px inset, rgba(0, 0, 0, 0.06) 0px 2px 1px, rgba(0, 0, 0, 0.09) 0px 4px 2px, rgba(0, 0, 0, 0.09) 0px 8px 4px, rgba(0, 0, 0, 0.09) 0px 16px 8px, rgba(0, 0, 0, 0.09) 0px 32px 16px;"><br>
				
				<input type="submit" value="Generate PDF" class="btn btn-warning"
					style="margin-left: 1px; box-shadow: rgba(0, 0, 0, 0.3) 0px 19px 38px, rgba(0, 0, 0, 0.22) 0px 15px 12px;" />
			</form>
		</div>
		<hr>
		<div class="mycol">
			<a href="downloadPDF" style="margin-left: 50px;">Generate All
				expense Report</a>
		</div>
	</div>
</body>
</html>