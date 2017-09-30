package org.yffd.easy.app.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.app.controller.system.support.MenuTreeEntity;
import org.yffd.easy.app.controller.system.support.SysFuncSupport;
import org.yffd.easy.app.system.entity.SysFunction;
import org.yffd.easy.app.system.service.SysFunctionService;
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
@RequestMapping("/sys/func")
public class SysFuncController extends BaseController {
	private Logger LOG = LoggerFactory.getLogger(SysFuncController.class);
	@Autowired
	private SysFunctionService sysFunctionService;
	@Autowired
	private SysFuncSupport sysFuncSupport;
	
	@RequestMapping(value="/listMenu", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity listMenu(HttpServletRequest req) {
		List<SysFunction> list = this.sysFunctionService.findFunction();
		if(!ValidUtils.isEmpty(list)) {
			List<MenuTreeEntity> menuTree = this.sysFuncSupport.menuTree(list);
			return this.successAjax(menuTree);
		}
		return this.successAjax("没有菜单");
	}
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity list(HttpServletRequest req) {
		String code = req.getParameter("id");
		List<SysFunction> list = this.sysFunctionService.findFunction();
		if(!ValidUtils.isEmpty(list)) {
			List<MenuTreeEntity> menuTree = this.sysFuncSupport.menuTree(list);
			return this.successAjax(menuTree);
		}
		return this.successAjax("没有菜单");
	}
	
}

