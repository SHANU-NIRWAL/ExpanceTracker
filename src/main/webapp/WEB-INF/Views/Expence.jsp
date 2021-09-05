<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
h1 {
	text-align: center;
}

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
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript">
    $(window).on('load',function(){
        $('#myModal').modal('show');
    });
</script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color:#d7d7d9">
<%
response.setHeader("Cache-Control", "no-cache,no-store,must-revolidate");
response.setHeader("Progma", "no-cache");
response.setHeader("Expires", "0");
%>

<c:if test="${accountSize==0 }">
<script>
window.alert("First add account");
window.location.href="/ExpanseTracker/account";

</script>

</c:if>

	<%@ include file="part/Header.jsp"%>

	
	<h1 style="text-align: center;background-color: black;color:white">Add Your Expense</h1>
	
	<br>
		<!-- <h1 style= "background-color: black;color:white">hello ---${accountSize==0}</h1>
		<br> -->
	<form class="row g-3 right mx-auto  p-3 mb-5 bg-body rounded"
		action="test" method="post" modelAttribute="expense"
		enctype="multipart/form-data" style=" box-shadow: 10px 10px 5px grey;border-style: outset;width:700px;height:700px;">

		<div class="col-6">
			<label for="exampleDataList"
				class="form-label form-label-margin-bottom"><h5>Enter
					Payee</h5></label> <input action="test2" class="form-control"
				list="datalistOptions" id="exampleDataList"
				placeholder="Type to search..." name="payeeName" onchange='onInputPayee()'>

			<datalist id="datalistOptions">
				<c:forEach items="${payeedata}" var="payedata">
					<option value="${payedata.payeename}"></option>
				</c:forEach>

			</datalist>
		</div>
		<div class="col-6 col-md-4">
			<label for="exampleInputEmail1" class="form-label"><h5>
					Ammount</h5></label> <input type="number" class="form-control" name="ammount">
			<span><h5>${error}</h5></span>
		</div>
		</div>
		<div class="row">
		<div class="col-6">
		<label for="Category" class="form-label" ><h5>Category</h5></label><input 
		type="text"class="form-control" name="categorydatalist" list="categorydatalist" onchange='onInput(this)' id="categoryinput" >
		<datalist id="categorydatalist">
				<!--<c:forEach items="${category}" var="catdata">
					<option value="${catdata.catName}"></option>
				</c:forEach>-->
			</datalist>
		
		</div>
		
		<div class="col-4">
		<label for="subCategory" class="form-label" ><h5>SubCategory</h5></label><input 
		type="text"class="form-control" name="subcategorydatalist" list="subcategorydatalist" id="subcategoryinput" >
		<datalist id="subcategorydatalist">
				<!--<c:forEach items="${category}" var="catdata">
					<option value="${catdata.catName}"></option>
				</c:forEach>-->
			</datalist>
		</div>
		</div>
		<div class="row g-3">
			<div class="col">
				<label for="inputAddress" class="form-label"><h5>Time</h5> <input
					type="time" id="currentTime" class="form-control" name="timeexp">
					<script>
						var today = new Date();
						var time = today.getHours() + ":" + today.getMinutes();
					
						document.getElementById("currentTime").value = time;
					</script></label>
			</div>
			<div class="col">
				<label for="inputAddress2" class="form-label"></label>
				<h5>Current Date:</h5>
				<input type="date" id="currentDate" class="form-control"
					name="dateexp">
				<script>
					document.getElementById('currentDate').value = new Date()
							.toISOString().substring(0, 10);
				</script>
			</div>
		</div>

		<div class="row">
			<div class="col-auto">
				<h5>Payment Method:</h5>
				<select class="form-select glow" aria-label="Default select Account"
					name="useraccountID">
					<c:forEach items="${useraccount}" var="useraccount">
						<option value="${useraccount.accountId}"
							<c:if test="${useraccount.accountId eq selectedCatId}">selected="selected"</c:if>>${useraccount.accountName}</option>
					</c:forEach>
				</select>



			</div>
		</div>
		<div class="mb-3">
			<label for="exampleFormControlTextarea1" class="form-label">Description</label>
			<textarea class="form-control" id="exampleFormControlTextarea1"
				rows="3" name="description"></textarea>
		</div>
		<div class="mb-3">
			<label for="formFileMultiple" class="form-label">Multiple
				files input example</label> <input class="form-control" type="file"
				id="formFileMultiple" name="expimage">
		</div>
		<div class="d-grid gap-2 d-md-flex justify-content-md-center">
			<button type="submit" class="btn btn-warning "
				style="margin-bottom: 25px; margin-top: 20px;">
				Add Expence<br>
			</button>

		</div>
	</form>
	
	
	<script>
	function onInputPayee()
	{
		var val=document.getElementById("exampleDataList").value;
		var xhttp = new XMLHttpRequest();
		console.log(val);
	     xhttp.onreadystatechange = function() {
	          if (this.readyState == 4 && this.status == 200) {
	        	  console.log(xhttp.response);
	        	  myFunction(this);
	          }
	      };
	      xhttp.open("GET", "/ExpanseTracker/payeecategory/"+val, true);
	      xhttp.send();
	      
	}
	
	
	function onInput(e) {
	    var val = document.getElementById("categoryinput").value;
	    var opts = document.getElementById('categorydatalist').childNodes;
	      console.log(val);
	      var xhttp = new XMLHttpRequest();
	      xhttp.onreadystatechange = function() {
	          if (this.readyState == 4 && this.status == 200) {
	        	  console.log(xhttp.response);
	        	  myFunctionsub(this);
	          }
	      };
	      xhttp.open("GET", "/ExpanseTracker/payeesubcategory/"+val, true);
	      xhttp.send();
	  }
	
	function myFunction(xml) {
		  var x, i, xmlDoc, txt;
		  var list=[];
		  xmlDoc = xml.responseXML;
		  console.log("xmlDoc");
		  console.log(xmlDoc);
		  txt = "";
		  x = xmlDoc.getElementsByTagName("catName");
		  console.log(x.length)
		  for (i = 0; i< x.length; i++) {
		   /// txt += x[i].childNodes[0].nodeValue + "<br>";
		   console.log("test");
		    console.log(x[i].childNodes[0].nodeValue);
		    list.push(x[i].childNodes[0].nodeValue);
		  }
		  console.log(list);
		  var list2 = document.getElementById('categorydatalist');
			
		  list.forEach(function(item){
			  var option = document.createElement('option');
			     option.value = item;
			     list2.appendChild(option);
		  });
		}
	
	function myFunctionsub(xml) {
		  var x, i, xmlDoc, txt;
		  var list=[];
		  xmlDoc = xml.responseXML;
		  console.log(xmlDoc);
		  txt = "";
		  x = xmlDoc.getElementsByTagName("item");
		  console.log(x.length)
		  for (i = 0; i< x.length; i++) {
		    txt += x[i].childNodes[0].nodeValue + "<br>";
		    console.log(x[i].childNodes[0].nodeValue);
		    list.push(x[i].childNodes[0].nodeValue);
		  }
		  console.log(list);
		  var list2 = document.getElementById('subcategorydatalist');
			
		  list.forEach(function(item){
			  var option = document.createElement('option');
			     option.value = item;
			     list2.appendChild(option);
		  });
		 
		}
	
	</script>
</body>
</html>