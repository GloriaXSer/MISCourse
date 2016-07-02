<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员系统</title>
<link rel="stylesheet" type="text/css" href="CSS/easyui.css">
<link rel="stylesheet" type="text/css" href="CSS/icon.css">
<link rel="stylesheet" type="text/css" href="CSS/demo.css">
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery.easyui.min.js"></script>
</head>
<body>
	<div style="width:1280px;height:auto;">
		<div style="text-align:center;font-size:22px;font-weight:bold;margin-bottom:5px;">管理员后台系统</div>
	</div>
	<div class="easyui-layout" style="width:1280px;height:680px;">
		<div region="west" split="true" title="菜 单 栏" style="width:200px;">
			<p style="padding:5px;margin:0;"></p>
			<ul>
				<li><a href="javascript:void(0)" onclick="addTab('登&emsp;&emsp;录','index.jsp')">登录</a></li>
				<li><a href="javascript:void(0)" onclick="addTab('编辑课程','editSection.jsp')">编辑课程</a></li>
				<li><a href="javascript:void(0)" onclick="addTab('编辑教师','editProfessor.jsp')">编辑教师</a></li>				
				<li><a href="javascript:void(0)" onclick="addTab('课程查询','searchSection.jsp')">课程查询</a></li>
				<li><a href="javascript:void(0)" onclick="addTab('课程情况查询','searchSectionDetail.jsp')">课程情况查询</a></li>
				<li><a href="javascript:void(0)" onclick="addTab('教师查询','searchProfessor.jsp')">教师查询</a></li>
			</ul>
		</div>
		<div id="content" region="center" title="后台管理系统" style="padding:5px;">
			<div id="ct_tab" class="easyui-tabs" style="width:100%;height:640px;">

			</div>
		</div>
	</div>


	
	<script type="text/javascript">
		function addTab(title, url){
			if ($('#ct_tab').tabs('exists', title)){
				$('#ct_tab').tabs('select', title);
			} else {
				var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
				//var content = '<div title="'+ url +'" iconCls="icon-reload" closable="true" style="padding:10px;">'+ url + '</div>';
				$('#ct_tab').tabs('add',{
					title:title,
					content:content,
					closable:true
				});
			}
		}
	</script>
</body>
</html>