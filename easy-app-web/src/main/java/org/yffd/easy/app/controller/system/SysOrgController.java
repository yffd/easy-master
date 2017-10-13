package org.yffd.easy.app.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.app.controller.system.support.SysOrgSupport;
import org.yffd.easy.app.controller.system.vo.SysOrganizationTreeVO;
import org.yffd.easy.app.system.model.SysOrganization;
import org.yffd.easy.app.system.service.SysOrganizationService;
import org.yffd.easy.common.core.model.RespEntity;
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
	private SysOrgSupport sysOrgSupport;
	@Autowired
	private SysOrganizationService sysOrganizationService;
	
	@RequestMapping(value="/tree", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity tree(HttpServletRequest req) {
		String parentCode = req.getParameter("id");
		if(ValidUtils.isEmpty(parentCode)) {
			return this.errorAjax("参数无效");
		}
		List<SysOrganization> list = this.sysOrganizationService.findAll();
		if(!ValidUtils.isEmpty(list)) {
			List<SysOrganizationTreeVO> treeList = this.sysOrgSupport.getTreeList(list);
			return this.successAjax(treeList);
		}
		return this.errorAjax("未找到有效数据");
	}
	
	@RequestMapping(value="/findByCode", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity findByCode(HttpServletRequest req) {
		String parentCode = req.getParameter("id");
		if(ValidUtils.isEmpty(parentCode)) {
			return this.errorAjax("参数无效");
		}
		List<SysOrganization> list = this.sysOrganizationService.findAll();
		if(!ValidUtils.isEmpty(list)) {
			List<SysOrganizationTreeVO> treeList = this.sysOrgSupport.getTreeList(list);
			return this.successAjax(treeList);
		}
		return this.errorAjax("未找到有效数据");
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

