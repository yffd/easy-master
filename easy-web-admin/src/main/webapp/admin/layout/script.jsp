<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String easyuiThemeName = "metro";
	Cookie cookies[] = request.getCookies();
	if(cookies!=null && cookies.length>0) {
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("cookiesColor")) {
				easyuiThemeName = cookie.getValue();
				break;
			}
		}
	}
%>
<link rel="stylesheet" type="text/css" href="static/easyui/themes/<%=easyuiThemeName %>/easyui.css">
<link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="static/css/common.css">
<link rel="stylesheet" type="text/css" href="static/css/icon.css">
<script type="text/javascript" src="static/js/jquery-1.8.0.min.js"></script>
<!-- <script type="text/javascript" src="static/easyui/jquery.min.js"></script> -->
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="static/js/easy.common.ui.js"></script>
<script type="text/javascript" src="static/js/easy.common.utils.js"></script>

<style type="text/css">
	body {
	    font-family:helvetica,tahoma,verdana,sans-serif;
	    font-size:13px;
	    margin:0px 0px 0px 0px;
	    padding:0px 0px 0px 0px;
	}
</style>