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
					<legend><img src="extend/fromedit.png" style="margin-bottom: -3px;"/> 组织编辑</legend>
					<input name="organizationId" id="organizationId"  type="hidden"/>
					<input name="created" id="created"  type="hidden"/>
					<input name="creater" id="creater"  type="hidden"/>
					<input name="status" id="status"  type="hidden"/>
					 <table>
						 <tr>
						    <th>组织名称</th>
							<td><input name="fullName" id="fullName" placeholder="请输入组织名称" class="easyui-textbox easyui-validatebox" type="text" data-options="required:true"/></td>
							<th>组织编码</th>
							<td><input name="myid" id="myid" type="text"  class="easyui-textbox easyui-validatebox" data-options="required:true"/></td>
						 </tr>
						 <tr>
						    <th>英文名称</th>
							<td><input name="ename" id="ename" type="text" class="easyui-textbox easyui-validatebox"/></td>
							<th>简称</th>
							<td><input id="shortName" name="shortName" type="text" class="easyui-textbox easyui-validatebox"/></td>
						 </tr>
						  <tr>
						    <th>上层组织</th>
							<td><input id="pid" name="pid" type="text" class="easyui-textbox easyui-validatebox"/></td>
							<th>组织图标</th>
							<td><input id=iconCls name="iconCls" type="text" class="easyui-textbox"/></td>
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