package org.yffd.easy.app.controller.permission;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yffd.easy.app.controller.support.PmsMenuSupport;
import org.yffd.easy.app.permission.entity.PmsMenu;
import org.yffd.easy.app.permission.service.PmsMenuService;
import org.yffd.easy.common.ssm.web.BaseController;

/**
 * @Description  登录.
 * @Date		 2017年9月18日 下午6:11:51 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
public class LoginController extends BaseController {
	@Autowired
	private PmsMenuSupport pmsMenuSupport;
	@Autowired
	private PmsMenuService pmsMenuService;
	
	/**
	 *  进入后台登陆页面
	 * @Date	2017年9月19日 下午2:05:57 <br/>
	 * @author  zhangST
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest req, Model model) {

		model.addAttribute("message", "用户名/密码错误");
		return "system/login";
	}

	/**
	 * 登陆后台管理系统
	 * @Date	2017年9月19日 下午2:06:30 <br/>
	 * @author  zhangST
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(HttpServletRequest req, Model model) {
		List<PmsMenu> menuList = pmsMenuService.findAll();
		String tree = this.pmsMenuSupport.getJsonTree(menuList);
		model.addAttribute("tree", tree);
		return "system/index";

	}
}

