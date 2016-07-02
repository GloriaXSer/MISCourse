<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成绩查询</title>
<link rel="stylesheet" type="text/css" href="CSS/easyui.css">
<link rel="stylesheet" type="text/css" href="CSS/icon.css">
<link rel="stylesheet" type="text/css" href="CSS/demo.css">
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery.easyui.min.js"></script>
</head>
<body>
	<h2>${sessionScope.student.name}的成绩查询</h2>
	<div id="tb" style="padding:3px;width:1000px;">
	 	<form action="SearchTranscriptAction" method="post" id="transcript">
	 		<input type="text" name="studentId" value="${sessionScope.student.id}" hidden="hidden"/>
	 		<label>Semester:</label>
	 		<select class="easyui-combobox" name="semester" style="width:120px;">
	 			<option value=""></option>
	 			<option value=""></option>
	 			<option value="SP2015">2015春季</option>
	 			<option value="FA2015">2015秋季</option>
	 			<option value="SP2016">2016春季</option>
	 			<option value="FA2016">2016秋季</option>
	 		</select>
	 		
	 		<label>Course:</label>
	 		<select class="easyui-combobox" name="courseNo" style="width:130px;">
	 			<option value=""></option>
	 			<option value=""></option>
	 			<option value="C001">高等数学(上)</option>
	 			<option value="C002">线性代数</option>
	 			<option value="C003">概率论与数理统计</option>
	 			<option value="C004">大学英语</option>
	 			<option value="C005">高等数学(下)</option>
	 			<option value="C006">微观经济学</option>
	 			<option value="C007">宏观经济学</option>
	 		</select>
	 		 	
	 		<input class="easyui-linkbutton" type="submit" name="search" value="Search"/>		 		
	 	</form>
	</div>
	
	<table id="tt" class="easyui-datagrid" style="width:1000px;height:500px;"
			title="Searching" iconCls="icon-search" toolbar="#tb"
			rownumbers="true" pagination="true">
		<thead>
			<tr>
				<th data-options="field:'semester',width:120,align:'center'">Semester</th>
				<th data-options="field:'sectionno',width:120,align:'center'">SectionNo</th>
				<th data-options="field:'course',width:120,align:'center'">Course</th>
				<th data-options="field:'credits',width:120,align:'center'">Credits</th>
				<th data-options="field:'grade',width:120,align:'center'">Grade</th>
			</tr>
		</thead>
		<c:forEach var="myTranscript" items="${requestScope.myTranscript}"> 
	 		<tr>
	 			<td>${myTranscript.section.offeredIn.semester}</td>
	 			<td>${myTranscript.section.sectionNo}</td>
	 			<td>${myTranscript.section.representedCourse.courseName}</td>
	 			<td>${myTranscript.section.representedCourse.credits}</td>
	 			<td>${myTranscript.grade}</td>
	 		</tr>			
	 	</c:forEach>
	</table>
</body>
</html>