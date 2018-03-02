package com.yffd.easy.uupm.web.vo;

import com.yffd.easy.common.core.tree.TreeNode;
import com.yffd.easy.uupm.api.model.UupmDataCategoryModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月28日 下午4:57:37 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmDataCategoryTreeVO extends TreeNode {
	private String text;
	private String iconCls = "icon-save";
	private String state = "closed";
	private boolean checked = false;
	
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
	
}

