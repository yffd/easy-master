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
<title>工作流系统</title>
<base href="<%=basePath%>">
<jsp:include page="/common/layout/script.jsp"></jsp:include>

<script type="text/javascript">
	var $openWindow = this;// 当前窗口
	var $dg;
	$(function() {
		$dg = $('#dg_id');
		$dg.datagrid({
			url:'workflow/query/findHistoryInstanceListPage',
		    width: 'auto',
		    height: $(this).height()-commonui.remainHeight-$('.search-form-div').height(),
		    toolbar: '#tb_id',
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
						title :"系统提示",
						msg : result.msg,
						timeout : commonui.messager_timeout
					});
		    		return [];
	    		}
	    	},
	    	frozenColumns: [[
	    	                 {field: 'wfCategory', title: '流程类别', width: 100, align: 'left'},
	    	                 {field: 'wfName', title: '流程名称', width: 100, align: 'left'},
	    	                 {field: 'wfKey', title: '流程关键字', width: 100, align: 'left'},
	    	                 {field: 'wfVersion', title: '流程版本号', width: 100, align: 'left'}
// 	    	                 {field: 'wfDesc', title: '流程描述', width: 200, align: 'left'}  
	    	                 ]],
	        columns: [[
						{field: 'startUserId', title: '发起人ID', width: 100, align: 'left'},
						{field: 'startTime', title: '开始时间', width: 150, align: 'center',
							formatter: function(value, row) {
								return new Date(value).format("yyyy-MM-dd HH:mm:ss");
							}	
						},
						{field: 'endTime', title: '结束时间', width: 150, align: 'center',
							formatter: function(value, row) {
								return new Date(value).format("yyyy-MM-dd HH:mm:ss");
							}
						},
						{field: 'instanceId', title: '流程实例ID', width: 100, align: 'left'},
						{field: 'definitionId', title: '流程定义ID', width: 100, align: 'left'}
	                   ]]
		});
		
	});
	
	// 查看参数
	function findVariables() {
		var row = $dg.datagrid('getSelected');
		if(row) {
			parent.$.modalDialog.instanceId=row.instanceId;
			parent.$.modalDialog({
				title: "任务参数信息",
				width: 941,
				height: $(this).height()-200,
				href: 'views/workflow/historyInstance/historyVariablesDlg.jsp',
				buttons: [{
					text: '关闭',
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
				timeout : commonui.messager_timeout
			});
		}
	}
	
	// 流程实例图的跟踪
	function tarceWorkFlowByDiagram() {
		var row = $dg.datagrid('getSelected');
		if(row) {
			var url = 'workflow/modeler/tarceWorkFlowByDiagram?instanceId=' + row.instanceId;
			window.open(url,"_blank");
		} else {
			$.messager.show({
				title :"系统提示",
				msg :"请选择一行记录!",
				timeout : commonui.messager_timeout
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
	
</script>
</head>
<body class="easyui-layout,fit:true">
	<div class="search-form-div" data-options="region:'north',border:false,title:'高级查询',iconCls:'icon-search',collapsible:true">
		<div class="badge-div">
			<span class="badge-title">提示</span>
			<p style="margin:0px;padding:2px;">
				流程类别为<span class="badge-info"><strong>流程图中的Namespace</strong></span>，
				流程名称为<span class="badge-info"><strong>流程图中的Name</strong></span>，
				流程关键字为<span class="badge-info"><strong>流程图中的id</strong></span>！
			</p>
		</div>
		<form id="searchForm_id">
			<table class="search-form-table">
				<tr>
					<th>流程类别：</th>
					<td>
						<input name="wfCategory" type="text" />
					</td>
					<th>流程名称：</th>
					<td>
						<input name="wfName" type="text" />
					</td>
					<th>流程关键字：</th>
					<td>
						<input name="wfKey" type="text" />
					</td>
					<th>发起人ID：</th>
					<td>
						<input name="startUserId" type="text" />
					</td>
				</tr>
				<tr>
					<td colspan="7"></td>
					<td style="text-align:right;padding-right:20px;">
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:'true'" onclick="_search();">查询</a> 
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:'true'" onclick="cleanSearch();">重置</a>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	
	<div data-options="region:'center',border:false" style="overflow:hidden;">
		<table id="dg_id" title="查询列表"></table>
	</div>
	
	<div id="tb_id" style="display:none;height:10px;padding:2px;height:auto">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td style="padding-left:2px;padding-bottom:2px;">
					<shiro:hasPermission name="user-add">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:'true'" onclick="findVariables();" href="javascript:void(0);">查看变量</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:'true'" onclick="tarceWorkFlowByDiagram();" href="javascript:void(0);">查看流程轨迹</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</table>
	</div>
	
</body>
</html>