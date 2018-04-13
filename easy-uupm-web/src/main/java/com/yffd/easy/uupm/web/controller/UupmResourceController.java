
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
import com.yffd.easy.uupm.api.model.UupmResourceModel;
import com.yffd.easy.uupm.service.UupmResourceService;
import com.yffd.easy.uupm.web.common.UupmCommonController;
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
	private UupmResourceService uupmResourceService;
	
	@RequestMapping(value="/listRoot", method=RequestMethod.POST)
	public RespModel listRoot(@RequestParam Map<String, Object> paramMap) {
		UupmResourceModel paramNode = new UupmResourceModel();
		paramNode.setTenantCode(this.getLoginInfo().getTenantCode());
		List<UupmResourceModel> result = this.uupmResourceService.findRootNodes(paramNode, null);
		if(null!=result && !result.isEmpty()) {
			List<UupmResourceModel> treeList = this.uupmResourceService.convertToMultiTree(result, "root");
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/listApp", method=RequestMethod.POST)
	public RespModel listApp(@RequestParam Map<String, Object> paramMap) {
		UupmResourceModel paramNode = new UupmResourceModel();
		paramNode.setTenantCode(this.getLoginInfo().getTenantCode());
		paramNode.setRsType("A");
		List<UupmResourceModel> result = this.uupmResourceService.findList(paramNode, this.getLoginInfo());
		if(null!=result && !result.isEmpty()) {
			List<UupmResourceModel> treeList = this.uupmResourceService.convertToMultiTree(result, "root");
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/listAll", method=RequestMethod.POST)
	public RespModel listAll(@RequestParam Map<String, Object> paramMap) {
		UupmResourceModel paramNode = new UupmResourceModel();
		paramNode.setTenantCode(this.getLoginInfo().getTenantCode());
		List<UupmResourceModel> result = this.uupmResourceService.findList(paramNode, this.getLoginInfo());
		if(null!=result && !result.isEmpty()) {
			List<UupmResourceModel> treeList = this.uupmResourceService.convertToMultiTree(result, "root");
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/listChildren", method=RequestMethod.POST)
	public RespModel listChildren(@RequestParam Map<String, Object> paramMap) {
		String treeId = (String) paramMap.get("treeId");
		String rsCode = (String) paramMap.get("rsCode");
		if(EasyStringCheckUtils.isEmpty(treeId) || EasyStringCheckUtils.isEmpty(rsCode)) return this.error("参数无效");
		UupmResourceModel paramNode = new UupmResourceModel();
		paramNode.setTenantCode(this.getLoginInfo().getTenantCode());
		paramNode.setTreeId(treeId);
		paramNode.setRsCode(rsCode);
		List<UupmResourceModel> result = this.uupmResourceService.findChildrenNodes(paramNode, this.getLoginInfo());
		if(null!=result && !result.isEmpty()) {
			List<UupmResourceModel> treeList = this.uupmResourceService.convertToMultiTree(result, rsCode);
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/addRoot", method=RequestMethod.POST)
	public RespModel addRoot(UupmResourceModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getRsCode())) return this.error("参数无效");
		model.setTreeId(model.getRsCode());
		// 存在判断
		UupmResourceModel paramNode = new UupmResourceModel();
		paramNode.setTenantCode(this.getLoginInfo().getTenantCode());
		boolean existRootNode = this.uupmResourceService.existRootNode(paramNode, this.getLoginInfo());
		if(existRootNode) return this.error("编号已存在");
		this.uupmResourceService.addRootNode(model, this.getLoginInfo());
		return this.successAjax();
	}
	
	@RequestMapping(value="/addChild", method=RequestMethod.POST)
	public RespModel addChild(UupmResourceModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getTreeId()) 
				|| EasyStringCheckUtils.isEmpty(model.getRsCode())) return this.error("参数无效");
		// 存在判断
		UupmResourceModel paramNode = new UupmResourceModel();
		paramNode.setTenantCode(this.getLoginInfo().getTenantCode());
		paramNode.setRsCode(model.getRsCode());	// 资源编号全局唯一
		boolean exsist = this.uupmResourceService.exsist(paramNode, getLoginInfo());
		if(exsist) return this.error("编号已存在");
		this.uupmResourceService.addChildNode(model, getLoginInfo());
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public RespModel edit(UupmResourceModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getTreeId()) 
				|| EasyStringCheckUtils.isEmpty(model.getRsCode())) return this.error("参数无效");
		UupmResourceModel oldNode = new UupmResourceModel();
		oldNode.setTreeId(model.getTreeId());
		oldNode.setRsCode(model.getRsCode());
		this.uupmResourceService.update(model, oldNode, null, getLoginInfo());
		return this.successAjax();
	}
	
	@RequestMapping(value="/editStatus", method=RequestMethod.POST)
	public RespModel editStatus(UupmResourceModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getTreeId()) 
				|| EasyStringCheckUtils.isEmpty(model.getRsCode())
				|| EasyStringCheckUtils.isEmpty(model.getRsStatus())) return this.error("参数无效");
		UupmResourceModel newNode = new UupmResourceModel();
		newNode.setRsStatus(model.getRsStatus());	// 待修改的属性
		
		UupmResourceModel oldNode = new UupmResourceModel();	// 更新条件
		oldNode.setTreeId(model.getTreeId());
		oldNode.setRsCode(model.getRsCode());
		this.uupmResourceService.updateNodes(model, oldNode, getLoginInfo());
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public RespModel del(UupmResourceModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getTreeId()) 
				|| EasyStringCheckUtils.isEmpty(model.getRsCode())) return this.error("参数无效");
		UupmResourceModel paramNode = new UupmResourceModel();
		paramNode.setTenantCode(this.getLoginInfo().getTenantCode());
		paramNode.setTreeId(model.getTreeId());
		paramNode.setRsCode(model.getRsCode());
		this.uupmResourceService.deleteNodes(model, getLoginInfo());
		return this.successAjax();
	}
	
}