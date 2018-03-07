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

import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.domain.RespModel;
import com.yffd.easy.uupm.api.model.UupmDictionaryModel;
import com.yffd.easy.uupm.service.UupmDictionaryService;
import com.yffd.easy.uupm.web.support.UupmDictionarySupport;
import com.yffd.easy.uupm.web.vo.UupmDictionaryTreeVO;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年03月02日 18时15分20秒 <br/>
 * @author		 ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/uupm/dictionary")
public class UupmDictionaryController extends UupmBaseController {
	@Autowired
	private UupmDictionaryService uupmDictionaryService;
	@Autowired
	private UupmDictionarySupport uupmDictionarySupport;
	
	@RequestMapping(value="/listCategory", method=RequestMethod.POST)
	public RespModel listCategory(@RequestParam Map<String, Object> paramMap) {
		List<UupmDictionaryModel> result = this.uupmDictionaryService.findChildrenListForCategory("CATEGORY");
		if(null!=result && !result.isEmpty()) {
			List<UupmDictionaryTreeVO> treeList = this.uupmDictionarySupport.toSyncTreeVO(result, null);
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/listDictTree", method=RequestMethod.POST)
	public RespModel listDictTree(@RequestParam Map<String, Object> paramMap) {
		String parentCode = (String) paramMap.get("parentCode");
		if(null==parentCode || EasyStringCheckUtils.isEmpty(parentCode)) return this.error("参数无效");
		List<UupmDictionaryModel> result = this.uupmDictionaryService.findChildrenListForDict(parentCode);
		if(null!=result && !result.isEmpty()) {
			List<UupmDictionaryTreeVO> treeList = this.uupmDictionarySupport.toSyncTreeVO(result, parentCode);
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/findOne", method=RequestMethod.POST)
	public RespModel findOne(UupmDictionaryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) return this.error("参数无效");
		UupmDictionaryModel result = this.uupmDictionaryService.findOne(model);
		return this.successAjax(result);
	}
	
	@RequestMapping(value="/addCategory", method=RequestMethod.POST)
	public RespModel addCategory(UupmDictionaryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getItemCode())) return this.error("参数无效");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("itemCode", model.getItemCode());
		UupmDictionaryModel result = this.uupmDictionaryService.findOne(paramMap);
		if(null!=result) return this.error("编号已存在");
		this.uupmDictionaryService.addCategory(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/addDict", method=RequestMethod.POST)
	public RespModel addDict(UupmDictionaryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getItemCode())) return this.error("参数无效");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("itemCode", model.getItemCode());
		UupmDictionaryModel result = this.uupmDictionaryService.findOne(paramMap);
		if(null!=result) return this.error("编号已存在");
		this.uupmDictionaryService.addDict(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/addChildDict", method=RequestMethod.POST)
	public RespModel addChildDict(UupmDictionaryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getItemCode())) return this.error("参数无效");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("itemCode", model.getItemCode());
		UupmDictionaryModel result = this.uupmDictionaryService.findOne(paramMap);
		if(null!=result) return this.error("编号已存在");
		this.uupmDictionaryService.addChildDict(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public RespModel edit(UupmDictionaryModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) {
			return this.error("参数无效");
		}
		this.uupmDictionaryService.updateBy(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public RespModel del(String itemCodes) {
		if(EasyStringCheckUtils.isEmpty(itemCodes)) return this.error("参数无效");
		if(itemCodes.indexOf(",")!=-1) {
			List<UupmDictionaryModel> list = new ArrayList<UupmDictionaryModel>();
			String[] arr = itemCodes.split(",");
			for(String code : arr) {
				UupmDictionaryModel model = new UupmDictionaryModel();
				model.setItemCode(code);
				list.add(model);
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("modelList", list);
			this.uupmDictionaryService.delBy(paramMap);
		} else {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("itemCode", itemCodes);
			this.uupmDictionaryService.delBy(paramMap);
		}
		return this.successAjax();
	}
}
