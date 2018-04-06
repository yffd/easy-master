package com.yffd.easy.uupm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.common.core.exception.EasyBizException;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.common.service.CommonServiceSupport;
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.api.model.UupmTreeNodeModel;
import com.yffd.easy.uupm.mapper.IUupmTreeNodeMapper;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年03月30日 18时02分49秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmTreeNodeService<T extends UupmTreeNodeModel> extends CommonServiceSupport {

	@Override
	protected String getStatement(String sqlId) {
		String namespace = IUupmTreeNodeMapper.class.getName();
		StringBuilder sb = new StringBuilder();
		sb.append(namespace).append(".").append(sqlId);
		return sb.toString();
	}
	
	public List<T> findChildrenNodeList(String nodeLabel, String nodeCode, LoginInfo loginInfo) {
		if(EasyStringCheckUtils.isEmpty(nodeLabel) || EasyStringCheckUtils.isEmpty(nodeCode)) 
			throw EasyBizException.BIZ_PARAMS_ERROR();
		T resultNode = this.findNode(nodeLabel, nodeCode, loginInfo);
		if(null==resultNode) return null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nodeLeft", resultNode.getNodeLeft());
		params.put("nodeRight", resultNode.getNodeRight());
		params.put("tenantCode", resultNode.getTenantCode());
		params.put("nodeLabel", resultNode.getNodeLabel());
		params.put("nodeLayer", null);
		return this.selectListBy("selectChildrenNodeList", params, true);
	}
	
	public List<T> findChildrenNodeList(String nodeLabel, String nodeCode, int layer, LoginInfo loginInfo) {
		if(EasyStringCheckUtils.isEmpty(nodeLabel) || EasyStringCheckUtils.isEmpty(nodeCode)) 
			throw EasyBizException.BIZ_PARAMS_ERROR();
		T resultNode = this.findNode(nodeLabel, nodeCode, loginInfo);
		if(null==resultNode) return null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nodeLeft", resultNode.getNodeLeft());
		params.put("nodeRight", resultNode.getNodeRight());
		params.put("tenantCode", resultNode.getTenantCode());
		params.put("nodeLabel", resultNode.getNodeLabel());
		params.put("nodeLayer", resultNode.getNodeLayer() + layer);
		return this.selectListBy("selectChildrenNodeList", params, true);
	}
	
	public List<T> findParentNodeList(String nodeLabel, String nodeCode, LoginInfo loginInfo) {
		if(EasyStringCheckUtils.isEmpty(nodeLabel) || EasyStringCheckUtils.isEmpty(nodeCode)) 
			throw EasyBizException.BIZ_PARAMS_ERROR();
		T resultNode = this.findNode(nodeLabel, nodeCode, loginInfo);
		if(null==resultNode) return null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nodeLeft", resultNode.getNodeLeft());
		params.put("nodeRight", resultNode.getNodeRight());
		params.put("tenantCode", resultNode.getTenantCode());
		params.put("nodeLabel", resultNode.getNodeLabel());
		params.put("nodeLayer", null);
		List<T> parentNodeList = this.selectListBy("selectParentNodeList", params, true);
		return parentNodeList;
	}
	
	public String findParentPath(String nodeLabel, String nodeCode, LoginInfo loginInfo) {
		List<T> parentNodeList = findParentNodeList(nodeLabel, nodeCode, loginInfo);
		if(null==parentNodeList || parentNodeList.size()==0) return null;
		StringBuffer sb = new StringBuffer();
		for(T model : parentNodeList) {
			sb.append(model.getNodeCode()).append(",");
		}
		return sb.length()>0?sb.substring(0, sb.length()-1) : sb.toString();
	}
	
	public List<T> findRootNodeList(String nodeStatus, LoginInfo loginInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(null!=loginInfo) paramMap.put("tenantCode", loginInfo.getTenantCode());
		paramMap.put("parentNodeCode", "root");
		paramMap.put("nodeStatus", nodeStatus);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("model", paramMap);
		return this.selectListBy("selectListBy", params, true);
	}
	
	public T findNode(String nodeLabel, String nodeCode, LoginInfo loginInfo) {
		if(EasyStringCheckUtils.isEmpty(nodeLabel) || EasyStringCheckUtils.isEmpty(nodeCode)) 
			throw EasyBizException.BIZ_PARAMS_ERROR();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(null!=loginInfo) paramMap.put("tenantCode", loginInfo.getTenantCode());
		paramMap.put("nodeLabel", nodeLabel);
		paramMap.put("nodeCode", nodeCode);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("model", paramMap);
		T result = this.selectOneBy("selectOneBy", params, true);
		return result;
	}
	
	public T findNode(String nodeLabel, LoginInfo loginInfo) {
		if(EasyStringCheckUtils.isEmpty(nodeLabel)) throw EasyBizException.BIZ_PARAMS_ERROR();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(null!=loginInfo) paramMap.put("tenantCode", loginInfo.getTenantCode());
		paramMap.put("nodeLabel", nodeLabel);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("model", paramMap);
		T result = this.selectOneBy("selectOneBy", params, true);
		return result;
	}
	
	public T findParentNode(String nodeLabel, String parentNodeCode, LoginInfo loginInfo) {
		if(EasyStringCheckUtils.isEmpty(nodeLabel) || EasyStringCheckUtils.isEmpty(parentNodeCode)) 
			throw EasyBizException.BIZ_PARAMS_ERROR();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(null!=loginInfo) paramMap.put("tenantCode", loginInfo.getTenantCode());
		paramMap.put("nodeLabel", nodeLabel);
		paramMap.put("nodeCode", parentNodeCode);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("model", paramMap);
		T result = this.selectOneBy("selectOneBy", params, true);
		return result;
	}
	
	public boolean exist(String nodeLabel, String nodeCode, LoginInfo loginInfo) {
		return null != this.findNode(nodeLabel, nodeCode, loginInfo);
	}
	
	public long countLayer(String nodeLabel, String nodeCode, LoginInfo loginInfo) {
		if(EasyStringCheckUtils.isEmpty(nodeLabel) || EasyStringCheckUtils.isEmpty(nodeCode)) 
			throw EasyBizException.BIZ_PARAMS_ERROR();
		T resultNode = this.findNode(nodeLabel, nodeCode, loginInfo);
		if(null==resultNode) return -1;
		return this.selectCountBy("countLayer", resultNode, true);
	}
	
	public int addRootNode(T node, LoginInfo loginInfo) {
		if(null==node || EasyStringCheckUtils.isEmpty(node.getNodeLabel()) 
				|| EasyStringCheckUtils.isEmpty(node.getNodeCode())) throw EasyBizException.BIZ_PARAMS_ERROR();
		if(EasyStringCheckUtils.isEmpty(node.getTenantCode()) && null!=loginInfo) node.setTenantCode(loginInfo.getTenantCode());
		node.setNodeLeft(1);
		node.setNodeRight(2);
		node.setParentNodeCode("root");				// 根节点的parentNodeCode=root
		node.setParentNodeName("根节点");
		node.setNodeLayer(1);
		if(EasyStringCheckUtils.isEmpty(node.getNodeLabel())) node.setNodeLabel(node.getNodeCode());		// 根节点的标签=根节点的编号
		int num = this.insertBy("insertOne", node, true);		// 添加
		return num;
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int addChildNode(String nodeLabel, String parentNodeCode, T node, LoginInfo loginInfo) {
		if(EasyStringCheckUtils.isEmpty(nodeLabel) || EasyStringCheckUtils.isEmpty(parentNodeCode)
				|| null==node || EasyStringCheckUtils.isEmpty(node.getNodeCode())) throw EasyBizException.BIZ_PARAMS_ERROR();
		T resultParentNode = this.findParentNode(nodeLabel, parentNodeCode, loginInfo);
		if(null==resultParentNode) return 0;
		
		this.updateLeftForAdd(resultParentNode);			// 修改左编号
		this.updateRightForAdd(resultParentNode);  			// 修改右编号
		
		node.setNodeLeft(resultParentNode.getNodeRight());
		node.setNodeRight(resultParentNode.getNodeRight() + 1);
		node.setNodeLabel(resultParentNode.getNodeLabel());
		node.setParentNodeCode(resultParentNode.getNodeCode());
		node.setParentNodeName(resultParentNode.getNodeName());
		node.setNodeLayer(resultParentNode.getNodeLayer() + 1);
		if(EasyStringCheckUtils.isEmpty(node.getNodeValue())) node.setNodeValue(node.getNodeCode());
		int num = this.insertBy("insertOne", node, true);		// 添加
		return num;
	}
	
	// nodeLabel、nodeLeft、nodeRight、parentNodeCode 不进行更新
	public int updateNode(T node, T oldNode, LoginInfo loginInfo) {
		if(null==node || null==oldNode || EasyStringCheckUtils.isEmpty(oldNode.getNodeLabel()) 
				|| EasyStringCheckUtils.isEmpty(oldNode.getNodeCode())) throw EasyBizException.BIZ_PARAMS_ERROR();
		node.setNodeLabel(null);
		node.setNodeLeft(null);
		node.setNodeRight(null);
		node.setParentNodeCode(null);
		if(null!=loginInfo && EasyStringCheckUtils.isEmpty(oldNode.getTenantCode())) oldNode.setTenantCode(loginInfo.getTenantCode());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("model", node);
		params.put("old", oldNode);
		int num = this.updateBy("updateBy", params, true);		// 修改
		return num;
	}
	
	/**
	 * 级联修改节点状态
	 * @Date	2018年4月2日 下午2:51:28 <br/>
	 * @author  zhangST
	 * @param nodeLabel
	 * @param nodeCode
	 * @param nodeStatus
	 * @param loginInfo
	 * @return
	 */
	public int updateStatus(String nodeLabel, String nodeCode, String nodeStatus, LoginInfo loginInfo) {
		if(EasyStringCheckUtils.isEmpty(nodeLabel) || EasyStringCheckUtils.isEmpty(nodeCode)
				|| EasyStringCheckUtils.isEmpty(nodeStatus)) throw EasyBizException.BIZ_PARAMS_ERROR();
		T resultNode = this.findNode(nodeLabel, nodeCode, loginInfo);
		if(null==resultNode) return 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nodeLeft", resultNode.getNodeLeft());
		params.put("nodeRight", resultNode.getNodeRight());
		params.put("tenantCode", resultNode.getTenantCode());
		params.put("nodeLabel", resultNode.getNodeLabel());
		params.put("nodeStatus", nodeStatus);
		params.put("nodeLayer", null);
		int num = this.updateBy("updateStatus", params, true);
		return num;
	}
	
	/**
	 * 级联删除节点
	 * @Date	2018年4月2日 下午2:51:33 <br/>
	 * @author  zhangST
	 * @param nodeLabel
	 * @param nodeCode
	 * @param loginInfo
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int delNodes(String nodeLabel, String nodeCode, LoginInfo loginInfo) {
		if(EasyStringCheckUtils.isEmpty(nodeLabel) || EasyStringCheckUtils.isEmpty(nodeCode)) 
			throw EasyBizException.BIZ_PARAMS_ERROR();
		T resultNode = this.findNode(nodeLabel, nodeCode, loginInfo);
		if(null==resultNode) return 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nodeLeft", resultNode.getNodeLeft());
		params.put("nodeRight", resultNode.getNodeRight());
		params.put("tenantCode", resultNode.getTenantCode());
		params.put("nodeLabel", resultNode.getNodeLabel());
		params.put("nodeStatus", null);
		params.put("nodeLayer", null);
		int delNum = this.deleteBy("delNodes", params, true);	// 删除节点、及子节点
//		int childrenNodes = (resultNode.getNodeRight() - resultNode.getNodeLeft() - 1) / 2; // 拥有子节点数
//		if(childrenNodes!=delNum) throw EasyBizException.newInstance("待删除节点数与已删除节点数不相等");
		this.updateLeftForDel(resultNode);			// 修改左编号
		this.updateRightForDel(resultNode);  		// 修改右编号
		return delNum;
	}
	
	private int updateLeftForAdd(T node) {
		if(null==node || EasyStringCheckUtils.isEmpty(node.getNodeLabel())) throw EasyBizException.BIZ_PARAMS_ERROR(); 
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("nodeLabel", node.getNodeLabel());		// 指定节点的归属，相当于treeId
		paramMap.put("nodeRight", node.getNodeRight());
		paramMap.put("nodeStep", 2);
		return this.updateBy("updateLeftForAdd", paramMap, true);
	}
	
	private int updateRightForAdd(T node) {
		if(null==node || EasyStringCheckUtils.isEmpty(node.getNodeLabel())) throw EasyBizException.BIZ_PARAMS_ERROR(); 
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("nodeLabel", node.getNodeLabel());		// 指定节点的归属，相当于treeId
		paramMap.put("nodeRight", node.getNodeRight());
		paramMap.put("nodeStep", 2);
		return this.updateBy("updateRightForAdd", paramMap, true);
	}
	
	private int updateLeftForDel(T node) {
		if(null==node || EasyStringCheckUtils.isEmpty(node.getNodeLabel())) throw EasyBizException.BIZ_PARAMS_ERROR(); 
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("nodeLabel", node.getNodeLabel());		// 指定节点的归属，相当于treeId
		paramMap.put("nodeLeft", node.getNodeLeft());
		int nodeStep = node.getNodeRight() - node.getNodeLeft() + 1;
		paramMap.put("nodeStep", nodeStep);
		return this.updateBy("updateLeftForDel", paramMap, true);
	}
	
	private int updateRightForDel(T node) {
		if(null==node || EasyStringCheckUtils.isEmpty(node.getNodeLabel())) throw EasyBizException.BIZ_PARAMS_ERROR(); 
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("nodeLabel", node.getNodeLabel());		// 指定节点的归属，相当于treeId
		paramMap.put("nodeRight", node.getNodeRight());
		int nodeStep = node.getNodeRight() - node.getNodeLeft() + 1;
		paramMap.put("nodeStep", nodeStep);
		return this.updateBy("updateRightForDel", paramMap, true);
	}
	
}
