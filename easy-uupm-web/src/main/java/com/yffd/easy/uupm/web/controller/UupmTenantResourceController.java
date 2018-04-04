package com.yffd.easy.uupm.web.controller;

import java.util.List;
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
import com.yffd.easy.uupm.api.model.UupmResourceModel;
import com.yffd.easy.uupm.api.model.UupmTenantResourceModel;
import com.yffd.easy.uupm.service.UupmTenantResourceService;
import com.yffd.easy.uupm.web.common.UupmCommonController;
import com.yffd.easy.uupm.web.support.UupmComboTreeSupport;
import com.yffd.easy.uupm.web.vo.UupmResourceComboTreeVO;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年03月22日 13时43分03秒 <br/>
 * @author		 ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/uupm/tenant/resource")
public class UupmTenantResourceController extends UupmCommonController {

	@Autowired
	private UupmTenantResourceService uupmTenantResourceService;
	@Autowired
	private UupmComboTreeSupport uupmComboTreeSupport;
	
//	@RequestMapping(value="/findTenantResWithAppTree", method=RequestMethod.POST)
//	public RespModel findResWithAppTree() {
//		UupmTenantResourceModel model = new UupmTenantResourceModel();
//		model.setTenantCode("it1.com.cn");
//		List<UupmResourceModel> listResult = this.uupmTenantResourceService.findTenantResourceListBy(model);
//		if(null!=listResult && !listResult.isEmpty()) {
//			List<UupmResourceComboTreeVO> treeList = this.uupmComboTreeSupport.toSyncTreeVO(listResult, "-1");
//			return this.successAjax(treeList);
//		}
//		return this.successAjax();
//	}
	
//	@RequestMapping(value="/findOne", method=RequestMethod.POST)
//	public RespModel findOne(UupmTenantResourceModel model) {
//		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) return this.error("参数无效");
//		UupmTenantResourceModel result = this.uupmTenantResourceService.findById(model.getId());
//		return this.successAjax(result);
//	}
	
//	@RequestMapping(value="/add", method=RequestMethod.POST)
//	public RespModel add(UupmTenantResourceModel model) {
//		if(null==model) return this.error("参数无效");
//		this.uupmTenantResourceService.addOne(model, null);
//		return this.successAjax();
//	}
	
//	@RequestMapping(value="/edit", method=RequestMethod.POST)
//	public RespModel edit(UupmTenantResourceModel model) {
//		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) {
//			return this.error("参数无效");
//		}
//		this.uupmTenantResourceService.updateById(model, null);
//		return this.successAjax();
//	}
	
//	@RequestMapping(value="/delById", method=RequestMethod.POST)
//	public RespModel delById(String id) {
//		if(EasyStringCheckUtils.isEmpty(id)) return this.errorAjax("参数无效");
//		this.uupmTenantResourceService.delById(id, null);
//		return this.successAjax();
//	}
	
//	@RequestMapping(value="/delBatch", method=RequestMethod.POST)
//	public RespModel delBatch(HttpServletRequest req) {
//		String ids = req.getParameter("ids");
//		if(EasyStringCheckUtils.isEmpty(ids)) return this.error("参数无效");
//		int result = this.uupmTenantResourceService.delByIds(ids, null);
//		if(result==-1) return this.error("参数无效");
//		return this.successAjax();
//	}
	
}