<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function() {
	$('#workFlowCategoryCode_id').combotree('loadData', [{
		id: 1,
		text: 'OA流程类',
		children: [{
			id: 11,
			text: '行政类'
		},{
			id: 12,
			text: '人事类'
		}]
	}]);
});
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" style="overflow: hidden;padding: 5px;">
		<form id="form_id" method="post">
			<fieldset>
				<legend>编辑</legend>
				<table>
					<tr>
						<th>流程分类</th>
						<td><input id="workFlowCategoryCode_id" name="workFlowCategoryCode" class="easyui-textbox easyui-validatebox" required="required" placeholder="请输入名称"/></td>
						<th>流程标题</th>
						<td><input name="workFlowCategoryName" class="easyui-textbox easyui-validatebox" required="required" placeholder="请输入名称"/></td>
						<th>流程关键字</th>
						<td><input name="workFlowKey" class="easyui-textbox easyui-validatebox" required="required" placeholder="请输入key" /></td>
					</tr>
					<tr>
						<th>流程描述</th>
						<td colspan="5"><textarea name="workFlowDesc" class="easyui-textbox" style="width: 435px;height: 100px;"></textarea></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
</div>
