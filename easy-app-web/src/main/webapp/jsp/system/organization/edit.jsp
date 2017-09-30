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
							<td><input type="text" name="name" id="name_id" placeholder="请输入名称" class="easyui-textbox easyui-validatebox" data-options="required:true"/></td>
							<th>组织编码</th>
							<td><input type="text" name="code" id="code_id" class="easyui-textbox easyui-validatebox" data-options="required:true"/></td>
						 </tr>
<!-- 						 <tr> -->
<!-- 						    <th>负责人</th> -->
<!-- 							<td><input type="text" name="managerCode" id="managerCode_id" class="easyui-textbox easyui-validatebox"/></td> -->
<!-- 							<th>副负责人</th> -->
<!-- 							<td><input type="text" name="deputyManagerCode" id="deputyManagerCode_id" class="easyui-textbox easyui-validatebox"/></td> -->
<!-- 						 </tr> -->
						 <tr>
							<th>简称</th>
							<td><input name="shortName" id="shortName_id" type="text" class="easyui-textbox easyui-validatebox"/></td>
						    <th>人员数</th>
							<td><input name="staffNum" id="staffNum_id" type="text" class="easyui-textbox easyui-validatebox"/></td>
						 </tr>
						  <tr>
						    <th>上层组织</th>
							<td><input name="parentCode" id="parentCode_id" type="text" class="easyui-textbox easyui-validatebox"/></td>
							<th>组织图标</th>
							<td><input name="iconCls" id=iconCls type="text" class="easyui-textbox"/></td>
						 </tr>
						 <tr>
						    <th>电话</th>
							<td><input id="tel" name="tel" type="text" class="easyui-textbox easyui-validatebox"/></td>
							<th>传真</th>
							<td><input id=fax name="fax" type="text" class="easyui-textbox easyui-validatebox"/></td>
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