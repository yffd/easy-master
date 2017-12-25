package com.yffd.easy.common.core.model;

import java.io.Serializable;

import com.yffd.easy.common.core.enums.SysConstantsEnum;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月14日 下午3:23:07 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class DataTransferModel implements Serializable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 4229090772630190028L;

	private String resultCode;
	private String resultMsg;
	
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
	public <T extends DataTransferModel> T success() {
		this.resultCode = SysConstantsEnum.SUCCESS.getValue();
		this.resultMsg = SysConstantsEnum.SUCCESS.getDesc();
		return (T) this;
	}
	
	public <T extends DataTransferModel> T success(String msg) {
		this.resultCode = SysConstantsEnum.SUCCESS.getValue();
		this.resultMsg = msg;
		return (T) this;
	}
	
	public <T extends DataTransferModel> T error() {
		this.resultCode = SysConstantsEnum.ERROR.getValue();
		this.resultMsg = SysConstantsEnum.ERROR.getDesc();
		return (T) this;
	}
	
	public <T extends DataTransferModel> T error(String msg) {
		this.resultCode = SysConstantsEnum.ERROR.getValue();
		this.resultMsg = msg;
		return (T) this;
	}
}

