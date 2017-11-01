package com.yffd.easy.web.pmi.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.common.core.model.RespModel;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.ssm.web.BaseController;

import com.yffd.easy.pmi.model.PmiOrganization;
import com.yffd.easy.pmi.service.PmiOrganizationService;
import com.yffd.easy.web.pmi.support.PmiOrganizationSupport;
import com.yffd.easy.web.pmi.vo.PmiOrganizationTreeGridVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月21日 下午1:47:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/pmi/org")
public class PmiOrganizationController extends BaseController {

	@Autowired
	private PmiOrganizationSupport pmiOrganizationSupport;
	@Autowired
	private PmiOrganizationService pmiOrganizationService;
	
	@RequestMapping(value="/orgTree", method=RequestMethod.POST)
	@ResponseBody
	public RespModel listTree(HttpServletRequest req) {
		List<PmiOrganization> list = this.pmiOrganizationService.findAll();
		if(!ValidUtils.isEmpty(list)) {
			List<PmiOrganizationTreeGridVO> treeList = this.pmiOrganizationSupport.toSyncTreeGridVO(list);
			return this.successAjax(treeList);
		}
		return this.errorAjax("未找到有效数据");
	}
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public RespModel list(HttpServletRequest req) {
		String code = req.getParameter("id");
		if(ValidUtils.isEmpty(code)) {
			code = "-1";
		}
		List<PmiOrganization> list = this.pmiOrganizationService.findByParentCode(code);
		if(!ValidUtils.isEmpty(list)) {
			List<PmiOrganizationTreeGridVO> voList = this.pmiOrganizationSupport.toAsyncTreeGridVO(list);
			return this.successAjax(voList);
		}
		return this.errorAjax("未找到有效数据");
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespModel add(HttpServletRequest req, PmiOrganization organization) {
		if(ValidUtils.isNull(organization) 
				|| ValidUtils.isEmpty(organization.getOrgCode())) {
			return this.error("参数无效");
		}
		PmiOrganization org = this.pmiOrganizationService.findByCode(organization.getOrgCode());
		if(!ValidUtils.isNull(org)) {
			return this.errorAjax("编号已存在");
		}
		if(ValidUtils.isEmpty(organization.getParentCode())) {
			organization.setParentCode("-1");
		}
		organization.setDefaultAdd("admin", new Date());
		this.pmiOrganizationService.add(organization);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespModel edit(HttpServletRequest req, PmiOrganization organization) {
		if(ValidUtils.isNull(organization) 
				|| ValidUtils.isEmpty(organization.getOrgCode())) {
			return this.error("参数无效");
		}
		if(ValidUtils.isEmpty(organization.getParentCode())) {
			organization.setParentCode("-1");
		}
		organization.setDefaultUpdate("admin", new Date());
		this.pmiOrganizationService.editByCode(organization);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespModel del(HttpServletRequest req) {
		String orgCode = req.getParameter("orgCode");
		if(ValidUtils.isEmpty(orgCode)) {
			return this.errorAjax("参数无效");
		}
		this.pmiOrganizationService.delByCode(orgCode);
		return this.successAjax();
	}
	
}

