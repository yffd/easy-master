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
	initMenu();
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
	$.post("sys/func/menuTree", {userCode:"1"}, function(data) {
		if(data.respData && data.respData.length>0) {
			$.each(data.respData, function(i, n) {
				var menulist ="<div class=\"well well-small\">";
	            if(n.children && n.children.length>0) {
	                $.each(n.children, function(ci, cn) {
	                	var effort=cn.text+"||"+cn.iconCls+"||"+cn.url;
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
	
	<div data-options="region:'north',border:false" style="height:40px;background:#EEE;padding:10px;overflow:hidden;" href="jsp/index/layout/north.jsp">
    </div>
    
    <div data-options="region:'west',split:true,title:'导航菜单'" style="width:200px;">
		<div id="menuAccordion">
		<!--  导航内容 -->
		</div>
    </div>
    
    <div data-options="region:'south',border:false" style="height:25px;background:#EEE;padding:5px;" href="jsp/index/layout/south.jsp">
    </div>
    
    
    <div data-options="region:'center',plain:true,title:'欢迎使用'" style="overflow: hidden;" href="jsp/index/layout/center.jsp">
    </div>
    
</body>
</html>