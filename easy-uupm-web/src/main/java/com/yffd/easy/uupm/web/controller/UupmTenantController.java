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
import com.yffd.easy.uupm.api.model.UupmTenantModel;
import com.yffd.easy.uupm.service.UupmTenantService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月9日 上午10:09:07 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/uupm/tenant")
public class UupmTenantController extends UupmBaseController {

	@Autowired
	private UupmTenantService uupmTenantService;
	
	@RequestMapping(value="/findPage", method=RequestMethod.POST)
	public RespModel findPage(@RequestParam Map<String, Object> paramMap) {
		PageParam pageParam = this.getPageParam(paramMap);
		PageResult<UupmTenantModel> pageResult = this.uupmTenantService.findPage(paramMap, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public RespModel add(UupmTenantModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getTenantCode())) return this.error("参数无效");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tenantCode", model.getTenantCode());
		UupmTenantModel hasModel = (UupmTenantModel) this.uupmTenantService.findOne(paramMap);
		if(null!=hasModel) return this.errorAjax("租户编号已存在");
		this.uupmTenantService.addOne(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public RespModel edit(UupmTenantModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) {
			return this.error("参数无效");
		}
		this.uupmTenantService.updateById(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/delById", method=RequestMethod.POST)
	public RespModel delById(String id) {
		if(EasyStringCheckUtils.isEmpty(id)) return this.errorAjax("参数无效");
		this.uupmTenantService.delById(id, null);
		return this.successAjax();
	}
}

