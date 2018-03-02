package com.yffd.easy.uupm.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.yffd.easy.uupm.api.model.UupmDataCategoryModel;
import com.yffd.easy.uupm.service.UupmDataCategoryService;
import com.yffd.easy.uupm.web.support.UupmDataCategorySupport;
import com.yffd.easy.uupm.web.vo.UupmDataCategoryTreeVO;
;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年02月28日 16时46分34秒 <br/>
 * @author		 ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/uupm/dataCategory")
public class UupmDataCategoryController extends UupmBaseController {

	@Autowired
	private UupmDataCategoryService uupmDataCategoryService;
	@Autowired
	private UupmDataCategorySupport uupmDataCategorySupport;
	
	@RequestMapping(value="/findPage", method=RequestMethod.POST)
	public RespModel findPage(@RequestParam Map<String, Object> paramMap) {
		PageParam pageParam = this.getPageParam(paramMap);
		PageResult<UupmDataCategoryModel> pageResult = this.uupmDataCategoryService.findPage(paramMap, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/listTree", method=RequestMethod.POST)
	public RespModel listTree(@RequestParam Map<String, Object> paramMap) {
		List<UupmDataCategoryModel> result = this.uupmDataCategoryService.findList(paramMap);
		if(null!=result && !result.isEmpty()) {
			List<UupmDataCategoryTreeVO> treeList = this.uupmDataCategorySupport.toSyncTreeVO(result);
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/findOne", method=RequestMethod.POST)
	public RespModel findOne(UupmDataCategoryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getCategoryCode())) return this.error("参数无效");
		UupmDataCategoryModel result = this.uupmDataCategoryService.findOne(model);
		return this.successAjax(result);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public RespModel add(UupmDataCategoryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getCategoryCode())) return this.error("参数无效");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("categoryCode", model.getCategoryCode());
		UupmDataCategoryModel result = this.uupmDataCategoryService.findOne(paramMap);
		if(null!=result) return this.error("编号已存在");
		this.uupmDataCategoryService.addOne(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public RespModel edit(UupmDataCategoryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) {
			return this.error("参数无效");
		}
		this.uupmDataCategoryService.updateBy(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public RespModel del(String categoryCodes) {
		if(EasyStringCheckUtils.isEmpty(categoryCodes)) return this.error("参数无效");
		if(categoryCodes.indexOf(",")!=-1) {
			List<UupmDataCategoryModel> list = new ArrayList<UupmDataCategoryModel>();
			String[] arr = categoryCodes.split(",");
			for(String categoryCode : arr) {
				UupmDataCategoryModel model = new UupmDataCategoryModel();
				model.setCategoryCode(categoryCode);
				list.add(model);
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("modelList", list);
			this.uupmDataCategoryService.delBy(paramMap);
		} else {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("categoryCode", categoryCodes);
			this.uupmDataCategoryService.delBy(paramMap);
		}
		return this.successAjax();
	}
}
