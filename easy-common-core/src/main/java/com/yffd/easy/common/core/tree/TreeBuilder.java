package com.yffd.easy.common.core.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月25日 下午2:12:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class TreeBuilder {

	/**
	 * 构建两层结构的树
	 * 第一层树的父ID为：-1 或 null 或  空字符串
	 * @Date	2017年9月25日 下午2:21:33 <br/>
	 * @author  zhangST
	 * @param treeNodes
	 * @return
	 */
	public <T extends TreeNode> List<T> build(List<T> treeNodes) {
        List<TreeNode> trees = new ArrayList<TreeNode>();
        for (TreeNode treeNode : treeNodes) {
            if (null==treeNode.getPid_() || "".equals(treeNode.getPid_()) || "-1".equals(treeNode.getPid_())) {
                trees.add(treeNode);
            }
            for (TreeNode it : treeNodes) {
                if (it.getPid_() == treeNode.getId_()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<TreeNode>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return (List<T>) trees;
    }
	
	/**
	 * 构建多层结构的树，使用递归方法
	 * 第一层树的父ID为：-1 或 null 或  空字符串
	 * @Date	2017年9月25日 下午2:17:55 <br/>
	 * @author  zhangST
	 * @param treeNodes		传入的树节点列表
	 * @return				返回递归树节点列表
	 */
	public <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes) {
        List<TreeNode> trees = new ArrayList<TreeNode>();  
        for (TreeNode treeNode : treeNodes) {
            if (null==treeNode.getPid_() || "".equals(treeNode.getPid_()) || "-1".equals(treeNode.getPid_())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return (List<T>) trees;
    }
	
	/**
	 * 构建多层结构的树，使用递归方法
	 * 第一层树的父ID为：-1 或 null 或  空字符串
	 * @Date	2017年9月25日 下午2:17:55 <br/>
	 * @author  zhangST
	 * @param treeNodes		传入的树节点列表
	 * @param rootPid		根节点的父ID
	 * @return				返回递归树节点列表
	 */
	public <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, String rootPid) {
		List<TreeNode> trees = new ArrayList<TreeNode>();  
		for (TreeNode treeNode : treeNodes) {
			if (treeNode.getPid_().equals(rootPid)) {
				trees.add(findChildren(treeNode, treeNodes));
			}
		}
		return (List<T>) trees;
	}
	
	/**
	 * 递归查找子节点
	 * @Date	2017年9月25日 下午2:16:41 <br/>
	 * @author  zhangST
	 * @param treeNode		要查找的树节点
	 * @param treeNodes		传入的树节点列表
	 * @return				返回递归树节点列表
	 */
	public TreeNode findChildren(TreeNode treeNode, List<? extends TreeNode> treeNodes) {
        for (TreeNode tn : treeNodes) {
            if(treeNode.getId_().equals(tn.getPid_())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<TreeNode>());
                }
                treeNode.getChildren().add(findChildren(tn, treeNodes));
            }
        }
        return treeNode;
    }
	
	
	public static void main(String[] args) {
		TreeNode treeNode1 = new TreeNode("1", "-1");  
        TreeNode treeNode2 = new TreeNode("2", "-1");  
  
        TreeNode treeNode3 = new TreeNode("3", "1");  
        TreeNode treeNode4 = new TreeNode("4", "1");  
        TreeNode treeNode5 = new TreeNode("5", "1");  
  
  
        TreeNode treeNode6 = new TreeNode("6", "2");  
        TreeNode treeNode7 = new TreeNode("7", "2");  
        TreeNode treeNode8 = new TreeNode("8", "2");  
  
        List<TreeNode> list = new ArrayList<TreeNode>();  
  
        list.add(treeNode1);  
        list.add(treeNode2);  
        list.add(treeNode3);  
        list.add(treeNode4);  
        list.add(treeNode5);  
        list.add(treeNode6);  
        list.add(treeNode7);  
        list.add(treeNode8);  
  
        TreeBuilder builder = new TreeBuilder();
        List<TreeNode> trees = builder.build(list);
        System.out.println(trees);
        
        System.out.println("*********************");
        
        TreeNode treeNode9 = new TreeNode("9", "8");  
        TreeNode treeNode10 = new TreeNode("10", "8");
        list.add(treeNode9);  
        list.add(treeNode10); 
        
        List<TreeNode> trees_ = builder.buildByRecursive(list);
        System.out.println(trees_);
	}
}

