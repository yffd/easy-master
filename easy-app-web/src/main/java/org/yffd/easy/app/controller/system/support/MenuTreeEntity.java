package org.yffd.easy.app.controller.system.support;

import java.util.List;

import org.yffd.easy.common.core.tree.TreeNode;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月25日 下午2:26:00 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class MenuTreeEntity extends TreeNode {
	
	private String id;
    private String name;
    private String parentId;
    private String url;
    private String icon;
    private List<TreeNode> children;
    
    public MenuTreeEntity() {
    	super();
    }
    
    public MenuTreeEntity(String id, String name, String parentId) {
		super(id, name, parentId);
	}
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
    
}

