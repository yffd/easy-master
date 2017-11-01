<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
	.easyui-textbox {
		height: 18px;
		width: 170px;
		line-height: 16px;
	    /*border-radius: 3px 3px 3px 3px;*/
	    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	    transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
	}
	textarea:focus, input[type="text"]:focus {
	    border-color: rgba(82, 168, 236, 0.8);
	    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(82, 168, 236, 0.6);
	    outline: 0 none;
	}
	table {
	    background-color: transparent;
	    border-collapse: collapse;
	    border-spacing: 0;
	    max-width: 100%;
	}
	fieldset {
	    border: 0 none;
	    margin: 0;
	    padding: 0;
	}
	legend {
	    -moz-border-bottom-colors: none;
	    -moz-border-left-colors: none;
	    -moz-border-right-colors: none;
	    -moz-border-top-colors: none;
	    border-color: #E5E5E5;
	    border-image: none;
	    border-style: none none solid;
	    border-width: 0 0 1px;
	    color: #999999;
	    line-height: 20px;
	    display: block;
	    margin-bottom: 10px;
	    padding: 0;
	    width: 100%;
	}
	input, textarea {
	    font-weight: normal;
	}
	table, th, td {
		text-align:left;
		padding: 6px;
	}
</style>

<script type="text/javascript">
$(function() {
	$('#parentCode_id').combotree({
		url:"pmi/org/orgTree",
		idFiled:'id',
	 	textFiled:'text',
		editable:false,
		loadFilter:function(data,parent) {
			var jsonData = data.respData;
// 			var obj = {id:'', text:'顶层名称', checked:true, children:null};
// 			obj.children = data.respData;
// 			return [obj];
			return data.respData;
		},
		onBeforeExpand:function(node) {
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
	<div data-options="region:'center',border:false" style="overflow: hidden;padding: 10px;">
		<form id="form_id" method="post">
			<fieldset>
				<legend>编辑</legend>
				<table>
					<tr>
					    <th>名称</th>
						<td><input name="orgName" class="easyui-textbox easyui-validatebox" data-options="required:true" placeholder="请输入名称"/></td>
						<th>编码</th>
						<td><input id="orgCode_id" name="orgCode" class="easyui-textbox easyui-validatebox" data-options="required:true"/></td>
					 </tr>
					 <tr>
					    <th>主负责人</th>
						<td><input type="text" name="firstManagerCode" class="easyui-textbox"/></td>
						<th>副负责人</th>
						<td><input type="text" name="secondManagerCode" class="easyui-textbox"/></td>
					 </tr>
					 <tr>
					    <th>父机构</th>
						<td><input id="parentCode_id" name="parentCode" class="easyui-textbox"/></td>
					 </tr>
					 <tr>
						<th>描述</th>
						<td colspan="3"><textarea class="easyui-textbox" name="remark" style="width: 435px;height: 100px;"></textarea></td>
					</tr>	
					 
				</table>
			</fieldset>
		</form>
	</div>
</div>
