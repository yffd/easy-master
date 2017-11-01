package com.yffd.easy.web.pmi.controller;

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

import com.yffd.easy.pmi.model.PmiResource;
import com.yffd.easy.pmi.model.PmiRole;
import com.yffd.easy.pmi.service.PmiResourceService;
import com.yffd.easy.pmi.service.PmiRoleService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月21日 下午1:47:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/pmi/role")
public class PmiRoleController extends BaseController {

	@Autowired
	private PmiRoleService pmiRoleService;
	@Autowired
	private PmiResourceService pmiResourceService;
	
	@RequestMapping(value="/findPage", method=RequestMethod.POST)
	@ResponseBody
	public RespModel findPage(PmiRole role, SearchBoxVO searchBoxVO) {
		PageParam pageParam = this.getPageParam(searchBoxVO);
		PageResult<PmiRole> pageResult = this.pmiRoleService.findPage(role, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findList", method=RequestMethod.POST)
	@ResponseBody
	public RespModel list(PmiRole role) {
		List<PmiRole> pageResult = this.pmiRoleService.findList(role);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespModel add(PmiRole role) {
		if(ValidUtils.isNull(role) 
				|| ValidUtils.isEmpty(role.getRoleCode())
				|| ValidUtils.isEmpty(role.getRoleName())) {
			return this.error("参数无效");
		}
		PmiRole _role = this.pmiRoleService.findByCode(role.getRoleCode());
		if(!ValidUtils.isNull(_role)) {
			return this.errorAjax("编号已存在");
		}
		role.setDefaultAdd("admin", new Date());
		this.pmiRoleService.add(role);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespModel edit(PmiRole role) {
		if(ValidUtils.isNull(role) 
				|| ValidUtils.isEmpty(role.getRoleCode())) {
			return this.error("参数无效");
		}
		role.setDefaultUpdate("admin", new Date());
		this.pmiRoleService.editByCode(role);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespModel del(HttpServletRequest req) {
		String roleCode = req.getParameter("roleCode");
		if(ValidUtils.isEmpty(roleCode)) {
			return this.errorAjax("参数无效");
		}
		this.pmiRoleService.delByCode(roleCode);
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
		this.pmiRoleService.saveRoleResource(roleCode, rsCodesList);
		return this.successAjax();
	}
	
	@RequestMapping(value="/findRoleResource", method=RequestMethod.POST)
	@ResponseBody
	public RespModel findRoleResource(HttpServletRequest req) {
		String roleCode = req.getParameter("roleCode");
		if(ValidUtils.isEmpty(roleCode)) {
			return this.error("参数无效");
		}
		List<PmiResource> list = this.pmiResourceService.findByRole(roleCode);
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
		this.pmiRoleService.saveRoleUser(userCode, roleCodesList);
		return this.successAjax();
	}
	
	@RequestMapping(value="/findRoleUser", method=RequestMethod.POST)
	@ResponseBody
	public RespModel findRoleUser(HttpServletRequest req) {
		String userCode = req.getParameter("userCode");
		if(ValidUtils.isEmpty(userCode)) {
			return this.error("参数无效");
		}
		List<PmiRole> list = this.pmiRoleService.findByUser(userCode);
		return this.successAjax(list);
	}
	
	
	
}

