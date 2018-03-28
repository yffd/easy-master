package com.yffd.easy.uupm.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.domain.RespModel;
import com.yffd.easy.uupm.api.model.UupmOrganizationModel;
import com.yffd.easy.uupm.service.UupmOrganizationService;
import com.yffd.easy.uupm.web.support.UupmOrganizationSupport;
import com.yffd.easy.uupm.web.vo.UupmOrganizationComboTreeVO;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年03月16日 16时08分03秒 <br/>
 * @author		 ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/uupm/org")
public class UupmOrganizationController extends UupmBaseController {

	@Autowired
	private UupmOrganizationService uupmOrganizationService;
	@Autowired
	private UupmOrganizationSupport uupmOrganizationSupport;
	
	@RequestMapping(value="/findTree", method=RequestMethod.POST)
	public RespModel findTree(@RequestParam Map<String, Object> paramMap) {
		List<UupmOrganizationModel> result = this.uupmOrganizationService.findList(paramMap);
		if(null!=result && !result.isEmpty()) {
			List<UupmOrganizationComboTreeVO> treeList = this.uupmOrganizationSupport.toSyncTreeVO(result, "-1");
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/findOne", method=RequestMethod.POST)
	public RespModel findOne(UupmOrganizationModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) return this.error("参数无效");
		UupmOrganizationModel result = this.uupmOrganizationService.findById(model.getId());
		return this.successAjax(result);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public RespModel add(UupmOrganizationModel model) {
		if(null==model) return this.error("参数无效");
		// 存在判断
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgCode", model.getOrgCode());
		UupmOrganizationModel result = this.uupmOrganizationService.findOne(paramMap);
		if(null!=result) return this.error("编号已存在");
		this.uupmOrganizationService.addOne(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public RespModel edit(UupmOrganizationModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) {
			return this.error("参数无效");
		}
		this.uupmOrganizationService.updateById(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/delById", method=RequestMethod.POST)
	public RespModel delById(String id) {
		if(EasyStringCheckUtils.isEmpty(id)) return this.errorAjax("参数无效");
		this.uupmOrganizationService.delById(id, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/delBatch", method=RequestMethod.POST)
	public RespModel delBatch(HttpServletRequest req) {
		String orgCodes = req.getParameter("orgCodes");
		if(EasyStringCheckUtils.isEmpty(orgCodes)) return this.error("参数无效");
		int result = this.uupmOrganizationService.delWithInBy("orgCode", orgCodes, null);
		if(result==-1) return this.error("参数无效");
		return this.successAjax();
	}
	
}
