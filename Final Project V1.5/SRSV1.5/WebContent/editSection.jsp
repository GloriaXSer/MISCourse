<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程编辑</title>
<link rel="stylesheet" type="text/css" href="CSS/easyui.css">
<link rel="stylesheet" type="text/css" href="CSS/icon.css">
<link rel="stylesheet" type="text/css" href="CSS/demo.css">
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery.easyui.min.js"></script>
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
</head>
<body>
	<h2>Edit Section</h2>	
	<table id="dg" title="Sections" class="easyui-datagrid" style="width:700px;height:360px"
            url="./SectionEditAction"
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
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:450px;height:280px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">Section Information</div>
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>SectionNo:</label>
                <input name="sectionNo" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>DayOfWeek:</label>
                <input name="dayOfWeek" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>TimeOfDay:</label>
                <input name="timeOfDay" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>Room:</label>
                <input name="room" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>SeatingCapacity:</label>
                <input name="seatingCapacity" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>Course:</label>
                <input name="courseName" class="easyui-textbox" required="true">
            </div>
            <div class="fitem">
                <label>Professor:</label>
                <input name="professorName" class="easyui-textbox" required="true">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
    </div>
    <script type="text/javascript"> 
	    var url;
	    function newUser(){
	        $('#dlg').dialog('open').dialog('center').dialog('setTitle','New Section');
	        $('#fm').form('clear');
	        url = './SectionAddAction';
	    }
	    function editUser(){
	        var row = $('#dg').datagrid('getSelected');
	        if (row){
	            $('#dlg').dialog('open').dialog('center').dialog('setTitle','Edit Section');
	            $('#fm').form('load',row);
	            url = './SectionUpdateAction?oldNo='+row.sectionNo;
	        }
	    }
	    function saveUser(){
	        $('#fm').form('submit',{
	            url: url,
	            onSubmit: function(){
	                return $(this).form('validate');
	            },
	            success: function(result){
	                var result = eval('('+result+')');
	                if (result.errorMsg){
	                    $.messager.show({
	                        title: 'Error',
	                        msg: result.errorMsg
	                    });
	                } else {
	                    $('#dlg').dialog('close');      
	                    $('#dg').datagrid('reload');
	                }
	            }
	        });
	    }
	    function destroyUser(){
	        var row = $('#dg').datagrid('getSelected');
	        if (row){
	            $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
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