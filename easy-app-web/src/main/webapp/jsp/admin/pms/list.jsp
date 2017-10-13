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
	var $dg;
	$(function() {
		$dg = $('#dg_id').datagrid({
		    url:'sys/pms/list',
		    loadFilter: function(data) {if(data.respData!=null) {return data.respData;} else {return [];}},
		    width: 'auto',
			height: $(this).height(),
			pagination: true,
			pageSize: commonui.pageSize,
			rownumbers: true,
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
	 * 打开添加消息框
	 */
	function openAddDlg() {
		parent.$.modalDialog({
			title: "菜单编辑",
			width: 600,
			height: 500,
			href: "jsp/admin/pms/edit.jsp",
			buttons: [{
				text: '确定',
				iconCls: 'icon-ok',
				handler: function() {
					parent.$.modalDialog.openner= $('#tree_id');//因为添加成功之后，需要刷新这个tree，所以先预定义好
					var form = parent.$.modalDialog.handler.find("#form_id");
					form.submit();
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
	 * 刷新tree
	 */
	function reloadFunc() {
		$('#tree_id').tree('reload');
	}
	
	/**
	 * 删除树节点
	 */
	function removeFunc() {
		var node = $('#tree_id').tree('getSelected');
		if(node) {
			parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){  
			    if(r) {
			    	$.post("sys/menu/del", {code:node.id}, function(result) {
						if(result.statusCode=='OK'){
							$('#tree_id').tree('remove', node.target);
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