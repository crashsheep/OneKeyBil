<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<table id="manager"></table>

<div id="manager_tool" style="padding:5px;">
	<div style="margin-bottom:5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add-new" plain="true" onclick="manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit-new" plain="true" onclick="manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-delete-new" plain="true" onclick="manager_tool.remove();">删除</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true"  onclick="manager_tool.reload();">刷新</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="manager_tool.redo();">取消选择</a>		
	</div>
	<div style="padding:0 0 0 7px;color:#333;">
		查询帐号：<input type="text" class="textbox" name="user" style="width:110px">
		创建时间从：<input type="text" name="date_from" class="easyui-datebox" editable="false" style="width:110px">
		到：<input type="text" name="date_to" class="easyui-datebox" editable="false" style="width:110px">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="obj.search();">查询</a>
	</div>
</div>


<form id="manager_add" style="margin:0;padding:5px 0 0 25px;color:#333;">
	<p>帐        号：<input type="text" name="manager" class="textbox" style="width:200px;"></p>
	<p>密        码：<input type="password" name="password" class="textbox" style="width:200px;"></p>
	<p>类        型：<input id="type" class="textbox" name="type" style="width:200px;"></p>
</form>


<form id="manager_edit" style="margin:0;padding:5px 0 0 25px;color:#333;">
    <input type="hidden" name="uid" class="textbox" style="width:200px;">
	<p>帐号：<input type="text" name="manager_edit"  class="textbox" style="width:200px;"></p>
	<p>密码：<input type="password" name="password_edit" class="textbox" style="width:200px;"></p>
	<p>类型：<input id="type_edit" class="textbox" name="type_edit" style="width:200px;"></p>
</form>



<script type="text/javascript" src="<%=basePath %>static/js/UserManager.js"></script>