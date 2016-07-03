<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程查询</title>
<link rel="stylesheet" type="text/css" href="CSS/easyui.css">
<link rel="stylesheet" type="text/css" href="CSS/icon.css">
<link rel="stylesheet" type="text/css" href="CSS/demo.css">
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery.easyui.min.js"></script>
</head>
<body>
	<h2>课程查询</h2>	

	<table id="tt" title="My Section" class="easyui-datagrid" style="width:700px;height:360px"
			url="./SectionSearchAction"         
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
		<form id="fm" method="post" novalidate>
			<label>Semester:</label>
	 		<select id="semester" name="semester" style="width:120px;">
	 			<option value=""></option>
	 			<option value="SP2015">2015春季</option>
	 			<option value="FA2015">2015秋季</option>
	 			<option value="SP2016">2016春季</option>
	 			<option value="FA2016">2016秋季</option>
	 		</select>
	 		<label>Professor:</label>
	 		<select id="id" name="id" style="width:120px;">
	 			<option value="" ></option>
	 			<option value="T09001">Mr.Smith</option>
	 			<option value="T09002">Ms.Barker</option>
	 			<option value="T09003">Snidely Whiplash</option>
	 			<option value="T09004">Mr.Brown</option>
	 			<option value="T09005">Sakura Yukina</option>
	 		</select>	 		 		 		
	 		<label>Course:</label>
	 		<select id="courseNo" name="courseNo" style="width:130px;">
	 			<option value=""></option>
	 			<option value="C001">高等数学(上)</option>
	 			<option value="C002">线性代数</option>
	 			<option value="C003">概率论与数理统计</option>
	 			<option value="C004">大学英语</option>
	 			<option value="C005">高等数学(下)</option>
	 			<option value="C006">微观经济学</option>
	 			<option value="C007">宏观经济学</option>
	 		</select>	 
	 		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchSection()">Search</a>
	 	</form>
    </div>
    <script type="text/javascript">
	    function searchSection(){
	    	$('#tt').datagrid('load',{
	    		semester:$("#semester").find("option:selected").val(),
	    		id:$("#id").find("option:selected").val(),
	    		courseNo:$("#courseNo").find("option:selected").val()
	    	});
  	    }
	</script>
</body>
</html>