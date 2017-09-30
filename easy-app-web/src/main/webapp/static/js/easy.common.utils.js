(function($){
	/**
	 * 全局系统对象
	 */
    window['utils'] = {};
	/**
	 * 修改ajax默认设置
	 */
	$.ajaxSetup({
		type:'POST',
		error:function(XMLHttpRequest, textStatus, errorThrown) {
			$.messager.progress('close');
			$.messager.alert('错误', XMLHttpRequest.responseText);
		}
	});
	
	/**
	 * IE检测
	 */
	utils.isLessThanIe8 = function() {
		return ($.support.msie && $.support.version < 8);
	};
	
	/**
	 * 序列化表单到对象
	 */
	utils.serializeObject = function(form) {
		var o = {};
		$.each(form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + (this['value']==''?' ':this['value']);
			} else {
				o[this['name']] = this['value']==''?' ':this['value'];
			}
		});
		//console.dir(o);
		return o;
	};
	
	/**
	 * cookies
	 */
	utils.cookies = (function() {
		var fn = function() {};
        fn.prototype.get = function(name) {
        	var cookieValue = "";
            var search = name + "=";
            if (document.cookie.length > 0) {
                offset = document.cookie.indexOf(search);
                if (offset != -1) {
                    offset += search.length;
                    end = document.cookie.indexOf(";", offset);
                    if (end == -1) end = document.cookie.length;
                    cookieValue = decodeURIComponent(document.cookie.substring(offset, end))
                }
            }
            return cookieValue;
        };
        fn.prototype.set = function(cookieName, cookieValue, DayValue) {
            var expire = "";
            var day_value = 1;
            if (DayValue != null) {
                day_value = DayValue;
            }
            expire = new Date((new Date()).getTime() + day_value * 86400000);
            expire = "; expires=" + expire.toGMTString();
            document.cookie = cookieName + "=" + encodeURIComponent(cookieValue) + ";path=/" + expire;
        };
        fn.prototype.remvoe = function(cookieName) {
            var expire = "";
            expire = new Date((new Date()).getTime() - 1);
            expire = "; expires=" + expire.toGMTString();
            document.cookie = cookieName + "=" + escape("") + ";path=/" + expire;
            /*path=/*/
        };
        return new fn();
	})();
	
	/**
	 * 获取随机时间
	 */
	utils.getRandTime = function() {
		var nowDate=new Date();
	 	var str="";
		var hour=nowDate.getHours();//HH
		str+=((hour<10)?"0":"")+hour;
		var min=nowDate.getMinutes();//MM
		str+=((min<10)?"0":"")+min;
		var sec=nowDate.getSeconds(); //SS
		str+=((sec<10)?"0":"")+sec;
		return Number(str);
	};
	
	 
	
}){jQuery}