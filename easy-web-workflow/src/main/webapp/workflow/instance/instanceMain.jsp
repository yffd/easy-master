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
		    height: $(this).height()-commonui.remainHeight-$('.search-form-div').height(),
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
		    	if("OK"==result.statusCode) {
		    		return result.respData;
		    	} else {
		    		$.messager.show({
						title :"系统提示",
						msg : result.statusDesc,
						timeout : commonui.messager_timeout
					});
		    		return [];
	    		}
	    	},
	    	frozenColumns: [[
	    	                 {field: 'workFlowCategoryCode', title: '类别编号', width: 150, align: 'left'},
	    	                 {field: 'workFlowCategoryName', title: '类别名称', width: 150, align: 'left'},
	    	                 {field: 'workFlowKey', title: '类别关键字', width: 100, align: 'left'},
	    	                 {field: 'workFlowVersion', title: '版本号', width: 50, align: 'left'}
// 	    	                 {field: 'workFlowDesc', title: '流程描述', width: 200, align: 'left'}  
	    	                 ]],
	        columns: [[
						{field: 'instanceId', title: '流程实例ID', width: 100, align: 'left'},
						{field: 'activityNodeId', title: '当前活动节点编号', width: 200, align: 'left'},
						{field: 'instanceState', title: '流程实例状态', width: 100, align: 'left',
							formatter: function(value, row) {
								if(1==row.instanceState)
									return "<font color=green>已激活<font>";
			            		else
			            			return "<font color=red>已挂起<font>";
							}
						},
						{field: 'definitionId', title: '流程定义ID', width: 100, align: 'left'},
						{field: 'operate', title: '操作', width: 200, align: 'center',
							formatter: function(value, row) {
								var text = "激活";
								if(1==row.instanceState) text = "挂起";
								var accountCode = row.userCode;
								var a1 = '[<a href="javascript:void(0);" onClick="updateState(\''+row.id+'\',\''+row.instanceState+'\');" width="100" style="color: blue">'+text+'</a>]';
								var a2 = '[<a href="javascript:void(0);" onclick="tarceWorkFlowByDiagram(\''+row.id+'\');" width="100" style="color: blue">跟踪流程图</a>]';
								return a1 + '&nbsp;' + a2 + '&nbsp;';
							}	
						}
	                   ]]
		});
		
		$('#instanceState_id').combobox({
			panelHeight: 80,
		    textField:'label',
		    valueField:'value',
		    data: [{
				label: '全部',
				value: ''
			},{
				label: '已激活',
				value: '1'
			},{
				label: '已挂起',
				value: '2'
			}]
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
				msg : commonui.messager_msg,
				timeout : commonui.messager_timeout
			});
		});
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
		$('#instanceState_id').combobox('select','');
	}
	
</script>
</head>
<body class="easyui-layout,fit:true">
	<div class="search-form-div" data-options="region:'north',border:false,title:'高级查询',iconCls:'icon-search',collapsible:true">
		<div class="badge-div">
			<span class="badge-title">提示</span>
			<p style="margin:0px;padding:2px;">
				类别编号为<span class="badge-info"><strong>流程图中的Namespace</strong></span>，
				类别名称为<span class="badge-info"><strong>流程图中的Name</strong></span>，
				类别关键字为<span class="badge-info"><strong>流程图中的id</strong></span>！
			</p>
		</div>
		<form id="searchForm_id">
			<table class="search-form-table">
				<tr>
					<th>类别编号：</th>
					<td>
						<input name="workFlowCategoryCode" type="text" />
					</td>
					<th>类别名称：</th>
					<td>
						<input name="workFlowCategoryName" type="text" />
					</td>
					<th>类别关键字：</th>
					<td>
						<input name="workFlowKey" type="text" />
					</td>
				</tr>
				<tr>
					<th>节点编号：</th>
					<td>
						<input name="activityNodeId" type="text" />
					</td>
					<th>实例状态：</th>
					<td>
						<input id="instanceState_id" name="instanceState" type="text" />
					</td>
					<th></th><td></td>
				</tr>
				<tr>
					<td colspan="5"></td>
					<td style="text-align:right;padding-right:20px;">
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="_search();">查询</a> 
						<a href="javascript:void(0);" class="easyui-linkbutton" onclick="cleanSearch();">重置</a>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	
	<div data-options="region:'center',border:false" style="overflow:hidden;">
		<table id="dg_id" title="查询列表"></table>
	</div>
	
</body>
</html>