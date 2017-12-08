package com.yffd.easy.common.core.model;

import java.io.Serializable;

/**
 * 
 * @Description  系统响应信息的标准类.
 * @Date		 2017年8月7日 下午2:18:45 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see
 */
public class RespModel implements Serializable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -1521500074035646023L;
	
	/** 响应状态编码  */
    private String statusCode;
    /** 响应状态描述 */
    private String statusDesc;
    /** 响应方式，同步、异步 */
    private String respType;
    /** 响应数据 */
    private Object respData;
    
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getRespType() {
		return respType;
	}
	public void setRespType(String respType) {
		this.respType = respType;
	}
	public Object getRespData() {
		return respData;
	}
	public void setRespData(Object respData) {
		this.respData = respData;
	}
    
}