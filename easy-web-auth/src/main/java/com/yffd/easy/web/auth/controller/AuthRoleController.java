package com.yffd.easy.web.auth.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

import com.yffd.easy.auth.model.AuthResource;
import com.yffd.easy.auth.model.AuthRole;
import com.yffd.easy.auth.service.AuthResourceService;
import com.yffd.easy.auth.service.AuthRoleService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月21日 下午1:47:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/auth/role")
public class AuthRoleController extends BaseController {

	@Autowired
	private AuthRoleService authRoleService;
	@Autowired
	private AuthResourceService authResourceService;
	
	@RequestMapping(value="/findPage", method=RequestMethod.POST)
	@ResponseBody
	public RespModel findPage(AuthRole role, SearchBoxVO searchBoxVO) {
		PageParam pageParam = this.getPageParam(searchBoxVO);
		PageResult<AuthRole> pageResult = this.authRoleService.findPage(role, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findList", method=RequestMethod.POST)
	@ResponseBody
	public RespModel list(AuthRole role) {
		List<AuthRole> pageResult = this.authRoleService.findList(role);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespModel add(AuthRole role) {
		if(ValidUtils.isNull(role) 
				|| ValidUtils.isEmpty(role.getRoleCode())
				|| ValidUtils.isEmpty(role.getRoleName())) {
			return this.error("参数无效");
		}
		AuthRole _role = this.authRoleService.findByCode(role.getRoleCode());
		if(!ValidUtils.isNull(_role)) {
			return this.errorAjax("编号已存在");
		}
		role.setDefaultAdd("admin", new Date());
		this.authRoleService.add(role);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespModel edit(AuthRole role) {
		if(ValidUtils.isNull(role) 
				|| ValidUtils.isEmpty(role.getRoleCode())) {
			return this.error("参数无效");
		}
		role.setDefaultUpdate("admin", new Date());
		this.authRoleService.editByCode(role);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespModel del(HttpServletRequest req) {
		String roleCode = req.getParameter("roleCode");
		if(ValidUtils.isEmpty(roleCode)) {
			return this.errorAjax("参数无效");
		}
		this.authRoleService.delByCode(roleCode);
		return this.successAjax();
	}
	
	@RequestMapping(value="/saveRoleResource", method=RequestMethod.POST)
	@ResponseBody
	public RespModel saveRoleResource(HttpServletRequest req) {
		String roleCode = req.getParameter("roleCode");
		String rsCodes = req.getParameter("rsCodes");
		if(ValidUtils.isEmpty(roleCode)) {
			return this.error("参数无效");
		}
		List<String> rsCodesList = null;
		if(!ValidUtils.isBlank(rsCodes)) {
			rsCodesList = Arrays.asList(rsCodes.split(","));
		}
		this.authRoleService.saveRoleResource(roleCode, rsCodesList);
		return this.successAjax();
	}
	
	@RequestMapping(value="/findRoleResource", method=RequestMethod.POST)
	@ResponseBody
	public RespModel findRoleResource(HttpServletRequest req) {
		String roleCode = req.getParameter("roleCode");
		if(ValidUtils.isEmpty(roleCode)) {
			return this.error("参数无效");
		}
		List<AuthResource> list = this.authResourceService.findByRole(roleCode);
		return this.successAjax(list);
	}
	
	@RequestMapping(value="/saveRoleUser", method=RequestMethod.POST)
	@ResponseBody
	public RespModel saveRoleUser(HttpServletRequest req) {
		String userCode = req.getParameter("userCode");
		String roleCodes = req.getParameter("roleCodes");
		if(ValidUtils.isEmpty(userCode)) {
			return this.error("参数无效");
		}
		List<String> roleCodesList = null;
		if(!ValidUtils.isBlank(roleCodes)) {
			roleCodesList = Arrays.asList(roleCodes.split(","));
		}
		this.authRoleService.saveRoleUser(userCode, roleCodesList);
		return this.successAjax();
	}
	
	@RequestMapping(value="/findRoleUser", method=RequestMethod.POST)
	@ResponseBody
	public RespModel findRoleUser(HttpServletRequest req) {
		String userCode = req.getParameter("userCode");
		if(ValidUtils.isEmpty(userCode)) {
			return this.error("参数无效");
		}
		List<AuthRole> list = this.authRoleService.findByUser(userCode);
		return this.successAjax(list);
	}
	
	
	
}

