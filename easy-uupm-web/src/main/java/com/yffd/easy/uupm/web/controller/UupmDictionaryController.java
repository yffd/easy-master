package com.yffd.easy.uupm.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.domain.RespModel;
import com.yffd.easy.uupm.api.model.UupmTreeDictionaryModel;
import com.yffd.easy.uupm.service.UupmTreeDictionaryService;
import com.yffd.easy.uupm.web.common.UupmCommonController;
import com.yffd.easy.uupm.web.support.UupmTreeNodeSupport;
import com.yffd.easy.uupm.web.vo.UupmTreeNodeComboTreeVO;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年03月02日 18时15分20秒 <br/>
 * @author		 ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/uupm/dictionary")
public class UupmDictionaryController extends UupmCommonController {
	@Autowired
	private UupmTreeDictionaryService uupmTreeDictionaryService;
	@Autowired
	private UupmTreeNodeSupport uupmTreeNodeSupport;
	
	@RequestMapping(value="/listRoot", method=RequestMethod.POST)
	public RespModel listRoot(@RequestParam Map<String, Object> paramMap) {
		String nodeStatus = null;
		List<UupmTreeDictionaryModel> result = this.uupmTreeDictionaryService.findRootNodeList(nodeStatus, null);
		if(null!=result && !result.isEmpty()) {
			List<UupmTreeNodeComboTreeVO> treeList = this.uupmTreeNodeSupport.toSyncTreeVO(result, "root");
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/listChildren", method=RequestMethod.POST)
	public RespModel listChildren(@RequestParam Map<String, Object> paramMap) {
		String nodeLabel = (String) paramMap.get("nodeLabel");
		String nodeCode = (String) paramMap.get("nodeCode");
		if(null==nodeLabel || EasyStringCheckUtils.isEmpty(nodeLabel)
				|| null==nodeCode || EasyStringCheckUtils.isEmpty(nodeCode)) return this.error("参数无效");
		List<UupmTreeDictionaryModel> result = this.uupmTreeDictionaryService.findChildrenNodeList(nodeLabel, nodeCode, null);
		if(null!=result && !result.isEmpty()) {
			List<UupmTreeNodeComboTreeVO> treeList = this.uupmTreeNodeSupport.toSyncTreeVO(result, nodeCode);
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/addRoot", method=RequestMethod.POST)
	public RespModel addRoot(UupmTreeDictionaryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getNodeCode())) return this.error("参数无效");
		model.setNodeLabel(model.getNodeCode());
		// 存在判断
		UupmTreeDictionaryModel result = this.uupmTreeDictionaryService.findNode(model.getNodeLabel(), null);
		if(null!=result) return this.error("编号已存在");
		this.uupmTreeDictionaryService.addRootNode(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/addChild", method=RequestMethod.POST)
	public RespModel addChild(UupmTreeDictionaryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getNodeLabel()) 
				|| EasyStringCheckUtils.isEmpty(model.getNodeCode())
				|| EasyStringCheckUtils.isEmpty(model.getParentNodeCode())) return this.error("参数无效");
		// 存在判断
		UupmTreeDictionaryModel result = this.uupmTreeDictionaryService.findNode(model.getNodeLabel(), model.getNodeCode(), null);
		if(null!=result) return this.error("编号已存在");
		this.uupmTreeDictionaryService.addChildNode(model.getNodeLabel(), model.getParentNodeCode(), model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public RespModel edit(UupmTreeDictionaryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getNodeLabel()) 
				|| EasyStringCheckUtils.isEmpty(model.getNodeCode())) return this.error("参数无效");
		UupmTreeDictionaryModel oldNode = new UupmTreeDictionaryModel();
		oldNode.setNodeLabel(model.getNodeLabel());
		oldNode.setNodeCode(model.getNodeCode());
		this.uupmTreeDictionaryService.updateNode(model, oldNode, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public RespModel delById(UupmTreeDictionaryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getNodeLabel()) 
				|| EasyStringCheckUtils.isEmpty(model.getNodeCode())) return this.error("参数无效");
		this.uupmTreeDictionaryService.delNodes(model.getNodeLabel(), model.getNodeCode(), null);
		return this.successAjax();
	}
	
}
