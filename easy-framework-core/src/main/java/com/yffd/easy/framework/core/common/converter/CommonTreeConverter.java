package com.yffd.easy.framework.core.common.converter;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.yffd.easy.common.core.converter.EasyModelConverter;
import com.yffd.easy.common.core.tree.EasyTreeCustomBuilder;
import com.yffd.easy.common.core.tree.EasyTreeJsonBuilder;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.core.exception.BizException;
import com.yffd.easy.framework.domain.tree.TreeModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年4月11日 下午4:49:15 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class CommonTreeConverter extends EasyModelConverter {
	
	private EasyTreeCustomBuilder treeCustomBuilder = new EasyTreeCustomBuilder();
	
	private EasyTreeJsonBuilder treeJsonBuilder = new EasyTreeJsonBuilder();
	
	public EasyTreeCustomBuilder getTreeCustomBuilder() {
		return treeCustomBuilder;
	}
	
	public EasyTreeJsonBuilder getTreeJsonBuilder() {
		return treeJsonBuilder;
	}


	/**
	 * 单棵树
	 * @Date	2018年4月13日 上午11:07:19 <br/>
	 * @author  zhangST
	 * @param treeNodes
	 * @param rootNode
	 * @return
	 */
	public <T extends TreeModel> T convertToTree(List<T> treeNodes, T rootNode) {
		if(null==treeNodes || treeNodes.size()==0) return rootNode;
		if(null==rootNode || EasyStringCheckUtils.isEmpty(rootNode.getNodeCode())) throw BizException.newInstance("rootNode的nodeCode为空.");
		this.filterChilren(treeNodes, rootNode);
		return rootNode;
	}
	
	/**
	 * 多棵树
	 * @Date	2018年4月13日 上午11:07:30 <br/>
	 * @author  zhangST
	 * @param treeNodes
	 * @param rootNodes
	 * @return
	 */
	public <T extends TreeModel> List<T> convertToMultiTree(List<T> treeNodes, List<T> rootNodes) {
		if(null==treeNodes || treeNodes.size()==0 || null==rootNodes || rootNodes.size()==0) throw BizException.BIZ_PARAMS_IS_EMPTY();
		List<T> treeList = new ArrayList<T>();
		for(int i=0;i<rootNodes.size();i++) {
			T rootNode = rootNodes.get(i);
			if(EasyStringCheckUtils.isEmpty(rootNode.getNodeCode())) throw BizException.newInstance("rootNode["+i+"]的nodeCode为空.");
			this.filterChilren(treeNodes, rootNode);
			treeList.add(rootNode);
		}
		return treeList;
	}
	
	/**
	 * 多棵树
	 * @Date	2018年4月13日 上午11:07:39 <br/>
	 * @author  zhangST
	 * @param treeNodes
	 * @param rootParentNodeCode
	 * @return
	 */
	public <T extends TreeModel> List<T> convertToMultiTree(List<T> treeNodes, String rootParentNodeCode) {
		if(null==treeNodes || treeNodes.size()==0) return null;
		if(EasyStringCheckUtils.isEmpty(rootParentNodeCode)) throw BizException.newInstance("rootParentNodeCode为空.");
		T rootNode = (T) new TreeModel();
		rootNode.setNodeCode(rootParentNodeCode);
		rootNode.setParentCode("tmp");
		this.filterChilren(treeNodes, rootNode);
		List<T> resultList = (List<T>) rootNode.getChildren();
		return resultList;
	}
	
	/**
	 * 单棵树
	 * @Date	2018年4月13日 上午11:16:23 <br/>
	 * @author  zhangST
	 * @param treeNodes
	 * @param rootParentNodeCode
	 * @return
	 */
	public <T extends TreeModel> T convertToTree(List<T> treeNodes, String rootParentNodeCode) {
		List<T> resultList = this.convertToMultiTree(treeNodes, rootParentNodeCode);
		if(null==resultList) return null;
		if(resultList.size()>1) throw BizException.newInstance("转换出多棵树.");
		return resultList.get(0);
	}
	
	protected <T extends TreeModel> void filterChilren(List<T> treeNodes, T rootNode) {
//		this.recursiveNodes(treeNodes, rootNode);
		this.iterNodes(treeNodes, rootNode);
	}
	
	/**
	 * 迭代方式
	 * @Date	2018年4月13日 上午10:33:00 <br/>
	 * @author  zhangST
	 * @param treeNodes
	 * @param rootNode
	 */
	private <T extends TreeModel> void iterNodes(List<T> treeNodes, T rootNode) {
		if(EasyStringCheckUtils.isEmpty(rootNode.getParentCode()) || EasyStringCheckUtils.isEmpty(rootNode.getNodeCode())) 
			throw BizException.newInstance("节点的nodeCode或parentCode为空.【node:" + rootNode + "】");
		List<TreeModel> children = new ArrayList<TreeModel>();
		for(int i=0;i<treeNodes.size();i++) {
			T node = treeNodes.get(i);
			if(node.getParentCode().equals(rootNode.getNodeCode())) {
				if(null==rootNode.getChildren()) rootNode.setChildren(new ArrayList<TreeModel>());
				rootNode.getChildren().add(node);
				children.add(node);
				treeNodes.remove(i);
				i--;
			} 
		}
		while(children.size()>0 && treeNodes.size()>0) {
			List<TreeModel> tmpChildren = new ArrayList<TreeModel>();
			for(int j=0;j<children.size();j++) {
				T tmpRoot = (T) children.get(j);
				for(int i=0;i<treeNodes.size();i++) {
					T node = treeNodes.get(i);
					if(node.getParentCode().equals(tmpRoot.getNodeCode())) {
						if(null==tmpRoot.getChildren()) tmpRoot.setChildren(new ArrayList<TreeModel>());
						tmpRoot.getChildren().add(node);
						tmpChildren.add(node);
						treeNodes.remove(i);
						i--;
					} 
				}
			}
			children = tmpChildren;
		}
	}
	/**
	 * 递归方式
	 * @Date	2018年4月13日 上午10:33:47 <br/>
	 * @author  zhangST
	 * @param treeNodes
	 * @param rootNode
	 */
	private <T extends TreeModel> void recursiveNodes(List<T> treeNodes, T rootNode) {
		if(EasyStringCheckUtils.isEmpty(rootNode.getParentCode()) || EasyStringCheckUtils.isEmpty(rootNode.getNodeCode())) 
			throw BizException.newInstance("节点的nodeCode或parentCode为空.【node:" + rootNode + "】");
		for(int i=0;i<treeNodes.size();i++) {
			T node = treeNodes.get(i);
			if(node.getParentCode().equals(rootNode.getNodeCode())) {
				if(null==rootNode.getChildren()) rootNode.setChildren(new ArrayList<TreeModel>());
				rootNode.getChildren().add(node);
				this.recursiveNodes(treeNodes, node);
			} 
		}
	}
	
	
	
	public static void main(String[] args) {
		TreeModel root = new TreeModel();
		root.setParentCode("-1");
		root.setNodeCode("root");
		TreeModel treeNode1 = new TreeModel();
		treeNode1.setParentCode("root");
		treeNode1.setNodeCode("1");
		TreeModel treeNode2 = new TreeModel();  
		treeNode2.setParentCode("root");
		treeNode2.setNodeCode("2");
		TreeModel treeNode3 = new TreeModel();  
		treeNode3.setParentCode("root");
		treeNode3.setNodeCode("3");
		
		TreeModel treeNode4 = new TreeModel();   
		treeNode4.setParentCode("1");
		treeNode4.setNodeCode("4");
		TreeModel treeNode5 = new TreeModel();   
		treeNode5.setParentCode("1");
		treeNode5.setNodeCode("5");
  
		TreeModel treeNode6 = new TreeModel();    
		treeNode6.setParentCode("2");
		treeNode6.setNodeCode("6");
		TreeModel treeNode7 = new TreeModel();   
		treeNode7.setParentCode("2");
		treeNode7.setNodeCode("7");
		TreeModel treeNode8 = new TreeModel();  
		treeNode8.setParentCode("2");
		treeNode8.setNodeCode("8");
		
		TreeModel treeNode9 = new TreeModel();  
		treeNode9.setParentCode("8");
		treeNode9.setNodeCode("9");
		
		TreeModel treeNode10 = new TreeModel();  
		treeNode10.setParentCode("9");
		treeNode10.setNodeCode("10");
  
        List<TreeModel> list = new ArrayList<TreeModel>();  
  
//        list.add(root);  
//        list.add(treeNode1);  
//        list.add(treeNode2);  
//        list.add(treeNode4);  
//        list.add(treeNode5);  
//        list.add(treeNode6);  
//        list.add(treeNode7);  
//        list.add(treeNode8); 
//        list.add(treeNode9); 
//        list.add(treeNode10); 
//        list.add(treeNode3);  
        
		TreeModel root2 = new TreeModel();
		root2.setParentCode("root");
		root2.setNodeCode("nuoyuan");
		TreeModel treeNode12 = new TreeModel();
		treeNode12.setParentCode("nuoyuan");
		treeNode12.setNodeCode("ceshi_1");
		TreeModel treeNode22 = new TreeModel();  
		treeNode22.setParentCode("it_2");
		treeNode22.setNodeCode("group_java");
		TreeModel treeNode32 = new TreeModel();  
		treeNode32.setParentCode("nuoyuan");
		treeNode32.setNodeCode("it_2");
		TreeModel treeNode42 = new TreeModel();   
		treeNode42.setParentCode("it_2");
		treeNode42.setNodeCode("group_python");
		TreeModel treeNode52 = new TreeModel();   
		treeNode52.setParentCode("nuoyuan");
		treeNode52.setNodeCode("it_1");
//		TreeModel root2 = new TreeModel();
//		root2.setParentCode("-1");
//		root2.setNodeCode("root2");
//		TreeModel treeNode12 = new TreeModel();
//		treeNode12.setParentCode("root2");
//		treeNode12.setNodeCode("12");
//		TreeModel treeNode22 = new TreeModel();  
//		treeNode22.setParentCode("root2");
//		treeNode22.setNodeCode("22");
//		TreeModel treeNode32 = new TreeModel();  
//		treeNode32.setParentCode("root2");
//		treeNode32.setNodeCode("32");
//		TreeModel treeNode42 = new TreeModel();   
//		treeNode42.setParentCode("12");
//		treeNode42.setNodeCode("42");
//		TreeModel treeNode52 = new TreeModel();   
//		treeNode52.setParentCode("12");
//		treeNode52.setNodeCode("52");
				
		list.add(root2);  
		list.add(treeNode12);  
		list.add(treeNode22);  
		list.add(treeNode32);  
		list.add(treeNode42);  
		list.add(treeNode52);  
        
        CommonTreeConverter converter = new CommonTreeConverter();
        
        TreeModel result = root;
//		converter.iterNodes(list, result);	// 迭代
		converter.recursiveNodes(list, result);	// 递归
        String json = JSON.toJSONString(result);
        System.out.println(json);
        
        
//        TreeModel result1 = converter.convertToTree(list, root);	// 单棵树
//        String json1 = JSON.toJSONString(result1);
//        System.out.println(json1);
        
//        TreeModel result2 = converter.convertToTree(list, "-1");	// 单棵树
//        String json2 = JSON.toJSONString(result2);
//        System.out.println(json2);
        
//        List<TreeModel> result3 = converter.convertToMultiTree(list, "-1");	// 多棵树
//        String json3 = JSON.toJSONString(result3);
//        System.out.println(json3);
        
        
        EasyTreeJsonBuilder builder = new EasyTreeJsonBuilder();
//        String json = builder.buildTreeJson("nodeCode", "parentCode", root, list);
//        String json = builder.buildTreeJson(TreeModel.class, "nodeCode", "parentCode", "root", list);
//        String json = builder.buildMultiTreeJson(TreeModel.class, "nodeCode", "parentCode", "root", list);
//        System.out.println(json);
        
	}
}

