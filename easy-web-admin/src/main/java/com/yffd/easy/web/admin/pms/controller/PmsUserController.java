package com.yffd.easy.web.admin.pms.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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

import com.yffd.easy.admin.login.model.LoginInfo;
import com.yffd.easy.admin.pms.model.PmsUser;
import com.yffd.easy.admin.pms.service.PmsUserService;
import com.yffd.easy.web.admin.AdminBaseController;
import com.yffd.easy.web.admin.shiro.utils.PasswordHelper;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月21日 下午1:47:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/pms/user")
public class PmsUserController extends AdminBaseController {

	@Autowired
	private PmsUserService pmsUserService;
	
	@RequestMapping(value="/findPage", method=RequestMethod.POST)
	@ResponseBody
	public RespModel listPage(PmsUser user, SearchBoxVO searchBoxVO) {
		PageParam pageParam = this.getPageParam(searchBoxVO);
		PageResult<PmsUser> pageResult = this.pmsUserService.findList(user, pageParam );
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequiresPermissions("user-add")
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespModel add(PmsUser user) {
		if(ValidUtils.isNull(user) 
				|| ValidUtils.isEmpty(user.getUserCode())
				|| ValidUtils.isEmpty(user.getUserName())) {
			return this.error("参数无效");
		}
		PmsUser _user = this.pmsUserService.findUser(user.getUserCode());
		if(!ValidUtils.isNull(_user)) {
			return this.errorAjax("编号已存在");
		}
		// 设置密码
		user.setPassword(user.getUserCode());
		PasswordHelper.encryptPassword(user);
		
		this.setAddDefault(user); //设置添加时默认值
		this.pmsUserService.add(user);
		return this.successAjax();
	}
	
	@RequiresPermissions("user-edit")
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespModel edit(PmsUser user) {
		if(ValidUtils.isNull(user) 
				|| ValidUtils.isEmpty(user.getUserCode())) {
			return this.error("参数无效");
		}
		this.setUpdateDefault(user); //设置修改时默认值
		this.pmsUserService.editByCode(user);
		return this.successAjax();
	}
	
	@RequiresPermissions("user-del")
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespModel del(HttpServletRequest req) {
		String userCode = req.getParameter("userCode");
		if(ValidUtils.isEmpty(userCode)) {
			return this.errorAjax("参数无效");
		}
		this.pmsUserService.delByCode(userCode);
		return this.successAjax();
	}
	
	@RequestMapping(value="/changeStatus", method=RequestMethod.POST)
	@ResponseBody
	public RespModel changeUserStatus(HttpServletRequest req) {
		String userCode = req.getParameter("userCode");
		String userStatus = req.getParameter("userStatus");
		if(ValidUtils.isEmpty(userCode) || ValidUtils.isEmpty(userStatus)) {
			return this.errorAjax("参数无效");
		}
		PmsUser user = this.pmsUserService.findUser(userCode);
		if(null==user) return this.errorAjax("修改失败");
		if("A".equals(userStatus)) {
			user.setUserStatus("A");
		} else {
			user.setUserStatus("I");
		}
		this.pmsUserService.editByCode(user);
		return this.successAjax();
	}
	
	@RequestMapping(value="/resetPassword", method=RequestMethod.POST)
	@ResponseBody
	public RespModel resetPassword(HttpServletRequest req) {
		String userCode = req.getParameter("userCode");
		if(ValidUtils.isEmpty(userCode)) {
			return this.errorAjax("参数无效");
		}
		PmsUser user = this.pmsUserService.findUser(userCode);
		if(null==user) return this.errorAjax("密码修改失败");
		user.setPassword(user.getUserCode());
		PasswordHelper.encryptPassword(user);
		this.pmsUserService.editByCode(user);
		return this.successAjax();
	}
	
	@RequestMapping(value="/changePassword", method=RequestMethod.POST)
	@ResponseBody
	public RespModel changePassword(HttpServletRequest req) {
		LoginInfo info = this.currentLoginInfo();
		if(null==info) return this.error("session失效");
		String userCode = info.getUserCode();
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		if(ValidUtils.isEmpty(userCode) || ValidUtils.isEmpty(oldPassword) || ValidUtils.isEmpty(newPassword)) {
			return this.errorAjax("参数无效");
		}
		PmsUser user = this.pmsUserService.findUser(userCode);
		if(null==user) return this.errorAjax("密码修改失败");
		String confirm = PasswordHelper.getPwd(oldPassword, user.getCredentialsSalt());
		if(confirm.equals(user.getPassword())) {
			PmsUser _user = new PmsUser();
			_user.setUserCode(userCode);
			_user.setPassword(newPassword);
			PasswordHelper.encryptPassword(_user);
			this.pmsUserService.editByCode(_user);
		} else {
			return this.error(); // 就密码错误
		}
		return this.successAjax();
	}
}

