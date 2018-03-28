package com.yffd.easy.uupm.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.domain.RespModel;
import com.yffd.easy.framework.web.view.vo.DataGridVO;
import com.yffd.easy.uupm.api.model.UupmRoleModel;
import com.yffd.easy.uupm.api.model.UupmUserModel;
import com.yffd.easy.uupm.service.UupmRoleService;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年03月15日 10时02分10秒 <br/>
 * @author		 ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/uupm/role")
public class UupmRoleController extends UupmBaseController {

	@Autowired
	private UupmRoleService uupmRoleService;
	
	@RequestMapping(value="/findPage", method=RequestMethod.POST)
	public RespModel findPage(@RequestParam Map<String, Object> paramMap) {
		PageParam pageParam = this.getPageParam(paramMap);
		PageResult<UupmRoleModel> pageResult = this.uupmRoleService.findPage(paramMap, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findOne", method=RequestMethod.POST)
	public RespModel findOne(UupmRoleModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) return this.error("参数无效");
		UupmRoleModel result = this.uupmRoleService.findById(model.getId());
		return this.successAjax(result);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public RespModel add(UupmRoleModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getRoleCode())) return this.error("参数无效");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleCode", model.getRoleCode());
		UupmRoleModel result = this.uupmRoleService.findOne(paramMap);
		if(null!=result) return this.error("编号已存在");
		this.uupmRoleService.addOne(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public RespModel edit(UupmRoleModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) {
			return this.error("参数无效");
		}
		this.uupmRoleService.updateById(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/delById", method=RequestMethod.POST)
	public RespModel delById(String id) {
		if(EasyStringCheckUtils.isEmpty(id)) return this.errorAjax("参数无效");
		this.uupmRoleService.delById(id, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/delByRoleCode", method=RequestMethod.POST)
	public RespModel delByRoleCode(String roleCode) {
		if(EasyStringCheckUtils.isEmpty(roleCode)) return this.errorAjax("参数无效");
		this.uupmRoleService.delByRoleCode(roleCode, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/delBatch", method=RequestMethod.POST)
	public RespModel delBatch(HttpServletRequest req) {
		String ids = req.getParameter("ids");
		if(EasyStringCheckUtils.isEmpty(ids)) return this.error("参数无效");
		int result = this.uupmRoleService.delByIds(ids, null);
		if(result==-1) return this.error("参数无效");
		return this.successAjax();
	}
	
}
