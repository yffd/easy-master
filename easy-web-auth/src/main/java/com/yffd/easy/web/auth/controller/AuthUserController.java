package com.yffd.easy.web.auth.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.common.core.model.RespModel;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.core.view.vo.DataGridVO;
import org.yffd.easy.common.core.view.vo.SearchBoxVO;
import org.yffd.easy.common.ssm.web.BaseController;

import com.yffd.easy.auth.model.AuthUser;
import com.yffd.easy.auth.service.AuthUserService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月21日 下午1:47:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/auth/user")
public class AuthUserController extends BaseController {

	@Autowired
	private AuthUserService authUserService;
	
	@RequestMapping(value="/findPage", method=RequestMethod.POST)
	@ResponseBody
	public RespModel listPage(AuthUser user, SearchBoxVO searchBoxVO) {
		PageParam pageParam = this.getPageParam(searchBoxVO);
		PageResult<AuthUser> pageResult = this.authUserService.findList(user, pageParam );
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespModel add(AuthUser user) {
		if(ValidUtils.isNull(user) 
				|| ValidUtils.isEmpty(user.getUserCode())
				|| ValidUtils.isEmpty(user.getUserName())) {
			return this.error("参数无效");
		}
		AuthUser _user = this.authUserService.findByCode(user.getUserCode());
		if(!ValidUtils.isNull(_user)) {
			return this.errorAjax("编号已存在");
		}
		user.setDefaultAdd("admin", new Date());
		this.authUserService.add(user);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespModel edit(AuthUser user) {
		if(ValidUtils.isNull(user) 
				|| ValidUtils.isEmpty(user.getUserCode())) {
			return this.error("参数无效");
		}
		user.setDefaultUpdate("admin", new Date());
		this.authUserService.editByCode(user);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespModel del(HttpServletRequest req) {
		String userCode = req.getParameter("userCode");
		if(ValidUtils.isEmpty(userCode)) {
			return this.errorAjax("参数无效");
		}
		this.authUserService.delByCode(userCode);
		return this.successAjax();
	}
	
}

