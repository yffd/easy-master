<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理</title>
<base href="<%=basePath%>">
<jsp:include page="/jsp/system/layout/script.jsp"></jsp:include>
<style type="text/css">
.datagrid-header-row {height: 30px;}
.datagrid-row {height: 30px;}
</style>
<script type="text/javascript">
	var $openWindow = this;// 当前窗口
	var $dg;
	$(function() {
		$dg = $('#dg_id');
		$dg.datagrid({
		    url:'sys/pms/list',
		    loadFilter: function(data) {if(data.respData!=null) {return data.respData;} else {return [];}},
		    width: 'auto',
			height: $(this).height(),
			pagination: true,
			pageSize: commonui.pageSize,
			rownumbers: true,
			animate: true,
			border: true,
			striped: true,
			singleSelect: true,
			toolbar: '#tb_id',
			frozenColumns: [[
							{field: 'name', title: '名称', width:parseInt(($(this).width()-30)*0.25)}
			                ]],
	        columns: [[
						{field: 'code', title: '编号', width: parseInt(($(this).width()-30)*0.25), align: 'left'},
						{field: 'createTime', title: '创建时间', width: parseInt(($(this).width()-30)*0.25), align: 'left',
							formatter: function(value, row) {
								return (new Date(value)).formatDate();
							}	
						},
						{field: 'remark', title: '描述', width: parseInt(($(this).width()-30)*0.25), align: 'left'}
	                   ]]
		});

		//搜索框
		$("#searchbox_id").searchbox({
			menu:"#mm",
			prompt :'请输入',
			height: 28,
			searcher:function(value, name) {
				var obj = {};
				obj[name] = value;
				$dg.datagrid('reload', obj); 
		    }
		});
	});
	
	/**
	 * 清除搜索条件
	 */
	function clearSearch() {
		$('#searchbox_id').searchbox('setValue', '');
	}
	
	/**
	 * 打开添加对话框
	 */
	function openAddDlg() {
		parent.$.modalDialog({
			title: "编辑",
			width: 500,
			height: 400,
			href: 'jsp/admin/pms/edit.jsp',
			buttons: [{
				text: '确定',
				iconCls: 'icon-ok',
				handler: function() {
					parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
					parent.$.modalDialog.openner = $dg;//定义对话框关闭要刷新的grid
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					editForm.attr("action", "sys/pms/add");
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
	
	/**
	 * 打开修改对话框
	 */
	function openEditDlg() {
		var row = $dg.datagrid('getSelected');
		if(row) {
			parent.$.modalDialog({
				title: "编辑",
				width: 500,
				height: 400,
				href: "jsp/admin/pms/edit.jsp",
				onLoad:function(){
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					editForm.form("load", row);
					editForm.find("#code_id").attr('readonly',true);
				},
				buttons: [{
					text: '确定',
					iconCls: 'icon-ok',
					handler: function() {
						parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
						parent.$.modalDialog.openner = $dg;//定义对话框关闭要刷新的grid
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.attr("action", "sys/pms/edit");
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
	
	/**
	 * 删除
	 */
	function removeFunc() {
		var node = $dg.datagrid('getSelected');
		if(node) {
			parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){  
			    if(r) {
			    	$.post("sys/pms/del", {code:node.code}, function(result) {
						if(result.statusCode=='OK'){
							var rowIndex = $dg.datagrid('getRowIndex', node);
							$dg.datagrid('deleteRow', rowIndex);
							$.messager.show({
								title : "系统提示",
								msg : result.statusDesc,
								timeout : 1000 * 2
							});
						}
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

</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div id="tb_id" style="padding:5px 20px;height:30px;">
			<table cellpadding="0" cellspacing="0">
				<td style="padding-left:2px">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg();" href="javascript:void(0);">添加</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="openEditDlg();" href="javascript:void(0);">编辑</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="removeFunc();" href="javascript:void(0);">删除</a>
				</td>
				<td style="padding-left:2px">
					<input id="searchbox_id" type="text"/>
				</td>
				<td style="padding-left:2px">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-clear',plain:'true'" onclick="clearSearch();" href="javascript:void(0);"></a>
				</td>
			</table>
			</div>
		<table id="dg_id" border="0"></table>
		
		<div id="mm">
			<!-- 查询 -->
			<div name="name">名称</div>
			<div name="code">编码</div>
		</div>
	</div>
</body>
</html>