package org.yffd.easy.app.controller.system.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.yffd.easy.app.controller.system.vo.SysMenuTreeVO;
import org.yffd.easy.app.system.model.SysMenu;
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
@Component("sysFuncSupport")
public class SysMenuSupport {
	
	public String getJsonTree(List<SysMenu> list) {
		if(ValidUtils.isEmpty(list)) return "";
		List<SysMenuTreeVO> treeList = this.getTreeList(list);
		String json = JSON.toJSONString(treeList);
		return json;
	}
	
	public List<SysMenuTreeVO> getTreeList(List<SysMenu> list) {
		if(ValidUtils.isEmpty(list)) return null;
		List<SysMenuTreeVO> nodes = toEntityList(list);
		return (List<SysMenuTreeVO>) TreeBuilder.buildByRecursive(nodes);
	}
	
	private List<SysMenuTreeVO> toEntityList(List<SysMenu> list) {
		List<SysMenuTreeVO> ret = new ArrayList<SysMenuTreeVO>();
		for(SysMenu sysFunc : list) {
			SysMenuTreeVO entity = new SysMenuTreeVO();
		    entity.setId(sysFunc.getCode());
		    entity.setPid(sysFunc.getParentCode());
		    entity.setText(sysFunc.getName());
		    entity.setUrl(sysFunc.getUrl());
		    entity.setIconCls(sysFunc.getIcon());
		    entity.setState("open");
		    ret.add(entity);
		}
		return ret;
	}
	
}

