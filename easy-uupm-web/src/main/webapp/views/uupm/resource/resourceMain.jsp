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
	var $json_rsStatus = [ {id:"", text:"全部", "selected": true} ];
	var $json_rsType = [ {id:"", text:"全部", "selected": true} ];
	var $openWindow = this;// 当前窗口
	var $dg_app;
	var $dg_res;
	$(function() {
		$dg_app = $('#dg_id_app');
		// 初始化控件数据
		$.post('/uupm/combox/findComboByDict', 
				{'comboxKeys':'func-status,rs-type'}, 
				function(result) {
					if("OK"==result.status) {
						var jsonData = result.data;
						$json_rsStatus = $json_rsStatus.concat(jsonData['func-status']);
						$json_rsType = $json_rsType.concat(jsonData['rs-type']);

						initDatagrid_app();	// 初始化datagrid组件
						initTreegrid_res();
					}
				}, 'json');
		//搜索框-app
		$("#searchbox_id_app").searchbox({
			menu:"#mm_id_app",
			prompt :'请输入',
// 			height: 28,
			searcher:function(value, name) {
				var obj = {};
				obj[name] = value;
				$dg_app.datagrid('reload', obj); 
		    }
		});
	});
	// 初始化datagrid组件
	function initDatagrid_app() {
		$dg_app.datagrid({
		    url:'uupm/app/findList',
		    width: 'auto',
		    height: $(this).height()-commonui.remainHeight-20-$('#tb_id_app').height(),
			pagination: true,
			pageSize: commonui.pageSize,
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: true,
			border: false,
			striped: true,
			singleSelect: true,
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
	    	onClickRow: function(rowIndex, rowData) {
	    		$dg_res.treegrid('loadData', {'clean':true});
				$.post('uupm/resource/findTree', {'appCode':rowData.appCode}, function(result) {
					$('#dg_id_res').treegrid('loadData', result);
				}, 'json');
	    	},
	    	frozenColumns: [[
	    	                 {field: 'appName', title: '名称', width: 200, align: 'left'}
	    	                 ]],
	        columns: [[
						{field: 'appCode', title: '编号', width: 100, align: 'left'},
						{field: 'remark', title: '备注', width: 100, align: 'left'}
	                   ]]
		});
	}
	function initTreegrid_res() {
		$dg_res = $('#dg_id_res');
		$dg_res.treegrid({
		    width: 'auto',
		    height: $(this).height()-commonui.remainHeight-20-$('#tb_id_res').height(),
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: true,
			border: false,
			striped: true,
			singleSelect: true,
			toolbar: '#tb_id_res',
			idField: 'id_',
			treeField: 'rsName',
			loadFilter: function(result) {
				if(result.clean) {
		    		return [];
		    	}
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
				$dg_res.treegrid('select', node.id_);
				$('#mm_id_res').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			},
	    	frozenColumns: [[
	    	                 {field: 'rsName', title: '名称', width: 200, align: 'left'}
	    	                 ]],
	        columns: [[
						{field: 'rsCode', title: '编号', width: 100, align: 'left'},
						{field: 'parentCode', title: '父编号', width: 100, align: 'left'},
						{field: 'rsPath', title: '路径', width: 100, align: 'left'},
						{field: 'rsType', title: '类型', width: 100, align: 'left',
							formatter: function(value, row) {
								return utils.fmtDict($json_rsType, value);
							}	
						},
						{field: 'rsStatus', title: '状态', width: 100, align: 'left',
							formatter: function(value, row) {
								return utils.fmtDict($json_rsStatus, value);
							}	
						},
						{field: 'remark', title: '备注', width: 100, align: 'left'},
	                   ]]
		});
	}
	
	// 设置控件选中
	function setComboForSelected(selectForm) {
		selectForm.find('#rsStatus_id').combobox({
			editable:false,
			panelHeight: 120,
			valueField:'id',
		    textField:'text',
		    data: $.grep($json_rsStatus, function(n,i){
		    	if(i==1) n['selected']=true;
		    	return i > 0;
		    })
		});
		selectForm.find('#rsType_id').combobox({
			editable:false,
			panelHeight: 120,
			valueField:'id',
		    textField:'text',
		    data: $.grep($json_rsType, function(n,i){
		    	if(i==1) n['selected']=true;
		    	return i > 0;
		    })
		});
	}
	
	// 清除搜索条件
	function cleanSearch() {
		$('#searchbox_id_app').searchbox('setValue', '');
	}
	
	// 打开添加对话框
	function openAddDlg() {
		var row = $dg_app.datagrid('getSelected');
		if(row) {
			parent.$.modalDialog({
				title: "添加",
				width: 800,
				height: 400,
				href: 'views/uupm/resource/resourceEditDlg.jsp',
				onLoad:function() {
					if(row) {
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.find('input[name="appCode"]').val(row.appCode);
						editForm.find('input[name="parentCode"]').val(row.appCode);
						setComboForSelected(editForm);
					}
				},
				buttons: [{
					text: '确定',
					iconCls: 'icon-ok',
					handler: function() {
						console.info(row);
						parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
						parent.$.modalDialog.openner_res = $dg_res;//定义对话框关闭要刷新的组件
						parent.$.modalDialog.openner_res_appCode = row.appCode;
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.attr("action", "uupm/resource/add");
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
				msg : "请选择【应用系统】!",
				timeout : commonui.msg_timeout
			});
		}
		
	}
	
	// 打开添加对话框--child
	function openAddDlg_child() {
		var row = $dg_res.treegrid('getSelected');
		if(row) {
			parent.$.modalDialog({
				title: "添加子项",
				width: 800,
				height: 400,
				href: 'views/uupm/resource/resourceEditDlg.jsp',
				onLoad:function() {
					if(row) {
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.find('input[name="appCode"]').val(row.appCode);
						editForm.find('input[name="parentCode"]').val(row.rsCode);
						setComboForSelected(editForm);
					}
				},
				buttons: [{
					text: '确定',
					iconCls: 'icon-ok',
					handler: function() {
						parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
						parent.$.modalDialog.openner_res = $dg_res;//定义对话框关闭要刷新的组件
						parent.$.modalDialog.openner_res_appCode = row.appCode;
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.attr("action", "uupm/resource/add");
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
				msg : "请选择一行【资源】记录!",
				timeout : commonui.msg_timeout
			});
		}
	}
	
	// 打开修改对话框
	function openEditDlg() {
		var row = $dg_res.treegrid('getSelected');
		if(row) {
			parent.$.modalDialog({
				title: "编辑",
				width: 800,
				height: 400,
				href: 'views/uupm/resource/resourceEditDlg.jsp',
				onLoad:function(){
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					setComboForSelected(editForm);
					editForm.form("load", row);
					editForm.find("#rsCode_id").attr('readonly',true);
				},
				buttons: [{
					text: '确定',
					iconCls: 'icon-ok',
					handler: function() {
						parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
						parent.$.modalDialog.openner_res = $dg_res;//定义对话框关闭要刷新的组件
						parent.$.modalDialog.openner_res_appCode = row.appCode;
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.attr("action", "uupm/resource/edit");
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
				msg : "请选择一行【资源】记录!",
				timeout : commonui.msg_timeout
			});
		}
	}
	
	// 删除
	function removeFunc() {
		var row = $dg_res.treegrid('getSelected');
		if(row) {
			parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){  
			    if(r) {
			    	var arr_id = getItemCodesFromTree(row);
			    	var ids = arr_id.join(",");
			    	$.post("uupm/resource/delBatch", {rsCodes:ids}, function(result) {
						if(result.status=='OK') {
							$dg_res.treegrid('remove', row.id_);
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
		var node = $dg_res.treegrid('getSelected');
		if(node) {
			$dg_res.treegrid('expandAll', node.rsCode);
		} else {
			$dg_res.treegrid('expandAll');
		}
	}
	// 收缩
	function collapseAll() {
		var node = $dg_res.treegrid('getSelected');
		if(node) {
			$dg_res.treegrid('collapseAll', node.rsCode);
		} else {
			$dg_res.treegrid('collapseAll');
		}
	}
		
</script>
</head>
<body class="easyui-layout">
    <div data-options="region:'west',title:'应用系统列表',split:true,border:true" style="width:500px;">
	    <div id="tb_id_app" style="background-color: #F5F5F5;padding-left:25px;">
	    	<table cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding-left:2px;padding-bottom:2px;">
						<input id="searchbox_id_app" type="text"/>
					</td>
					<td style="padding-left:2px">
						<a class="easyui-linkbutton" data-options="iconCls:'icon-clear',plain:'true'" onclick="cleanSearch();" href="javascript:void(0);"></a>
					</td>
				</tr>
			</table>
		</div>
		<table id="dg_id_app"></table>
		
		<div id="mm_id_app">
			<div name="appName">应用名称</div>
			<div name="appCode">应用编号</div>
		</div>
    </div>

    <div data-options="region:'center',title:'资源列表'" style="padding:5px;">
	    <table id="dg_id_res"></table>
		<div id="tb_id_res" style="border-bottom:1px solid #cccccc;">
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
		
		<div id="mm_id_res" class="easyui-menu" style="width:120px;">
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