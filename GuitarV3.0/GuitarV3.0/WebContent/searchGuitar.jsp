<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GuitarSearch</title>
<link rel="stylesheet" type="text/css" href="CSS/easyui.css">
<link rel="stylesheet" type="text/css" href="CSS/icon.css">
<link rel="stylesheet" type="text/css" href="CSS/demo.css">
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery.easyui.min.js"></script>
</head>
<body>
	<h2>GuitarSearch</h2>
	<div id="tb" style="padding:3px;width:1000px;">
	 	<form action="GuitarPost" method="post" id="guitar">
	 		<label>Builder:</label>
	 		<select class="easyui-combobox" name="builder">
	 			<option value=""></option>
	 			<option value="FENDER">Fender</option>
	 			<option value="MARTIN">Martin</option>
	 			<option value="GIBSON">Gibson</option>
	 			<option value="COLLINGS">Collings</option>
	 			<option value="OLSON">Olson</option>
	 			<option value="RYAN">Ryan</option>
	 			<option value="PRS">Prs</option>
	 		</select>
	 		
	 		<label>Model:</label>
	 		<select class="easyui-combobox" name="model">
	 			<option value=""></option>
	 			<option value="03">03</option>
	 			<option value="04">04</option>
	 			<option value="05">05</option>
	 			<option value="06">06</option>
	 			<option value="08">08</option>
	 			<option value="11">11</option>
	 		</select>
	 		
	 		<label>Type:</label>
	 		<select class="easyui-combobox" name="type">
	 			<option value=""></option>
	 			<option value="ACOUSTIC">Acoustic</option>
	 			<option value="ELECTRIC">Electric</option>
	 		</select>
	 			 		
	 		<label>BackWood:</label>
	 		<select class="easyui-combobox" name="backwood">
	 			<option value=""></option>
	 			<option value="INDIAN_ROSEWOOD">Indian_Rosewood</option>
				<option value="BRAZILIAN_ROSEWOOD">Brazilian_Rosewood</option>
				<option value="MAHOGANY">Mahogany</option>
				<option value="MAPLE">Maple</option>
				<option value="COCOBOLO">Cocobolo</option>
				<option value="CEDAR">Cedar</option>
				<option value="ADIRONDACK">Adirondack</option>
				<option value="ALDER">Alder</option>
				<option value="SITKA">Sitka</option>
	 		</select>
	 		
	 		<label>TopWood:</label>
	 		<select class="easyui-combobox" name="topwood">
	 			<option value=""></option>
	 			<option value="INDIAN_ROSEWOOD">Indian_Rosewood</option>
				<option value="BRAZILIAN_ROSEWOOD">Brazilian_Rosewood</option>
				<option value="MAHOGANY">Mahogany</option>
				<option value="MAPLE">Maple</option>
				<option value="COCOBOLO">Cocobolo</option>
				<option value="CEDAR">Cedar</option>
				<option value="ADIRONDACK">Adirondack</option>
				<option value="ALDER">Alder</option>
				<option value="SITKA">Sitka</option>
	 		</select>
	 		
	 		<label>NumStrings:</label>
	 		<select class="easyui-combobox" name="numstrings">
	 			<option value=""></option>
	 			<option value="6">6</option>
	 			<option value="12">12</option>
	 		</select>
	 		<input class="easyui-linkbutton" type="submit" name="search" value="Search"/>		 		
	 	</form>
	</div>

	<table id="tt" class="easyui-datagrid" style="width:1000px;height:250px"
			url="datagrid24_getdata.php"
			title="Searching" iconCls="icon-search" toolbar="#tb"
			rownumbers="true" pagination="true">
		<thead>
			<tr>
				<th data-options="field:'serialnumber',width:100,align:'center'">SerialNumber</th>
				<th data-options="field:'backwood',width:120,align:'center'">BackWood</th>
				<th data-options="field:'topwood',width:120,align:'center'">TopWood</th>
				<th data-options="field:'price',width:80,align:'center'">Price</th>
				<th data-options="field:'builder',width:80,align:'center'">Builder</th>
				<th data-options="field:'model',width:80,align:'center'">Model</th>
				<th data-options="field:'type',width:80,align:'center'">Type</th>
				<th data-options="field:'numstrings',width:80,align:'center'">NumStrings</th>
			</tr>
		</thead>
		<c:forEach var="matchingGuitar" items="${requestScope.matchingGuitar}"> 
	 		<tr>
	 			<td>${matchingGuitar.serialNumber}</td>
	 			<td>${matchingGuitar.spec.builder}</td>
	 			<td>${matchingGuitar.spec.backWood}</td>
	 			<td>${matchingGuitar.spec.topWood}</td>
	 			<td>${matchingGuitar.spec.type}</td>
	 			<td>${matchingGuitar.spec.model}</td>
	 			<td>${matchingGuitar.price}</td>
	 			<td>${matchingGuitar.spec.numStrings}</td>
	 		</tr>			
	 	</c:forEach>
	</table>
</body>
</html>