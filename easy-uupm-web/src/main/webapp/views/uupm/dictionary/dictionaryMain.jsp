<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统一用户授权管理</title>
<base href="<%=basePath%>">
<jsp:include page="/common/layout/script.jsp"></jsp:include>

<script type="text/javascript">
	var $openWindow = this;// 当前窗口
	var $dg_category;
	var $dg_dictionary;
	$(function() {
		$dg_category = $('#dg_id_category');
		$dg_category.tree({
			url: 'uupm/dataCategory/listTree',
			animate: true,
		    loadFilter: function(result){
		    	if("OK"==result.status) {
		    		return result.data;
		    	} else {
		    		$.messager.show({
						title :commonui.msg_title,
						msg : result.msg,
						timeout : commonui.msg_timeout
					});
		    		return [];
	    		}
		    },
		    onContextMenu: function(e, node){
				e.preventDefault();
				$dg_category.tree('select', node.target);
				$('#mm').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			}
		});
		
		/********************************************************/
		
		$dg_dictionary = $('#dg_id_dictionary');
		$dg_dictionary.treegrid({
// 			url:'uupm/dictionary/listTree',
		    width: 'auto',
		    height: $(this).height()-commonui.remainHeight-20,
		    fit:true,
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: true,
			fit:true,
			border: false,
			striped: true,
			singleSelect: true,
			toolbar: '#tb_id_dictionary',
			idField: 'orgCode',
			treeField: 'orgName',
		    loadFilter: function(result) {
		    	if("OK"==result.statusCode) {
		    		return result.respData;
		    	} else {
		    		$.messager.show({
						title :"系统提示",
						msg : result.statusDesc,
						timeout : 1000 * 4
					});
		    		return [];
	    		}
	    	},
			frozenColumns: [[{field: 'orgName', title: '名称', width:parseInt($(this).width()*0.2),
								formatter:function(value){
									return '<span style="color:red">'+value+'</span>';
			                  	}	
							}]],
	        columns: [[
						{field: 'orgCode', title: '编号', width: parseInt($(this).width()*0.2), align: 'left'},
						{field: 'parentCode', title: '父编号', width: parseInt($(this).width()*0.2), align: 'left'},
						{field: 'firstManagerCode', title: '主负责人', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'secondManagerCode', title: '副负责人', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'remark', title: '描述', width: parseInt($(this).width()*0.2), align: 'left'}
	                   ]]
		});
		
	});
	
	/************************************************/
	// 打开添加对话框
	function openAddDlg() {
		parent.$.modalDialog({
			title: "添加",
			width: 800,
			height: 400,
			href: 'views/uupm/dataCategory/categoryEditDlg.jsp',
			onLoad:function() {
				var row = $dg_category.tree('getSelected');
				if(row) {
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					editForm.find('input[name="parentCode"]').val(row.id);
				}
			},
			buttons: [{
				text: '确定',
				iconCls: 'icon-ok',
				handler: function() {
					parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
					parent.$.modalDialog.openner = $dg_category;//定义对话框关闭要刷新的组件
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					editForm.attr("action", "uupm/dataCategory/add");
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
		var row = $dg_category.tree('getSelected');
		if(row) {
			parent.$.modalDialog({
				title: "编辑",
				width: 800,
				height: 400,
				href: 'views/uupm/dataCategory/categoryEditDlg.jsp',
				onLoad:function(){
					$.post("uupm/dataCategory/findOne", {categoryCode:row.id}, function(result){
						result = JSON.parse(result);
						if("OK"==result.status) {
				    		var editForm = parent.$.modalDialog.handler.find("#form_id");
							editForm.form("load", result.data);
							editForm.find("#categoryCode_id").attr('readonly',true);
				    	} else {
				    		$.messager.show({
								title :commonui.msg_title,
								msg : result.msg,
								timeout : commonui.msg_timeout
							});
			    		}
		          	});
				},
				buttons: [{
					text: '确定',
					iconCls: 'icon-ok',
					handler: function() {
						parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
						parent.$.modalDialog.openner = $dg_category;//定义对话框关闭要刷新的组件
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.attr("action", "uupm/dataCategory/edit");
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
				title :commonui.msg_title,
				msg : "请选择一行记录!",
				timeout : commonui.msg_timeout
			});
		}
	}
	
	// 删除
	function removeFunc() {
		var row = $dg_category.tree('getSelected');
		var nodeDataWithChildren = $dg_category.tree('getData', row.target);
		if(row) {
			parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){  
			    if(r) {
			    	var arr_id = getTreeIds(nodeDataWithChildren);
			    	var ids = arr_id.join(",");
			    	$.post("uupm/dataCategory/del", {categoryCodes:ids}, function(result) {
						if(result.status=='OK') {
							$dg_category.tree('remove', row.target);
						}
						$.messager.show({
							title :commonui.msg_title,
							msg : result.msg,
							timeout : commonui.msg_timeout
						});
					}, "JSON").error(function() {
						$.messager.show({
							title :commonui.msg_title,
							msg : result.msg,
							timeout : commonui.msg_timeout
						});
					});
			    }  
			});
		} else {
			$.messager.show({
				title :commonui.msg_title,
				msg : "请选择一行记录!",
				timeout : commonui.msg_timeout
			});
		}
	}
	// 递归获取tree节点，及子节点的id
	function getTreeIds(treeNode) {
		var arrs = [];
		if(treeNode) {
			arrs.push(treeNode.id);
			if(treeNode.children) {
				$.each(treeNode.children, function(i, obj){
					arrs = arrs.concat(getTreeIds(obj));
				});
			}
		}
		return arrs;
	}
	
	// 展开
	function expandAll() {
		var node = $dg_category.tree('getSelected');
		if(node) {
			$dg_category.tree('expandAll', node.target);
		} else {
			$dg_category.tree('expandAll');
		}
	}
	// 收缩
	function collapseAll() {
		var node = $dg_category.tree('getSelected');
		if(node) {
			$dg_category.tree('collapseAll', node.target);
		} else {
			$dg_category.tree('collapseAll');
		}
	}
	// 刷新
	function refresh() {
		$dg_category.tree('reload');
	}
</script>
</head>
<body class="easyui-layout">
    <div data-options="region:'west',title:'数据分类树',split:true,border:true" style="width:400px;">
		<div id="tb_id_category">
			<shiro:hasPermission name="org-add">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg();" href="javascript:void(0);">添加</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="org-edit">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="openEditDlg();" href="javascript:void(0);">编辑</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="org-del">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="removeFunc();" href="javascript:void(0);">删除</a>
			</shiro:hasPermission>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="expandAll();">展开</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="collapseAll();">收缩</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="refresh();">刷新</a>
		</div>
		<div data-options="fit:true" class="easyui-panel" style="border:1px solid #ccc; border-width:1px 0 0 0;background:#F5F5F5;">
			<ul id="dg_id_category" data-options="animate:true,lines:true" style="padding:5px;"></ul>
		</div>
		
		<div id="mm" class="easyui-menu" style="width:120px;">
			<div onclick="openAddDlg()" data-options="iconCls:'icon-add'">添加</div>
			<div onclick="openEditDlg()" data-options="iconCls:'icon-add'">编辑</div>
			<div onclick="removeFunc()" data-options="iconCls:'icon-remove'">删除</div>
			<div class="menu-sep"></div>
	        <div onclick="expandAll()">展开</div>
	        <div onclick="collapseAll()">收缩</div>
		</div>
    </div>

    <div data-options="region:'center',title:'字典树'" style="padding:5px;">
	    <table id="dg_id_dictionary"></table>
		<div id="tb_id_dictionary">
			<shiro:hasPermission name="org-add">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg();" href="javascript:void(0);">添加</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="org-edit">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="openEditDlg();" href="javascript:void(0);">编辑</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="org-del">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="removeFunc();" href="javascript:void(0);">删除</a>
			</shiro:hasPermission>
		</div>
    </div>
		
</body>
</html>