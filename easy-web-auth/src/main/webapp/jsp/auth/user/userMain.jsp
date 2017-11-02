<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<base href="<%=basePath%>">
<jsp:include page="/jsp/index/layout/script.jsp"></jsp:include>

<script type="text/javascript">
	var $openWindow = this;// 当前窗口
	var $dg;
	$(function() {
		$dg = $('#dg_id');
		$dg.datagrid({
		    url:'auth/user/findPage',
		    width: 'auto',
			height: $(this).height()-90,
			pagination: true,
			pageSize: commonui.pageSize,
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: true,
			border: true,
			striped: true,
			singleSelect: true,
			toolbar: '#tb_id',
		    loadFilter: function(data) {if(data.respData!=null) {return data.respData;} else {return [];}},
			frozenColumns: [[{field: 'userName', title: '名称', width:parseInt($(this).width()*0.2),
								formatter:function(value){
									return '<span style="color:red">'+value+'</span>';
			                  	}	
							}]],
	        columns: [[
						{field: 'userCode', title: '编号', width: parseInt($(this).width()*0.2), align: 'left'},
						{field: 'orgName', title: '机构', width: parseInt($(this).width()*0.1), align: 'left',
							formatter: function(value, row) {
								return row.organization.orgName; 
							}
						},
						{field: 'userState', title: '状态', width: parseInt($(this).width()*0.1), align: 'left',
							formatter: function(value, row) {
								if("A"==row.userState)
									return "<font color=green>激活<font>";
			            		else
			            			return "<font color=red>冻结<font>";
							}
						},
						{field: 'tel', title: '电话', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'email', title: '邮箱', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'remark', title: '描述', width: parseInt($(this).width()*0.2), align: 'left'}
	                   ]]
		});
		
		//搜索框
		$("#searchbox_id").searchbox({
			menu:"#mm",
			prompt :'请输入',
// 			height: 28,
			searcher:function(value, name) {
				var obj = {};
				obj[name] = value;
				$dg.datagrid('reload', obj); 
		    }
		});

	});
	
	// 打开添加对话框
	function openAddDlg() {
		parent.$.modalDialog({
			title: "添加",
			width: 600,
			height: 400,
			href: 'jsp/auth/user/userEditDlg.jsp',
			buttons: [{
				text: '确定',
				iconCls: 'icon-ok',
				handler: function() {
					parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
					parent.$.modalDialog.openner = $dg;//定义对话框关闭要刷新的grid
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					editForm.attr("action", "auth/user/add");
					editForm.submit();
				}
			},{
				text: '取消',
				iconCls: 'icon-cancel',
				handler: function() {
					parent.$.modalDialog.handler.dialog('destroy');
					parent.$.modalDialog.handler = undefined;
				}
			}]
		});
	}
	
	// 打开修改对话框
	function openEditDlg() {
		var row = $dg.datagrid('getSelected');
		if(row) {
			parent.$.modalDialog({
				title: "编辑",
				width: 600,
				height: 400,
				href: 'jsp/auth/user/userEditDlg.jsp',
				onLoad:function(){
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					if("-1"==row.orgCode) row.orgCode = "";
					editForm.form("load", row);
					editForm.find("#userCode_id").attr('readonly',true);
				},
				buttons: [{
					text: '确定',
					iconCls: 'icon-ok',
					handler: function() {
						parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
						parent.$.modalDialog.openner = $dg;//定义对话框关闭要刷新的grid
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.attr("action", "auth/user/edit");
						editForm.submit();
					}
				},{
					text: '取消',
					iconCls: 'icon-cancel',
					handler: function() {
						parent.$.modalDialog.handler.dialog('destroy');
						parent.$.modalDialog.handler = undefined;
					}
				}]
			});
		} else {
			$.messager.show({
				title :"系统提示",
				msg :"请选择一行记录!",
				timeout : 1000 * 2
			});
		}
	}
	
	// 删除
	function removeFunc() {
		var row = $dg.datagrid('getSelected');
		if(row) {
			parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){  
			    if(r) {
			    	$.post("auth/user/del", {userCode:row.userCode}, function(result) {
						if(result.statusCode=='OK') {
							var rowIndex = $dg.datagrid('getRowIndex', row);
							$dg.datagrid('deleteRow', rowIndex);
						}
						$.messager.show({
							title : "系统提示",
							msg : result.statusDesc,
							timeout : 1000 * 2
						});
					}, "JSON").error(function() {
						$.messager.show({
							title : "系统提示",
							msg : result.statusDesc,
							timeout : 1000 * 2
						});
					});
			    }  
			});
		} else {
			$.messager.show({
				title :"系统提示",
				msg :"请选择一行记录!",
				timeout : 1000 * 2
			});
		}
	}

	// 清除搜索条件
	function clearSearch() {
		$('#searchbox_id').searchbox('setValue', '');
	}
</script>
</head>
<body>
	<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
		<span class="badge">提示</span>
		<p>
			在此你可以对<span class="label-info"><strong>用户</strong></span>进行编辑!
		</p>
	</div>
	<div id="tb_id" style="padding:10px;height:auto">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td style="padding-left:2px;padding-bottom:2px;">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg();" href="javascript:void(0);">添加</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="openEditDlg();" href="javascript:void(0);">编辑</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="removeFunc();" href="javascript:void(0);">删除</a>
				</td>
				<td style="padding-left:2px;padding-bottom:2px;">
					<input id="searchbox_id" type="text"/>
				</td>
				<td style="padding-left:2px">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-clear',plain:'true'" onclick="clearSearch();" href="javascript:void(0);"></a>
				</td>
			</tr>
		</table>
	</div>
	
	<table id="dg_id" title="用户管理"></table>
	
	<div id="mm">
		<div name="userCode">用户编码</div>
		<div name="userName">用户名</div>
		<div name="tel">电话</div>
	</div>
		
</body>
</html>