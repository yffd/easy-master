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
		$dg_category.treegrid({
			url:'uupm/dictionary/listCategory',
		    width: 'auto',
		    height: $(this).height()-commonui.remainHeight-20,
		    fit:true,
			rownumbers: false,
			animate: true,
			collapsible: true,
			fitColumns: true,
			fit:true,
			border: false,
			striped: true,
			singleSelect: true,
			showHeader: false,
			toolbar: '#tb_id_category',
			idField: 'itemCode',
			treeField: 'itemName',
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
				$dg_category.treegrid('select', node.itemCode);
				$('#mm').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			},
			onClickRow: function(row) {
				$dg_dictionary.treegrid('loadData', {'clean':'true'});
				$.post('uupm/dictionary/listDictTree', {'parentCode':row.itemCode}, function(result) {
							$('#dg_id_dictionary').treegrid('loadData', result);
						}, 'json');
			},
			frozenColumns: [[{field: 'itemName', title: '', width:parseInt($(this).width())}]]
		});
		
		makeDictionaryGrid();
	});
	
	function makeDictionaryGrid() {
		$dg_dictionary = $('#dg_id_dictionary');
		$dg_dictionary.treegrid({
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
			showHeader: true,
			toolbar: '#tb_id_dictionary',
			idField: 'itemCode',
			treeField: 'itemName',
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
				$dg_dictionary.treegrid('select', node.itemCode);
				$('#mm_dictionary').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			},
			frozenColumns: [[{field: 'itemName', title: '名称', width:300,align: 'left'}]],
	        columns: [[
						{field: 'itemCode', title: '编号', width: 100, align: 'left'},
						{field: 'parentCode', title: '父编号', width: 100, align: 'left'},
						{field: 'seqNo', title: '序号', width: 100, align: 'left'},
// 						{field: 'dataLabel', title: '标签', width: 100, align: 'left'},
// 						{field: 'dataScope', title: '范围', width: 100, align: 'left'},
						{field: 'remark', title: '描述', width: 100, align: 'left'}
	                   ]]
		});
	}
	
	/************************************************/
	// 打开添加对话框
	function openAddDlg() {
		parent.$.modalDialog({
			title: "添加",
			width: 800,
			height: 400,
			href: 'views/uupm/dictionary/dictionaryEditDlg.jsp',
			onLoad:function() {
				var row = $dg_category.treegrid('getSelected');
				var editForm = parent.$.modalDialog.handler.find("#form_id");
				editForm.find('input[name="dataScope"]').val('CATEGORY');
				if(row) {
					editForm.find('input[name="parentCode"]').val(row.itemCode);
				}
			},
			buttons: [{
				text: '确定',
				iconCls: 'icon-ok',
				handler: function() {
					parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
					parent.$.modalDialog.openner = $dg_category;//定义对话框关闭要刷新的组件
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					editForm.attr("action", "uupm/dictionary/addCategory");
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
		var row = $dg_category.treegrid('getSelected');
		if(row) {
			parent.$.modalDialog({
				title: "编辑",
				width: 800,
				height: 400,
				href: 'views/uupm/dictionary/dictionaryEditDlg.jsp',
				onLoad:function(){
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					editForm.form("load", row);
					editForm.find("#itemCode_id").attr('readonly',true);
				},
				buttons: [{
					text: '确定',
					iconCls: 'icon-ok',
					handler: function() {
						parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
						parent.$.modalDialog.openner = $dg_category;//定义对话框关闭要刷新的组件
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.attr("action", "uupm/dictionary/edit");
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
		var row = $dg_category.treegrid('getSelected');
		if(row) {
			parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){  
			    if(r) {
			    	// 字典treegrid
			    	var arr_dict_codes = [];
			    	var dictData = $dg_dictionary.treegrid('getData');
			    	if(dictData && dictData.length>0) {
			    		$.each(dictData, function(i, obj) {
			    			var arr_tmp = getItemCodesFromTree(obj);
			    			if(arr_tmp && arr_tmp.length>0) arr_dict_codes = arr_dict_codes.concat(arr_tmp);
			    		});
			    	}
			    	// 分类treegrid
			    	var arr_category_id = getItemCodesFromTree(row);
			    	arr_category_id = arr_category_id.concat(arr_dict_codes);	//合并
			    	
			    	var _itemCodes = arr_category_id.join(",");
			    	
			    	$.post("uupm/dictionary/delBatch", {itemCodes: _itemCodes}, function(result) {
						if(result.status=='OK') {
							$dg_category.treegrid('remove', row.itemCode); //分类：移除row
							if(arr_dict_codes && arr_dict_codes.length>0) { //字典：移除row
					    		$.each(arr_dict_codes, function(i, obj) {
					    			$dg_dictionary.treegrid('remove', obj);
					    		});
					    	}
							
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
	// 递归获取tree的itemCode
	function getItemCodesFromTree(treeNode) {
		var arrs = [];
		if(treeNode) {
			arrs.push(treeNode.itemCode);
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
		var node = $dg_category.treegrid('getSelected');
		if(node) {
			$dg_category.treegrid('expandAll', node.itemCode);
		} else {
			$dg_category.treegrid('expandAll');
		}
	}
	// 收缩
	function collapseAll() {
		var node = $dg_category.treegrid('getSelected');
		if(node) {
			$dg_category.treegrid('collapseAll', node.itemCode);
		} else {
			$dg_category.treegrid('collapseAll');
		}
	}
	// 刷新
	function refresh() {
		$dg_category.treegrid('reload');
	}
	
	/*********************************************************/
	
	// 打开添加对话框
	function openAddDlg_dictionary() {
		var row = $dg_category.treegrid('getSelected');
		if(row) {
			parent.$.modalDialog({
				title: "添加",
				width: 800,
				height: 400,
				href: 'views/uupm/dictionary/dictionaryEditDlg.jsp',
				onLoad:function() {
					if(row) {
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.find('input[name="parentCode"]').val(row.itemCode);
						editForm.find('input[name="dataScope"]').val('DICT');
					}
				},
				buttons: [{
					text: '确定',
					iconCls: 'icon-ok',
					handler: function() {
						parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
						parent.$.modalDialog.openner_dictionary = $dg_dictionary;//定义对话框关闭要刷新的组件
						parent.$.modalDialog.openner_dictionary_pcode = row.itemCode;//定义对话框关闭要刷新的组件
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.attr("action", "uupm/dictionary/addDict");
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
				msg : "请选择【数据分类】!",
				timeout : commonui.msg_timeout
			});
		}
	}
	
	// 打开添加对话框-子项
	function openAddDlg_dictionary_child() {
		var row = $dg_dictionary.treegrid('getSelected');
		if(row) {
			parent.$.modalDialog({
				title: "添加子项",
				width: 800,
				height: 400,
				href: 'views/uupm/dictionary/dictionaryEditDlg.jsp',
				onLoad:function() {
					if(row) {
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.find('input[name="parentCode"]').val(row.itemCode);
						editForm.find('input[name="dataScope"]').val('DICT');
					}
				},
				buttons: [{
					text: '确定',
					iconCls: 'icon-ok',
					handler: function() {
						parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
						parent.$.modalDialog.openner_dictionary = $dg_dictionary;//定义对话框关闭要刷新的组件
						parent.$.modalDialog.openner_dictionary_pcode = row.dataLabel;//定义对话框关闭要刷新的组件
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.attr("action", "uupm/dictionary/addChildDict");
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
	function openEditDlg_dictionary() {
		var row = $dg_dictionary.treegrid('getSelected');
		if(row) {
			parent.$.modalDialog({
				title: "编辑",
				width: 800,
				height: 400,
				href: 'views/uupm/dictionary/dictionaryEditDlg.jsp',
				onLoad:function(){
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					editForm.form("load", row);
					editForm.find("#itemCode_id").attr('readonly',true);
				},
				buttons: [{
					text: '确定',
					iconCls: 'icon-ok',
					handler: function() {
						parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
						parent.$.modalDialog.openner_dictionary = $dg_dictionary;//定义对话框关闭要刷新的组件
						parent.$.modalDialog.openner_dictionary_pcode = row.dataLabel;//定义对话框关闭要刷新的组件
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.attr("action", "uupm/dictionary/edit");
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
	function removeFunc_dictionary() {
		var row = $dg_dictionary.treegrid('getSelected');
		if(row) {
			parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){  
			    if(r) {
			    	var arr_id = getItemCodesFromTree(row);
			    	var ids = arr_id.join(",");
			    	$.post("uupm/dictionary/delBatch", {itemCodes:ids}, function(result) {
						if(result.status=='OK') {
							$dg_dictionary.treegrid('remove', row.itemCode);
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
	
	// 展开
	function expandAll_dictionary() {
		var node = $dg_dictionary.treegrid('getSelected');
		if(node) {
			$dg_dictionary.treegrid('expandAll', node.itemCode);
		} else {
			$dg_dictionary.treegrid('expandAll');
		}
	}
	// 收缩
	function collapseAll_dictionary() {
		var node = $dg_dictionary.treegrid('getSelected');
		if(node) {
			$dg_dictionary.treegrid('collapseAll', node.itemCode);
		} else {
			$dg_dictionary.treegrid('collapseAll');
		}
	}
	
</script>
</head>
<body class="easyui-layout">
    <div data-options="region:'west',title:'数据分类',split:true,border:true" style="width:400px;">
		<table id="dg_id_category"></table>
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
		
		<div id="mm" class="easyui-menu" style="width:120px;">
			<div onclick="openAddDlg()" data-options="iconCls:'icon-add'">添加</div>
			<div onclick="openEditDlg()" data-options="iconCls:'icon-edit'">编辑</div>
			<div onclick="removeFunc()" data-options="iconCls:'icon-remove'">删除</div>
			<div class="menu-sep"></div>
	        <div onclick="expandAll()" data-options="iconCls:'icon-undo'">展开</div>
	        <div onclick="collapseAll()" data-options="iconCls:'icon-redo'">收缩</div>
		</div>
    </div>

    <div data-options="region:'center',title:'字典列表'" style="padding:5px;">
	    <table id="dg_id_dictionary"></table>
		<div id="tb_id_dictionary" style="border-bottom:1px solid #cccccc;">
			<shiro:hasPermission name="org-add">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg_dictionary();" href="javascript:void(0);">添加</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg_dictionary_child();" href="javascript:void(0);">添加子项</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="org-edit">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="openEditDlg_dictionary();" href="javascript:void(0);">编辑</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="org-del">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="removeFunc_dictionary();" href="javascript:void(0);">删除</a>
			</shiro:hasPermission>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="expandAll_dictionary();">展开</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="collapseAll_dictionary();">收缩</a>
		</div>
		
		<div id="mm_dictionary" class="easyui-menu" style="width:120px;">
			<div onclick="openAddDlg_dictionary_child()" data-options="iconCls:'icon-add'">添加子项</div>
			<div onclick="openEditDlg_dictionary()" data-options="iconCls:'icon-edit'">编辑</div>
			<div onclick="removeFunc_dictionary()" data-options="iconCls:'icon-remove'">删除</div>
			<div class="menu-sep"></div>
	        <div onclick="expandAll_dictionary()" data-options="iconCls:'icon-undo'">展开</div>
	        <div onclick="collapseAll_dictionary()" data-options="iconCls:'icon-redo'">收缩</div>
		</div>
    </div>
		
</body>
</html>