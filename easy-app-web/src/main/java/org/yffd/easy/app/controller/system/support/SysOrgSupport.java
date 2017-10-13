package org.yffd.easy.app.controller.system.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.yffd.easy.app.controller.system.vo.SysOrganizationTreeVO;
import org.yffd.easy.app.system.model.SysOrganization;
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
@Component("sysOrgSupport")
public class SysOrgSupport {
	
	public String getJsonTree(List<SysOrganization> list) {
		if(ValidUtils.isEmpty(list)) return "";
		List<SysOrganizationTreeVO> treeList = this.getTreeList(list);
		String json = JSON.toJSONString(treeList);
		return json;
	}
	
	public List<SysOrganizationTreeVO> getTreeList(List<SysOrganization> list) {
		if(ValidUtils.isEmpty(list)) return null;
		List<SysOrganizationTreeVO> nodes = toEntityList(list);
		return (List<SysOrganizationTreeVO>) TreeBuilder.buildByRecursive(nodes);
	}
	
	private List<SysOrganizationTreeVO> toEntityList(List<SysOrganization> list) {
		List<SysOrganizationTreeVO> ret = new ArrayList<SysOrganizationTreeVO>();
		for(SysOrganization sysOrg : list) {
			SysOrganizationTreeVO entity = new SysOrganizationTreeVO();
		    entity.setId(sysOrg.getOrgCode());
		    entity.setPid(sysOrg.getParentCode());
		    entity.setText(sysOrg.getOrgName());
		    entity.setIconCls(sysOrg.getIcon());
		    entity.setState("open");
		    ret.add(entity);
		}
		return ret;
	}
	
}

