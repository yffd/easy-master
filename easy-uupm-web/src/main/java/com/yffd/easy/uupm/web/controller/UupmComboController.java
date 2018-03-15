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

import com.yffd.easy.common.core.tree.TreeBuilder;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.domain.RespModel;
import com.yffd.easy.framework.web.view.vo.ComboTreeVO;
import com.yffd.easy.uupm.api.model.UupmDictionaryModel;
import com.yffd.easy.uupm.service.UupmDictionaryService;
import com.yffd.easy.uupm.service.UupmResourceService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年3月7日 下午2:44:55 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/uupm/combox")
public class UupmComboController extends UupmBaseController {
	
	private TreeBuilder treeBuilder = new TreeBuilder();
	
	@Autowired
	private UupmDictionaryService uupmDictionaryService;
	@Autowired
	private UupmResourceService uupmResourceService;
	
	@RequestMapping(value="/findComboByDict", method=RequestMethod.POST)
	public RespModel findComboByDict(@RequestParam Map<String, Object> paramMap) {
		String comboxKeys = (String) paramMap.get("comboxKeys");
		if(null==comboxKeys || EasyStringCheckUtils.isEmpty(comboxKeys)) return this.error("参数无效");
		if(comboxKeys.indexOf(",")==-1) {
			List<UupmDictionaryModel> result = this.uupmDictionaryService.findChildrenListForDict(comboxKeys);
			if(null!=result && !result.isEmpty()) {
				List<ComboTreeVO> voList = this.dict2VO(result);
				List<ComboTreeVO> treeList = treeBuilder.buildByRecursive(voList, comboxKeys);
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put(comboxKeys, treeList);
				return this.successAjax(resultMap);
			}
		} else {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			String[] keys = comboxKeys.split(",");
			for(String key : keys) {
				List<UupmDictionaryModel> result = this.uupmDictionaryService.findChildrenListForDict(key);
				if(null!=result && !result.isEmpty()) {
					List<ComboTreeVO> voList = this.dict2VO(result);
					List<ComboTreeVO> treeList = treeBuilder.buildByRecursive(voList, key);
					resultMap.put(key, treeList);
				}
			}
			return this.successAjax(resultMap);
		}
		return null;
	}
	
	@RequestMapping(value="/findComboByResource", method=RequestMethod.POST)
	public RespModel findComboByResource(@RequestParam Map<String, Object> paramMap) {
		List<Map<String, Object>> result = this.uupmResourceService.findResWithApp();
		List<ComboTreeVO> voList = this.resource2VO(result);
		List<ComboTreeVO> treeList = treeBuilder.buildByRecursive(voList, "-1");
		return this.successAjax(treeList);
	}
	
	
	private List<ComboTreeVO> resource2VO(List<Map<String, Object>> list) {
		List<ComboTreeVO> retList = new ArrayList<ComboTreeVO>();
		for(Map<String, Object> map : list) {
			ComboTreeVO vo = new ComboTreeVO();
			String rsName = (String) map.get("rsName");
			String rsCode = (String) map.get("rsCode");
			String parentCode = (String) map.get("parentCode");
			vo.setId_(rsCode);
			vo.setPid_(parentCode);
			vo.setId(rsCode);
			vo.setText(rsName);
			vo.setState("open");
			retList.add(vo);
		}
		return retList;
	}
	
	private List<ComboTreeVO> dict2VO(List<UupmDictionaryModel> list) {
		List<ComboTreeVO> retList = new ArrayList<ComboTreeVO>();
		for(UupmDictionaryModel model : list) {
			ComboTreeVO vo = new ComboTreeVO();
			vo.setId_(model.getItemCode());
			vo.setPid_(model.getParentCode());
			vo.setId(model.getItemCode());
			vo.setText(model.getItemName());
			vo.setState("open");
			retList.add(vo);
		}
		return retList;
	}
}

