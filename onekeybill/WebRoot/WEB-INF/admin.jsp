<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<title>一键开票系统</title>
<meta charset="UTF-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/css/admin.css" />
</head>
<body class="easyui-layout">

<div data-options="region:'north',title:'header',split:true,noheader:true" style="height:60px;background:#666;">
	<div class="logo">一键开票系统</div>
	<div class="logout">您好，<%=request.getSession().getAttribute("username")%> | <a href="<%=basePath %>logout">退出</a></div>
</div>   
<div data-options="region:'south',title:'footer',split:true,noheader:true" style="height:35px;line-height:30px;text-align:center;">
	&copy;2009-2016 . Powered by 南京御安神物联网有限公司.
</div>    
<div data-options="region:'west',title:'导航',split:true,iconCls:'icon-world'" style="width:180px;padding:10px;">
	<ul id="nav"></ul>
</div>   
<div data-options="region:'center'" style="overflow:hidden;">
	<div id="tabs">
		<div title="起始页" iconCls="icon-house" style="padding:0 10px;display:block;">
			欢迎来到后台管理系统！
		</div>
	</div>
</div> 


<script type="text/javascript" src="<%=basePath %>static/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/easyui/locale/easyui-lang-zh_CN.js" ></script>
<script type="text/javascript" src="<%=basePath %>static/js/admin.js"></script>
</body>
</html>