package com.yffd.easy.web.auth.controller;

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

import com.yffd.easy.auth.model.AuthOrganization;
import com.yffd.easy.auth.service.AuthOrganizationService;
import com.yffd.easy.web.auth.support.AuthOrganizationSupport;
import com.yffd.easy.web.auth.vo.AuthOrganizationTreeGridVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月21日 下午1:47:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/auth/org")
public class AuthOrganizationController extends BaseController {

	@Autowired
	private AuthOrganizationSupport authOrganizationSupport;
	@Autowired
	private AuthOrganizationService authOrganizationService;
	
	@RequestMapping(value="/orgTree", method=RequestMethod.POST)
	@ResponseBody
	public RespModel listTree(HttpServletRequest req) {
		List<AuthOrganization> list = this.authOrganizationService.findAll();
		if(!ValidUtils.isEmpty(list)) {
			List<AuthOrganizationTreeGridVO> treeList = this.authOrganizationSupport.toSyncTreeGridVO(list);
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
		List<AuthOrganization> list = this.authOrganizationService.findByParentCode(code);
		if(!ValidUtils.isEmpty(list)) {
			List<AuthOrganizationTreeGridVO> voList = this.authOrganizationSupport.toAsyncTreeGridVO(list);
			return this.successAjax(voList);
		}
		return this.errorAjax("未找到有效数据");
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespModel add(HttpServletRequest req, AuthOrganization organization) {
		if(ValidUtils.isNull(organization) 
				|| ValidUtils.isEmpty(organization.getOrgCode())) {
			return this.error("参数无效");
		}
		AuthOrganization org = this.authOrganizationService.findByCode(organization.getOrgCode());
		if(!ValidUtils.isNull(org)) {
			return this.errorAjax("编号已存在");
		}
		if(ValidUtils.isEmpty(organization.getParentCode())) {
			organization.setParentCode("-1");
		}
		organization.setDefaultAdd("admin", new Date());
		this.authOrganizationService.add(organization);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespModel edit(HttpServletRequest req, AuthOrganization organization) {
		if(ValidUtils.isNull(organization) 
				|| ValidUtils.isEmpty(organization.getOrgCode())) {
			return this.error("参数无效");
		}
		if(ValidUtils.isEmpty(organization.getParentCode())) {
			organization.setParentCode("-1");
		}
		organization.setDefaultUpdate("admin", new Date());
		this.authOrganizationService.editByCode(organization);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespModel del(HttpServletRequest req) {
		String orgCode = req.getParameter("orgCode");
		if(ValidUtils.isEmpty(orgCode)) {
			return this.errorAjax("参数无效");
		}
		this.authOrganizationService.delByCode(orgCode);
		return this.successAjax();
	}
	
}

