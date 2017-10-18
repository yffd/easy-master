package org.yffd.easy.app.controller.system;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.app.controller.system.support.SysOrgSupport;
import org.yffd.easy.app.controller.system.vo.SysOrgVO;
import org.yffd.easy.app.system.model.SysOrganization;
import org.yffd.easy.app.system.service.SysOrganizationService;
import org.yffd.easy.common.core.model.RespEntity;
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
public class SysOrgController extends BaseController {

	@Autowired
	private SysOrgSupport sysOrgSupport;
	@Autowired
	private SysOrganizationService sysOrganizationService;
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity list(HttpServletRequest req) {
		String code = req.getParameter("id");
		if(ValidUtils.isEmpty(code)) {
			code = "-1";
		}
		List<SysOrganization> list = this.sysOrganizationService.findByParentCode(code);
		if(!ValidUtils.isEmpty(list)) {
			List<SysOrgVO> voList = this.sysOrgSupport.toListVO(list);
			return this.successAjax(voList);
		}
		return this.errorAjax("未找到有效数据");
	}
	
	@RequestMapping(value="/listTree", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity listTree(HttpServletRequest req) {
		List<SysOrganization> list = this.sysOrganizationService.findAll();
		if(!ValidUtils.isEmpty(list)) {
			List<ComboTreeVO> treeList = this.sysOrgSupport.toListTreeVO(list);
			return this.successAjax(treeList);
		}
		return this.errorAjax("未找到有效数据");
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity add(HttpServletRequest req, SysOrganization sysOrganization) {
		if(ValidUtils.isNull(sysOrganization) 
				|| ValidUtils.isEmpty(sysOrganization.getCode())) {
			return this.error("参数无效");
		}
		if(ValidUtils.isEmpty(sysOrganization.getParentCode())) {
			sysOrganization.setParentCode("-1");
		}
		SysOrganization org = this.sysOrganizationService.findByCode(sysOrganization.getCode());
		if(!ValidUtils.isNull(org)) {
			return this.errorAjax("编号已存在");
		}
		sysOrganization.setDefaultAdd("admin", new Date());
		this.sysOrganizationService.add(sysOrganization);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity edit(HttpServletRequest req, SysOrganization sysOrganization) {
		if(ValidUtils.isNull(sysOrganization) 
				|| ValidUtils.isEmpty(sysOrganization.getCode())
				|| ValidUtils.isEmpty(sysOrganization.getParentCode())) {
			return this.error("参数无效");
		}
		sysOrganization.setDefaultUpdate("admin", new Date());
		this.sysOrganizationService.editByCode(sysOrganization);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity del(HttpServletRequest req) {
		String code = req.getParameter("code");
		if(ValidUtils.isEmpty(code)) {
			return this.errorAjax("参数无效");
		}
		this.sysOrganizationService.delByCode(code);
		return this.successAjax();
	}
	
}

