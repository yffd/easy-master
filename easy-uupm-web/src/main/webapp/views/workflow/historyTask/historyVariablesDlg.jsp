<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function() {
	var instanceId=parent.$.modalDialog.instanceId;
	var dialogWidth=parseInt(parent.$.modalDialog.width-34);
	if(instanceId) {
		var url = "workflow/historyActivityNode/findVariables";
		$.post(url, {'instanceId':instanceId}, function(result) {
			if(result.status=='OK') {
				var tmpData = result.data.rows;
				if(tmpData) {
					var paramData = [];
					var localParamData = [];
					$.each(tmpData, function(i, v) {
						if(v.local) {
							localParamData.push(v);
						} else {
							paramData.push(v);
						}
					});
					if(paramData.length>0) {
						$('#tb_id').datagrid({
							fitColumns: false,
							border: true,
							striped: true,
						    data: paramData,
						    columns:[[
								{field:'variableName',title:'变量名称',width:parseInt(dialogWidth*0.2)},
								{field:'variableTypeName',title:'变量类型',width:parseInt(dialogWidth*0.2)},
								{field:'value',title:'变量值',width:parseInt(dialogWidth*0.2)},
								{field:'createTime',title:'创建时间',width:parseInt(dialogWidth*0.2),
									formatter: function(value, row) {
										return new Date(value).format("yyyy-MM-dd HH:mm:ss");
									}	
								},
								{field:'lastUpdatedTime',title:'最后修改时间',width:parseInt(dialogWidth*0.2),
									formatter: function(value, row) {
										return new Date(value).format("yyyy-MM-dd HH:mm:ss");
									}	
								}
						    ]]
						});
					}
					if(localParamData.length>0) {
						$('#tb_local_id').datagrid({
							fitColumns: false,
							border: true,
							striped: true,
						    data: localParamData,
						    columns:[[
								{field:'variableName',title:'变量名称',width:parseInt(dialogWidth*0.125)},
								{field:'variableTypeName',title:'变量类型',width:parseInt(dialogWidth*0.125)},
								{field:'value',title:'变量值',width:parseInt(dialogWidth*0.125)},
								{field:'createTime',title:'创建时间',width:parseInt(dialogWidth*0.125),
									formatter: function(value, row) {
										return new Date(value).format("yyyy-MM-dd HH:mm:ss");
									}	
								},
								{field:'lastUpdatedTime',title:'最后修改时间',width:parseInt(dialogWidth*0.125),
									formatter: function(value, row) {
										return new Date(value).format("yyyy-MM-dd HH:mm:ss");
									}	
								},
								{field:'taskId',title:'任务ID',width:parseInt(dialogWidth*0.125)},
								{field:'activityNodeId',title:'节点ID',width:parseInt(dialogWidth*0.125)},
								{field:'activityNodeName',title:'节点名称',width:parseInt(dialogWidth*0.125)},
						    ]]
						});
					}
				}
			}
		}, "JSON").error(function() {
			$.messager.show({
				title : "系统提示",
				msg : commonui.error_msg,
				timeout : commonui.messager_timeout
			});
		});
	}
	
});
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" style="overflow: hidden;padding: 5px;">
		<fieldset>
			<legend>实例范围变量</legend>
			<div id="tb_id" style="overflow:hidden;"></div>
		</fieldset>
		<div style="height:20px;"></div>
		<fieldset>
			<legend>任务节点范围变量</legend>
			<div id="tb_local_id"></div>
		</fieldset>
	</div>
</div>
