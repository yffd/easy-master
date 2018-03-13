<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function() {
	$("#form_id").form({
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
			if(result.status=='OK') {
				parent.$.modalDialog.handler.dialog('close');//打开此窗口时预定义的对象
				
				var dictionary_parentCode = parent.$.modalDialog.openner_dictionary_pcode;
				parent.$.modalDialog.openner_dictionary_pcode=undefined;
				if(dictionary_parentCode) {
					$.post('uupm/dictionary/listDictTree', {'parentCode':dictionary_parentCode}, function(result) {
						parent.$.modalDialog.openner_dictionary.treegrid('loadData', result);//打开此窗口时预定义的对象
					}, 'json');
				} else {
					parent.$.modalDialog.openner.treegrid('reload');//打开此窗口时预定义的对象
				}
			}
			parent.$.modalDialog.openWindow.$.messager.show({
				title :commonui.msg_title,
				msg : result.msg,
				timeout : commonui.msg_timeout
			});
		}
	});
});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" class="edit-form-div">
		<form id="form_id" method="post" style="width:100%;height:100%;">
			<input type="hidden" name="dataScope" value=""/>
			<input type="hidden" name="id" value=""/>
			<input type="hidden" name="parentCode" value="-1"/>
			<fieldset>
				<table class="edit-form-table">
					<tr>
						<th>名称：</th>
						<td><input name="itemName" class="easyui-textbox easyui-validatebox" required="required" placeholder="请输入名称" /></td>
						<th>编号：</th>
						<td><input id="itemCode_id" name="itemCode" class="easyui-textbox easyui-validatebox" required="required" placeholder="请输入编号"/></td>
					</tr>
					<tr>
						<th>序号：</th>
						<td><input name="seqNo" type="text" class="easyui-numberspinner" value="1" data-options="min:0,precision:0"/></td>
						<th></th>
						<td></td>
					</tr>
				 	<tr>
						<th>备注：</th>
						<td colspan="3"><textarea name="remark" class="easyui-textbox" style="width:600px;height:80px;"></textarea></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
</div>
