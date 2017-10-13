package org.yffd.easy.common.core.tree.copy;

import java.util.List;

/**
 * @Description  树节点对象.
 * @Date		 2017年9月25日 下午2:10:48 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class TreeNode {

	private String id;
	private String pid;
    private String text;
    private List<TreeNode> children;
    
    public TreeNode() {

    }
    
    public TreeNode(String id, String pid, String text) {
        this.id = id;
        this.pid = pid;
        this.text = text;
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
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

}

