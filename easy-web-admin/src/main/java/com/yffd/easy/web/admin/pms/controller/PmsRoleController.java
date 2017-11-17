package com.yffd.easy.web.admin.pms.controller;

import java.util.Arrays;
import java.util.List;

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

import com.yffd.easy.admin.pms.model.PmsResource;
import com.yffd.easy.admin.pms.model.PmsRole;
import com.yffd.easy.admin.pms.service.PmsResourceService;
import com.yffd.easy.admin.pms.service.PmsRoleService;
import com.yffd.easy.web.admin.AdminBaseController;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月21日 下午1:47:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/pms/role")
public class PmsRoleController extends AdminBaseController {

	@Autowired
	private PmsRoleService pmsRoleService;
	@Autowired
	private PmsResourceService pmsResourceService;
	
	@RequestMapping(value="/findPage", method=RequestMethod.POST)
	@ResponseBody
	public RespModel findPage(PmsRole role, SearchBoxVO searchBoxVO) {
		PageParam pageParam = this.getPageParam(searchBoxVO);
		PageResult<PmsRole> pageResult = this.pmsRoleService.findPage(role, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findList", method=RequestMethod.POST)
	@ResponseBody
	public RespModel list(PmsRole role) {
		List<PmsRole> pageResult = this.pmsRoleService.findList(role);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequiresPermissions("role-add")
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespModel add(PmsRole role) {
		if(ValidUtils.isNull(role) 
				|| ValidUtils.isEmpty(role.getRoleCode())
				|| ValidUtils.isEmpty(role.getRoleName())) {
			return this.error("参数无效");
		}
		PmsRole _role = this.pmsRoleService.findByCode(role.getRoleCode());
		if(!ValidUtils.isNull(_role)) {
			return this.errorAjax("编号已存在");
		}
		this.setAddDefault(role); //设置添加时默认值
		this.pmsRoleService.add(role);
		return this.successAjax();
	}
	
	@RequiresPermissions("role-edit")
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespModel edit(PmsRole role) {
		if(ValidUtils.isNull(role) 
				|| ValidUtils.isEmpty(role.getRoleCode())) {
			return this.error("参数无效");
		}
		this.setUpdateDefault(role); //设置修改时默认值
		this.pmsRoleService.editByCode(role);
		return this.successAjax();
	}
	
	@RequiresPermissions("role-del")
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespModel del(HttpServletRequest req) {
		String roleCode = req.getParameter("roleCode");
		if(ValidUtils.isEmpty(roleCode)) {
			return this.errorAjax("参数无效");
		}
		this.pmsRoleService.delByCode(roleCode);
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
		this.pmsRoleService.saveRoleResource(roleCode, rsCodesList);
		return this.successAjax();
	}
	
	@RequestMapping(value="/findRoleResource", method=RequestMethod.POST)
	@ResponseBody
	public RespModel findRoleResource(HttpServletRequest req) {
		String roleCode = req.getParameter("roleCode");
		if(ValidUtils.isEmpty(roleCode)) {
			return this.error("参数无效");
		}
		List<PmsResource> list = this.pmsResourceService.findByRole(roleCode);
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
		this.pmsRoleService.saveRoleUser(userCode, roleCodesList);
		return this.successAjax();
	}
	
	@RequestMapping(value="/findRoleUser", method=RequestMethod.POST)
	@ResponseBody
	public RespModel findRoleUser(HttpServletRequest req) {
		String userCode = req.getParameter("userCode");
		if(ValidUtils.isEmpty(userCode)) {
			return this.error("参数无效");
		}
		List<PmsRole> list = this.pmsRoleService.findByUser(userCode);
		return this.successAjax(list);
	}
	
	
	
}
