package org.yffd.easy.app.controller.system.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.yffd.easy.app.system.entity.SysFunction;
import org.yffd.easy.common.core.tree.TreeBuilder;
import org.yffd.easy.common.core.util.ValidUtils;

import com.alibaba.fastjson.JSON;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月20日 下午3:22:36 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component("sysFuncSupport")
public class SysFuncSupport {
	
	public String getJsonTree(List<SysFunction> list) {
		if(ValidUtils.isEmpty(list)) return "";
		List<MenuTreeEntity> treeList = this.menuTree(list);
		String json = JSON.toJSONString(treeList);
		return json;
	}
	
	public List<MenuTreeEntity> menuTree(List<SysFunction> list) {
		if(ValidUtils.isEmpty(list)) return null;
		List<MenuTreeEntity> nodes = toEntityList(list);
		return (List<MenuTreeEntity>) TreeBuilder.buildByRecursive(nodes);
	}
	
	private List<MenuTreeEntity> toEntityList(List<SysFunction> list) {
		List<MenuTreeEntity> ret = new ArrayList<MenuTreeEntity>();
		for(SysFunction sysFunc : list) {
			MenuTreeEntity entity = new MenuTreeEntity();
		    entity.setId(sysFunc.getCode());
		    entity.setParentId(sysFunc.getParentCode());
		    entity.setName(sysFunc.getName());
		    entity.setUrl(sysFunc.getUrl());
		    entity.setIcon(sysFunc.getIcon());
		    ret.add(entity);
		}
		return ret;
	}
	
}

