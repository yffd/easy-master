<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能管理</title>
<base href="<%=basePath%>">
<jsp:include page="/jsp/system/layout/script.jsp"></jsp:include>
<script type="text/javascript">
$(function() {
// 	$("#iconCls_id").combobox({
// // 		width:171,
// 		data:commonui.iconData,
// 		formatter: function(v){
// 			return utils.formatString('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.text);
// 		}
// 	});
	
	$('#menu_id').tree({
	    url:'sys/func/listMenu',
	    loadFilter: function(data) {if(data.respData!=null) {return data.respData;} else {return [];}},
	    onClick: function(node){
	    	$.post("sys/func/findByCode", {code:node.id}, function(data) {
	    		var jsonData = data.respData;
	    		$('input[name="name"]').val(jsonData.name);
	    		$('input[name="code"]').val(jsonData.code);
	    		$('input[name="shortName"]').val(jsonData.shortName);
	    		$('input[name="staffNum"]').val(jsonData.staffNum);
	    		$('input[name="parentCode"]').val(jsonData.parentCode);

				$("#iconCls_id").combobox({
					data:commonui.iconData,
					formatter: function(v){
						if(v.value==jsonData.icon) {v.selected=true;} else {v.selected=false;}
						return utils.formatString('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.text);
					}
				});
	    		
	    		$('input[name="tel"]').val(jsonData.tel);
	    		$('input[name="fax"]').val(jsonData.fax);
	    		$('textarea[name="remark"]').val(jsonData.remark);
	    		
	    		$('#edit_id').show();
	    	}, "json");
		}
	});
})


</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true,border:false" style="width:270px;">
			<div style="margin:10px 0;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="addRowsOpenDlg();" href="javascript:void(0);">添加</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="updRowsOpenDlg();" href="javascript:void(0);">编辑</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="removeNode();" href="javascript:void(0);">删除</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="removeNode();" href="javascript:void(0);">刷新</a>
			</div>
			
			<div class="easyui-panel" data-options="fit:true" style="padding:5px">
				<ul id="menu_id"></ul>
			</div>
		</div>
		
		<div id="edit_id" data-options="region:'center',fit:true,border:false" style="margin:80px 40px;display:none;">
			<form id="form" method="post">
				<fieldset style="width:500px;">
					<legend>编辑</legend>
					<input name="orgCode" id="orgCode_id"  type="hidden"/>
					<table>
						<tr>
						    <th>机构名称</th>
							<td><input type="text" name="name" class="easyui-textbox" data-options="required:true"/></td>
							<th>机构编码</th>
							<td><input type="text" name="code" class="easyui-textbox" data-options="required:true"/></td>
						</tr>
					 	<tr>
							<th>架构简称</th>
							<td><input name="shortName" type="text" class="easyui-textbox"/></td>
						    <th>机构人数</th>
							<td><input name="staffNum" type="text" class="easyui-textbox"/></td>
					 	</tr>
					  		<tr>
						    <th>上层机构</th>
							<td><input name="parentCode" id="parentCode_id" type="text" class="easyui-textbox"/></td>
							<th>机构图标</th>
							<td><input name="iconCls" id="iconCls_id" type="text" class="easyui-textbox"/></td>
					 	</tr>
					 	<tr>
						    <th>机构电话</th>
							<td><input name="tel" type="text" class="easyui-textbox easyui-validatebox"/></td>
							<th>机构传真</th>
							<td><input name="fax" type="text" class="easyui-textbox easyui-validatebox"/></td>
					 	</tr>
					 	<tr>
							<th>描述</th>
							<td colspan="3"><textarea class="easyui-textbox" name="remark" style="width: 350px;height: 100px;"></textarea></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td>
								<div style="margin:10px;text-align:right;">
									<button type="submit">保存</button>
								</div>
							</td>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>