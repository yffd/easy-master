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
	var $json_activeStatus = [ {id:"", text:"全部", "selected": true} ];
	var $json_rsType = [ {id:"", text:"全部", "selected": true} ];
	var $openWindow = this;// 当前窗口
	var $dg;
	$(function() {
		// 初始化控件数据
		$.post('/uupm/combox/findComboByDict', 
				{'comboxKeys':'active-status,rs-type'}, 
				function(result) {
					if("OK"==result.status) {
						var jsonData = result.data;
						$json_activeStatus = $json_activeStatus.concat(jsonData['active-status']);
						$json_rsType = $json_rsType.concat(jsonData['rs-type']);

						initTreegrid();	// 初始化datagrid组件
					}
				}, 'json');
		//搜索框-app
		$("#searchbox_id").searchbox({
			menu:"#mm_id",
			prompt :'请输入',
// 			height: 28,
			searcher:function(value, name) {
				var obj = {};
				obj[name] = value;
				$dg.datagrid('reload', obj); 
		    }
		});
	});
	// 初始化datagrid组件
	function initTreegrid() {
		$dg = $('#dg_id');
		$dg.treegrid({
			url: 'uupm/org/findTree',
		    width: 'auto',
		    height: $(this).height()-commonui.remainHeight-20-$('#tb_id').height(),
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: true,
			border: false,
			striped: true,
			singleSelect: true,
			toolbar: '#tb_id',
			idField: 'id_',
			treeField: 'orgName',
			loadFilter: function(result) {
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
				$dg.treegrid('select', node.id_);
				$('#mm_id').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			},
	    	frozenColumns: [[
	    	                 {field: 'orgName', title: '名称', width: 200, align: 'left'}
	    	                 ]],
	        columns: [[
						{field: 'orgCode', title: '编号', width: 100, align: 'left'},
						{field: 'parentCode', title: '父编号', width: 100, align: 'left'},
						{field: 'rsPath', title: '路径', width: 100, align: 'left'},
						{field: 'seqNo', title: '类型', width: 100, align: 'left'},
						{field: 'remark', title: '备注', width: 100, align: 'left'},
	                   ]]
		});
	}
	
	// 清除搜索条件
	function cleanSearch() {
		$('#searchbox_id_app').searchbox('setValue', '');
	}
	
	// 打开添加对话框
	function openAddDlg() {
		parent.$.modalDialog({
			title: "添加",
			width: 800,
			height: 400,
			href: 'views/uupm/organization/organizationEditDlg.jsp',
			onLoad:function() {
				var editForm = parent.$.modalDialog.handler.find("#form_id");
				editForm.find('input[name="parentCode"]').val("-1");
			},
			buttons: [{
				text: '确定',
				iconCls: 'icon-ok',
				handler: function() {
					parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
					parent.$.modalDialog.openner = $dg;//定义对话框关闭要刷新的组件
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					editForm.attr("action", "uupm/org/add");
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
	
	// 打开添加对话框--child
	function openAddDlg_child() {
		var row = $dg.treegrid('getSelected');
		if(row) {
			parent.$.modalDialog({
				title: "添加子项",
				width: 800,
				height: 400,
				href: 'views/uupm/organization/organizationEditDlg.jsp',
				onLoad:function() {
					if(row) {
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.find('input[name="parentCode"]').val(row.orgCode);
					}
				},
				buttons: [{
					text: '确定',
					iconCls: 'icon-ok',
					handler: function() {
						parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
						parent.$.modalDialog.openner = $dg;//定义对话框关闭要刷新的组件
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.attr("action", "uupm/org/add");
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
	
	// 打开修改对话框
	function openEditDlg() {
		var row = $dg.treegrid('getSelected');
		if(row) {
			parent.$.modalDialog({
				title: "编辑",
				width: 800,
				height: 400,
				href: 'views/uupm/organization/organizationEditDlg.jsp',
				onLoad:function(){
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					editForm.form("load", row);
					editForm.find("#orgCode_id").attr('readonly',true);
				},
				buttons: [{
					text: '确定',
					iconCls: 'icon-ok',
					handler: function() {
						parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
						parent.$.modalDialog.openner = $dg;//定义对话框关闭要刷新的组件
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.attr("action", "uupm/org/edit");
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
		var row = $dg.treegrid('getSelected');
		if(row) {
			parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){  
			    if(r) {
			    	var arr_id = getItemCodesFromTree(row);
			    	var ids = arr_id.join(",");
			    	$.post("uupm/org/delBatch", {orgCodes:ids}, function(result) {
						if(result.status=='OK') {
							$dg.treegrid('remove', row.id_);
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
				msg : "请选择一行【资源】记录!",
				timeout : commonui.msg_timeout
			});
		}
	}
	// 递归获取tree的itemCode
	function getItemCodesFromTree(treeNode) {
		var arrs = [];
		if(treeNode) {
			arrs.push(treeNode.id_);
			if(treeNode.children) {
				$.each(treeNode.children, function(i, obj){
					arrs = arrs.concat(getItemCodesFromTree(obj));
				});
			}
		}
		return arrs;
	}
	
	// 展开
	function expandAll() {
		var node = $dg.treegrid('getSelected');
		if(node) {
			$dg.treegrid('expandAll', node.orgCode);
		} else {
			$dg.treegrid('expandAll');
		}
	}
	// 收缩
	function collapseAll() {
		var node = $dg.treegrid('getSelected');
		if(node) {
			$dg.treegrid('collapseAll', node.orgCode);
		} else {
			$dg.treegrid('collapseAll');
		}
	}
		
</script>
</head>
<body class="easyui-layout">

    <div data-options="region:'center',title:'机构列表'" style="padding:5px;">
	    <table id="dg_id"></table>
		<div id="tb_id" style="border-bottom:1px solid #cccccc;">
			<shiro:hasPermission name="org-add">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg();" href="javascript:void(0);">添加</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg_child();" href="javascript:void(0);">添加子项</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="org-edit">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="openEditDlg();" href="javascript:void(0);">编辑</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="org-del">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="removeFunc();" href="javascript:void(0);">删除</a>
			</shiro:hasPermission>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="expandAll();">展开</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="collapseAll();">收缩</a>
		</div>
		
		<div id="mm_id" class="easyui-menu" style="width:120px;">
			<div onclick="openAddDlg_child()" data-options="iconCls:'icon-add'">添加子项</div>
			<div onclick="openEditDlg()" data-options="iconCls:'icon-edit'">编辑</div>
			<div onclick="removeFunc()" data-options="iconCls:'icon-remove'">删除</div>
			<div class="menu-sep"></div>
	        <div onclick="expandAll()" data-options="iconCls:'icon-undo'">展开</div>
	        <div onclick="collapseAll()" data-options="iconCls:'icon-redo'">收缩</div>
		</div>
    </div>
		
</body>
</html>