package org.yffd.easy.app.controller.system.vo;

import org.yffd.easy.common.core.view.tree.TreeNode;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月9日 上午11:26:11 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SysOrganizationTreeVO extends TreeNode {
	
	private String id;
    private String pid;
    private String text;
	private String iconCls;
	private String state;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
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
	
}

