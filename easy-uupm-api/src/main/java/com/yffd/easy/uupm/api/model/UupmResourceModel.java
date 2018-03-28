/**
 * @Copyright: Copyright (c) 2018
 * @Author:  ZhangST
 * @version 1.0
*/

package com.yffd.easy.uupm.api.model;

import com.yffd.easy.framework.domain.CustomPo;

/**
 * 
 * @Description  应用系统功能信息.
 * @Date		 2018年2月1日 上午9:39:43 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see
 */
public class UupmResourceModel extends CustomPo {
	
	private static final long serialVersionUID = -7487327163115556293L;
	private String tenantCode;		//租户编号
	private String appCode;		//应用系统编号
	private String rsName;		//资源名称
	private String rsCode;		//资源编号
	private String parentCode;	//父资源名称
	private String rsPath;		//资源路径
	private String rsType;		//资源类型：M=菜单、O=操作
	private String rsStatus;	//资源状态：1=激活、0=冻结
	private String remark;		//备注
	
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
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