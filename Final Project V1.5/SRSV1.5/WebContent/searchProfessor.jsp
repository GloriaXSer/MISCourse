<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="CSS/easyui.css">
<link rel="stylesheet" type="text/css" href="CSS/icon.css">
<link rel="stylesheet" type="text/css" href="CSS/demo.css">
<style type="text/css">
	#fm{
		margin:0;
        padding:10px 30px;
    }
    .ftitle{
        font-size:14px;
        font-weight:bold;
        padding:5px 0;
        margin-bottom:10px;
        border-bottom:1px solid #ccc;
    }
    .fitem{
        margin-bottom:5px;
    }
    .fitem label{
        display:inline-block;
        width:80px;
    }
    .fitem input{
        width:160px;
    }
</style>
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery.easyui.min.js"></script>
</head>
<body>
	<h2>Search Professor</h2>	

	<table id="tt" title="My Users" class="easyui-datagrid" style="width:700px;height:360px"
			url="./ProfessorSearchAction"         
            toolbar="#toolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="id" width="50">ProfessorId</th>
                <th field="name" width="50">Name</th>
                <th field="title" width="50">Title</th>
                <th field="department" width="50">Department</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
		<form id="fm" method="post" novalidate>
	 		<label>ProfessorName:</label>
	 		<select id="id" name="id" style="width:120px;">
	 			<option value="" ></option>
	 			<option value="T09001">Mr.Smith</option>
	 			<option value="T09002">Ms.Barker</option>
	 			<option value="T09003">Snidely Whiplash</option>
	 			<option value="T09004">Mr.Brown</option>
	 			<option value="T09005">Sakura Yukina</option>
	 		</select>	 		
	 		<label>Department:</label>
	 		<select id="department" name="department" style="width:130px;">
	 			<option value=""></option>
	 			<option value="Math Education">Math Education</option>
	 			<option value="Information Technology">Information Technology</option>
	 			<option value="Physical Education">Physical Education</option>
	 			<option value="Management">Management</option>
	 		</select>
	 		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchUser()">Search</a> 		 			 		
	 	</form>
    </div>
    <script type="text/javascript">
	    function searchUser(){
	    	$('#tt').datagrid('load',{
	    		id:$("#id").find("option:selected").val(),
	    		department:$("#department").find("option:selected").val()
	    	});
  	    }
    </script>
</body>
</html>