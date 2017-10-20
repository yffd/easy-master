package org.yffd.easy.app.controller.system;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.app.controller.system.support.SysOrganizationSupport;
import org.yffd.easy.app.controller.system.vo.SysOrganizationVO;
import org.yffd.easy.app.system.model.SysOrganizationModel;
import org.yffd.easy.app.system.service.SysOrganizationService;
import org.yffd.easy.common.core.model.RespModel;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.core.view.vo.ComboTreeVO;
import org.yffd.easy.common.ssm.web.BaseController;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月21日 下午1:47:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/sys/org")
public class SysOrganizationController extends BaseController {

	@Autowired
	private SysOrganizationSupport sysOrganizationSupport;
	@Autowired
	private SysOrganizationService sysOrganizationService;
	
	@RequestMapping(value="/orgTree", method=RequestMethod.POST)
	@ResponseBody
	public RespModel listTree(HttpServletRequest req) {
		List<SysOrganizationModel> list = this.sysOrganizationService.findAll();
		if(!ValidUtils.isEmpty(list)) {
			List<ComboTreeVO> treeList = this.sysOrganizationSupport.toListTreeVO(list);
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
		List<SysOrganizationModel> list = this.sysOrganizationService.findByParentCode(code);
		if(!ValidUtils.isEmpty(list)) {
			List<SysOrganizationVO> voList = this.sysOrganizationSupport.toListVO(list);
			return this.successAjax(voList);
		}
		return this.errorAjax("未找到有效数据");
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespModel add(HttpServletRequest req, SysOrganizationModel sysOrganization) {
		if(ValidUtils.isNull(sysOrganization) 
				|| ValidUtils.isEmpty(sysOrganization.getOrgCode())) {
			return this.error("参数无效");
		}
		SysOrganizationModel org = this.sysOrganizationService.findByCode(sysOrganization.getOrgCode());
		if(!ValidUtils.isNull(org)) {
			return this.errorAjax("编号已存在");
		}
		if(ValidUtils.isEmpty(sysOrganization.getParentCode())) {
			sysOrganization.setParentCode("-1");
		}
		sysOrganization.setDefaultAdd("admin", new Date());
		this.sysOrganizationService.add(sysOrganization);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespModel edit(HttpServletRequest req, SysOrganizationModel sysOrganization) {
		if(ValidUtils.isNull(sysOrganization) 
				|| ValidUtils.isEmpty(sysOrganization.getOrgCode())) {
			return this.error("参数无效");
		}
		if(ValidUtils.isEmpty(sysOrganization.getParentCode())) {
			sysOrganization.setParentCode("-1");
		}
		sysOrganization.setDefaultUpdate("admin", new Date());
		this.sysOrganizationService.editByCode(sysOrganization);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespModel del(HttpServletRequest req) {
		String code = req.getParameter("code");
		if(ValidUtils.isEmpty(code)) {
			return this.errorAjax("参数无效");
		}
		this.sysOrganizationService.delByCode(code);
		return this.successAjax();
	}
	
}

