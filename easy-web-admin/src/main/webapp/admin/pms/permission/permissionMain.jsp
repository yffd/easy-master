<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/admin/common/taglib/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理</title>
<base href="<%=basePath%>">
<jsp:include page="/admin/layout/script.jsp"></jsp:include>

<script type="text/javascript">
	var $openWindow = this;// 当前窗口
	var $dg_role;
	var $dg_user;
	$(function() {
		$("#panel").panel({
			width:'auto',
			height:$(this).height(),
			title: '权限管理',   
		});
		
		$dg_role = $('#role_dg_id');
		$dg_role.datagrid({
		    url:'pms/role/findList',
		    width: 'auto',
			height: $(this).height()-120,
			pagination: false,
			rownumbers: true,
			animate: true,
			collapsible: false,
			fitColumns: true,
			border: true,
			striped: true,
			toolbar: '#role_tb_id',
			idField: 'id',
			loadFilter: function(result) {
		    	if("OK"==result.statusCode) {
		    		return result.respData;
		    	} else {
		    		$.messager.show({
						title :"系统提示",
						msg : result.statusDesc,
						timeout : 1000 * 4
					});
		    		return [];
	    		}
	    	},
	        columns: [[
						{field: 'ck', checkbox:true},
						{field: 'roleName', title: '名称', width: parseInt($(this).width()*0.2), align: 'left'},
						{field: 'roleCode', title: '编号', width: parseInt($(this).width()*0.2), align: 'left'},
						{field: 'remark', title: '描述', width: parseInt($(this).width()*0.2), align: 'left'}
	                   ]],
		});
		
		//搜索框
		$("#role_searchbox_id").searchbox({
			menu:"#mm_role",
			prompt :'请输入',
// 			height: 28,
			searcher:function(value, name) {
				var obj = {};
				obj[name] = value;
				$dg_role.datagrid('reload', obj); 
		    }
		});

		/****************************************************************/
		$dg_user = $('#user_dg_id');
		$dg_user.datagrid({
			url:'pms/user/findPage',
		    width: 'auto',
			height: $(this).height()-120,
			pagination: true,
			pageSize: commonui.pageSize,
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: true,
			border: true,
			striped: true,
			singleSelect: true,
			toolbar: '#user_tb_id',
		    loadFilter: function(data) {if(data.respData!=null) {return data.respData;} else {return [];}},
		    onDblClickRow: function(rowIndex, rowData) {
            	findRoleUser(rowIndex, rowData);
            },
	        columns: [[
						{field: 'userName', title: '名称', width: parseInt($(this).width()*0.2), align: 'left'},
						{field: 'userCode', title: '编号', width: parseInt($(this).width()*0.1), align: 'left'},
						{field: 'orgName', title: '机构', width: parseInt($(this).width()*0.1), align: 'left',
							formatter: function(value, row) {
								return row.organization.orgName; 
							}
						},
						{field: 'userStatus', title: '状态', width: parseInt($(this).width()*0.1), align: 'left',
							formatter: function(value, row) {
								if("A"==row.userStatus)
									return "<font color=green>激活<font>";
			            		else
			            			return "<font color=red>冻结<font>";
							}
						},
// 						{field: 'tel', title: '电话', width: parseInt($(this).width()*0.1), align: 'left'},
// 						{field: 'email', title: '邮箱', width: parseInt($(this).width()*0.1), align: 'left'},
// 						{field: 'remark', title: '描述', width: parseInt($(this).width()*0.1), align: 'left'}
	                   ]]
		});
		
		//搜索框
		$("#user_searchbox_id").searchbox({
			menu:"#mm_user",
			prompt :'请输入',
// 			height: 28,
			searcher:function(value, name) {
				var obj = {};
				obj[name] = value;
				$dg_user.datagrid('reload', obj); 
		    }
		});

	});
	
	// 清除搜索条件
	function clearSearch_role() {
		$('#role_searchbox_id').searchbox('setValue', '');
	}
	// 清除搜索条件
	function clearSearch_user() {
		$('#user_searchbox_id').searchbox('setValue', '');
	}
	
	// 为用户分配角色
	function saveRoleUser() {
		var role_selections = $dg_role.datagrid('getSelections');
		var role_checked_codes = [];
		if(role_selections) {
			$.each(role_selections, function(i, obj) {
				role_checked_codes.push(obj.roleCode);
			});
		}
		var user_selection = $dg_user.datagrid('getSelected');
		if(user_selection) {
			var userCode = user_selection.userCode;
			$.ajax({
				url:"pms/role/saveRoleUser",
				data: "userCode=" + userCode + "&roleCodes=" + (role_checked_codes.length==0?"":role_checked_codes),
				success: function(result) {
					$.messager.show({
						title :"系统提示",
						msg : result.statusDesc,
						timeout : 1000 * 2
					});
				},
				error:function(){
					$.messager.show({
						title :"系统提示",
						msg : "分配失败！",
						timeout : 1000 * 2
					});
				}
			});
		} else {
			parent.$.messager.show({
				title :"系统提示",
				msg : "请选择用户",
				timeout : 1000 * 2
			});
		}
	}
	// 双击事件
	function findRoleUser(rowIndex, rowData) {
		$.post("pms/role/findRoleUser", {userCode:rowData.userCode}, function(result) {
			if(result.statusCode=='OK') {
				$dg_role.datagrid('unselectAll');
				var data = result.respData;
				if(data.length>0) {
					$.each(data, function(i, n) {
						$dg_role.datagrid('selectRecord', n.id);
					});
				} else {
					$.messager.show({
						title :"系统提示",
						msg : "该用户暂无角色",
						timeout : 1000 * 2
					});
				}
			} else {
				$.messager.show({
					title :"系统提示",
					msg : "获取角色失败",
					timeout : 1000 * 2
				});
			}
			
		});
	}
	
</script>
</head>
<body>
	<div id="panel" data-options="border:false">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',border:false" style="height: 82px; overflow: hidden; padding: 5px;">
				<div class="well well-small">
					<span class="badge">提示</span>
					<p>
						请<span class="label-info"><strong>双击用户</strong></span>查看所属角色！
						超级管理员默认拥有<span class="label-info"><strong>所有角色！</strong></span>
					</p>
				</div>
			</div>
			
			<div data-options="region:'west',split:true,border:true" style="width:800px;">
				<div id="user_tb_id" style="padding:2px 0">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td colspan="2" style="padding-left:2px;padding-bottom:2px;">
								<shiro:hasPermission name="pms-set">
								<a class="easyui-linkbutton" data-options="iconCls:'icon-config',plain:'true'" onclick="saveRoleUser();" href="javascript:void(0);">保存设置</a>
								</shiro:hasPermission>
							</td>
							<td style="padding-left:2px;padding-bottom:2px;width:25px;">
								<input id="user_searchbox_id" type="text"/>
							</td>
							<td style="padding-left:2px">
								<a class="easyui-linkbutton" data-options="iconCls:'icon-clear',plain:'true'" onclick="clearSearch_user();" href="javascript:void(0);"></a>
							</td>
						</tr>
					</table>
				</div>
				<table id="user_dg_id" title="用户"></table>
				<div id="mm_user">
					<div name="userName">用户名</div>
					<div name="userCode">用户编码</div>
					<div name="tel">电话</div>
				</div>
			</div>
			
			<div data-options="region:'center',split:true,border:true">
				<div id="role_tb_id">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td style="padding-left:2px;padding-bottom:2px;width:25px;">
								<input id="role_searchbox_id" type="text"/>
							</td>
							<td style="padding-left:2px">
								<a class="easyui-linkbutton" data-options="iconCls:'icon-clear',plain:'true'" onclick="clearSearch_role();" href="javascript:void(0);"></a>
							</td>
						</tr>
					</table>
				</div>
				<table id="role_dg_id" title="角色"></table>
				<div id="mm_role">
					<div name="roleName">角色名称</div>
					<div name="roleCode">角色编号</div>
				</div>
			</div>
		</div>
	</div>
		
</body>
</html>