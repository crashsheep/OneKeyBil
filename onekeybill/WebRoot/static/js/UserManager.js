function getRootPath(){      
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp      
    var curWwwPath=window.document.location.href;      
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp      
    var pathName=window.document.location.pathname;      
    var pos=curWwwPath.indexOf(pathName);      
    //获取主机地址，如： http://localhost:8083      
    var localhostPaht=curWwwPath.substring(0,pos);      
    //获取带"/"的项目名，如：/uimcardprj      
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);      
    return(localhostPaht+projectName);  
}

$(function () {	
	$('#manager').datagrid({
		url : getRootPath()+'/user_load',
		fit : true,
		fitColumns : true,
		striped : true,
		rownumbers : true,
		border : false,
		pagination : true,
		pageSize : 20,
		pageList : [10, 20, 30, 40, 50],
		pageNumber : 1,
		sortName : 'id',
		sortOrder : 'desc',
		toolbar : '#manager_tool',
		columns : [[
			{
				field : 'id',
				title : '自动编号',
				width : 100,
				checkbox : true,
			},
			{
				field : 'name',
				title : '用户名',
				width : 100,
			},
			{
				field : 'create_time',
				title : '创建日期',
				width : 100,
			},
			{
				field : 'type',
				title : '账号类型',
				width : 100,
			},
		]],
	});
	
	$('#manager_add').dialog({
		width : 350,
		title : '新增用户',
		modal : true,
		closed : true,
		iconCls : 'icon-user-add',
		buttons : [{
			text : '提交',
			iconCls : 'icon-add-new',
			handler : function () {
				if ($('#manager_add').form('validate')) {
					$.ajax({
						url : getRootPath()+'/user_add',
						type : 'post',
						data : {
							manager : $('input[name="manager"]').val(),
							password : $('input[name="password"]').val(),
							type : $('#type').val(),
						},
						beforeSend : function () {
							$.messager.progress({
								text : '正在新增中...',
							});
						},
						success : function (data, response, status) {
							$.messager.progress('close');
							
							if (data > 0) {
								$.messager.show({
									title : '提示',
									msg : '新增成功',
								});
								$('#manager_add').dialog('close').form('reset');
								$('#manager').datagrid('reload');
							} else {
								$.messager.alert('新增失败！', '未知错误，请重试！', 'warning');
							}
						}
					});
				}
			},
		},{
			text : '取消',
			iconCls : 'icon-redo',
			handler : function () {
				$('#manager_add').dialog('close').form('reset');
			},
		}],
	});
	
	$('#manager_edit').dialog({
		width : 350,
		title : '修改管理',
		modal : true,
		closed : true,
		iconCls : 'icon-user-add',
		buttons : [{
			text : '提交',
			iconCls : 'icon-edit-new',
			handler : function () {
				if ($('#manager_edit').form('validate')) {
					$.ajax({
						url : getRootPath()+'/user_update',
						type : 'post',
						data : {
							id : $('input[name="uid"]').val(),
							username : $('input[name="manager_edit"]').val(),
							password : $('input[name="password_edit"]').val(),
							type : $('#type_edit').val(),
						},
						beforeSend : function () {
							$.messager.progress({
								text : '正在修改中...',
							});
						},
						success : function (data, response, status) {
							$.messager.progress('close');
							
							if (data > 0) {
								$.messager.show({
									title : '提示',
									msg : '修改管理成功',
								});
								$('#manager_edit').dialog('close').form('reset');
								$('#manager').datagrid('reload');
							} else {
								$.messager.alert('修改失败！', '未知错误或没有任何修改，请重试！', 'warning');
							}
						}
					});
				}
			},
		},{
			text : '取消',
			iconCls : 'icon-redo',
			handler : function () {
				$('#manager_edit').dialog('close').form('reset');
			},
		}],
	});
	
	//管理帐号
	$('input[name="manager"]').validatebox({
		required : true,
		validType : 'length[2,20]',
		missingMessage : '请输入名称',
		invalidMessage : '名称在 2-20 位',
	});
	
	//管理密码
	$('input[name="password"]').validatebox({
		required : true,
		validType : 'length[5,30]',
		missingMessage : '请输入密码',
		invalidMessage : '密码在 5-30 位',
	});
	
	//修改管理密码
	$('input[name="password_edit"]').validatebox({
		//required : true,
		validType : 'length[5,30]',
		missingMessage : '请输入密码',
		invalidMessage : '密码在5-30 位',
	});
	
	//修改账号类型
	$('#type').validatebox({
		required : true,
		validType : 'length[1,2]',
		missingMessage : '请输入账号类型',
		invalidMessage : '账号类型1-2 位',
	});
	
	
	manager_tool = {
		reload : function () {
			$('#manager').datagrid('reload');
		},
		redo : function () {
			$('#manager').datagrid('unselectAll');
		},
		add : function () {
			$('#manager_add').dialog('open');
			$('input[name="manager"]').focus();
		},
		remove : function () {
			var rows = $('#manager').datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('确定操作', '您正在要删除所选的记录吗？', function (flag) {
					if (flag) {
						var ids = [];
						for (var i = 0; i < rows.length; i ++) {
							ids.push(rows[i].id);
						}
						//console.log(ids.join(','));
						$.ajax({
							type : 'POST',
							url : getRootPath()+'/user_del',
							data : {
								ids : ids.join(','),
							},
							beforeSend : function () {
								$('#manager').datagrid('loading');
							},
							success : function (data) {
								if (data) {
									$('#manager').datagrid('loaded');
									$('#manager').datagrid('load');
									$('#manager').datagrid('unselectAll');
									$.messager.show({
										title : '提示',
										msg : data + '个记录被删除成功！',
									});
								}
							},
						});
					}
				});
			} else {
				$.messager.alert('提示', '请选择要删除的记录！', 'info');
			}
		},
		edit : function () {
			var rows = $('#manager').datagrid('getSelections');
			if (rows.length > 1) {
				$.messager.alert('警告操作！', '编辑记录只能选定一条数据！', 'warning');
			} else if (rows.length == 1) {
											
							$('#manager_edit').form('load', {
								uid : rows[0].id,
								manager_edit : rows[0].name,
								type_edit : rows[0].type,
							}).dialog('open');
						
			} else if (rows.length == 0) {
				$.messager.alert('警告操作！', '编辑记录至少选定一条数据！', 'warning');
			}
		},
	};
	
	
	
});