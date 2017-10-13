<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织机构管理</title>
<base href="<%=basePath%>">
<jsp:include page="/jsp/system/layout/script.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$("#iconCls").combobox({
			width:171,
			data:$.iconData,
			formatter: function(v){
				console.info(v);
				return $.formatString('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.value);
			}
		});
		$("#pid").combotree({
			width:171,
			url:"orgz/organizationAction!findOrganizationList.action",
			idFiled:'id',
		 	textFiled:'name',
		 	parentField:'pid'
		});
		$("#form").form({
			url :"orgz/organizationAction!persistenceOrganization.action",
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.status) {
					parent.reload;
					parent.$.modalDialog.openner.treegrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_datagrid这个对象，是因为role.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
					parent.$.messager.show({
						title : result.title,
						msg : result.message,
						timeout : 1000 * 2
					});
				}else{
					parent.$.messager.show({
						title :  result.title,
						msg : result.message,
						timeout : 1000 * 2
					});
				}
			}
		});
	});
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" style="overflow: hidden;padding: 10px;">
			<form id="form" method="post">
				<fieldset>
					<legend>编辑</legend>
					 <table>
						 <tr>
						    <th>组织名称</th>
							<td><input type="text" name="name" placeholder="请输入名称" class="easyui-textbox easyui-validatebox" data-options="required:true"/></td>
							<th>组织编码</th>
							<td><input type="text" name="code" placeholder="请输入编码" class="easyui-textbox easyui-validatebox" data-options="required:true"/></td>
						 </tr>
<!-- 						 <tr> -->
<!-- 						    <th>负责人</th> -->
<!-- 							<td><input type="text" name="managerCode" id="managerCode_id" class="easyui-textbox easyui-validatebox"/></td> -->
<!-- 							<th>副负责人</th> -->
<!-- 							<td><input type="text" name="deputyManagerCode" id="deputyManagerCode_id" class="easyui-textbox easyui-validatebox"/></td> -->
<!-- 						 </tr> -->
						 <tr>
							<th>简称</th>
							<td><input name="shortName" type="text" class="easyui-textbox easyui-validatebox"/></td>
						    <th>人员数</th>
							<td><input name="staffNum" type="text" class="easyui-textbox easyui-validatebox"/></td>
						 </tr>
						  <tr>
						    <th>上层组织</th>
							<td><input name="parentCode" id="parentCode_id" type="text" class="easyui-textbox easyui-validatebox"/></td>
							<th>组织图标</th>
							<td><input name="iconCls" id="iconCls_id" type="text" class="easyui-textbox"/></td>
						 </tr>
						 <tr>
						    <th>电话</th>
							<td><input name="tel" type="text" class="easyui-textbox easyui-validatebox"/></td>
							<th>传真</th>
							<td><input name="fax" type="text" class="easyui-textbox easyui-validatebox"/></td>
						 </tr>
						 <tr>
							<th>描述</th>
							<td colspan="3"><textarea class="easyui-textbox" name="description"  style="width: 420px;height: 100px;"></textarea></td>
						</tr>
					 </table>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>