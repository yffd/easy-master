package org.yffd.easy.app.controller.system;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.app.controller.system.support.SysMenuSupport;
import org.yffd.easy.app.controller.system.vo.SysMenuTreeVO;
import org.yffd.easy.app.system.model.SysMenu;
import org.yffd.easy.app.system.service.SysMenuService;
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
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {
	@Autowired
	private SysMenuSupport sysMenuSupport;
	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping(value="/tree", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity tree(HttpServletRequest req) {
		List<SysMenu> list = this.sysMenuService.findAll();
		if(!ValidUtils.isEmpty(list)) {
			List<SysMenuTreeVO> treeList = this.sysMenuSupport.getTreeList(list);
			return this.successAjax(treeList);
		}
		return this.errorAjax("未找到有效数据");
	}
	
	@RequestMapping(value="/findByCode", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity findByCode(HttpServletRequest req) {
		String code = req.getParameter("code");
		if(ValidUtils.isEmpty(code)) {
			return this.errorAjax("参数无效");
		}
		SysMenu entity = this.sysMenuService.findByCode(code);
		return this.successAjax(entity);
	}
	
	@RequestMapping(value="/findByParentCode", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity findByParentCode(HttpServletRequest req) {
		String parentCode = req.getParameter("parentCode");
		if(ValidUtils.isEmpty(parentCode)) {
			return this.errorAjax("参数无效");
		}
		List<SysMenu> list = this.sysMenuService.findByParentCode(parentCode);
		return this.successAjax(list);
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity edit(HttpServletRequest req, SysMenu sysMenu) {
		if(ValidUtils.isNull(sysMenu) || ValidUtils.isEmpty(sysMenu.getCode()) 
				|| ValidUtils.isEmpty(sysMenu.getParentCode())) {
			return this.errorAjax("参数无效");
		}
		sysMenu.setDefaultUpdate("admin", new Date());
		this.sysMenuService.editByCode(sysMenu);
		return this.successAjax();
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity add(HttpServletRequest req, SysMenu sysMenu) {
		if(ValidUtils.isNull(sysMenu) || ValidUtils.isEmpty(sysMenu.getCode()) 
				|| ValidUtils.isEmpty(sysMenu.getParentCode())) {
			return this.errorAjax("参数无效");
		}
		sysMenu.setDefaultAdd("admin", new Date());
		this.sysMenuService.add(sysMenu);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity del(HttpServletRequest req) {
		String code = req.getParameter("code");
		if(ValidUtils.isEmpty(code)) {
			return this.errorAjax("参数无效");
		}
		this.sysMenuService.delByCode(code);
		return this.successAjax();
	}
	
}

