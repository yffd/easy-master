package com.yffd.easy.demo.soa.api;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月11日 下午4:29:45 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface CallbackService {
	
	void addListener(String key, CallbackListener listener);
}

