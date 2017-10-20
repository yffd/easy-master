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
<jsp:include page="/jsp/index/layout/script.jsp"></jsp:include>

<script type="text/javascript">
	var $openWindow = this;// 当前窗口
	var $dg_role;
	var $dg_func;
	$(function() {
		$("#panel").panel({
			width:'auto',
			height:$(this).height(),
			title: '角色管理',   
		});
		
		$dg_role = $('#dg_role_id');
		$dg_role.datagrid({
		    url:'sys/user/listPage',
		    width: 'auto',
			height: $(this).height()-90,
			pagination: true,
			pageSize: commonui.pageSize,
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: true,
			border: true,
			striped: true,
			singleSelect: true,
			toolbar: '#tb_id',
			idField: 'userCode',
			treeField: 'userName',
		    loadFilter: function(data) {if(data.respData!=null) {return data.respData;} else {return [];}},
			frozenColumns: [[{field: 'userName', title: '名称', width:parseInt($(this).width()*0.2),
								formatter:function(value){
									return '<span style="color:red">'+value+'</span>';
			                  	}	
							}]],
	        columns: [[
						{field: 'userCode', title: '编号', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'account', title: '账号', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'password', title: '密码', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'orgName', title: '机构', width: parseInt($(this).width()*0.1), align: 'left',
							formatter: function(value, row) {
								return row.sysOrganization.orgName; 
							}
						},
						{field: 'active', title: '是否启用', width: parseInt($(this).width()*0.1), align: 'left',
							formatter: function(value, row) {
								if("A"==row.active)
									return "<font color=green>是<font>";
			            		else
			            			return "<font color=red>否<font>";
							}
						},
						{field: 'tel', title: '电话', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'email', title: '邮箱', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'remark', title: '描述', width: parseInt($(this).width()*0.1), align: 'left'}
	                   ]]
		});
		
		//搜索框
		$("#searchbox_id").searchbox({
			menu:"#mm",
			prompt :'请输入',
			height: 28,
			searcher:function(value, name) {
				var obj = {};
				obj[name] = value;
				$dg.datagrid('reload', obj); 
		    }
		});

	});
	

</script>
</head>
<body>
	<div id="panel" data-options="border:false">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',border:false" style="height: 82px; overflow: hidden; padding: 5px;">
				<div class="well well-small">
					<span class="badge">提示</span>
					<p>
						请<span class="label-info"><strong>双击角色</strong></span>查看所属资源！
						超级管理员默认拥有<span class="label-info"><strong>所有权限！</strong></span>
					</p>
				</div>
			</div>
		</div>
		
		<div data-options="region:'west',split:true,border:true" style="width:500px;">
			<div id="tbRole" style="padding:2px 0">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td style="padding-left:2px;padding-bottom:2px;">
							<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="openAddDlg();" href="javascript:void(0);">添加</a>
							<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="openEditDlg();" href="javascript:void(0);">编辑</a>
							<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="removeFunc();" href="javascript:void(0);">删除</a>
						</td>
						<td style="padding-left:2px;padding-bottom:2px;">
							<input id="searchbox_id" type="text"/>
						</td>
					</tr>
				</table>
			</div>
			<table id="dg_role_id" title="角色"></table>
			<div id="mm">
				<div name="roleName">角色名称</div>
				<div name="roleCode">角色编号</div>
			</div>
		</div>
		
		<div data-options="region:'center',border:true">
			<div id="tb_id">
				<div style="margin:5px 5px 5px 5px;">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="expandAll();">展开</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="collapseAll();">收缩</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="refresh();">刷新</a>
				</div>
			</div>
			<table id="dg_func_id" title="功能"></table>
		</div>
	</div>
		
</body>
</html>