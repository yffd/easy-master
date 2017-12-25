package com.yffd.easy.common.core.exception;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月12日 上午11:09:22 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class EasyBizException extends EasySysException {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -6877199237574806153L;

	public static final EasySysException newInstance(String msg) {
		return new EasySysException("5B0001", msg);
	}
	
	public static final EasySysException newInstance(String msg, Throwable cause) {
		return new EasySysException("5B0001", msg, cause);
	}
	
}

