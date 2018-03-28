<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统一用户授权管理</title>
<base href="<%=basePath%>">
<jsp:include page="/common/layout/script.jsp"></jsp:include>

<script type="text/javascript">
	var $json_activeStatus = [ {id:"", text:"全部", "selected": true} ];
	var $json_rsType = [ {id:"", text:"全部", "selected": true} ];
	var $openWindow = this;// 当前窗口
	var $dg_role;
	var $dg_rs;
	$(function() {
		$dg_role = $('#dg_id_role');
		// 初始化控件数据
		$.post('/uupm/combox/findComboByDict', 
				{'comboxKeys':'active-status,rs-type'}, 
				function(result) {
					if("OK"==result.status) {
						var jsonData = result.data;
						$json_activeStatus = $json_activeStatus.concat(jsonData['active-status']);
						$json_rsType = $json_rsType.concat(jsonData['rs-type']);
						
						initDatagrid_role();	// 初始化datagrid组件
						initTreegrid_rs();
					}
				}, 'json');
		//搜索框
		$("#searchbox_id").searchbox({
			menu:"#mm_id_role",
			prompt :'请输入',
// 			height: 28,
// 			width:200,
			searcher:function(value, name) {
				var obj = {};
				obj[name] = value;
				$dg_role.datagrid('reload', obj); 
		    }
		});
	});
	// 初始化datagrid组件
	function initDatagrid_role() {
		$dg_role.datagrid({
		    url:'uupm/role/findPage',
		    width: 'auto',
		    height: $(this).height()-commonui.remainHeight-20-$('#tb_id_role').height(),
			pagination: true,
			pageSize: commonui.pageSize,
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: true,
			border: false,
			striped: true,
			singleSelect: true,
			loadFilter: function(result) {
		    	if("OK"==result.status) {
		    		return result.data;
		    	} else {
		    		$.messager.show({
						title :commonui.msg_title,
						timeout : commonui.msg_timeout,
						msg : result.msg
					});
		    		return [];
	    		}
	    	},
	    	onDblClickRow: function(rowIndex, rowData) {
	    		findResByRole(rowIndex, rowData);
            },
	        columns: [[
						{field: 'roleName', title: '名称', width: 200, align: 'left'},
						{field: 'roleCode', title: '编号', width: 100, align: 'left'},
						{field: 'roleStatus', title: '状态', width: 100, align: 'left',
							formatter: function(value, row) {
								return utils.fmtDict($json_activeStatus, value);
							}
						},
						{field: 'remark', title: '备注', width: 100, align: 'left'}
	                   ]]
		});
	}
	function initTreegrid_rs() {
		$dg_rs = $('#dg_id_rs');
		$dg_rs.treegrid({
		    url:'uupm/tenant/resource/findTenantResWithAppTree',
		    width: 'auto',
		    height: $(this).height()-commonui.remainHeight-20,
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: true,
			border: false,
			striped: true,
			singleSelect: false,
			cascadeCheck: true,
			idField: 'id_',
			treeField: 'rsName',
			loadFilter: function(result) {
		    	if("OK"==result.status) {
		    		var jsonData = [];
		    		$.each(result.data, function(i, obj) {
		    			if(obj.children) {
		    				jsonData[i] = result.data[i];
		    			}
		    		});
		    		return jsonData;
		    	} else {
		    		$.messager.show({
						title :commonui.msg_title,
						timeout : commonui.msg_timeout,
						msg : result.msg
					});
		    		return [];
	    		}
	    	},
	    	onClickRow: function(row) {
	    	},
	    	onContextMenu: function(e, node){
				e.preventDefault();
				$dg_rs.treegrid('select', node.id_);
				$('#mm_id_rs').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			},
	        columns: [[
						{field: 'ck', checkbox: true},
						{field: 'rsName', title: '名称', width: 200, align: 'left'},
						{field: 'rsCode', title: '编号', width: 100, align: 'left'},
						{field: 'parentCode', title: '父编号', width: 100, align: 'left'},
						{field: 'rsPath', title: '路径', width: 100, align: 'left'},
						{field: 'rsType', title: '类型', width: 100, align: 'left',
							formatter: function(value, row) {
								return utils.fmtDict($json_rsType, value);
							}	
						},
						{field: 'rsStatus', title: '状态', width: 100, align: 'left',
							formatter: function(value, row) {
								return utils.fmtDict($json_activeStatus, value);
							}	
						},
						{field: 'remark', title: '备注', width: 100, align: 'left'},
	                   ]]
		});
	}
	
	// 为角色分配功能
	function saveRoleRes() {
		var rows_rs = $dg_rs.treegrid('getSelections');//获取选中的行-多行
		var rsCodeArr = [];
		if(rows_rs) {
			$.each(rows_rs, function(i, obj) {
				if('A'==obj.rsType) {
				} else {
					rsCodeArr.push({'appCode': obj['appCode'], 'rsCode': obj['rsCode'], 'rsType': obj['rsType']});
				}
			});
		}
		if(rsCodeArr.length==0) {
			parent.$.messager.show({
				title :commonui.msg_title,
				timeout : commonui.msg_timeout,
				msg : "请选择资源"
			});
			return;
		}
		var row_role = $dg_role.datagrid('getSelected');//获取选中的行-单行
		if(row_role) {
			var role_code = row_role.roleCode;
			var data={"roleCode":role_code, "rsCodes": JSON.stringify(rsCodeArr)};
			$.post("uupm/auth/saveRoleResource", data, function(result) {
						$.messager.show({
							title :commonui.msg_title,
							timeout : commonui.msg_timeout,
							msg : result.msg
						});
					}, "JSON").error(function() {
						$.messager.show({
							title :commonui.msg_title,
							timeout : commonui.msg_timeout,
							msg : "分配失败！"
						});
					});
		} else {
			parent.$.messager.show({
				title :commonui.msg_title,
				timeout : commonui.msg_timeout,
				msg : "请选择角色"
			});
		}
	}
	//双击事件
	function findResByRole(rowIndex, rowData) {
		$.post("uupm/auth/findResourceByRoleCode", {roleCode:rowData.roleCode}, function(result) {
			if(result.status=='OK') {
				$dg_rs.treegrid('unselectAll');
				var data = result.data;
				if(data.length>0) {
					if(data[0].appCode) $dg_rs.treegrid('select', data[0].appCode);
					$.each(data, function(i, n) {
						$dg_rs.treegrid('select', n.rsCode);
					});
				} else {
					$.messager.show({
						title :commonui.msg_title,
						timeout : commonui.msg_timeout,
						msg : "该角色暂无权限"
					});
				}
			} else {
				$.messager.show({
					title :commonui.msg_title,
					timeout : commonui.msg_timeout,
					msg : result.msg
				});
			}
			
		}, "json");
	}
	
	// 清除搜索条件
	function cleanSearch() {
		$('#searchbox_id').searchbox('setValue', '');
	}
	// 刷新资源列表
	function reloadRs() {
		$dg_rs.treegrid('reload', {});
	}
	// 展开
	function expandAll() {
		var node = $dg_rs.treegrid('getSelected');
		if(node) {
			$dg_rs.treegrid('expandAll', node.rsCode);
		} else {
			$dg_rs.treegrid('expandAll');
		}
	}
	// 收缩
	function collapseAll() {
		var node = $dg_rs.treegrid('getSelected');
		if(node) {
			$dg_rs.treegrid('collapseAll', node.rsCode);
		} else {
			$dg_rs.treegrid('collapseAll');
		}
	}
		
</script>
</head>
<body class="easyui-layout">
<!-- 	<div class="search-form-div" data-options="region:'north',border:false,title:'高级查询',iconCls:'icon-search',collapsible:true"> -->
<!-- 		<div class="badge-div-hidden" > -->
<!-- 			<span class="badge-title">提示</span> -->
<!-- 			<p style="margin:0px;padding:2px;"> -->
<!-- 				请<span class="label-info"><strong>双击角色</strong></span>查看所属资源！ -->
<!-- 				超级管理员默认拥有<span class="label-info"><strong>所有资源！</strong></span> -->
<!-- 			</p> -->
<!-- 		</div> -->
<!-- 	</div> -->
    <div data-options="region:'west',title:'角色列表',split:true,border:true" style="width:500px;">
	    <div id="tb_id_role" style="background-color: #F5F5F5;padding-left:25px;">
	    	<table cellpadding="0" cellspacing="0">
				<tr>
					<td style="padding-left:2px;padding-bottom:2px;">
						<a class="easyui-linkbutton" data-options="iconCls:'icon-config',plain:'true'" onclick="saveRoleRes();" href="javascript:void(0);">保存设置</a>
					</td>
					<td style="padding-left:10px;padding-bottom:2px;">
						<input id="searchbox_id" type="text"/>
					</td>
					<td style="padding-left:2px">
						<a class="easyui-linkbutton" data-options="iconCls:'icon-clear',plain:'true'" onclick="cleanSearch();" href="javascript:void(0);"></a>
					</td>
				</tr>
			</table>
		</div>
		<table id="dg_id_role"></table>
		
		<div id="mm_id_role">
			<div name="roleName">&nbsp;&nbsp;名称&nbsp;&nbsp;&nbsp;&nbsp;</div>
			<div name="roleCode">&nbsp;&nbsp;编号&nbsp;&nbsp;&nbsp;&nbsp;</div>
			<div class="menu-sep"></div>
			<div name="roleStatus">
				<span>&nbsp;&nbsp;状态&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<div>
					<div>&nbsp;&nbsp;有效&nbsp;&nbsp;&nbsp;&nbsp;</div>
					<div>&nbsp;&nbsp;无效&nbsp;&nbsp;&nbsp;&nbsp;</div>
				</div>
			</div>
		</div>
    </div>

    <div data-options="region:'center',title:'资源列表'" style="padding:5px;">
	    <table id="dg_id_rs"></table>
		
		<div id="mm_id_rs" class="easyui-menu" style="width:120px;">
	        <div onclick="expandAll()" data-options="iconCls:'icon-undo'">展开</div>
	        <div onclick="collapseAll()" data-options="iconCls:'icon-redo'">收缩</div>
	        <div class="menu-sep"></div>
	        <div onclick="reloadRs()" data-options="iconCls:'icon-reload'">刷新</div>
		</div>
    </div>
		
</body>
</html>