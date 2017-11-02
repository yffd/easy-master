package com.yffd.easy.auth.exception;

import org.yffd.easy.common.core.exception.SysException;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月1日 下午1:57:56 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class AuthException extends SysException {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 3474203632044269518L;

	public static final SysException newInstance(String errorMsg) {
		return new SysException(5001, errorMsg);
	}
}

