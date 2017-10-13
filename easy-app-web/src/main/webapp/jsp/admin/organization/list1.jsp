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
	var $dg;
	var $grid;
	$(function() {
		$dg = $("#dg_id");
	 	$grid=$dg.treegrid({
	 		toolbar: '#btn_id',
	 		width: 'auto',
	 		height: $(this).height()-90,
	 		url: "sys/org/list",
	 		loadFilter: function(data) {if(data.statusCode=='OK') {return data.respData;} else {return [];}},
	 		rownumbers: true,
	 		animate: true,
	 		collapsible: true,
	 		fitColumns: true,
	 		striped: true,
	 		border: true,
	 		//singleSelect:false,
	 		idField: 'orgCode',
	 		treeField: 'orgName',
	 		frozenColumns:[[{
	 		                	field:'orgName',title:'组织名称',width:parseInt($(this).width()*0.2),
	 		                	formatter:function(value){
	 		                		return '<span style="color:purple">'+value+'</span>';
	 		                	}
	 		                }]],
	 		columns:[[
	 		          {
	 		        	 field:'orgCode',title:'组织编码',width:parseInt($(this).width()*0.1)
	 		          },
	 		          {
	 		        	 field:'shortName',title:'简称',width:parseInt($(this).width()*0.1),align:'left'
	 		          },
	 		          {
	 		        	 field:'iconCls',title:'组织图标',align:'center',width:parseInt($(this).width()*0.1),
	 		        	 formatter:function(value,row){
	 		        		return "<span class='"+row.iconCls+"' style='display:inline-block;vertical-align:middle;width:16px;height:16px;'></span>";
	 		        	 }
	 		          },
	 		          {
	 		        	 field:'tel',title:'电话',width:parseInt($(this).width()*0.1),align:'left'
	 		          },
	 		          {
	 		        	 field:'fax',title:'传真',width:parseInt($(this).width()*0.1),align:'left'
	 		          },
	 		          {
	 		        	 field:'description',title:'描述',width:parseInt($(this).width()*0.2),align:'left'
	 		          }
	 		          ]]
	 	});
	});
	
	//弹窗增加
	function addRowsOpenDlg() {
		parent.$.modalDialog({
			title: "添加组织机构",
			width: 600,
			height: 400,
			href: "jsp/system/organization/edit.jsp",
			buttons: [{
				text: '保存',
				iconCls: 'icon-ok',
				handler: function() {
// 					parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
					var form = parent.$.modalDialog.handler.find("#form");
					form.submit();
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
</script>
</head>
<body>
	<div data-options="region:'center',border:false">
		<div id="btn_id" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:'true'" onclick="addRowsOpenDlg();" href="javascript:void(0);">添加</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:'true'" onclick="updRowsOpenDlg();" href="javascript:void(0);">编辑</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:'true'" onclick="removeNode();" href="javascript:void(0);">删除</a>
			</div>
		</div>
		
		<table id="dg_id" title="组织机构管理"></table>
	</div>
</body>
</html>