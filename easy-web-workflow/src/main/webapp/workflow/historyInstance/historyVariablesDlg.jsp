<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
	fieldset {
	    border: 0 none;
	    margin: 0;
	    padding: 0;
	}
	legend {
	    -moz-border-bottom-colors: none;
	    -moz-border-left-colors: none;
	    -moz-border-right-colors: none;
	    -moz-border-top-colors: none;
	    border-color: #E5E5E5;
	    border-image: none;
	    border-style: none none solid;
	    border-width: 0 0 1px;
	    color: #999999;
	    line-height: 20px;
	    display: block;
	    margin-bottom: 10px;
	    padding: 0;
	    width: 100%;
	}
</style>

<script type="text/javascript">
$(function() {
	var instanceId=parent.$.modalDialog.instanceId;
	if(instanceId) {
		var url = "workflow/historyInstance/findVariables";
		$.post(url, {'instanceId':instanceId}, function(result) {
			if(result.statusCode=='OK') {
				var tmpData = result.respData.rows;
				if(tmpData) {
					var paramData = [];
					var localParamData = [];
					$.each(tmpData, function(i, v) {
						if(v.local) {
							localParamData.push(v);
						} else {
							paramData.push(v);
						}
					});
					if(paramData.length>0) {
						$('#tb_id').datagrid({
						    data: paramData,
						    columns:[[
								{field:'variableName',title:'变量名称',width:180},
								{field:'variableTypeName',title:'变量类型',width:180},
								{field:'value',title:'变量值',width:180},
								{field:'createTime',title:'创建时间',width:180,
									formatter: function(value, row) {
										return new Date(value).format("yyyy-MM-dd HH:mm:ss");
									}	
								},
								{field:'lastUpdatedTime',title:'最后修改时间',width:180,
									formatter: function(value, row) {
										return new Date(value).format("yyyy-MM-dd HH:mm:ss");
									}	
								}
						    ]]
						});
					}
					if(localParamData.length>0) {
						$('#tb_local_id').datagrid({
						    data: localParamData,
						    columns:[[
								{field:'variableName',title:'变量名称',width:110},
								{field:'variableTypeName',title:'变量类型',width:110},
								{field:'value',title:'变量值',width:110},
								{field:'createTime',title:'创建时间',width:110,
									formatter: function(value, row) {
										return new Date(value).format("yyyy-MM-dd HH:mm:ss");
									}	
								},
								{field:'lastUpdatedTime',title:'最后修改时间',width:110,
									formatter: function(value, row) {
										return new Date(value).format("yyyy-MM-dd HH:mm:ss");
									}	
								},
								{field:'taskId',title:'任务ID',width:110},
								{field:'activityNodeId',title:'节点ID',width:110},
								{field:'activityNodeName',title:'节点名称',width:110},
						    ]]
						});
					}
				}
			}
		}, "JSON").error(function() {
			$.messager.show({
				title : "系统提示",
				msg : commonui.messager_msg,
				timeout : commonui.messager_timeout
			});
		});
	}
	
});
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" style="overflow: hidden;padding: 10px;">
		<fieldset>
			<legend>实例范围变量</legend>
			<div id="tb_id"></div>
		</fieldset>
		<fieldset>
			<legend>任务节点范围变量</legend>
			<div id="tb_local_id"></div>
		</fieldset>
	</div>
</div>
