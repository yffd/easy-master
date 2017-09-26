package org.yffd.easy.common.core.exception;

/**
 * @Description  系统异常基类.
 * @Date		 2017年9月21日 下午5:40:02 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SysException extends RuntimeException {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -6141550530478539296L;

    /** 具体异常码 */
    protected int code;
    /** 异常信息 */
    protected String msg;
    
	public SysException() {
		super();
	}
    
	public SysException(Throwable cause) {
        super(cause);
    }

    public SysException(String message) {
        super(message);
    }
    
    public SysException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public SysException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }
    
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
    
}

