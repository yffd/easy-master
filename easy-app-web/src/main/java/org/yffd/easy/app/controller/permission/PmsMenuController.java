package org.yffd.easy.app.controller.permission;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.app.controller.support.PmsMenuSupport;
import org.yffd.easy.app.permission.entity.PmsMenu;
import org.yffd.easy.app.permission.service.PmsMenuService;
import org.yffd.easy.common.core.entity.MenuTreeEntity;
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
@RequestMapping("/pms/menu")
public class PmsMenuController extends BaseController {
	private Logger LOG = LoggerFactory.getLogger(PmsMenuController.class);
	
	@Autowired
	private PmsMenuService pmsMenuService;
	@Autowired
	private PmsMenuSupport pmsMenuSupport;
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity listPmsMenu(HttpServletRequest req) {
		List<PmsMenu> list = this.pmsMenuService.findAll();
		if(!ValidUtils.isEmpty(list)) {
			List<MenuTreeEntity> menuTree = this.pmsMenuSupport.menuTree(list);
			return this.successAjax(menuTree);
		}
		return this.successAjax("没有菜单");
	}
	
	@RequestMapping("/addUI")
	public String addUI(HttpServletRequest req, Model model) {
		return "pms/pmsMenuAdd";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest req, PmsMenu pmsMenu, Model model) {
		return "pms/pmsMenuList";
	}
	
	@RequestMapping("/editUI")
	public String editUI(HttpServletRequest req, String menuCode, Model model) {
		if(!ValidUtils.isEmpty(menuCode)) {
			PmsMenu pmsMenu = this.pmsMenuService.findMenuWithParent(menuCode);
			model.addAttribute(pmsMenu);
		}
		return "pms/pmsMenuEdit";
	}
	
	@RequestMapping("/edit")
	public RespEntity edit(HttpServletRequest req, PmsMenu pmsMenu, Model model) {
		try {
			if(null!=pmsMenu) {
				pmsMenu.setEditTime(new Date());
				this.pmsMenuService.update(pmsMenu);
			}
			return this.success();
		} catch (Exception e) {
			LOG.error("修改菜单错误", e);
			return this.error("修改菜单错误", model);
		}
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest req, Model model) {
		return "pms/pmsMenuList";
	}
}

