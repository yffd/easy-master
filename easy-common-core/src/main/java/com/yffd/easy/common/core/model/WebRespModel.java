package com.yffd.easy.common.core.model;

import java.io.Serializable;
import java.util.Map;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月15日 下午5:11:07 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WebRespModel implements Serializable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -2319591342534617882L;

	private String status; //状态码
    private String msg; //提示信息
    private String redirectUrl; //重定向的url
    private boolean back; //返回
    private boolean refresh; //刷新页面
    private Map<String, Object> data; //数据信息
    
}

