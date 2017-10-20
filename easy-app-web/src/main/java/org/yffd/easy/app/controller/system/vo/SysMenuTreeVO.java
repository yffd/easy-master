package org.yffd.easy.app.controller.system.vo;

import java.util.List;

import org.yffd.easy.common.core.view.tree.TreeNode;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月25日 下午2:26:00 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SysMenuTreeVO extends TreeNode {
	
	private String id;
    private String pid;
    private String text;
    private String url;
    private String iconCls;
    private String state;
    private List<TreeNode> children;
    
    public SysMenuTreeVO() {
    }
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
    
}

