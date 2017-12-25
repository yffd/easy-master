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
		    url:'workflow/instance/listPage',
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
						{field: 'instanceId', title: '流程实例ID', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'activityNodeId', title: '当前活动节点ID', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'instanceState', title: '流程实例状态', width: parseInt($(this).width()*0.1), align: 'left',
							formatter: function(value, row) {
								if(1==row.instanceState)
									return "<font color=green>已激活<font>";
			            		else
			            			return "<font color=red>已挂起<font>";
							}
						},
						{field: 'definitionId', title: '流程定义ID', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'definitionKey', title: '流程定义KEY', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'definitionName', title: '流程定义名称', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'definitionCategory', title: '流程定义类别', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'definitionVersion', title: '流程定义版本号', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'deployId', title: '流程发布ID', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'operate', title: '操作', width: parseInt($(this).width()*0.1), align: 'center',
							formatter: function(value, row) {
								var text = "激活";
								if(1==row.instanceState) text = "挂起";
								var accountCode = row.userCode;
								var a1 = '[<a href="javascript:void(0);" onClick="updateState(\''+row.id+'\',\''+row.instanceState+'\');" width="950" style="color: blue">'+text+'</a>]';
								var a2 = '[<a href="javascript:void(0);" onclick="tarceWorkFlowByDiagram(\''+row.id+'\');" width="950" style="color: blue">跟踪流程图</a>]';
								var a2 = '[<a href="javascript:void(0);" onclick="queryInstanceParams(\''+row.id+'\');" width="950" style="color: blue">查看实例参数</a>]';
								return a1 + '&nbsp;' + a2 + '&nbsp;';
							}	
						}
	                   ]]
		});
		
	});
	
	// 激活、挂起流程实例
	function updateState(instanceId, instanceState) {
		var state = "active";
		if(1==instanceState) state = "suspend";
		var url = "workflow/instance/update/"+state+"/"+instanceId;
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
	
	// 查看流程实例作用域范围参数
	function queryInstanceParams(instanceId) {
		
	}
	
	// 流程实例图的跟踪
	function tarceWorkFlowByDiagram(instanceId) {
		var url = 'workflow/instance/tarceWorkFlowByDiagram?instanceId=' + instanceId;
		window.open(url,"_blank");
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
		<table id="dg_id" title="流程实例列表"></table>
	</div>
	
</body>
</html>