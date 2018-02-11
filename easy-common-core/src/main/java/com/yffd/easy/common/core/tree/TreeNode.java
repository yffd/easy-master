package com.yffd.easy.common.core.tree;

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
    private List<TreeNode> children;
    
    public TreeNode() {

    }
    
    public TreeNode(String id, String pid) {
        this.id = id;
        this.pid = pid;
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
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", pid=" + pid + "]";
	}

}

