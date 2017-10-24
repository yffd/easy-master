package org.yffd.easy.app.controller.system.vo;

import org.yffd.easy.common.core.view.tree.TreeNode;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月25日 下午2:26:00 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SysFunctionTreeGridVO extends TreeNode {
	
    private String state;
    
    private String funcName;
	private String funcCode;
	private String parentCode;
	private String url;
	private String type;
	private String active;
	private Float sort;
	private String iconCls;
	private String remark;
    
    public SysFunctionTreeGridVO() {
    }

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Float getSort() {
		return sort;
	}

	public void setSort(Float sort) {
		this.sort = sort;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}

