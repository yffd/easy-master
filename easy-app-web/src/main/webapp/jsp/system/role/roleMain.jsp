<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能管理</title>
<base href="<%=basePath%>">
<jsp:include page="/jsp/index/layout/script.jsp"></jsp:include>

<script type="text/javascript">
	var $openWindow = this;// 当前窗口
	var $dg_role;
	var $dg_func;
	$(function() {
		$("#panel").panel({
			width:'auto',
			height:$(this).height(),
			title: '角色管理',   
		});
		
		$dg_role = $('#role_dg_id');
		$dg_role.datagrid({
		    url:'sys/role/findPage',
		    width: 'auto',
			height: $(this).height()-120,
			pagination: true,
			pageSize: commonui.pageSize,
			rownumbers: true,
			animate: true,
			collapsible: false,
			fitColumns: true,
			border: true,
			striped: true,
			singleSelect: true,
			toolbar: '#role_tb_id',
		    loadFilter: function(data) {if(data.respData!=null) {return data.respData;} else {return [];}},
            onDblClickRow: function(rowIndex, rowData) {
            	findRoleFunc(rowIndex, rowData);
            },
	        columns: [[
						{field: 'roleName', title: '名称', width: parseInt($(this).width()*0.3), align: 'left'},
						{field: 'roleCode', title: '编号', width: parseInt($(this).width()*0.3), align: 'left'},
						{field: 'remark', title: '描述', width: parseInt($(this).width()*0.4), align: 'left'}
	                   ]],
		});
		
		//搜索框
		$("#role_searchbox_id").searchbox({
			menu:"#mm_role",
			prompt :'请输入',
// 			height: 28,
			searcher:function(value, name) {
				var obj = {};
				obj[name] = value;
				$dg_role.datagrid('reload', obj); 
		    }
		});

		/****************************************************************/
		$dg_func = $('#func_dg_id');
		$dg_func.treegrid({
		    url:'sys/func/listAllMenu',
		    width: 'auto',
			height: $(this).height()-120,
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: true,
			border: true,
			striped: true,
			singleSelect: false,
			cascadeCheck: true,
			toolbar: '#func_tb_id',
			idField: 'funcCode',
			treeField: 'funcName',
			parentField: 'parentCode',
		    loadFilter: function(data) {if(data.respData!=null) {return data.respData;} else {return [];}},
	        columns: [[
	                    {field: 'ck', checkbox: true},
	                    {field: 'funcName', title: '名称', width:parseInt($(this).width()*0.2)},
						{field: 'funcCode', title: '编号', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'parentCode', title: '父编号', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'url', title: '链接地址', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'type', title: '类型', width: parseInt($(this).width()*0.1), align: 'left',
							formatter: function(value, row) {
								if("M"==row.type)
									return "<font color=green>菜单<font>";
			            		else
			            			return "<font color=red>操作<font>";  
							}
						},
						{field: 'active', title: '是否启用', width: parseInt($(this).width()*0.1), align: 'left',
							formatter: function(value, row) {
								if("A"==row.active)
									return "<font color=green>是<font>";
			            		else
			            			return "<font color=red>否<font>";
							}
						},
						{field: 'sort', title: '排序编码', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'iconCls', title: '图标', width: parseInt($(this).width()*0.1), align: 'left',
							formatter: function(value, row) {
								return "<span class='"+row.iconCls+"' style='display:inline-block;vertical-align:middle;width:16px;height:16px;'></span>";
							}
						},
						{field: 'remark', title: '描述', width: parseInt($(this).width()*0.1), align: 'left'}
	                   ]]
		});

	});
	
	// 打开添加对话框
	function openAddDlg() {
		parent.$.modalDialog({
			title: "添加",
			width: 600,
			height: 400,
			href: 'jsp/system/role/roleEditDlg.jsp',
			buttons: [{
				text: '确定',
				iconCls: 'icon-ok',
				handler: function() {
					parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
					parent.$.modalDialog.openner = $dg_role;//定义对话框关闭要刷新的grid
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					editForm.attr("action", "sys/role/add");
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
		var row = $dg_role.datagrid('getSelected');
		if(row) {
			parent.$.modalDialog({
				title: "编辑",
				width: 600,
				height: 400,
				href: 'jsp/system/role/roleEditDlg.jsp',
				onLoad:function(){
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					editForm.form("load", row);
					editForm.find("#roleCode_id").attr('readonly',true);
				},
				buttons: [{
					text: '确定',
					iconCls: 'icon-ok',
					handler: function() {
						parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
						parent.$.modalDialog.openner = $dg_role;//定义对话框关闭要刷新的grid
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.attr("action", "sys/role/edit");
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
		var row = $dg_role.datagrid('getSelected');
		if(row) {
			parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){  
			    if(r) {
			    	$.post("sys/role/del", {roleCode:row.roleCode}, function(result) {
						if(result.statusCode=='OK') {
							var rowIndex = $dg_role.datagrid('getRowIndex', row);
							$dg_role.datagrid('deleteRow', rowIndex);
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
		$('#role_searchbox_id').searchbox('setValue', '');
	}
	
	// 展开
	function expandAll() {
		var node = $dg_func.treegrid('getSelected');
		if(node) {
			$dg_func.treegrid('expandAll', node.funcCode);
		} else {
			$dg_func.treegrid('expandAll');
		}
	}
	// 收缩
	function collapseAll() {
		var node = $dg_func.treegrid('getSelected');
		if(node) {
			$dg_func.treegrid('collapseAll', node.funcCode);
		} else {
			$dg_func.treegrid('collapseAll');
		}
	}
	// 刷新
	function refresh() {
		$dg_func.treegrid('reload');
	}
	// 为角色分配功能
	function saveRoleFunc() {
		var func_selections = $dg_func.treegrid('getSelections');
		var func_checked_codes = [];
		if(func_selections) {
			$.each(func_selections, function(i, obj) {
				func_checked_codes.push(obj.funcCode);
			});
		}
		var role_selection = $dg_role.datagrid('getSelected');
		if(role_selection) {
			var roleCode = role_selection.roleCode;
			$.ajax({
				url:"sys/role/saveRoleFunc",
				data: "roleCode=" + roleCode + "&funcCodes=" + (func_checked_codes.length==0?"":func_checked_codes),
				success: function(result) {
					$.messager.show({
						title :"系统提示",
						msg : result.statusDesc,
						timeout : 1000 * 2
					});
				},
				error:function(){
					$.messager.show({
						title :"系统提示",
						msg : "分配失败！",
						timeout : 1000 * 2
					});
				}
			});
		} else {
			parent.$.messager.show({
				title :"系统提示",
				msg : "请选择角色",
				timeout : 1000 * 2
			});
		}
	}
	
	function findRoleFunc(rowIndex, rowData) {
		$.post("sys/role/findRoleFunc", {roleCode:rowData.roleCode}, function(result) {
			if(result.statusCode=='OK') {
				$dg_func.treegrid('unselectAll');
				var data = result.respData;
				if(data.length>0) {
					$.each(data, function(i, n) {
						$dg_func.treegrid('select', n.funcCode);
					});
				} else {
					$.messager.show({
						title :"系统提示",
						msg : "该角色暂无权限",
						timeout : 1000 * 2
					});
				}
			} else {
				$.messager.show({
					title :"系统提示",
					msg : "获取权限失败",
					timeout : 1000 * 2
				});
			}
			
		});
	}
	
</script>
</head>
<body>
	<div id="panel" data-options="border:false">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',border:false" style="height: 82px; overflow: hidden; padding: 5px;">
				<div class="well well-small">
					<span class="badge">提示</span>
					<p>
						请<span class="label-info"><strong>双击角色</strong></span>查看所属功能！
						超级管理员默认拥有<span class="label-info"><strong>所有功能！</strong></span>
					</p>
				</div>
			</div>
			
			<div data-options="region:'west',split:true,border:true" style="width:500px;">
				<div id="role_tb_id" style="padding:2px 0">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="2" style="padding-left:2px;padding-bottom:2px;">
								<a class="easyui-linkbutton" data-options="iconCls:'icon-config',plain:'true'" onclick="saveRoleFunc();" href="javascript:void(0);">保存设置</a>
								<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg();" href="javascript:void(0);">添加</a>
								<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="openEditDlg();" href="javascript:void(0);">编辑</a>
								<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="removeFunc();" href="javascript:void(0);">删除</a>
							</td>
						</tr>
						<tr>
							<td style="padding-left:2px;padding-bottom:2px;width:25px;">
								<input id="role_searchbox_id" type="text"/>
							</td>
							<td style="padding-left:2px">
								<a class="easyui-linkbutton" data-options="iconCls:'icon-clear',plain:'true'" onclick="clearSearch();" href="javascript:void(0);"></a>
							</td>
						</tr>
					</table>
				</div>
				<table id="role_dg_id" title="角色"></table>
				<div id="mm_role">
					<div name="roleName">角色名称</div>
					<div name="roleCode">角色编号</div>
				</div>
			</div>
			
			<div data-options="region:'center',split:true,border:true">
				<div id="func_tb_id">
					<div style="margin:2px;">
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="expandAll();">展开</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="collapseAll();">收缩</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="refresh();">刷新</a>
					</div>
				</div>
				<table id="func_dg_id" title="功能"></table>
			</div>
		</div>
	</div>
		
</body>
</html>