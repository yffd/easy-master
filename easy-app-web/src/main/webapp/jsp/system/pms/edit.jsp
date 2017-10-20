<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {
	$("#form_id").form({
// 		url: "sys/pms/add",
		onSubmit: function() {
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			var isValid = $(this).form('validate');
			if(!isValid) {
				parent.$.messager.progress('close');
			}
			return isValid;
		},
		success: function(result) {
			parent.$.messager.progress('close');
			result = $.parseJSON(result);
			if(result.statusCode=='OK') {
				parent.$.modalDialog.handler.dialog('close');//打开此窗口时预定义的对象
				parent.$.modalDialog.openner.datagrid('reload');//打开此窗口时预定义的对象
				parent.$.modalDialog.openWindow.$.messager.show({
					title : '系统提示',
					msg : result.statusDesc,
					timeout : 1000 * 2
				});
			} else {
				parent.$.modalDialog.openWindow.$.messager.show({
					title : '系统提示',
					msg : result.statusDesc,
					timeout : 1000 * 2
				});
			}
		}
	});
});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" style="text-align:center;">
		<div style="position:absolute;top:0;left:0;bottom:0;right:0;width:50%;height:50%;margin:auto;">
			<form id="form_id" method="post">
				<table style="border-collapse:separate; border-spacing:0px 10px;">
					<tr>
						<td>名称：</td>
						<td><input name="name" class="easyui-textbox easyui-validatebox" data-options="required:true" style="width:200px;height:20px;"/></td>
					</tr>
					<tr>
						<td>编号：</td>
						<td><input id="code_id" name="code" class="easyui-textbox easyui-validatebox" data-options="required:true" style="width:200px;height:20px;"/></td>
					</tr>
					<tr>
						<td>描述：</td><td>
						<textarea name="remark" class="easyui-textbox" style="width:200px;height:50px;"></textarea></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
