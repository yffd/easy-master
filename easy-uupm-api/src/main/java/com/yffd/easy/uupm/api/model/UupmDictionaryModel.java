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
	private String categoryCode;	//数据分类编号:CAT_DICT、CAT_OA等
	private String itemName;		//项名称
	private String itemCode; 		//项编号
	private String parentCode;		//父项编号
	private String dataPath;		//数据路径，用点（.）分隔
	private String dataLabel;		//数据标签：BRANCH（分支）、LEAF（叶子）
	private String dataShowType;	//数据展示方式:TREE、FLAT
	private Integer seqNo;			//序号
	private String remark;			//备注
	
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getDataLabel() {
		return dataLabel;
	}
	public void setDataLabel(String dataLabel) {
		this.dataLabel = dataLabel;
	}
	public String getDataShowType() {
		return dataShowType;
	}
	public void setDataShowType(String dataShowType) {
		this.dataShowType = dataShowType;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}