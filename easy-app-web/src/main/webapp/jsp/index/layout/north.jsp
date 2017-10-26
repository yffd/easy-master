<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" charset="utf-8">
	
	function changeTheme(themeName) {
		var cookiesColor = utils.cookies.get("cookiesColor");
    	if(cookiesColor != themeName) {
    		utils.cookies.set("cookiesColor", themeName, 30);
    		commonui.chgSkin(themeName, cookiesColor);
        }
	}

	function logout(b) {
		/*$.post('systemAction!logout.action', function() {
			if (b) {
				if (jqueryUtil.isLessThanIe8()) {
					loginAndRegDialog.dialog('open');
				} else {
						location.replace('login.jsp');
				}
			} else {
				loginAndRegDialog.dialog('open');
			}
		});*/
		$.messager.confirm("提示", "确认退出吗?",function(r){
			if(r){
				$.ajax({
					async : false,
					cache : false,
					type : "POST",
					url : "systemAction!logout.action",
					error : function() {
					},
					success : function(json) {
						location.replace("login.jsp");
					}
				});
			}
		});
		
	}

	var userInfoWindow;
	function showUserInfo() {
		userInfoWindow = $('<div/>').window({
			modal : true,
			title : '当前用户信息',
			width : 350,
			height : 300,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			//href : 'userAction!showUserInfo.action',
			onClose : function() {
				$(this).window('destroy');
			}
		});
	}
</script>
<span style="padding-left:10px; font-size: 16px; "><img src="<%=basePath%>static/images/blocks.gif" width="20" height="20" align="absmiddle" /> 后台管理系统</span>
<div style="position: absolute; right: 0px; bottom: 0px; ">
	<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-help">控制面板</a> 
	<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_zxMenu" iconCls="icon-logout">注销</a>
</div>

<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div onclick="showUserInfo();">个人信息</div>
	<div class="menu-sep"></div>
	<div>
		<span>更换主题</span>
		<div id="theme" style="width: 120px;">
			<div data-options="iconCls:'icon-save'" onclick="changeTheme('default');">default</div>  
		    <div data-options="iconCls:'icon-save'" onclick="changeTheme('black');">black</div> 
		    <div data-options="iconCls:'icon-save'" onclick="changeTheme('bootstrap');">bootstrap</div>  
		    <div data-options="iconCls:'icon-save'" onclick="changeTheme('gray');">gray</div>  
		    <div data-options="iconCls:'icon-save'" onclick="changeTheme('metro');">metro</div>
		</div>
	</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div onclick="loginAndRegDialog.dialog('open');">锁定窗口</div>
	<div class="menu-sep"></div>
	<div onclick="logout();">重新登录</div>
	<div onclick="logout(true);">退出系统</div>
</div>
