package com.yffd.easy.framework.core.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.core.exception.BizException;
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.framework.domain.tree.TreeModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年03月30日 18时02分49秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class CommonTreeServiceAbstract<T extends TreeModel> extends CommonServiceAbstract<T>{

	// set nodeCode
//	public abstract String replaceNodeCode(T node);
	
	public T findTreeNode(T node, LoginInfo loginInfo) {
//		String nodeCode = this.replaceNodeCode(node);	// nodeCode映射
		String nodeCode = node.getNodeCode();	// nodeCode映射
		if(null==node || EasyStringCheckUtils.isEmpty(node.getTreeId())
				|| EasyStringCheckUtils.isEmpty(nodeCode)) return null;
		TreeModel paramModel = null;
		try {
			paramModel = node.getClass().newInstance();
			paramModel.setTenantCode(node.getTenantCode());
			paramModel.setTreeId(node.getTreeId());
			paramModel.setNodeCode(nodeCode);
		} catch (Exception e) {
			throw BizException.newInstance("实例化树节点对象失败", e);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("model", paramModel);
		return this.selectOneBy("selectOneBy", params, true);
	}
	
	public List<T> findChildrenNodes(T node, LoginInfo loginInfo) {
		if(null==node || EasyStringCheckUtils.isEmpty(node.getTreeId())) return null;
		T resultNode = this.findTreeNode(node, loginInfo);
		if(null==resultNode) return null;
		TreeModel paramModel = new TreeModel();
		paramModel.setTenantCode(node.getTenantCode());
		paramModel.setTreeId(node.getTreeId());
		paramModel.setNodeLeft(resultNode.getNodeLeft());
		paramModel.setNodeRight(resultNode.getNodeRight());
		paramModel.setNodeLayer(null);
		
		return this.selectListBy("selectChildrenNodes", paramModel, true);
	}
	
	public List<T> findChildrenNodes(T node, int layer, LoginInfo loginInfo) {
		if(null==node || EasyStringCheckUtils.isEmpty(node.getTreeId())) return null;
		T resultNode = this.findTreeNode(node, loginInfo);
		if(null==resultNode) return null;
		TreeModel paramModel = new TreeModel();
		paramModel.setTenantCode(node.getTenantCode());
		paramModel.setTreeId(node.getTreeId());
		paramModel.setNodeLeft(resultNode.getNodeLeft());
		paramModel.setNodeRight(resultNode.getNodeRight());
		paramModel.setNodeLayer(resultNode.getNodeLayer() + layer);
		return this.selectListBy("selectChildrenNodes", paramModel, true);
	}
	
	public List<T> findParentNodes(T node, LoginInfo loginInfo) {
		if(null==node || EasyStringCheckUtils.isEmpty(node.getTreeId())) return null;
		T resultNode = this.findTreeNode(node, loginInfo);
		if(null==resultNode) return null;
		TreeModel paramModel = new TreeModel();
		paramModel.setTenantCode(node.getTenantCode());
		paramModel.setTreeId(node.getTreeId());
		paramModel.setNodeLeft(resultNode.getNodeLeft());
		paramModel.setNodeRight(resultNode.getNodeRight());
		paramModel.setNodeLayer(null);
		List<T> parentNodeList = this.selectListBy("selectParentNodes", paramModel, true);
		return parentNodeList;
	}
	
	public String findNodePath(T node, LoginInfo loginInfo) {
		List<T> parentNodeList = findParentNodes(node, loginInfo);
		if(null==parentNodeList || parentNodeList.size()==0) return null;
		StringBuffer sb = new StringBuffer();
		for(T model : parentNodeList) {
			sb.append(model.getNodeCode()).append(",");
		}
		return sb.length()>0?sb.substring(0, sb.length()-1) : sb.toString();
	}
	
	public long countNodeLayer(T node, LoginInfo loginInfo) {
		if(null==node || EasyStringCheckUtils.isEmpty(node.getTreeId())) return 0;
		T resultNode = this.findTreeNode(node, loginInfo);
		if(null==resultNode) return 0;
		return this.selectCountBy("countLayer", resultNode, true);
	}
	
	
	// add root section
	public List<T> findRootNodes(T node, LoginInfo loginInfo) {
		if(null==node) return null;
//		String nodeCode = this.replaceNodeCode(node);	// nodeCode映射
		String nodeCode = node.getNodeCode();	// nodeCode映射
		node.setNodeCode(nodeCode);
		node.setParentCode("root");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("model", node);
		return this.selectListBy("selectListBy", params, true);
	}
	
	public boolean existRootNode(T node, LoginInfo loginInfo) {
//		String nodeCode = this.replaceNodeCode(node);	// nodeCode映射
		String nodeCode = node.getNodeCode();	// nodeCode映射
		if(null==node || EasyStringCheckUtils.isEmpty(nodeCode)) throw BizException.BIZ_PARAMS_IS_EMPTY();
		node.setNodeCode(nodeCode);
		node.setParentCode("root");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("model", node);
		List<T> resultList = this.selectListBy("selectListBy", params, true);
		return !(null==resultList || resultList.size()==0);
	}
	
	public int addRootNode(T node, LoginInfo loginInfo) {
//		String nodeCode = this.replaceNodeCode(node);	// nodeCode映射
		String nodeCode = node.getNodeCode();	// nodeCode映射
		if(null==node || EasyStringCheckUtils.isEmpty(node.getTreeId()) 
				|| EasyStringCheckUtils.isEmpty(nodeCode)) return 0;
		node.setNodeCode(nodeCode);
		node.setNodeLeft(1);
		node.setNodeRight(2);
		node.setParentCode("root");
		node.setNodeLayer(1);
		int num = this.insertBy("insertOne", node, true);
		return num;
	}
	
	
	// add child section
	public T findParentNode(T node, LoginInfo loginInfo) {
		if(null==node || EasyStringCheckUtils.isEmpty(node.getTreeId()) 
				|| EasyStringCheckUtils.isEmpty(node.getParentCode())) return null;
		TreeModel paramModel = null;
		try {
			paramModel = node.getClass().newInstance();
			paramModel.setTenantCode(node.getTenantCode());
			paramModel.setTreeId(node.getTreeId());
			paramModel.setNodeCode(node.getParentCode());
		} catch (Exception e) {
			throw BizException.newInstance("实例化树节点对象失败", e);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("model", paramModel);
		T result = this.selectOneBy("selectOneBy", params, true);
		return result;
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int addChildNode(T node, LoginInfo loginInfo) {
//		String nodeCode = this.replaceNodeCode(node);	// nodeCode映射
		String nodeCode = node.getNodeCode();	// nodeCode映射
		if(null==node || EasyStringCheckUtils.isEmpty(node.getTreeId()) 
				|| EasyStringCheckUtils.isEmpty(nodeCode)) return 0;
		T resultParentNode = this.findParentNode(node, loginInfo);
		if(null==resultParentNode) return 0;
		// 先更新节点左右偏序号，再插入节点，顺序不能变
		// 1.先更新节点左右偏序号
		this.updateLeftForAdd(resultParentNode);			// 修改左编号
		this.updateRightForAdd(resultParentNode);  			// 修改右编号
		
		node.setNodeCode(nodeCode);
		node.setTenantCode(resultParentNode.getTenantCode());
		node.setNodeLeft(resultParentNode.getNodeRight());
		node.setNodeRight(resultParentNode.getNodeRight() + 1);
		node.setParentCode(resultParentNode.getNodeCode());
		node.setNodeLayer(resultParentNode.getNodeLayer() + 1);
		// 2.再插入节点
		int num = this.insertBy("insertOne", node, true);
		return num;
	}
	
	
	// update node section
	/**
	 * 级联更新节点，以及其子孙节点
	 * @Date	2018年4月11日 上午10:18:38 <br/>
	 * @author  zhangST
	 * @param node
	 * @param oldNode
	 * @param loginInfo
	 * @return
	 */
	public int updateNodes(T node, T oldNode, LoginInfo loginInfo) {
		if(null==node || null==oldNode || EasyStringCheckUtils.isEmpty(oldNode.getTreeId())) return 0;
		T resultUpdateNode = this.findTreeNode(oldNode, loginInfo);
		if(null==resultUpdateNode) return 0;
		// 不进行更新属性
		node.setTenantCode(null);
		node.setTreeId(null);
		node.setNodeLeft(null);
		node.setNodeRight(null);
		node.setParentCode(null);
		
		TreeModel paramModel = null;
		try {
			paramModel = node.getClass().newInstance();
			paramModel.setTenantCode(resultUpdateNode.getTenantCode());
			paramModel.setTreeId(resultUpdateNode.getTreeId());
			paramModel.setNodeLeft(resultUpdateNode.getNodeLeft());
			paramModel.setNodeRight(resultUpdateNode.getNodeRight());
		} catch (Exception e) {
			throw BizException.newInstance("实例化树节点对象失败", e);
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("model", node);
		params.put("old", paramModel);
		int num = this.updateBy("updateNodes", params, true);
		return num;
	}
	

	// delete node section
	/**
	 * 级联删除节点，以及其子孙节点
	 * @Date	2018年4月11日 上午10:19:32 <br/>
	 * @author  zhangST
	 * @param node
	 * @param loginInfo
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int deleteNodes(T node, LoginInfo loginInfo) {
		if(null==node || EasyStringCheckUtils.isEmpty(node.getTreeId())) return 0;
		T resultDeleteNode = this.findTreeNode(node, loginInfo);
		if(null==resultDeleteNode) return 0;
		// 先删除节点，再更新节点左右偏序号，顺序不能变
		TreeModel paramModel = null;
		try {
			paramModel = node.getClass().newInstance();
			paramModel.setTenantCode(resultDeleteNode.getTenantCode());
			paramModel.setTreeId(resultDeleteNode.getTreeId());
			paramModel.setNodeLeft(resultDeleteNode.getNodeLeft());
			paramModel.setNodeRight(resultDeleteNode.getNodeRight());
		} catch (Exception e) {
			throw BizException.newInstance("实例化树节点对象失败", e);
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("model", paramModel);
		// 1.先删除节点
		int delNum = this.deleteBy("deleteNodes", params, true);	// 删除节点、及子节点
//		int childrenNodes = (resultDeleteNode.getNodeRight() - resultDeleteNode.getNodeLeft() - 1) / 2; // 拥有子节点数
//		if(childrenNodes!=delNum) throw EasyBizException.newInstance("待删除节点数与已删除节点数不相等");
		// 2.再更新节点左右偏序号
		this.updateLeftForDel(resultDeleteNode);		// 修改左编号
		this.updateRightForDel(resultDeleteNode);  		// 修改右编号
		return delNum;
	}
	
	
	private int updateLeftForAdd(T node) {
		if(null==node || EasyStringCheckUtils.isEmpty(node.getTreeId()) ||
				null==node.getNodeRight()) throw BizException.BIZ_PARAMS_IS_EMPTY(); 
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tenantCode", node.getTenantCode());
		paramMap.put("treeId", node.getTreeId());
		paramMap.put("nodeRight", node.getNodeRight());
		paramMap.put("nodeStep", 2);
		return this.updateBy("updateLeftForAdd", paramMap, true);
	}
	
	private int updateRightForAdd(T node) {
		if(null==node || EasyStringCheckUtils.isEmpty(node.getTreeId())
				|| null==node.getNodeRight()) throw BizException.BIZ_PARAMS_IS_EMPTY(); 
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tenantCode", node.getTenantCode());
		paramMap.put("treeId", node.getTreeId());
		paramMap.put("nodeRight", node.getNodeRight());
		paramMap.put("nodeStep", 2);
		return this.updateBy("updateRightForAdd", paramMap, true);
	}
	
	private int updateLeftForDel(T node) {
		if(null==node || EasyStringCheckUtils.isEmpty(node.getTreeId())
				|| null==node.getNodeRight() || null==node.getNodeLeft()) throw BizException.BIZ_PARAMS_IS_EMPTY(); 
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tenantCode", node.getTenantCode());
		paramMap.put("treeId", node.getTreeId());
		paramMap.put("nodeLeft", node.getNodeLeft());
		int nodeStep = node.getNodeRight() - node.getNodeLeft() + 1;
		paramMap.put("nodeStep", nodeStep);
		return this.updateBy("updateLeftForDel", paramMap, true);
	}
	
	private int updateRightForDel(T node) {
		if(null==node || EasyStringCheckUtils.isEmpty(node.getTreeId())
				|| null==node.getNodeRight() || null==node.getNodeLeft()) throw BizException.BIZ_PARAMS_IS_EMPTY(); 
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tenantCode", node.getTenantCode());
		paramMap.put("treeId", node.getTreeId());
		paramMap.put("nodeRight", node.getNodeRight());
		int nodeStep = node.getNodeRight() - node.getNodeLeft() + 1;
		paramMap.put("nodeStep", nodeStep);
		return this.updateBy("updateRightForDel", paramMap, true);
	}
	
}
