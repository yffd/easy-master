<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理</title>
<base href="<%=basePath%>">
<jsp:include page="/jsp/system/layout/script.jsp"></jsp:include>
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
		jsonData.unshift({code:'-1', name:'顶层菜单'});
		$("#parentCode_id").combobox({
			data:jsonData,
			valueField:'code',
		    textField:'name',
		    editable:false
		});
	});
	//初始化tree，并绑定点击事件
	$('#tree_id').tree({
	    url:'sys/menu/tree',
	    loadFilter: function(data) {if(data.respData!=null) {return data.respData;} else {return [];}},
	    onClick: function(node){
	    	$.post("sys/menu/findByCode", {code:node.id}, function(data) {
	    		var jsonData = data.respData;
	    		$('input[name="name"]').val(jsonData.name);
	    		$('input[name="name"]').validatebox('validate');
	    		$('input[name="code"]').val(jsonData.code);
	    		$('input[name="code"]').validatebox('validate');
	    		$('input[name="code"]').attr('readonly',true);
	    		$('input[name="url"]').val(jsonData.url);
	    		$('input[name="parentCode"]').val(jsonData.parentCode);

				$('#icon_id').combobox('setValue', jsonData.icon);
				$('#status_id').combobox('setValue', jsonData.status);
				$('#parentCode_id').combobox('setValue', jsonData.parentCode);
				
	    		$('#sort_id').numberbox('setValue', jsonData.sort);
	    		$('#sort_id').validatebox('validate');
	    		$('textarea[name="remark"]').val(jsonData.remark);
	    		
	    		$('#info_id').show();
	    	}, "json");
		}
	});
	
	$("#form_id").form({
		url:"sys/menu/edit",
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
			$('#tree_id').tree('reload');
		}
	});
});

/**
 * 打开添加对话框
 */
function openAddDlg() {
	var openWindow = this;
	parent.$.modalDialog({
		title: "编辑",
		width: 600,
		height: 500,
		href: "jsp/admin/menu/edit.jsp",
		buttons: [{
			text: '确定',
			iconCls: 'icon-ok',
			handler: function() {
				parent.$.modalDialog.openWindow = openWindow;//定义打开对话框的窗口
				parent.$.modalDialog.openner = $('#tree_id');//定义对话框关闭要刷新的grid
				var editForm = parent.$.modalDialog.handler.find("#form_id");
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

/**
 * 刷新tree
 */
function reloadFunc() {
	$('#tree_id').tree('reload');
}

/**
 * 删除树节点
 */
function removeFunc() {
	var node = $('#tree_id').tree('getSelected');
	if(node) {
		parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){  
		    if(r) {
		    	$.post("sys/menu/del", {code:node.id}, function(result) {
					if(result.statusCode=='OK'){
						$('#tree_id').tree('remove', node.target);
						$.messager.show({
							title : "系统提示",
							msg : result.statusDesc,
							timeout : 1000 * 2
						});
					}
				}, "JSON").error(function() {
					$.messager.show({
						title : "系统提示",
						msg : result.statusDesc,
						timeout : 1000 * 2
					});
				});
		    }  
		});
	} else {
		$.messager.show({
			title :"系统提示",
			msg :"请选择一行记录!",
			timeout : 1000 * 2
		});
	}
}

</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',split:false,border:false" style="height:40px;">
			<div style="margin:5px;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg();" href="javascript:void(0);">添加</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="removeFunc();" href="javascript:void(0);">删除</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:'true'" onclick="reloadFunc();" href="javascript:void(0);">刷新</a>
			</div>
		</div>
		<div data-options="region:'center',split:false,border:false,fit:true" >
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'west',split:true,border:true" style="width:280px">
					<ul id="tree_id" style="margin:10px;"></ul>
				</div>
				
				<div data-options="region:'center',border:false">
					<div id="info_id" style="margin:10px;display:none;">
						<form id="form_id" method="post">
							<table style="border-collapse:separate; border-spacing:0px 10px;">
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
									<input id="sort_id" name="sort" class="easyui-numberbox easyui-validatebox" data-options="min:1,precision:1,required:true" style="width:200px;height:20px;"/></td>
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
							<div style="margin-left: 66px; margin-top: 20px;">
								<div>
									<button type="submit">保存</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>