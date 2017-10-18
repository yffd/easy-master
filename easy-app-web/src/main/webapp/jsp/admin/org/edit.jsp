<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {
	$('#parentCode_id').combotree({
		url:"sys/org/listTree",
		idFiled:'id',
	 	textFiled:'text',
		editable:false,
		loadFilter:function(data,parent) {
			var jsonData = data.respData;
			var obj = {id:'', text:'组织机构', checked:true, children:null};
			obj.children = data.respData;
			return [obj];
		},
		onBeforeExpand:function(node) {
			console.info(node);
			var children = $('#parentCode_id').tree("getChildren", node.target);
			if(children && children.length>0) {
				return true;
			} else {
				return false;
			}
		}
	});
	
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
			if(result.statusCode=='OK') {
				parent.$.modalDialog.handler.dialog('close');//打开此窗口时预定义的对象
				parent.$.modalDialog.openner.treegrid('reload');//打开此窗口时预定义的对象
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
	<div data-options="region:'center',fit:true,border:false">
<!-- 		<div style="position:absolute;top:0;left:0;bottom:0;right:0;width:50%;height:50%;margin:auto;"> -->
			<form id="form_id" method="post">
				<table width="100%" height="100%" style="border-collapse:separate; border-spacing:0px 10px;padding:20px;">
					<tr>
					    <th>名称</th>
						<td><input name="name" class="easyui-textbox easyui-validatebox" data-options="required:true" style="width:200px;height:20px;"/></td>
						<th>编码</th>
						<td><input id="code_id" name="code" class="easyui-textbox easyui-validatebox" data-options="required:true" style="width:200px;height:20px;"/></td>
					 </tr>
					 <tr>
					    <th>主负责人</th>
						<td><input type="text" name="firstManagerCode" class="easyui-textbox" style="width:200px;height:20px;"/></td>
						<th>副负责人</th>
						<td><input type="text" name="secondManagerCode" class="easyui-textbox" style="width:200px;height:20px;"/></td>
					 </tr>
					 <tr>
					    <th>电话</th>
						<td><input name="tel" type="text" class="easyui-textbox easyui-validatebox" style="width:200px;height:20px;"/></td>
						<th>传真</th>
						<td><input name="fax" type="text" class="easyui-textbox easyui-validatebox" style="width:200px;height:20px;"/></td>
					 </tr>
					 <tr>
					    <th>父机构</th>
						<td><input id="parentCode_id" name="parentCode" class="easyui-textbox" style="width:200px;height:20px;"/></td>
					 </tr>
					 <tr>
						<th>描述</th>
						<td colspan="3"><textarea class="easyui-textbox" name="remark"  style="width:530px;height:60px;"></textarea></td>
					</tr>
				</table>
			</form>
<!-- 		</div> -->
	</div>
</div>
