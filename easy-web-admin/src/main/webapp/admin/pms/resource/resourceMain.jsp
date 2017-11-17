<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/admin/common/taglib/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理</title>
<base href="<%=basePath%>">
<jsp:include page="/admin/layout/script.jsp"></jsp:include>

<script type="text/javascript">
	var $openWindow = this;// 当前窗口
	var $dg;
	$(function() {
		$dg = $('#dg_id');
		$dg.treegrid({
		    url:'pms/resource/asyncList',
		    width: 'auto',
			height: $(this).height()-90,
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: false,
			border: true,
			striped: true,
			singleSelect: true,
			toolbar: '#tb_id',
			idField: 'rsCode',
			treeField: 'rsName',
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
			frozenColumns: [[{field: 'rsName', title: '名称', width:parseInt($(this).width()*0.2),
								formatter:function(value){
									return '<span style="color:red">'+value+'</span>';
			                  	}	
							}]],
	        columns: [[
						{field: 'rsCode', title: '编号', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'parentCode', title: '父编号', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'inUrl', title: '链接地址', width: parseInt($(this).width()*0.2), align: 'left'},
						{field: 'rsType', title: '类型', width: parseInt($(this).width()*0.1), align: 'left',
							formatter: function(value, row) {
								if("A"==row.rsType)
									return "<font color=green>应用<font>";
								else if("M"==row.rsType)
									return "<font color=green>菜单<font>";
			            		else
			            			return "<font color=red>操作<font>";  
							}
						},
						{field: 'rsState', title: '是否启用', width: parseInt($(this).width()*0.1), align: 'left',
							formatter: function(value, row) {
								if("A"==row.rsState)
									return "<font color=green>是<font>";
			            		else
			            			return "<font color=red>否<font>";
							}
						},
						{field: 'rsNum', title: '排序编码', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'remark', title: '描述', width: parseInt($(this).width()*0.1), align: 'left'}
	                   ]]
		});

	});
	
	/**
	 * 打开添加对话框
	 */
	function openAddDlg() {
		var row = $dg.treegrid('getSelected');
		parent.$.modalDialog({
			title: "添加",
			width: 600,
			height: 400,
			href: 'admin/pms/resource/resourceEditDlg.jsp',
			onLoad: function() {
				if(row){
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					editForm.form("load", {"parentCode": row.funcCode});
				}
			},
			buttons: [{
				text: '确定',
				iconCls: 'icon-ok',
				handler: function() {
					parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
					parent.$.modalDialog.openner = $dg;//定义对话框关闭要刷新的grid
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					editForm.attr("action", "pms/resource/add");
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
				width: 600,
				height: 400,
				href: 'admin/pms/resource/resourceEditDlg.jsp',
				onLoad:function(){
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					if("-1"==row.parentCode) row.parentCode = "";
					editForm.form("load", row);
					editForm.find("#rsCode_id").attr('readonly',true);
				},
				buttons: [{
					text: '确定',
					iconCls: 'icon-ok',
					handler: function() {
						parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
						parent.$.modalDialog.openner = $dg;//定义对话框关闭要刷新的grid
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.attr("action", "pms/resource/edit");
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
		var row = $dg.treegrid('getSelected');
		if(row) {
			parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){  
			    if(r) {
			    	$.post("pms/resource/del", {rsCode: row.rsCode}, function(result) {
						if(result.statusCode=='OK') {
							$dg.treegrid('remove', row.rsCode);
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

</script>
</head>
<body>
	<div class="well well-small" style="margin-left: 5px;margin-top: 5px">
		<span class="badge">提示</span>
		<p>
			在此你可以对<span class="label-info"><strong>系统资源</strong></span>进行编辑!  &nbsp;<span class="label-info"><strong>注意</strong></span>操作功能是对菜单功能的操作权限！
			请谨慎填写功能编码，权限区分标志，请勿重复!
		</p>
	</div>
	<div id="tb_id" style="padding:10px;height:auto">
		<div style="margin-bottom:5px">
			<shiro:hasPermission name="rs-add">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg();" href="javascript:void(0);">添加</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="rs-edit">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="openEditDlg();" href="javascript:void(0);">编辑</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="rs-del">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="removeFunc();" href="javascript:void(0);">删除</a>
			</shiro:hasPermission>
		</div>
	</div>
	
	<table id="dg_id" title="资源管理"></table>
		
</body>
</html>