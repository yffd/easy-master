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
<title>流程管理</title>
<base href="<%=basePath%>">
<jsp:include page="/common/layout/script.jsp"></jsp:include>

<script type="text/javascript">
	var $openWindow = this;// 当前窗口
	var $dg;
	$(function() {
		$dg = $('#dg_id');
		$dg.datagrid({
		    url:'workflow/definition/listPage',
		    width: 'auto',
			height: $(this).height()-120,
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
	        columns: [[
						{field: 'id', title: '流程定义ID', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'category', title: '流程定义类别', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'name', title: '流程定义名称', width: parseInt($(this).width()*0.15), align: 'left'},
						{field: 'key', title: '流程定义关键字', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'version', title: '版本号', width: parseInt($(this).width()*0.05), align: 'left'},
						{field: 'resourceName', title: 'XML名称', width: parseInt($(this).width()*0.15), align: 'left',
							formatter: function(value, row) {
								return '<a href="javascript:void(0);" onClick="loadResource(\''+row.id+'\',\'xml\');" width="950" style="color: blue">'+value+'</a>';
							}	
						},
						{field: 'dgrmResourceName', title: 'PNG名称', width: parseInt($(this).width()*0.15), align: 'left',
							formatter: function(value, row) {
								return '<a href="javascript:void(0);" onClick="loadResource(\''+row.id+'\',\'image\');" width="950" style="color: blue">'+value+'</a>';
							}	
						},
						{field: 'suspensionState', title: '状态', width: parseInt($(this).width()*0.05), align: 'left',
							formatter: function(value, row) {
								if(1==row.suspensionState)
									return "<font color=green>已激活<font>";
			            		else
			            			return "<font color=red>已挂起<font>";
							}
						},
						{field: 'deploymentId', title: '流程发布ID', width: parseInt($(this).width()*0.05), align: 'left'},
						{field: 'deployTime', title: '流程发布时间', width: parseInt($(this).width()*0.1), align: 'left',
							formatter: function(value, row) {
								return new Date(value).format("yyyy-MM-dd HH:mm:ss");
							}	
						},
						{field: 'operate', title: '操作', width: parseInt($(this).width()*0.1), align: 'center',
							formatter: function(value, row) {
								var text = "激活";
								if(1==row.suspensionState) text = "挂起";
								var accountCode = row.userCode;
								var a1 = '[<a href="javascript:void(0);" onClick="updateState(\''+row.id+'\',\''+row.suspensionState+'\');" width="950" style="color: blue">'+text+'</a>]';
								var a2 = '[<a href="javascript:void(0);" onclick="changeStatus(\''+accountCode+'\',\'A\');" width="950" style="color: blue">转化为Model</a>]';
								return a1 + '&nbsp;' + a2 + '&nbsp;';
							}	
						}
	                   ]]
		});
		
	});
	
	function loadResource(definitionId, resourceType) {
		var url = "workflow/definition/loadResource?definitionId="+definitionId+"&resourceType="+resourceType;
		window.open(url,"_blank");
	}
	
	function updateState(definitionId, definitionState) {
		var state = "active";
		if(1==definitionState) state = "suspend";
		var url = "workflow/definition/update/"+state+"/"+definitionId;
		$.post(url, {}, function(result) {
			if(result.statusCode=='OK') {
				$dg.datagrid('reload');
			}
			$.messager.show({
				title : "系统提示",
				msg : result.statusDesc,
				timeout : commonui.messager_timeout
			});
		}, "JSON").error(function() {
			$.messager.show({
				title : "系统提示",
				msg : result.statusDesc,
				timeout : commonui.messager_timeout
			});
		});
		
	}
	
	// 打开发布对话框
	function openAddDlg() {
		parent.$.modalDialog({
			title: "请选择上传文件",
			width: 600,
			height: 400,
			href: 'common/combo/fileUploaderCombo.jsp',
			onLoad:function(){
				parent.$.modalDialog.handler.find("#uploadURL").val('workflow/definition/deploy');
			}
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
				href: 'admin/pms/user/userEditDlg.jsp',
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
						editForm.attr("action", "pms/user/edit");
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
			    	$.post("pms/user/del", {userCode:row.userCode}, function(result) {
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

	// 高级查询
	function _search() {
		var params = {};
		var inputs = $('#searchForm_id input');
		if(inputs) {
			$.each(inputs, function(i, obj) {
				params[obj.name] = obj.value;
			});
		}
		$dg.datagrid('reload', params);
	}
	
	// 清除搜索条件
	function cleanSearch() {
		$('#searchForm_id input').val('');
	}
	
	// 激活账户
	function changeStatus(accountCode, status) {
		$.post("pms/user/changeStatus", {userCode:accountCode, userStatus:status}, function(result) {
			if(result.statusCode=='OK') {
				$dg.datagrid('reload');
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
	// 重置密码
	function resetPassword(accountCode) {
		parent.$.messager.confirm("提示","确定要重置密码吗?",function(r){  
		    if(r) {
		    	$.post("pms/user/resetPassword", {userCode:accountCode}, function(result) {
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
	}
</script>
</head>
<body class="easyui-layout,fit:true">
	<div data-options="region:'north',border:false,title:'高级查询',iconCls:'icon-search',collapsible:true" style="height:120px;overflow:hidden; align:left">
		<form id="searchForm_id" style="width:100%;height:100%;">
			<table cellpadding="0" cellspacing="0" style="width:100%;height:100%;padding:0px;margin:0px,auto;" class="tableForm">
				<tr>
					<th>名称：</th>
					<td>
						<input name="name" type="text" />
					</td>
					<th>关键字：</th>
					<td>
						<input name="key" type="text" />
					</td>
					<th>发布日期：</th>
					<td>
						<input type="text" name="startDate" id="startDate" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})"/>&nbsp;&nbsp;~&nbsp;&nbsp;
						<input type="text" name="endDate" id="endDate" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})"/>
					</td>
				</tr>
				<tr>
					<td colspan="5">
					</td>
					<td style="text-align:right;padding-right:20px;">
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">查询</a> 
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">重置</a>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	
	<div data-options="region:'center',border:false" style="overflow:hidden;">
		<table id="dg_id" title="流程定义列表"></table>
	</div>
	
	<div id="tb_id" style="display:none;padding:10px;height:auto">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td style="padding-left:2px;padding-bottom:2px;">
					<shiro:hasPermission name="user-add">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg();" href="javascript:void(0);">添加</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="user-del">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="removeFunc();" href="javascript:void(0);">删除</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</table>
	</div>
		
</body>
</html>