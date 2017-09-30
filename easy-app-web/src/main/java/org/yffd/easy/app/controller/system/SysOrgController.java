package org.yffd.easy.app.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.app.system.entity.SysOrganization;
import org.yffd.easy.app.system.service.SysOrganizationService;
import org.yffd.easy.common.core.entity.RespEntity;
import org.yffd.easy.common.core.util.ValidUtils;
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
	private SysOrganizationService sysOrganizationService;
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity list(HttpServletRequest req) {
		String parentCode = req.getParameter("id");
		if(ValidUtils.isEmpty(parentCode)) {
			parentCode = "-1";
		}
		List<SysOrganization> list = this.sysOrganizationService.findByParentCode(parentCode);
		return this.successAjax(list);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity add(HttpServletRequest req, SysOrganization organization) {
		return this.successAjax();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity update(HttpServletRequest req, SysOrganization organization) {
		return this.successAjax();
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity delete(HttpServletRequest req, SysOrganization organization) {
		return this.successAjax();
	}
	
}

