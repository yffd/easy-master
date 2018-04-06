package com.yffd.easy.uupm.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.domain.RespModel;
import com.yffd.easy.uupm.api.model.UupmOrganizationModel;
import com.yffd.easy.uupm.service.UupmOrganizationService;
import com.yffd.easy.uupm.web.common.UupmCommonController;
import com.yffd.easy.uupm.web.support.UupmOrganizationSupport;
import com.yffd.easy.uupm.web.vo.UupmOrganizationComboTreeVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年03月30日 11时47分01秒 <br/>
 * @author		 ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/uupm/organization")
public class UupmOrganizationController extends UupmCommonController {

	@Autowired
	private UupmOrganizationService uupmOrganizationService;
	@Autowired
	private UupmOrganizationSupport uupmOrganizationSupport;
	
	@RequestMapping(value="/findTree", method=RequestMethod.POST)
	public RespModel findTree(@RequestParam Map<String, Object> paramMap) {
		List<UupmOrganizationModel> result = this.uupmOrganizationService.findList(null, paramMap, null);
		if(null!=result && !result.isEmpty()) {
			List<UupmOrganizationComboTreeVO> treeList = this.uupmOrganizationSupport.toSyncTreeVO(result, "root");
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/findOne", method=RequestMethod.POST)
	public RespModel findOne(UupmOrganizationModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) return this.error("参数无效");
		UupmOrganizationModel result = this.uupmOrganizationService.findOne(model, null);
		return this.successAjax(result);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public RespModel add(UupmOrganizationModel model) {
		if(null==model) return this.error("参数无效");
		// 存在校验
		UupmOrganizationModel paramModel = new UupmOrganizationModel();
		paramModel.setOrgCode(model.getOrgCode());
		UupmOrganizationModel result = this.uupmOrganizationService.findOne(paramModel, null);
		if(null!=result) return this.error("数据已存在");
		this.uupmOrganizationService.addOne(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public RespModel edit(UupmOrganizationModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) {
			return this.error("参数无效");
		}
		UupmOrganizationModel paramOld = new UupmOrganizationModel();
		paramOld.setId(model.getId());
		this.uupmOrganizationService.update(model, paramOld, null, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/delById", method=RequestMethod.POST)
	public RespModel delById(String id) {
		if(EasyStringCheckUtils.isEmpty(id)) return this.errorAjax("参数无效");
		this.uupmOrganizationService.deleteBy("id", id);
		return this.successAjax();
	}
	
	@RequestMapping(value="/delBatch", method=RequestMethod.POST)
	public RespModel delBatch(HttpServletRequest req) {
		String ids = req.getParameter("ids");
		if(EasyStringCheckUtils.isEmpty(ids)) return this.error("参数无效");
		String[] idsArr = ids.split(",");
		List<String> idsList = Arrays.asList(idsArr);
		int result = this.uupmOrganizationService.delete("idList", idsList);
		if(result==0) return this.error("删除失败");
		return this.successAjax();
	}
	
}
