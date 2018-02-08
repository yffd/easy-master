<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function() {
	var taskId=parent.$.modalDialog.taskId;
	if(taskId) {
		var url = "workflow/activityNode/findVariables";
		$.post(url, {'taskId':taskId}, function(result) {
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
								{field:'variableName',title:'变量名称',width:280},
								{field:'value',title:'变量值',width:282}
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
								{field:'variableName',title:'变量名称',width:280},
								{field:'value',title:'变量值',width:282}
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
	<div data-options="region:'center',border:false" style="overflow: hidden;padding: 10px;">
		<fieldset>
			<legend>实例范围变量</legend>
			<div id="tb_id"></div>
		</fieldset>
		<div style="height:20px;"></div>
		<fieldset>
			<legend>任务节点范围变量</legend>
			<div id="tb_local_id"></div>
		</fieldset>
	</div>
</div>
