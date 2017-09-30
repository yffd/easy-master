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
<base href="<%=basePath%>">
<jsp:include page="layout/script.jsp"></jsp:include>

<script type="text/javascript">
$(function(){
	initMenu();
	if (jqueryUtil.isLessThanIe8()) {
		$.messager.show({
			title : '警告',
			msg : '您使用的浏览器版本太低！<br/>建议您使用谷歌浏览器来获得更快的页面响应效果！',
			timeout : 1000 * 30
		});
	}
});

function initMenu(){
	$.post("sys/func/listMenu", {userCode:"1"}, function(data) {
		if(data.respData && data.respData.length>0) {
			indexLayout.initLeftMenu(data.respData);
		} else {return;}
	}, "json").error(function() {
		$.messager.alert("提示", "获取菜单出错,请重新登陆!");
	});
}

</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
		    <img src="<%=basePath%>static/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	
	<div class="easyui-header" data-options="region:'north',border:false,split:true" href="jsp/system/layout/north.jsp">
    </div>
    
    <div class="easyui-footer" data-options="region:'south',split:true" href="jsp/system/layout/south.jsp">
    </div>
    
    <div class="easyui-sidebar" data-options="region:'west',hide:'true',split:true,border:true,title:'导航菜单'">
		<div id="menuAccordion" class="easyui-accordion" data-options="border:false,fit:true">
		<!--  导航内容 -->
		</div>
    </div>
    
    <div id="mainPanle" class="easyui-main" data-options="region:'center'" href="jsp/system/layout/center.jsp">
    </div>
    
</body>
</html>