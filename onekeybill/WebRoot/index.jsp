<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>欢迎来到发票管理系统</title>
<meta charset="UTF-8" />
</head>
<body>
<script type="text/javascript" src="<%=basePath %>static/easyui/jquery.min.js"></script>
<script type="text/javascript">
$(function () {	
	location.href = '<%=basePath%>index';
})
</script>
</body>
</html>