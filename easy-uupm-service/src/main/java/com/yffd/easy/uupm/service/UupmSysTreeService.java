package com.yffd.easy.uupm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.common.core.exception.EasyBizException;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.uupm.api.model.UupmSysTreeModel;
import com.yffd.easy.uupm.dao.UupmSysTreeDao;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月19日 13时57分51秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmSysTreeService {

	@Autowired
	private UupmSysTreeDao uupmSysTreeDao;
	
	public List<UupmSysTreeModel> findChildrenNodeListBy(String nodeCode) {
		if(EasyStringCheckUtils.isEmpty(nodeCode)) throw EasyBizException.BIZ_PARAMS_ERROR();
		UupmSysTreeModel result = this.findByNodeCode(nodeCode);
		if(null==result) return null;
		return this.uupmSysTreeDao.selectChildrenNodeListBy(result);
	}
	
	//TODO
	public List<UupmSysTreeModel> findChildrenNodeListBy(UupmSysTreeModel node, int layer) {
		if(null==node) return null;
		return this.uupmSysTreeDao.selectChildrenNodeListBy(node);
	}
	
	public List<UupmSysTreeModel> findParentNodeListBy(String nodeCode) {
		if(EasyStringCheckUtils.isEmpty(nodeCode)) throw EasyBizException.BIZ_PARAMS_ERROR();
		UupmSysTreeModel result = this.findByNodeCode(nodeCode);
		if(null==result) return null;
		return this.uupmSysTreeDao.selectParentNodeListBy(result);
	}
	
	public String findParentPath(String nodeCode) {
		if(EasyStringCheckUtils.isEmpty(nodeCode)) throw EasyBizException.BIZ_PARAMS_ERROR();
		UupmSysTreeModel result = this.findByNodeCode(nodeCode);
		if(null==result) return null;
		List<UupmSysTreeModel> resultList = this.uupmSysTreeDao.selectParentNodeListBy(result);
		if(null==resultList || resultList.size()==0) return null;
		StringBuffer sb = new StringBuffer();
		for(UupmSysTreeModel model : resultList) {
			sb.append(model.getNodeCode()).append(",");
		}
		return sb.length()>0?sb.substring(0, sb.length()-1) : sb.toString();
	}
	
	public void addRootNode(UupmSysTreeModel node) {
		if(null==node || EasyStringCheckUtils.isEmpty(node.getNodeCode())) throw EasyBizException.BIZ_PARAMS_ERROR();
		node.setNodeLeft(1);
		node.setNodeRight(2);
		node.setNodeLabel(node.getNodeCode());
		this.uupmSysTreeDao.insertOne(node);	// 添加
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void addNode(String parentCode, UupmSysTreeModel newNode) {
		if(EasyStringCheckUtils.isEmpty(parentCode) || null==newNode || EasyStringCheckUtils.isEmpty(newNode.getNodeCode())) throw EasyBizException.BIZ_PARAMS_ERROR();
		UupmSysTreeModel parentNode = this.findByNodeCode(parentCode);
		if(null==parentNode) return;
		
		this.updateLeftForAdd(parentNode);	// 修改左编号
		this.updateRightForAdd(parentNode);  // 修改右编号
		
		newNode.setNodeLeft(parentNode.getNodeRight());
		newNode.setNodeRight(parentNode.getNodeRight() + 1);
		newNode.setNodeLabel(parentNode.getNodeCode());
		this.uupmSysTreeDao.insertOne(newNode);	// 添加
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void delByNodeCode(String nodeCode) {
		if(EasyStringCheckUtils.isEmpty(nodeCode)) throw EasyBizException.BIZ_PARAMS_ERROR();
		UupmSysTreeModel result = this.findByNodeCode(nodeCode);
		if(null==result) return;
		
		int delNum = this.uupmSysTreeDao.delNodes(result); // 删除节点、及子节点
//		int childrenNodes = (result.getNodeRight() - result.getNodeLeft() - 1) / 2; // 拥有子节点数
//		if(childrenNodes!=delNum) throw EasyBizException.newInstance("待删除节点数与已删除节点数不相等");
		this.updateLeftForDel(result);	// 修改左编号
		this.updateRightForDel(result);  // 修改右编号
	}
	
	public int countLayer(String nodeCode) {
		if(EasyStringCheckUtils.isEmpty(nodeCode)) throw EasyBizException.BIZ_PARAMS_ERROR();
		UupmSysTreeModel result = this.findByNodeCode(nodeCode);
		if(null==result) return -1;
		return this.uupmSysTreeDao.countLayer(result);
	}
	
	public UupmSysTreeModel findOne(UupmSysTreeModel node) {
		if(null==node) return null;
		return (UupmSysTreeModel) this.uupmSysTreeDao.selectOne(node);
	}
	
	public UupmSysTreeModel findByNodeCode(String nodeCode) {
		if(EasyStringCheckUtils.isEmpty(nodeCode)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("nodeCode", nodeCode);
		return (UupmSysTreeModel) this.uupmSysTreeDao.selectOne(paramMap);
	}
	
	private int updateLeftForAdd(UupmSysTreeModel node) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("nodeRight", node.getNodeRight());
		paramMap.put("nodeStep", 2);
		return this.uupmSysTreeDao.updateLeftForAdd(paramMap);
	}
	
	private int updateRightForAdd(UupmSysTreeModel node) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("nodeRight", node.getNodeRight());
		paramMap.put("nodeStep", 2);
		return this.uupmSysTreeDao.updateRightForAdd(paramMap);
	}
	
	private int updateLeftForDel(UupmSysTreeModel node) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("nodeLeft", node.getNodeLeft());
		int nodeStep = node.getNodeRight() - node.getNodeLeft() + 1;
		paramMap.put("nodeStep", nodeStep);
		return this.uupmSysTreeDao.updateLeftForDel(paramMap);
	}
	
	private int updateRightForDel(UupmSysTreeModel node) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("nodeRight", node.getNodeRight());
		int nodeStep = node.getNodeRight() - node.getNodeLeft() + 1;
		paramMap.put("nodeStep", nodeStep);
		return this.uupmSysTreeDao.updateRightForDel(paramMap);
	}
	
}
