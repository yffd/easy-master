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
import com.yffd.easy.uupm.api.model.UupmDictionaryModel;
import com.yffd.easy.uupm.service.UupmDictionaryService;
import com.yffd.easy.uupm.web.common.UupmCommonController;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年04月10日 17时19分46秒 <br/>
 * @author		 ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/uupm/dictionary")
public class UupmDictionaryController extends UupmCommonController {

	@Autowired
	private UupmDictionaryService uupmDictionaryService;
	
	@RequestMapping(value="/listRoot", method=RequestMethod.POST)
	public RespModel listRoot(@RequestParam Map<String, Object> paramMap) {
		UupmDictionaryModel paramNode = new UupmDictionaryModel();
		paramNode.setTenantCode(this.getLoginInfo().getTenantCode());
		List<UupmDictionaryModel> result = this.uupmDictionaryService.findRootNodes(paramNode, null);
		if(null!=result && !result.isEmpty()) {
			List<UupmDictionaryModel> treeList = this.uupmDictionaryService.convertToMultiTree(result, "root");
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/listChildren", method=RequestMethod.POST)
	public RespModel listChildren(@RequestParam Map<String, Object> paramMap) {
		String treeId = (String) paramMap.get("treeId");
		String keyCode = (String) paramMap.get("keyCode");
		if(EasyStringCheckUtils.isEmpty(treeId) || EasyStringCheckUtils.isEmpty(keyCode)) return this.error("参数无效");
		UupmDictionaryModel paramNode = new UupmDictionaryModel();
		paramNode.setTenantCode(this.getLoginInfo().getTenantCode());
		paramNode.setTreeId(treeId);
		paramNode.setKeyCode(keyCode);
		List<UupmDictionaryModel> result = this.uupmDictionaryService.findChildrenNodes(paramNode, this.getLoginInfo());
		if(null!=result && !result.isEmpty()) {
			List<UupmDictionaryModel> treeList = this.uupmDictionaryService.convertToMultiTree(result, "root");
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/addRoot", method=RequestMethod.POST)
	public RespModel addRoot(UupmDictionaryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getKeyCode())) return this.error("参数无效");
		model.setTreeId(model.getKeyCode());
		// 存在判断
		UupmDictionaryModel paramNode = new UupmDictionaryModel();
		paramNode.setTenantCode(this.getLoginInfo().getTenantCode());
		boolean existRootNode = this.uupmDictionaryService.existRootNode(paramNode, this.getLoginInfo());
		if(existRootNode) return this.error("编号已存在");
		this.uupmDictionaryService.addRootNode(model, this.getLoginInfo());
		return this.successAjax();
	}
	
	@RequestMapping(value="/addChild", method=RequestMethod.POST)
	public RespModel addChild(UupmDictionaryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getTreeId()) 
				|| EasyStringCheckUtils.isEmpty(model.getKeyCode())) return this.error("参数无效");
		// 存在判断
		UupmDictionaryModel paramNode = new UupmDictionaryModel();
		paramNode.setTenantCode(this.getLoginInfo().getTenantCode());
		paramNode.setKeyCode(model.getKeyCode());
		boolean exsist = this.uupmDictionaryService.exsist(paramNode, getLoginInfo());
		if(exsist) return this.error("编号已存在");
		this.uupmDictionaryService.addChildNode(model, getLoginInfo());
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public RespModel edit(UupmDictionaryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getTreeId()) 
				|| EasyStringCheckUtils.isEmpty(model.getKeyCode())) return this.error("参数无效");
		UupmDictionaryModel oldNode = new UupmDictionaryModel();
		oldNode.setTreeId(model.getTreeId());
		oldNode.setKeyCode(model.getKeyCode());
		this.uupmDictionaryService.update(model, oldNode, null, getLoginInfo());
		return this.successAjax();
	}
	
	@RequestMapping(value="/editStatus", method=RequestMethod.POST)
	public RespModel editStatus(UupmDictionaryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getTreeId()) 
				|| EasyStringCheckUtils.isEmpty(model.getKeyCode())
				|| EasyStringCheckUtils.isEmpty(model.getValueStatus())) return this.error("参数无效");
		UupmDictionaryModel newNode = new UupmDictionaryModel();
		newNode.setValueStatus(model.getValueStatus());	// 待修改的属性
		
		UupmDictionaryModel oldNode = new UupmDictionaryModel();	// 更新条件
		oldNode.setTreeId(model.getTreeId());
		oldNode.setKeyCode(model.getKeyCode());
		this.uupmDictionaryService.updateNodes(newNode, oldNode, getLoginInfo());
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public RespModel del(UupmDictionaryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getTreeId()) 
				|| EasyStringCheckUtils.isEmpty(model.getKeyCode())) return this.error("参数无效");
		UupmDictionaryModel paramNode = new UupmDictionaryModel();
		paramNode.setTenantCode(this.getLoginInfo().getTenantCode());
		paramNode.setTreeId(model.getTreeId());
		paramNode.setKeyCode(model.getKeyCode());
		this.uupmDictionaryService.deleteNodes(model, getLoginInfo());
		return this.successAjax();
	}
	
}
