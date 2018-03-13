package com.yffd.easy.uupm.web.vo;

import com.yffd.easy.framework.web.view.vo.ComboTreeVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年3月9日 上午11:46:18 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmResourceComboTreeVO extends ComboTreeVO {
	private String appCode;		//应用系统编号
	private String rsName;		//资源名称
	private String rsCode;		//资源编号
	private String parentCode;	//父资源名称
	private String rsPath;		//资源路径
	private String rsType;		//资源类型：M=菜单、O=操作
	private String rsStatus;	//资源状态：1=激活、0=冻结
	private String remark;		//备注
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getRsName() {
		return rsName;
	}
	public void setRsName(String rsName) {
		this.rsName = rsName;
	}
	public String getRsCode() {
		return rsCode;
	}
	public void setRsCode(String rsCode) {
		this.rsCode = rsCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getRsPath() {
		return rsPath;
	}
	public void setRsPath(String rsPath) {
		this.rsPath = rsPath;
	}
	public String getRsType() {
		return rsType;
	}
	public void setRsType(String rsType) {
		this.rsType = rsType;
	}
	public String getRsStatus() {
		return rsStatus;
	}
	public void setRsStatus(String rsStatus) {
		this.rsStatus = rsStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

