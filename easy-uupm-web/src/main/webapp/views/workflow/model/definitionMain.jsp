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
<title>业务工作流程</title>
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
			toolbar: '#tb_id',
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
	    	                 {field: 'workFlowCategoryCode', title: '类别编号', width: 100, align: 'left'},
	    	                 {field: 'workFlowCategoryName', title: '类别名称', width: 100, align: 'left'},
	    	                 {field: 'workFlowKey', title: '类别关键字', width: 100, align: 'left'},
	    	                 {field: 'workFlowVersion', title: '版本号', width: 50, align: 'left'}
// 	    	                 {field: 'workFlowDesc', title: '流程描述', width: 200, align: 'left'}  
	    	                 ]],
	        columns: [[
						{field: 'id', title: '流程定义ID', width: 80, align: 'left'},
						{field: 'resourceName', title: 'XML名称', width: 200, align: 'left',
							formatter: function(value, row) {
								return '<a href="javascript:void(0);" onClick="loadResource(\''+row.id+'\',\'xml\');" width="950" style="color: blue">'+value+'</a>';
							}	
						},
						{field: 'dgrmResourceName', title: 'PNG名称', width: 200, align: 'left',
							formatter: function(value, row) {
								return '<a href="javascript:void(0);" onClick="loadResource(\''+row.id+'\',\'image\');" width="950" style="color: blue">'+value+'</a>';
							}	
						},
						{field: 'definitionState', title: '状态', width: 100, align: 'left',
							formatter: function(value, row) {
								if(1==row.definitionState)
									return "<font color=green>已激活<font>";
			            		else
			            			return "<font color=red>已挂起<font>";
							}
						},
						{field: 'deploymentId', title: '流程发布ID', width: 100, align: 'left'},
						{field: 'deployTime', title: '流程发布时间', width: 150, align: 'left',
							formatter: function(value, row) {
								return new Date(value).format("yyyy-MM-dd HH:mm:ss");
							}	
						},
						{field: 'operate', title: '操作', width: 200, align: 'center',
							formatter: function(value, row) {
								var text = "激活";
								if(1==row.definitionState) text = "挂起";
								var accountCode = row.userCode;
								var a1 = '[<a href="javascript:void(0);" onClick="updateState(\''+row.id+'\',\''+row.definitionState+'\');" width="100" style="color: blue">'+text+'</a>]';
								var a2 = '[<a href="javascript:void(0);" onclick="changeStatus(\''+accountCode+'\',\'A\');" width="100" style="color: blue">转化为Model</a>]';
								return a1 + '&nbsp;' + a2 + '&nbsp;';
							}	
						}
	                   ]]
		});
		
		$('#versionState_id').combobox({
			panelHeight: 80,
		    textField:'label',
		    valueField:'value',
		    data: [{
				label: '所有版本',
				value: 'all'
			},{
				label: '最新版本',
				value: 'last',
				"selected": true
			}]
		});
		$('#definitionState_id').combobox({
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
	
	// 打开添加对话框
	function openAddDlg() {
		parent.$.modalDialog({
			title: "添加",
			width: 600,
			height: 400,
			href: '/views/workflow/definition/definitonEditDlg.jsp',
			buttons: [{
				text: '确定',
				iconCls: 'icon-ok',
				handler: function() {
					parent.$.modalDialog.openWindow = $openWindow;//定义打开对话框的窗口
					parent.$.modalDialog.openner = $dg;//定义对话框关闭要刷新的grid
					var editForm = parent.$.modalDialog.handler.find("#form_id");
					editForm.attr("action", "workflow/definition/addOne");
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
	
	// 打开发布对话框
	function uploadBpmn() {
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
		$('#versionState_id').combobox('select','last');
		$('#definitionState_id').combobox('select','');
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
					<th>版本：</th>
					<td>
						<input id="versionState_id" name="versionState" type="text" />
					</td>
					<th>状态：</th>
					<td>
						<input id="definitionState_id" name="definitionState" type="text" />
					</td>
					<th></th><td></td>
				</tr>
				<tr>
					<th>开始时间：</th>
					<td>
						<input type="text" name="searchStartTime" id="startTime" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\',{d:0});}'})"/>
					</td>
					<th>结束时间：</th><td>
						<input type="text" name="searchEndTime" id="endTime" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startTime\',{d:0});}'})"/>
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
	
	<div id="tb_id" style="display:none;padding:10px;height:auto">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td style="padding-left:2px;padding-bottom:2px;">
					<shiro:hasPermission name="user-add">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg();" href="javascript:void(0);">添加</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg();" href="javascript:void(0);">复制新建</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</table>
	</div>
		
</body>
</html>