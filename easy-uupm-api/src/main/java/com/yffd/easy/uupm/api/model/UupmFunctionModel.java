/**
 * @Copyright: Copyright (c) 2018
 * @Author:  ZhangST
 * @version 1.0
*/

package com.yffd.easy.uupm.api.model;

import com.yffd.easy.framework.domain.GenericPO;

/**
 * 
 * @Description  应用系统功能信息.
 * @Date		 2018年2月1日 上午9:39:43 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see
 */
public class UupmFunctionModel extends GenericPO {
	
	private static final long serialVersionUID = -7487327163115556293L;
	private String applicationCode;	//应用系统编号
	private String functionCode;	//功能编号
	private String functionName;	//功能名称
	private String functionStatus;	//功能状态：1=激活、0=冻结
	private String innerUrl;		//内部链接
	private String outerUrl;		//外部链接
	private String remark;			//备注
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getFunctionStatus() {
		return functionStatus;
	}
	public void setFunctionStatus(String functionStatus) {
		this.functionStatus = functionStatus;
	}
	public String getInnerUrl() {
		return innerUrl;
	}
	public void setInnerUrl(String innerUrl) {
		this.innerUrl = innerUrl;
	}
	public String getOuterUrl() {
		return outerUrl;
	}
	public void setOuterUrl(String outerUrl) {
		this.outerUrl = outerUrl;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}