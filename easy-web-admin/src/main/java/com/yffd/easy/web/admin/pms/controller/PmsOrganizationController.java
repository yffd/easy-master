package com.yffd.easy.web.admin.pms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.common.core.model.RespModel;
import org.yffd.easy.common.core.util.ValidUtils;

import com.yffd.easy.admin.pms.model.PmsOrganization;
import com.yffd.easy.admin.pms.service.PmsOrganizationService;
import com.yffd.easy.web.admin.AdminBaseController;
import com.yffd.easy.web.admin.pms.support.PmsOrganizationSupport;
import com.yffd.easy.web.admin.pms.vo.PmsOrganizationTreeGridVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月21日 下午1:47:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/pms/org")
public class PmsOrganizationController extends AdminBaseController {

	@Autowired
	private PmsOrganizationSupport pmsOrganizationSupport;
	@Autowired
	private PmsOrganizationService pmsOrganizationService;
	
	@RequestMapping(value="/orgTree", method=RequestMethod.POST)
	@ResponseBody
	public RespModel listTree(HttpServletRequest req) {
		List<PmsOrganization> list = this.pmsOrganizationService.findAll();
		if(!ValidUtils.isEmpty(list)) {
			List<PmsOrganizationTreeGridVO> treeList = this.pmsOrganizationSupport.toSyncTreeGridVO(list);
			return this.successAjax(treeList);
		}
		return this.errorAjax("未找到有效数据");
	}
	
	@RequiresPermissions("org-view")
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public RespModel list(HttpServletRequest req) {
		String code = req.getParameter("id");
		if(ValidUtils.isEmpty(code)) {
			code = "-1";
		}
		List<PmsOrganization> list = this.pmsOrganizationService.findByParentCode(code);
		if(!ValidUtils.isEmpty(list)) {
			List<PmsOrganizationTreeGridVO> voList = this.pmsOrganizationSupport.toAsyncTreeGridVO(list);
			return this.successAjax(voList);
		}
		return this.errorAjax("未找到有效数据");
	}
	
	@RequiresPermissions("org-add")
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespModel add(HttpServletRequest req, PmsOrganization organization) {
		if(ValidUtils.isNull(organization) 
				|| ValidUtils.isEmpty(organization.getOrgCode())) {
			return this.error("参数无效");
		}
		PmsOrganization org = this.pmsOrganizationService.findByCode(organization.getOrgCode());
		if(!ValidUtils.isNull(org)) {
			return this.errorAjax("编号已存在");
		}
		if(ValidUtils.isEmpty(organization.getParentCode())) {
			organization.setParentCode("-1");
		}
		this.setAddDefault(organization); //设置添加时默认值
		this.pmsOrganizationService.add(organization);
		return this.successAjax();
	}
	
	@RequiresPermissions("org-edit")
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespModel edit(HttpServletRequest req, PmsOrganization organization) {
		if(ValidUtils.isNull(organization) 
				|| ValidUtils.isEmpty(organization.getOrgCode())) {
			return this.error("参数无效");
		}
		if(ValidUtils.isEmpty(organization.getParentCode())) {
			organization.setParentCode("-1");
		}
		this.setUpdateDefault(organization); //设置修改时默认值
		this.pmsOrganizationService.editByCode(organization);
		return this.successAjax();
	}
	
	@RequiresPermissions("org-del")
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespModel del(HttpServletRequest req) {
		String orgCode = req.getParameter("orgCode");
		if(ValidUtils.isEmpty(orgCode)) {
			return this.errorAjax("参数无效");
		}
		this.pmsOrganizationService.delByCode(orgCode);
		return this.successAjax();
	}
	
}

