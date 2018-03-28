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
		return new EasySysException("5B0101", msg);
	}
	
	public static final EasySysException newInstance(String msg, Throwable cause) {
		return new EasySysException("5B0101", msg, cause);
	}
	
	/**
	 * 业务参数为空
	 */
	public static final EasySysException BIZ_PARAMS_NULL() {
		return new EasySysException("5D0201", "业务参数为空.");
	}
	
	/**
	 * 业务参数错误
	 * @Date	2018年3月19日 下午2:02:20 <br/>
	 * @author  zhangST
	 * @return
	 */
	public static final EasySysException BIZ_PARAMS_ERROR() {
		return new EasySysException("5D0202", "业务参数错误.");
	}
	
	/**
	 *租户为空
	 * @Date	2018年3月19日 下午2:02:20 <br/>
	 * @author  zhangST
	 * @return
	 */
	public static final EasySysException BIZ_TENANT_ISNULL() {
		return new EasySysException("5D0203", "租户为空.");
	}
	
}

