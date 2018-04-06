package com.yffd.easy.uupm.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yffd.easy.common.core.tree.TreeBuilder;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.domain.RespModel;
import com.yffd.easy.framework.web.view.vo.ComboTreeVO;
import com.yffd.easy.uupm.api.model.UupmTreeDictionaryModel;
import com.yffd.easy.uupm.service.UupmTreeDictionaryService;
import com.yffd.easy.uupm.web.common.UupmCommonController;

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
public class UupmComboController extends UupmCommonController {
	
	private TreeBuilder treeBuilder = new TreeBuilder();
	
	@Autowired
	private UupmTreeDictionaryService uupmTreeDictionaryService;
	
	@RequestMapping(value="/findComboByDict", method=RequestMethod.POST)
	public RespModel findTreeByDict(@RequestParam Map<String, Object> paramMap) {
		if(null==paramMap) return this.error("参数无效");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Set<String> keys = paramMap.keySet();
		for(String key : keys) {
			String value = (String) paramMap.get(key);
			if(EasyStringCheckUtils.isEmpty(value)) continue;
			String[] valueArr = value.split(",");
			Map<String, Object> tmp = new HashMap<String, Object>();
			for(String nodeCode : valueArr) {
				List<UupmTreeDictionaryModel> result = this.uupmTreeDictionaryService.findChildrenNodeList(key, nodeCode, null);
				if(null!=result && !result.isEmpty()) {
					List<ComboTreeVO> voList = this.dict2VO(result);
					List<ComboTreeVO> treeList = treeBuilder.buildByRecursive(voList, key);
					tmp.put(nodeCode, treeList);
				}
			}
			resultMap.put(key, tmp);
		}
		return this.successAjax(resultMap);
	}
	
	private List<ComboTreeVO> dict2VO(List<UupmTreeDictionaryModel> list) {
		List<ComboTreeVO> retList = new ArrayList<ComboTreeVO>();
		for(UupmTreeDictionaryModel model : list) {
			ComboTreeVO vo = new ComboTreeVO();
			vo.setId_(model.getNodeCode());
			vo.setPid_(model.getParentNodeCode());
			vo.setId(model.getNodeValue());
			vo.setText(model.getNodeName());
			vo.setState("open");
			retList.add(vo);
		}
		return retList;
	}
}

