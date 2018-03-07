package com.yffd.easy.uupm.web.vo;

import com.yffd.easy.common.core.tree.TreeNode;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月28日 下午4:57:37 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmDictionaryTreeVO extends TreeNode {
	private String text;
	private String iconCls = "icon-save";
	private String state = "closed";	//closed、open
	private boolean checked = false;

	private String id;
	private String itemName;		//项名称
	private String itemCode; 		//项编号
	private String parentCode;		//父项编号
	private String dataLabel;		//数据标签：主要用于查询tree（包括其下的所有子节点），一般是数据范围的子集
	private String dataScope;		//数据范围，CATEGORY、DICT等
	private Integer seqNo;			//序号
	private String remark;			//备注
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getDataLabel() {
		return dataLabel;
	}
	public void setDataLabel(String dataLabel) {
		this.dataLabel = dataLabel;
	}
	public String getDataScope() {
		return dataScope;
	}
	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
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

