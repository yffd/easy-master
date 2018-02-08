<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>欢迎</title>
<link rel="shortcut icon" href="/favicon.ico" />
<base href="<%=basePath%>">
<jsp:include page="layout/script.jsp"></jsp:include>

<style type="text/css">
	#menuAccordion a.l-btn span span.l-btn-text {
	    display: inline-block;
	    height: 14px;
	    line-height: 14px;
	    margin: 0px 0px 0px 10px;
	    padding: 0px 0px 0px 10px;
	    vertical-align: baseline;
	    width: 128px;
	}
	#menuAccordion 	a.l-btn span span.l-btn-icon-left {
	    background-position: left center;
	    padding: 0px 0px 0px 20px;
	}
	#menuAccordion .panel-body {
		padding:5px;
	}
	#menuAccordion span:focus{
		outline: none;
	}
</style>
	
<script type="text/javascript">
$(function(){
// 	initMenu();
	initMenuLocal();
	if (utils.isLessThanIe8()) {
		$.messager.show({
			title : '警告',
			msg : '您使用的浏览器版本太低！<br/>建议您使用谷歌浏览器来获得更快的页面响应效果！',
			timeout : 1000 * 30
		});
	}
});

function initMenu(){
	var $ma = $("#menuAccordion");
	$ma.accordion({animate:true,fit:true,border:false});
	$.post("pms/resource/listLeftMenu", {userCode:"1"}, function(data) {
		if(data.respData && data.respData.length>0) {
			$.each(data.respData, function(i, n) {
				var menulist ="<div class=\"badge-div\">";
	            if(n.children && n.children.length>0) {
	                $.each(n.children, function(ci, cn) {
	                	var effort=cn.text+"||"+cn.iconCls+"||"+cn.inUrl;
						menulist+="<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'"+cn.iconCls+"'\" onclick=\"addTab('"+effort+"');\">"+cn.text+"</a><br/>";
	                });
	            }
	            menulist+="</div>";

	    		$ma.accordion('add', {
	                title: n.text,
	                content: menulist,
	                iconCls: n.iconCls,
	                selected: false
	            });
	        });
			
			//选中第一个
	    	var panels = $ma.accordion('panels');
	    	var t = panels[0].panel('options').title;
	    	$ma.accordion('select', t);
	    	
		} else {return;}
	}, "json").error(function() {
// 		$.messager.alert("提示", "获取菜单出错,请重新登陆!");
	});
}

function initMenuLocal() {
	var menuJson = [{
		"id": "sys",
        "pid": "-1",
        "text": "工作流管理",
        "children": [{
        	"id": "sys-111",
            "pid": "sys",
            "text": "流程发布管理",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "views/workflow/deploy/deployMain.jsp",
            "rsType": "M"
        },{
        	"id": "sys-111",
            "pid": "sys",
            "text": "流程定义管理",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "views/workflow/definition/definitionMain.jsp",
            "rsType": "M"
        },{
        	"id": "sys-112",
            "pid": "sys",
            "text": "流程实例管理",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "views/workflow/instance/instanceMain.jsp",
            "rsType": "M"
        },{
        	"id": "sys-113",
            "pid": "sys",
            "text": "流程任务跟踪",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "views/workflow/task/taskMain.jsp",
            "rsType": "M"
        },{
        	"id": "sys-117",
            "pid": "sys",
            "text": "待办流程任务",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "views/workflow/todoTask/todoTaskMain.jsp",
            "rsType": "M"
        },{
        	"id": "sys-114",
            "pid": "sys",
            "text": "历史流程实例",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "views/workflow/historyInstance/historyInstanceMain.jsp",
            "rsType": "M"
        },{
        	"id": "sys-115",
            "pid": "sys",
            "text": "历史流程任务",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "views/workflow/historyTask/historyTaskMain.jsp",
            "rsType": "M"
        }]
	},{
		"id": "17",
        "pid": "-1",
        "text": "业务流程管理",
        "children": [{
        	"id": "14-112",
            "pid": "sys",
            "text": "业务流程方案",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "workflow/app/leave/leaveMain.jsp",
            "rsType": "M"
        },{
        	"id": "14-113",
            "pid": "sys",
            "text": "业务流程授权",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "workflow/app/leave/leaveMain.jsp",
            "rsType": "M"
        },{
        	"id": "14-114",
            "pid": "sys",
            "text": "业务流程表单模板",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "workflow/app/leave/leaveMain.jsp",
            "rsType": "M"
        }]
	},{
		"id": "14",
        "pid": "-1",
        "text": "流程管理",
        "children": [{
        	"id": "14-111",
            "pid": "sys",
            "text": "流程发布管理",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "views/workflow/deploy/deployMain.jsp",
            "rsType": "M"
        },{
        	"id": "14-112",
            "pid": "sys",
            "text": "业务流程方案",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "workflow/app/leave/leaveMain.jsp",
            "rsType": "M"
        },{
        	"id": "14-113",
            "pid": "sys",
            "text": "流程授权",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "workflow/app/leave/leaveMain.jsp",
            "rsType": "M"
        },{
        	"id": "14-114",
            "pid": "sys",
            "text": "表单模板",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "workflow/app/leave/leaveMain.jsp",
            "rsType": "M"
        }]
	},{
		"id": "13",
        "pid": "-1",
        "text": "流程监控",
        "children": [{
        	"id": "13-111",
            "pid": "sys",
            "text": "流程实例",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "workflow/app/leave/leaveMain.jsp",
            "rsType": "M"
        },{
        	"id": "13-112",
            "pid": "sys",
            "text": "待办任务",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "workflow/app/leave/leaveMain.jsp",
            "rsType": "M"
        }]
	},{
		"id": "11",
        "pid": "-1",
        "text": "任务跟踪",
        "children": [{
        	"id": "11-111",
            "pid": "sys",
            "text": "我的申请",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "workflow/app/leave/leaveMain.jsp",
            "rsType": "M"
        },{
        	"id": "11-112",
            "pid": "sys",
            "text": "我的处理",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "workflow/app/leave/leaveMain.jsp",
            "rsType": "M"
        },{
        	"id": "11-113",
            "pid": "sys",
            "text": "我的已阅",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "workflow/app/leave/leaveMain.jsp",
            "rsType": "M"
        },{
        	"id": "11-114",
            "pid": "sys",
            "text": "我的委托",
            "state": "open",
            "iconCls": "icon-sys",
            "inUrl": "workflow/app/leave/leaveMain.jsp",
            "rsType": "M"
        }]
	}];
	var $ma = $("#menuAccordion");
	$ma.accordion({animate:true,fit:true,border:false});
	$.each(menuJson, function(i, n) {
		var menulist ="<div class=\"badge-div\">";
        if(n.children && n.children.length>0) {
            $.each(n.children, function(ci, cn) {
            	var effort=cn.text+"||"+cn.iconCls+"||"+cn.inUrl;
				menulist+="<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'"+cn.iconCls+"'\" onclick=\"addTab('"+effort+"');\">"+cn.text+"</a><br/>";
            });
        }
        menulist+="</div>";

		$ma.accordion('add', {
            title: n.text,
            content: menulist,
            iconCls: n.iconCls,
            selected: false
        });
    });
	
	//选中第一个
	var panels = $ma.accordion('panels');
	var t = panels[0].panel('options').title;
	$ma.accordion('select', t);
}

</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div style="position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
		    <img src="<%=basePath%>static/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	
	<div data-options="region:'north',border:false" style="overflow:hidden;height:40px;padding:10px;" href="common/layout/north.jsp">
    </div>
    
    <div data-options="region:'west',split:true,title:'导航菜单'" style="width:200px;">
		<div id="menuAccordion">
		<!--  导航内容 -->
		</div>
    </div>
    
    <div data-options="region:'south',split:true" style="height:30px;padding:2px;" href="common/layout/south.jsp">
    </div>
    
    
    <div data-options="region:'center',plain:true,title:'欢迎使用'" style="overflow:hidden;" href="common/layout/center.jsp">
    </div>
    
</body>
</html>