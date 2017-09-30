(function($){
	/**
	 * 全局系统对象
	 */
    window['commonui'] = {};
	/**
	 * 切换皮肤
	 */
	commonui.chgSkin = function(selectId,cookiesColor) {
		docchgskin(document,selectId,cookiesColor);
        $("iframe").each(function () {
            var dc = this.contentWindow.document;
            docchgskin(dc,selectId,cookiesColor);
        });
        function docchgskin(dc,selectId,cookiesColor) {
        	removejscssfile(dc,"themes/"+cookiesColor+"/easyui.css", "css");
        	createLink(dc,"themes/"+selectId+"/easyui.css");
    	}
        function createLink(dc,url) {
        	var urls = url.replace(/[,]\s*$/ig,"").split(",");
	    	var links = [];
	    	for( var i = 0; i < urls.length; i++ ) {
			    links[i] = dc.createElement("link");
			    links[i].rel = "stylesheet";
			    links[i].href = urls[i];
			    dc.getElementsByTagName("head")[0].appendChild(links[i]);
	     	}
    	}
        function removejscssfile(dc,filename, filetype) {
        	var targetelement=(filetype=="js")? "script" : (filetype=="css")? "link" : "none"
            var targetattr=(filetype=="js")? "src" : (filetype=="css")? "href" : "none"
            var allsuspects=dc.getElementsByTagName(targetelement)
            for (var i=allsuspects.length; i>=0; i--) {
            	if (allsuspects[i] && allsuspects[i].getAttribute(targetattr)!=null && allsuspects[i].getAttribute(targetattr).indexOf(filename)!=-1)
	                allsuspects[i].parentNode.removeChild(allsuspects[i])
            }
    	}
	};
	
	/**
	 * 高级查询
	 */
	commonui.gradeSearch = function($dg,formId,url) {
		$("<div/>").dialog({
			href:url,
			modal:true,
			title:'高级查询',
			top:120,
			width:480,
			buttons:[{
				text:'增加一行',
				iconCls:'icon-add',
				handler:function() {
					var currObj = $(this).closest('.panel').find('table');
					currObj.find('tr:last').clone().appendTo(currObj);
					currObj.find('tr:last a').show();
				}
			},{
				text:'确定',
				iconCls:'icon-ok',
				handler:function() {
					$dg.datagrid('reload',utils.serializeObject($(formId)));
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function() {
					$(this).closest('.window-body').dialog('destroy');
				}
			}],
			onClose:function() {
				$(this).dialog('destroy');
			}
		});
	};
	
	/*
	 * 定义图标样式的数组
	 */
	commonui.iconData = [{
		value : '',
		text : '默认'
	},{
		value : 'icon-adds',
		text : 'icon-adds'
	},{
		value : 'icon-ok',
		text : 'icon-ok'
	},{
		value : 'icon-edit',
		text : 'icon-edit'
	},{
		value : 'icon-tip',
		text : 'icon-tip'
	},{
		value : 'icon-back',
		text : 'icon-back'
	},{
		value : 'icon-remove',
		text : 'icon-remove'
	},{
		value : 'icon-undo',
		text : 'icon-undo'
	},{
		value : 'icon-cancel',
		text : 'icon-cancel'
	},{
		value : 'icon-save',
		text : 'icon-save'
	},{
		value : 'icon-config',
		text : 'icon-config'
	},{
		value : 'icon-comp',
		text : 'icon-comp'
	},{
		value : 'icon-sys',
		text : 'icon-sys'
	},{
		value : 'icon-db',
		text : 'icon-db'
	},{
		value : 'icon-pro',
		text : 'icon-pro'
	},{
		value : 'icon-role',
		text : 'icon-role'
	},{
		value : 'icon-end',
		text : 'icon-end'
	},{
		value : 'icon-bug',
		text : 'icon-bug'
	},{
		value : 'icon-badd',
		text : 'icon-badd'
	},{
		value : 'icon-bedit',
		text : 'icon-bedit'
	},{
		value : 'icon-bdel',
		text : 'icon-bdel'
	},{
		value : 'icon-item',
		text : 'icon-item'
	},{
		value : 'icon-excel',
		text : 'icon-excel'
	},{
		value : 'icon-auto',
		text : 'icon-auto'
	},{
		value : 'icon-time',
		text : 'icon-time'
	}];
	
	
	/***************** easyui默认实现的覆盖 BEGIN *****************/
	
	
	
	/***************** easyui默认实现的覆盖 END   *****************/
	
})(jQuery)