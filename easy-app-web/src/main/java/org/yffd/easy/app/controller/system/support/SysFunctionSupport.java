package org.yffd.easy.app.controller.system.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.yffd.easy.app.controller.system.vo.SysMenuTreeVO;
import org.yffd.easy.app.system.model.SysFunctionModel;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.core.view.tree.TreeBuilder;

import com.alibaba.fastjson.JSON;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月20日 下午3:22:36 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component("sysFunctionSupport")
public class SysFunctionSupport {
	
	public String toTreeJson(List<SysFunctionModel> list) {
		if(ValidUtils.isEmpty(list)) return "";
		List<SysMenuTreeVO> treeList = this.toTreeVO(list);
		String json = JSON.toJSONString(treeList);
		return json;
	}
	
	public List<SysMenuTreeVO> toTreeVO(List<SysFunctionModel> list) {
		if(ValidUtils.isEmpty(list)) return null;
		List<SysMenuTreeVO> nodes = toVOList(list);
		return (List<SysMenuTreeVO>) TreeBuilder.buildByRecursive(nodes);
	}
	
	private List<SysMenuTreeVO> toVOList(List<SysFunctionModel> list) {
		List<SysMenuTreeVO> ret = new ArrayList<SysMenuTreeVO>();
		for(SysFunctionModel model : list) {
			SysMenuTreeVO vo = new SysMenuTreeVO();
			vo.setId(model.getFuncCode());
			vo.setPid(model.getParentCode());
			vo.setText(model.getFuncName());
			vo.setUrl(model.getUrl());
			vo.setIconCls(model.getIconCls());
		    ret.add(vo);
		}
		return ret;
	}
	
}

