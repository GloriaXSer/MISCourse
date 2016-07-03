<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已选课程</title>
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
	<h2>已选课程</h2>	

	<table id="dg" title="My Sections" class="easyui-datagrid" style="width:700px;height:360px"
            url="./MySectionsAction?id=${sessionScope.student.id}" 
            toolbar="#toolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="sectionNo" width="50">SectionNo</th>
                <th field="dayOfWeek" width="50">DayOfWeek</th>
                <th field="timeOfDay" width="50">TimeOfDay</th>
                <th field="room" width="50">Room</th>
                <th field="seatingCapacity" width="70">SeatingCapacity</th>
                <th field="courseName" width="60">Course</th>
                <th field="professorName" width="60">Professor</th>
            </tr>
        </thead>
    </table>
        <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="dropSection()">退课</a>
    </div>
    <script type="text/javascript">
	    function dropSection(){
	    	var id = "${sessionScope.student.id}";
	        var row = $('#dg').datagrid('getSelected');
	        if (row){
	            $.messager.confirm('Confirm','Are you sure you want to drop this section?',function(r){
	                if (r){
	                    $.post('./SectionDeleteAction',{sectionNo:row.sectionNo},function(result){ 
	                      $('#dg').datagrid('reload'); 
	                    },"json");
	                }
	            });
	        }
	    }
    </script>
</body>
</html>