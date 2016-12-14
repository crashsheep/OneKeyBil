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
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/css/login.css" />
</head>
<body>


<div id="login">
	<p>用户名:<input type="text" id="manager" class="textbox"></p>
	<p>密    码:<input type="password" id="password" class="textbox"></p>
</div>

<div id="btn">
	<a href="#" class="easyui-linkbutton">登陆</a>
</div>


<script type="text/javascript" src="<%=basePath %>static/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/easyui/locale/easyui-lang-zh_CN.js" ></script>
<script type="text/javascript">
$(function () {
	//登录界面
	$('#login').dialog({
		title : '登录后台',
		width : 300,
		height : 180,
		modal : true,
		iconCls : 'icon-login',
		buttons : '#btn',
	});
	
	//管理员帐号验证
	$('#manager').validatebox({
		required : true,
		missingMessage : '请输入管理员帐号',
		invalidMessage : '管理员帐号不得为空',
	});
	
	//管理员密码验证
	$('#password').validatebox({
		required : true,
		validType : 'length[5,30]',
		missingMessage : '请输入管理员密码',
		invalidMessage : '管理员密码不得为空',
	});
	
	//加载时判断验证
	if (!$('#manager').validatebox('isValid')) {
		$('#manager').focus();
	} else if (!$('#password').validatebox('isValid')) {
		$('#password').focus();
	}
	
	
	//单击登录
	$('#btn a').click(function () {
		if (!$('#manager').validatebox('isValid')) {
			$('#manager').focus();
		} else if (!$('#password').validatebox('isValid')) {
			$('#password').focus();
		} else {
			$.ajax({
				url : '<%=basePath%>validate',
				type : 'post',
				data : {
					manager : $('#manager').val(),
					password : $('#password').val(),
				},
				beforeSend : function () {
					$.messager.progress({
						text : '正在登录中...',
					});
				},
				success : function (data, response, status) {				    
					$.messager.progress('close');			
					if (data.data !=null) {
						location.href = '<%=basePath%>login';
					} else {
						$.messager.alert('登录失败！', '用户名或密码错误！', 'warning', function () {
							$('#password').select();
						});
					}
				}
			});
		}
	});
	
});
</script>
</body>
</html>