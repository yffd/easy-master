/**
 * @Copyright: Copyright (c) 2018
 * @Author:  ZhangST
 * @version 1.0
*/

package com.yffd.easy.uupm.api.model;

import com.yffd.easy.framework.domain.GenericPO;

/**
 * 
 * @Description  字典信息.
 * @Date		 2018年2月1日 上午9:33:03 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see
 */
public class UupmDictionaryModel extends GenericPO {
   
	private static final long serialVersionUID = -3027407448427505570L;
	private String tenantCode;		//租户编号
	private String itemCode; 		//项编号
	private String itemName;		//项名称
	private String parentCode;		//父机构编号
	private String accessType;		//访问类型：1=公开、0=私有
	private int seqNo;				//菜单序号
	private String remark;			//备注
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}