<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function() {
	var taskId=parent.$.modalDialog.taskId;
	if(taskId) {
		var url = "workflow/historyActivityNode/findCandidators";
		$.post(url, {'taskId':taskId}, function(result) {
			if(result.status=='OK') {
				var tmpUserData = result.data.users;
				if(tmpUserData) {
					var userData = [];
					$.each(tmpUserData, function(i, v) {
						userData.push({'value':v});
					});
					$('#user_tb_id').datagrid({
						fitColumns: false,
						border: true,
						striped: true,
					    data: userData,
					    columns:[[
							{field:'value',title:'获选人ID',width:562}
					    ]]
					});
				}
				
				var tmpRoleData = result.data.roles;
				if(tmpRoleData) {
					var roleData = [];
					$.each(tmpRoleData, function(i, v) {
						roleData.push({'value':v});
					});
					$('#role_tb_id').datagrid({
						fitColumns: false,
						border: true,
						striped: true,
					    data: roleData,
					    columns:[[
							{field:'value',title:'候选角色ID',width:562}
					    ]]
					});
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
			<legend>候选人</legend>
			<div id="user_tb_id"></div>
		</fieldset>
		<div style="height:20px;"></div>
		<fieldset>
			<legend>候选角色</legend>
			<div id="role_tb_id"></div>
		</fieldset>
	</div>
</div>
