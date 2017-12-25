package com.yffd.easy.common.core.exception;

/**
 * @Description  系统异常基类.
 * @Date		 2017年9月21日 下午5:40:02 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class EasySysException extends RuntimeException {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -6141550530478539296L;

    /** 异常码 */
    protected String code;
    /** 异常信息 */
    protected String msg;
    
	public EasySysException() {
		super();
	}
    
	public EasySysException(Throwable cause) {
        super(cause);
    }

    public EasySysException(String message) {
        super(message);
    }
    
    public EasySysException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public EasySysException(String code, String message) {
    	super("[" + code + "]" + message);
    	this.code = code;
    	this.msg = message;
    }
    
    public EasySysException(String code, String message, Throwable cause) {
    	super("[" + code + "]" + message, cause);
    	this.code = code;
    	this.msg = message;
    }
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
    
}

