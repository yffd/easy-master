package com.yffd.easy.web.admin.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.common.core.model.RespModel;

import com.yffd.easy.admin.login.model.LoginInfo;
import com.yffd.easy.admin.pms.model.PmsOrganization;
import com.yffd.easy.admin.pms.model.PmsUser;
import com.yffd.easy.admin.pms.service.PmsOrganizationService;
import com.yffd.easy.admin.pms.service.PmsUserService;
import com.yffd.easy.web.admin.AdminBaseController;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月1日 上午11:10:00 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
public class LoginController extends AdminBaseController {
//	@Autowired
//	private PmsAccountService pmsAccountService;
	@Autowired
	private PmsUserService pmsUserService;
	@Autowired
	private PmsOrganizationService pmsOrganizationService;
	
	@RequestMapping(value="/login")
	@ResponseBody
	public RespModel login(HttpServletRequest req, HttpServletResponse resp) {
		if("GET".equalsIgnoreCase(req.getMethod())) {
			try {
				resp.sendRedirect("/admin/login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		String errorMsg = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			errorMsg = "用户名/密码错误"; // 账户名认证错误
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			errorMsg = "用户名/密码错误"; // 账户认证错误
		} else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
			errorMsg = "用户名/密码错误"; // 账户锁定
		} else if (AuthenticationException.class.getName().equals(exceptionClassName)) {
			errorMsg = "用户名/密码错误"; // 密码认证错误
		} else if (exceptionClassName != null) {
			errorMsg = "错误提示：" + exceptionClassName;
		}
		if(null==errorMsg) {
			return this.successAjax();
		} else {
			return this.error(errorMsg);
		}
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	@ResponseBody
	public RespModel logout() {
		if(null==this.getSession()) {
			return this.successAjax();
		}
		SecurityUtils.getSubject().logout();
		return this.successAjax();
	}
	
	@RequestMapping(value="/showLoginUserInfo", method=RequestMethod.POST)
	@ResponseBody
	public RespModel showLoginUserInfo() {
		LoginInfo info = this.currentLoginInfo();
		if(null==info) return this.successAjax();
		
		String userCode = info.getUserCode();
		PmsUser user = this.pmsUserService.findUser(userCode);
		if(null!=user) {
			PmsOrganization org = this.pmsOrganizationService.findByCode(user.getOrgCode());
			user.setOrganization(org);
		}
		return this.successAjax(user);
	}
	
	
}

