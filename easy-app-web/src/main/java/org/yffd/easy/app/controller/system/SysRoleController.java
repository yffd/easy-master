package org.yffd.easy.app.controller.system;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.app.system.model.SysFunction;
import org.yffd.easy.app.system.model.SysRole;
import org.yffd.easy.app.system.service.SysFunctionService;
import org.yffd.easy.app.system.service.SysRoleService;
import org.yffd.easy.common.core.model.RespModel;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.core.view.vo.DataGridVO;
import org.yffd.easy.common.core.view.vo.SearchBoxVO;
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
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysFunctionService sysFunctionService;
	
	@RequestMapping(value="/findPage", method=RequestMethod.POST)
	@ResponseBody
	public RespModel findPage(SysRole sysRole, SearchBoxVO searchBoxVO) {
		PageParam pageParam = this.getPageParam(searchBoxVO);
		PageResult<SysRole> pageResult = this.sysRoleService.findPage(sysRole, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findList", method=RequestMethod.POST)
	@ResponseBody
	public RespModel list(SysRole sysRole) {
		List<SysRole> pageResult = this.sysRoleService.findList(sysRole);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespModel add(SysRole sysRole) {
		if(ValidUtils.isNull(sysRole) 
				|| ValidUtils.isEmpty(sysRole.getRoleCode())
				|| ValidUtils.isEmpty(sysRole.getRoleName())) {
			return this.error("参数无效");
		}
		SysRole role = this.sysRoleService.findByCode(sysRole.getRoleCode());
		if(!ValidUtils.isNull(role)) {
			return this.errorAjax("编号已存在");
		}
		sysRole.setDefaultAdd("admin", new Date());
		this.sysRoleService.add(sysRole);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespModel edit(SysRole sysRole) {
		if(ValidUtils.isNull(sysRole) 
				|| ValidUtils.isEmpty(sysRole.getRoleCode())) {
			return this.error("参数无效");
		}
		sysRole.setDefaultUpdate("admin", new Date());
		this.sysRoleService.editByCode(sysRole);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespModel del(HttpServletRequest req) {
		String roleCode = req.getParameter("roleCode");
		if(ValidUtils.isEmpty(roleCode)) {
			return this.errorAjax("参数无效");
		}
		this.sysRoleService.delByCode(roleCode);
		return this.successAjax();
	}
	
	@RequestMapping(value="/saveRoleFunc", method=RequestMethod.POST)
	@ResponseBody
	public RespModel saveRoleFunc(HttpServletRequest req) {
		String roleCode = req.getParameter("roleCode");
		String funcCodes = req.getParameter("funcCodes");
		if(ValidUtils.isEmpty(roleCode)) {
			return this.error("参数无效");
		}
		List<String> funcCodesList = null;
		if(!ValidUtils.isBlank(funcCodes)) {
			funcCodesList = Arrays.asList(funcCodes.split(","));
		}
		this.sysRoleService.saveRoleFunc(roleCode, funcCodesList);
		return this.successAjax();
	}
	
	@RequestMapping(value="/findRoleFunc", method=RequestMethod.POST)
	@ResponseBody
	public RespModel findRoleFunc(HttpServletRequest req) {
		String roleCode = req.getParameter("roleCode");
		if(ValidUtils.isEmpty(roleCode)) {
			return this.error("参数无效");
		}
		List<SysFunction> list = this.sysFunctionService.findByRole(roleCode);
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
		this.sysRoleService.saveRoleUser(userCode, roleCodesList);
		return this.successAjax();
	}
	
	@RequestMapping(value="/findRoleUser", method=RequestMethod.POST)
	@ResponseBody
	public RespModel findRoleUser(HttpServletRequest req) {
		String userCode = req.getParameter("userCode");
		if(ValidUtils.isEmpty(userCode)) {
			return this.error("参数无效");
		}
		List<SysRole> list = this.sysRoleService.findByUser(userCode);
		return this.successAjax(list);
	}
	
	
	
}

