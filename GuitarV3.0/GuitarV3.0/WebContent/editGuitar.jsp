<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加吉他</title>
<link rel="stylesheet" type="text/css" href="CSS/easyui.css">
<link rel="stylesheet" type="text/css" href="CSS/icon.css">
<link rel="stylesheet" type="text/css" href="CSS/demo.css">
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery.easyui.min.js"></script>
</head>
<body>
	<h2>Guitar Shop</h2>	
	<div style="margin:20px 0;">
		<form action="GuitarAll" method="post">
			<input type="submit" data-options="iconCls:'icon-search'" value="查看所有吉他"/>
		</form>
	</div>
	<table class="easyui-datagrid" title="Guitar List" style="width:900px;height:250px"
			data-options="rownumbers:true,singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get',toolbar:toolbar">
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
				<th data-options="field:'operate',width:100,align:'center'">Operate</th>
			</tr>
		</thead>
		<c:forEach var="allGuitars" items="${requestScope.allGuitars}"> 
	 		<tr>
	 			<td>${allGuitars.serialNumber}</td>
	 			<td>${allGuitars.spec.backWood}</td>
	 			<td>${allGuitars.spec.topWood}</td>
	 			<td>${allGuitars.price}</td>
	 			<td>${allGuitars.spec.builder}</td>
	 			<td>${allGuitars.spec.model}</td>	 			
	 			<td>${allGuitars.spec.type}</td>
	 			<td>${allGuitars.spec.numStrings}</td>
	 			<td><a href="./GuitarDelete?serialNumber=${allGuitars.serialNumber}" class="easyui-linkbutton" data-options="iconCls:'icon-no'">Delete</a></td>
	 		</tr>			
	 	</c:forEach>
	</table>
	
	<form method="post" action="GuitarAdd" id="add" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'" title="Add Guitar" closed="true">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>SerialNumber:</td>
	    			<td><input class="easyui-textbox" type="text" name="serialnumber" id="serialnumber" data-options="required:true" style="width:280px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>BackWood:</td>
	    			<td><input class="easyui-textbox" type="text" name="backwood" data-options="required:true" style="width:280px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>TopWood:</td>
	    			<td><input class="easyui-textbox" type="text" name="topwood" data-options="required:true" style="width:280px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>Price:</td>
	    			<td><input class="easyui-textbox" type="text" name="price" data-options="required:true" style="width:280px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>Builder:</td>
	    			<td><input class="easyui-textbox" type="text" name="builder" data-options="required:true" style="width:280px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>Model:</td>
	    			<td><input class="easyui-textbox" type="text" name="model" data-options="required:true" style="width:280px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>Type:</td>
	    			<td><input class="easyui-textbox" type="text" name="type" data-options="required:true" style="width:280px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td>NumStrings:</td>
	    			<td><input class="easyui-textbox" type="text" name="numstrings" data-options="required:true" style="width:280px;"></input></td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" style="text-align: center;">
	    				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
	    			</td>
	    		</tr>
	    	</table>
	</form>
	
	<script type="text/javascript">
		var toolbar = [{
			text:'Add',
			iconCls:'icon-add',
			handler:function(){$('#add').window('open')}
		}];	

		function submitForm(){
			$('#add').form('submit');
			window.location.reload();
		}	
	</script>

</body>
</html>