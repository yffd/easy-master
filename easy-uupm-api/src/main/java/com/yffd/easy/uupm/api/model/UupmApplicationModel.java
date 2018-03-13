/**
 * @Copyright: Copyright (c) 2018
 * @Author:  ZhangST
 * @version 1.0
*/

package com.yffd.easy.uupm.api.model;

import com.yffd.easy.framework.domain.GenericPO;

/**
 * 
 * @Description  应用系统信息.
 * @Date		 2018年2月1日 上午9:28:08 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see
 */
public class UupmApplicationModel extends GenericPO {
	
	private static final long serialVersionUID = -3316333314800573791L;
	
	private String appCode;			//应用系统编号
	private String appName;			//应用系统名称
	private String appDomain;		//应用系统域名
	private String appPort;			//应用系统端口
	private String appContextPath;	//应用系统上下文路径
	private String appType;			//应用系统类型：app-local=本地系统、app-inner=内部系统、app-outer=外部系统
	private String appStatus;		//应用系统状态：1=激活、0=冻结
	private String remark;			//备注
	
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppDomain() {
		return appDomain;
	}
	public void setAppDomain(String appDomain) {
		this.appDomain = appDomain;
	}
	public String getAppPort() {
		return appPort;
	}
	public void setAppPort(String appPort) {
		this.appPort = appPort;
	}
	public String getAppContextPath() {
		return appContextPath;
	}
	public void setAppContextPath(String appContextPath) {
		this.appContextPath = appContextPath;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}