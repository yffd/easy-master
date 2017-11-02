package com.yffd.easy.web.auth.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.core.view.tree.TreeBuilder;

import com.yffd.easy.auth.model.AuthOrganization;
import com.yffd.easy.web.auth.vo.AuthOrganizationTreeGridVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月20日 下午3:22:36 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component("authOrganizationSupport")
public class AuthOrganizationSupport {
	
	/**
	 * 同步treegrid数据转换
	 * @Date	2017年10月24日 下午2:16:20 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public List<AuthOrganizationTreeGridVO> toSyncTreeGridVO(List<AuthOrganization> list) {
		if(ValidUtils.isEmpty(list)) return null;
	    
		List<AuthOrganizationTreeGridVO> voList = new ArrayList<AuthOrganizationTreeGridVO>();
		for(AuthOrganization org : list) {
			AuthOrganizationTreeGridVO vo = new AuthOrganizationTreeGridVO();
			vo.setState("open");				// datagrid:
			vo.setIconCls("icon-sys");				// datagrid:
			vo.setId(org.getOrgCode());			// treeNode:设置父子关系
			vo.setPid(org.getParentCode());		// treeNode:设置父子关系
			vo.setText(org.getOrgName());		// treeNode:设置文本
			
			vo.setOrgName(org.getOrgName());
			vo.setOrgCode(org.getOrgCode());
			vo.setFirstManagerCode(org.getFirstManagerCode());
			vo.setSecondManagerCode(org.getSecondManagerCode());
			vo.setParentCode(org.getParentCode());
			vo.setRemark(org.getRemark());
			voList.add(vo);
		}
		List<AuthOrganizationTreeGridVO> treeList = (List<AuthOrganizationTreeGridVO>) TreeBuilder.buildByRecursive(voList);
		if(ValidUtils.isEmpty(treeList)) return null;
		return treeList;
	}
	
	/**
	 * 异步treegrid数据转换
	 * @Date	2017年10月24日 下午2:16:00 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public List<AuthOrganizationTreeGridVO> toAsyncTreeGridVO(List<AuthOrganization> list) {
		if(ValidUtils.isEmpty(list)) return null;
		List<AuthOrganizationTreeGridVO> ret = new ArrayList<AuthOrganizationTreeGridVO>();
		for(AuthOrganization org : list) {
			AuthOrganizationTreeGridVO vo = new AuthOrganizationTreeGridVO();
			vo.setState("closed");
			vo.setIconCls("icon-sys");
			vo.setId(org.getId());
			vo.setOrgName(org.getOrgName());
			vo.setOrgCode(org.getOrgCode());
			vo.setFirstManagerCode(org.getFirstManagerCode());
			vo.setSecondManagerCode(org.getSecondManagerCode());
			vo.setParentCode(org.getParentCode());
			vo.setRemark(org.getRemark());
			ret.add(vo);
		}
		return ret;
	}
	
}

