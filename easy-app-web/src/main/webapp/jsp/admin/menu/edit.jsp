<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {
	$("#icon_id").combobox({
		data:commonui.iconData,
		valueField:'value',
	    textField:'text',
	    editable:false,
		formatter: function(v){
			return utils.formatString('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.text);
		}
	});
	$("#status_id").combobox({
		data:commonui.yesOrNoData,
		valueField:'value',
	    textField:'text',
	    editable:false,
		formatter: function(v){
			return utils.formatString('<span style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{0}', v.text);
		}
	});
	
	$.post("sys/menu/findByParentCode", {parentCode:"-1"}, function(data) {
		var jsonData = data.respData;
		jsonData.unshift({code:'-1', name:'顶层菜单', selected:true});
		$("#parentCode_id").combobox({
			data:jsonData,
			valueField:'code',
		    textField:'name',
		    editable:false
		});
	});
	
	$("#form_id").form({
		url: "sys/menu/add",
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
				parent.$.modalDialog.handler.dialog('close');
				parent.$.modalDialog.openner.tree('reload');//parent.$.modalDialog.openner#tree这个对象在列表页面已预定义
				$.messager.show({
					title : '系统提示',
					msg : result.statusDesc,
					timeout : 1000 * 2
				});
			} else {
				$.messager.show({
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
	<div data-options="region:'center',border:false">
		<form id="form_id" method="post">
			<table style="border-collapse:separate; border-spacing:0px 10px;margin:10px auto;width:300px;">
				<tr>
					<td>名称：</td>
					<td><input name="name" class="easyui-textbox easyui-validatebox" data-options="required:true" style="width:200px;height:20px;"/></td>
				</tr>
				<tr>
					<td>编号：</td>
					<td><input name="code" class="easyui-textbox easyui-validatebox" data-options="required:true" style="width:200px;height:20px;"/></td>
				</tr>
				<tr>
					<td>上级菜单：</td>
					<td><input id="parentCode_id" name="parentCode" class="easyui-textbox" style="width:203px;height:25px;"/></td>
				</tr>
				<tr>
					<td>内部链接：</td>
					<td><input name="url" class="easyui-textbox" style="width:200px;height:20px;"/></td>
				</tr>
				<tr>
					<td>图标：</td><td>
					<input id="icon_id" name="icon" class="easyui-textbox" style="width:203px;height:25px;"/></td>
				</tr>
				<tr>
					<td>排序：</td><td>
					<input name="sort" class="easyui-textbox" style="width:200px;height:20px;"/></td>
				</tr>
				<tr>
					<td>是否生效：</td><td>
					<input id="status_id" name="status" class="easyui-textbox" style="width:203px;height:25px;"/></td>
				</tr>
				<tr>
					<td>描述：</td><td>
					<textarea name="remark" class="easyui-textbox" style="width:200px;height:50px;"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>
