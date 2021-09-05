<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
 

 
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

window.onload = function() { 
 
var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,
	exportEnabled: true,
	title: {
		text: "Statistical representation of expense "
	},
	axisY:{
		includeZero: true
	},
	data: [{
		type: "column", //change type to bar, line, area, pie, etc
		//indexLabel: "{y}", //Shows y value on all Data Points
		
		indexLabelFontColor: "#5A5757",
		indexLabelPlacement: "outside",
		dataPoints: ${chartreport}
	}]
});
chart.render();
 
}


</script>
</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache,no-store,must-revolidate");
response.setHeader("Progma", "no-cache");
response.setHeader("Expires", "0");
%>
<%@ include file="part/Header.jsp"%>
<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>