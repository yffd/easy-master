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
import com.yffd.easy.uupm.api.model.UupmTreeResourceModel;
import com.yffd.easy.uupm.service.UupmTreeResourceService;
import com.yffd.easy.uupm.web.common.UupmCommonController;
import com.yffd.easy.uupm.web.support.UupmResourceSupport;
import com.yffd.easy.uupm.web.vo.UupmResourceComboTreeVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年04月03日 14时09分26秒 <br/>
 * @author		 ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/uupm/resource")
public class UupmResourceController extends UupmCommonController {

	@Autowired
	private UupmTreeResourceService uupmTreeResourceService;
	@Autowired
	private UupmResourceSupport uupmResourceSupport;
	
	@RequestMapping(value="/listRoot", method=RequestMethod.POST)
	public RespModel listRoot(@RequestParam Map<String, Object> paramMap) {
		String nodeStatus = null;
		List<UupmTreeResourceModel> result = this.uupmTreeResourceService.findRootNodeList(nodeStatus, null);
		if(null!=result && !result.isEmpty()) {
			List<UupmResourceComboTreeVO> treeList = this.uupmResourceSupport.toSyncTreeVO(result, "root");
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/listApp", method=RequestMethod.POST)
	public RespModel listApp(@RequestParam Map<String, Object> paramMap) {
		String nodeValueType = "A";
		String nodeStatus = null;
		List<UupmTreeResourceModel> result = this.uupmTreeResourceService.findNodeListByValueType(nodeValueType, nodeStatus, null);
		if(null!=result && !result.isEmpty()) {
			List<UupmResourceComboTreeVO> treeList = this.uupmResourceSupport.toSyncTreeVO(result, "root");
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
		List<UupmTreeResourceModel> result = this.uupmTreeResourceService.findChildrenNodeList(nodeLabel, nodeCode, null);
		if(null!=result && !result.isEmpty()) {
			List<UupmResourceComboTreeVO> treeList = this.uupmResourceSupport.toSyncTreeVO(result, nodeCode);
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/listAll", method=RequestMethod.POST)
	public RespModel listAll(@RequestParam Map<String, Object> paramMap) {
		List<UupmTreeResourceModel> result = this.uupmTreeResourceService.findAllNodeList(null);
		if(null!=result && !result.isEmpty()) {
			List<UupmResourceComboTreeVO> treeList = this.uupmResourceSupport.toSyncTreeVO(result, "root");
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/addRoot", method=RequestMethod.POST)
	public RespModel addRoot(UupmTreeResourceModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getNodeCode())) return this.error("参数无效");
		model.setNodeLabel(model.getNodeCode());
		// 存在判断
		UupmTreeResourceModel result = this.uupmTreeResourceService.findNode(model.getNodeLabel(), null);
		if(null!=result) return this.error("编号已存在");
		this.uupmTreeResourceService.addRootNode(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/addChild", method=RequestMethod.POST)
	public RespModel addChild(UupmTreeResourceModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getNodeLabel()) 
				|| EasyStringCheckUtils.isEmpty(model.getNodeCode())
				|| EasyStringCheckUtils.isEmpty(model.getParentNodeCode())) return this.error("参数无效");
		// 存在判断
		UupmTreeResourceModel result = this.uupmTreeResourceService.findNode(model.getNodeLabel(), model.getNodeCode(), null);
		if(null!=result) return this.error("编号已存在");
		this.uupmTreeResourceService.addChildNode(model.getNodeLabel(), model.getParentNodeCode(), model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public RespModel edit(UupmTreeResourceModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getNodeLabel()) 
				|| EasyStringCheckUtils.isEmpty(model.getNodeCode())) return this.error("参数无效");
		UupmTreeResourceModel oldNode = new UupmTreeResourceModel();
		oldNode.setNodeLabel(model.getNodeLabel());
		oldNode.setNodeCode(model.getNodeCode());
		this.uupmTreeResourceService.updateNode(model, oldNode, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/editStatus", method=RequestMethod.POST)
	public RespModel editStatus(UupmTreeResourceModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getNodeLabel()) 
				|| EasyStringCheckUtils.isEmpty(model.getNodeCode())
				|| EasyStringCheckUtils.isEmpty(model.getNodeStatus())) return this.error("参数无效");
		this.uupmTreeResourceService.updateStatus(model.getNodeLabel(), model.getNodeCode(), model.getNodeStatus(), null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public RespModel del(UupmTreeResourceModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getNodeLabel()) 
				|| EasyStringCheckUtils.isEmpty(model.getNodeCode())) return this.error("参数无效");
		this.uupmTreeResourceService.delNodes(model.getNodeLabel(), model.getNodeCode(), null);
		return this.successAjax();
	}
	
}
