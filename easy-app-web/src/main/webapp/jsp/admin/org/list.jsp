<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机构管理</title>
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
		$dg = $("#dg_id");
	 	$dg.treegrid({
	 		url: "sys/org/list",
	 		width: 'auto',
			height: $(this).height(),
	 		rownumbers: true,
	 		animate: true,
	 		collapsible: true,
	 		striped: true,
	 		border: true,
	 		toolbar: '#btn_id',
	 		idField: 'code',
	 		treeField: 'name',
	 		loadFilter: function(data) {if(data.statusCode=='OK') {return data.respData;} else {return [];}},
	 		frozenColumns:[[
	 		                {field:'name',title:'组织名称', width:parseInt(($(this).width()-30)*0.2)}
	 						]],
	 		columns:[[
	 		          {field:'code', title:'组织编码', width:parseInt(($(this).width()-30)*0.1), align:'left'},
	 		          {field:'firstManagerCode', title:'主负责人', width:parseInt(($(this).width()-30)*0.1), align:'left'},
	 		          {field:'secondManagerCode', title:'副负责人', width:parseInt(($(this).width()-30)*0.1), align:'left'},
	 		          {field:'parentCode',title:'父机构编号', width:parseInt(($(this).width()-30)*0.1), align:'left'},
	 		          {field:'tel', title:'电话', width:parseInt(($(this).width()-30)*0.1), align:'left'},
	 		          {field:'fax', title:'传真', width:parseInt(($(this).width()-30)*0.1), align:'left'},
	 		          {field:'remark', title:'描述', width:parseInt(($(this).width()-30)*0.2), align:'left'}
	 		          ]]
	 	});
	 	
	});
	
	/**
	 * 打开添加对话框
	 */
	function openAddDlg() {
		parent.$.modalDialog({
			title: "编辑",
			width: 700,
			height: 400,
			href: 'jsp/admin/org/edit.jsp',
			buttons: [{
				text: '确定',
				iconCls: 'icon-ok',
				handler: function() {
					parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
					parent.$.modalDialog.openner = $dg;//定义对话框关闭要刷新的grid
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					editForm.attr("action", "sys/org/add");
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
		var row = $dg.treegrid('getSelected');
		if(row) {
			parent.$.modalDialog({
				title: "编辑",
				width: 700,
				height: 400,
				href: "jsp/admin/org/edit.jsp",
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
						editForm.attr("action", "sys/org/edit");
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
			parent.$.messager.confirm("提示","该操作会级联删除所有子机构，确定要删除记录吗?",function(r){  
			    if(r) {
			    	$.post("sys/org/del", {code:node.code}, function(result) {
						if(result.statusCode=='OK'){
							$dg.treegrid('reload');
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
		<div id="btn_id" style="padding:5px 20px;height:30px;">
			<table cellpadding="0" cellspacing="0">
				<td style="padding-left:2px">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg();" href="javascript:void(0);">添加</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="openEditDlg();" href="javascript:void(0);">编辑</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="removeFunc();" href="javascript:void(0);">删除</a>
				</td>
			</table>
		</div>
		<table id="dg_id" border="0"></table>
		
	</div>
</body>
</html>