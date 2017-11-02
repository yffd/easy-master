package com.yffd.easy.web.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.common.core.model.RespModel;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.ssm.web.BaseController;

import com.yffd.easy.auth.model.AuthUser;
import com.yffd.easy.auth.service.AuthUserService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月1日 上午11:10:00 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/auth")
public class AuthLoginController extends BaseController {

	@Autowired
	private AuthUserService authUserService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public RespModel login(HttpServletRequest req) {
		String userCode = req.getParameter("userCode");
		String userPwd = req.getParameter("userPwd");
		
		if(ValidUtils.isEmpty(userCode) || ValidUtils.isEmpty(userPwd)) {
			return this.errorAjax("用户名或密码为空");
		}
		AuthUser user = this.authUserService.findByCodeAndPwd(userCode, userPwd);
		if(null==user) {
			return this.errorAjax("该用户不存在");
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	@ResponseBody
	public RespModel logout() {
		
		return this.successAjax();
	}
	
	@RequestMapping(value="/findUserInfo", method=RequestMethod.POST)
	@ResponseBody
	public RespModel findUserInfo() {
		
		return this.successAjax();
	}
	
}

