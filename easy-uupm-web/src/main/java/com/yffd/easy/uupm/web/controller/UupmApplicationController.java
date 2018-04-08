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

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.domain.RespModel;
import com.yffd.easy.framework.web.view.vo.DataGridVO;
import com.yffd.easy.framework.web.view.vo.PropertyGridVO;
import com.yffd.easy.uupm.api.model.UupmApplicationModel;
import com.yffd.easy.uupm.service.UupmApplicationService;
import com.yffd.easy.uupm.web.common.UupmCommonController;
import com.yffd.easy.uupm.web.support.UupmApplicationSupport;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年04月06日 14时09分38秒 <br/>
 * @author		 ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/uupm/application")
public class UupmApplicationController extends UupmCommonController {
	@Autowired
	private UupmApplicationSupport uupmApplicationSupport;
	@Autowired
	private UupmApplicationService uupmApplicationService;
	
	@RequestMapping(value="/findAppCfg", method=RequestMethod.POST)
	public RespModel findAppCfg(@RequestParam Map<String, Object> paramMap) {
		String appCode = (String) paramMap.get("appCode");
		if(EasyStringCheckUtils.isEmpty(appCode)) return this.error("参数无效");
		UupmApplicationModel model = new UupmApplicationModel();
		model.setAppCode(appCode);
		UupmApplicationModel result = this.uupmApplicationService.findOne(model , null);
		if(null==result) result = new UupmApplicationModel();
		List<PropertyGridVO> listResult = this.uupmApplicationSupport.toPropertyGridVO(result);
		DataGridVO dataGridVO = this.toDataGrid(listResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/saveAppCfg", method=RequestMethod.POST)
	public RespModel saveAppCfg(UupmApplicationModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getAppCode())) return this.error("参数无效");
		int result = this.uupmApplicationService.saveAppCfg(model, null);
		if(result==0) return this.error("保存配置失败");
		return this.successAjax();
	}
	
	@RequestMapping(value="/findPage", method=RequestMethod.POST)
	public RespModel findPage(@RequestParam Map<String, Object> paramMap) {
		PageParam paramPage = this.getPageParam(paramMap);
		PageResult<UupmApplicationModel> pageResult = this.uupmApplicationService.findPage(null, paramMap, paramPage, null);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findOne", method=RequestMethod.POST)
	public RespModel findOne(UupmApplicationModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) return this.error("参数无效");
		UupmApplicationModel result = this.uupmApplicationService.findOne(model, null);
		return this.successAjax(result);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public RespModel add(UupmApplicationModel model) {
		if(null==model) return this.error("参数无效");
		// 存在校验
		UupmApplicationModel paramModel = new UupmApplicationModel();
		paramModel.setTenantCode(model.getTenantCode());
		UupmApplicationModel resultModel = this.uupmApplicationService.findOne(paramModel, null);
		if(null!=resultModel) return this.error("数据已存在");
		int result = this.uupmApplicationService.addOne(model, null);
		if(result==0) return this.error("添加失败");
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public RespModel edit(UupmApplicationModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) return this.error("参数无效");
		UupmApplicationModel paramOld = new UupmApplicationModel();
		paramOld.setId(model.getId());
		int result = this.uupmApplicationService.update(model, paramOld, null, null);
		if(result==0) return this.error("更新失败");
		return this.successAjax();
	}
	
	@RequestMapping(value="/delById", method=RequestMethod.POST)
	public RespModel delById(String id) {
		if(EasyStringCheckUtils.isEmpty(id)) return this.errorAjax("参数无效");
		int result = this.uupmApplicationService.deleteBy("id", id);
		if(result==0) return this.error("删除失败");
		return this.successAjax();
	}
	
	@RequestMapping(value="/delBatch", method=RequestMethod.POST)
	public RespModel delBatch(HttpServletRequest req) {
		String ids = req.getParameter("ids");
		if(EasyStringCheckUtils.isEmpty(ids)) return this.error("参数无效");
		String[] idsArr = ids.split(",");
		List<String> idsList = Arrays.asList(idsArr);
		int result = this.uupmApplicationService.delete("id", idsList);
		if(result==0) return this.error("删除失败");
		return this.successAjax();
	}
	
}
