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
	var $dg;
	$(function() {
		$dg = $('#dg_id');
		$dg.treegrid({
		    url:'sys/func/listMenu',
		    width: 'auto',
			height: $(this).height()-90,
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: true,
			border: true,
			striped: true,
			singleSelect: true,
			toolbar: '#tb_id',
			idField: 'funcCode',
			treeField: 'funcName',
		    loadFilter: function(data) {if(data.respData!=null) {return data.respData;} else {return [];}},
			frozenColumns: [[{field: 'funcName', title: '名称', width:parseInt($(this).width()*0.2),
								formatter:function(value){
									return '<span style="color:red">'+value+'</span>';
			                  	}	
							}]],
	        columns: [[
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
	
	/**
	 * 打开添加对话框
	 */
	function openAddDlg() {
		var row = $dg.treegrid('getSelected');
		parent.$.modalDialog({
			title: "添加",
			width: 600,
			height: 400,
			href: 'jsp/system/function/functionEditDlg.jsp',
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
					editForm.attr("action", "sys/func/add");
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
				href: "jsp/system/function/functionEditDlg.jsp",
				onLoad:function(){
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					if("-1"==row.parentCode) row.parentCode = "";
					editForm.form("load", row);
					editForm.find("#funcCode_id").attr('readonly',true);
				},
				buttons: [{
					text: '确定',
					iconCls: 'icon-ok',
					handler: function() {
						parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
						parent.$.modalDialog.openner = $dg;//定义对话框关闭要刷新的grid
						var editForm = parent.$.modalDialog.handler.find("#form_id");
						editForm.attr("action", "sys/func/edit");
						parent.$.modalDialog.handler.find('#funcCode_id').attr('readonly',true);
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
			    	$.post("sys/func/del", {funcCode: row.funcCode}, function(result) {
						if(result.statusCode=='OK') {
							$dg.treegrid('remove', row.funcCode);
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
			在此你可以对<span class="label-info"><strong>菜单功能</strong></span>进行编辑!  &nbsp;<span class="label-info"><strong>注意</strong></span>操作功能是对菜单功能的操作权限！
			请谨慎填写功能编码，权限区分标志，请勿重复!
		</p>
	</div>
	<div id="tb_id" style="padding:10px;height:auto">
		<div style="margin-bottom:5px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg();" href="javascript:void(0);">添加</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="openEditDlg();" href="javascript:void(0);">编辑</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="removeFunc();" href="javascript:void(0);">删除</a>
		</div>
	</div>
	
	<table id="dg_id" title="功能管理"></table>
		
</body>
</html>