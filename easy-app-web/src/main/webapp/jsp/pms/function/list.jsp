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
	var $dg;
	var $grid;
	var typedata=[{"type":"F","typeName":"菜单"},{"type":"O","typeName":"操作"}];
	$(function() {
		$dg = $("#dg_id");
	 	$grid=$dg.treegrid({
		width: 'auto',
		height: $(this).height()-90,
		url: "",
		rownumbers: true,
		animate: true,
		collapsible: true,
		fitColumns: true,
		striped: true,
		border: true,
		//singleSelect:false,
		idField: 'id',
		treeField: 'name',
	 	frozenColumns: [[{
 	                	 	title:'程式名称',
	 	                	 field:'name',
	 	                	 editor: {
	 	                		 type:'validatebox',
	 	                		 options:{required:true}
	 	                 	 },
	                		 width:parseInt($(this).width()*0.2),
	                		 formatter:function(value) {
	                			 return '<span style="color:red">'+value+'</span>';
	               			 }}
	    				]],
		columns: [[{
			field: 'pname', 
       	   title: '父程式名称', 
       	   width: parseInt($(this).width()*0.1), align: 'left'},
		               {field: 'sort', title: '排序编码', width: parseInt($(this).width()*0.1), editor:{type:'numberbox'}},
			           {field: 'iconCls', title: '程式图标', align: 'center', width: parseInt($(this).width()*0.1),
		            	   formatter :function(value,row) {
		            		   return "<span class='"+row.iconCls+"' style='display:inline-block;vertical-align:middle;width:16px;height:16px;'></span>";
						   },
		            	  editor :{
									type:'combobox',
									options:{
										//valueField:'type',
										//textField:'typeName',
										data:$.iconData,
										formatter : function(v) {
											return $.formatString('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.value);
										},
										value : 'wrench'
									}
								}
						  },
			              {field : 'url',title : '程式路径',width : parseInt($(this).width()*0.1),align : 'left',editor : {type:'validatebox',options:{required:true}}},
			              {field : 'myid',title : '程式编码',width : parseInt($(this).width()*0.1),align : 'left',editor : {type:'validatebox',options:{required:true}}},
			              {field : 'type',title : '程式类型',width : parseInt($(this).width()*0.1),align : 'left',
			            	  formatter:function(value,row){
			            		  if("F"==row.type)
										return "<font color=green>菜单<font>";
				            		  else
				            			return "<font color=red>操作<font>";  
								},
								editor:{
									type:'combobox',
									options:{
										valueField:'type',
										textField:'typeName',
										data:typedata,
										required:true
									}
								}},
			              {field : 'isused',title : '是否启用',width : parseInt($(this).width()*0.1),align : 'center',
			            	  formatter:function(value,row){
			            		  if("Y"==row.isused)
									return "<font color=green>是<font>";
			            		  else
			            			return "<font color=red>否<font>";  
								},
								editor:{type:'checkbox',options:{on:'Y',off:'N'}}
			              },
			              {field : 'description',title : '程式描述',width : parseInt($(this).width()*0.2),align : 'left',editor : "text"}
			              ] ],toolbar:'#tb'
		});
	});
</script>
</head>
<body>
	<div data-options="region:'center',border:false">
		<div id="btn_id" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRowsOpenDlg();">添加</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updRowsOpenDlg();">编辑</a>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeNode();">删除</a>
			</div>
		</div>
		
		<table id="dg_id" title="功能管理"></table>
	</div>
</body>
</html>