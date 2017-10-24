package org.yffd.easy.app.controller.system.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.yffd.easy.app.controller.system.vo.SysOrganizationTreeGridVO;
import org.yffd.easy.app.system.model.SysOrganization;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.core.view.tree.TreeBuilder;
import org.yffd.easy.common.core.view.vo.ComboTreeVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月20日 下午3:22:36 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component("sysOrganizationSupport")
public class SysOrganizationSupport {
	
	/**
	 * 同步treegrid数据转换
	 * @Date	2017年10月24日 下午2:16:20 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public List<SysOrganizationTreeGridVO> toSyncTreeGridVO(List<SysOrganization> list) {
		if(ValidUtils.isEmpty(list)) return null;
	    
		List<SysOrganizationTreeGridVO> voList = new ArrayList<SysOrganizationTreeGridVO>();
		for(SysOrganization org : list) {
			SysOrganizationTreeGridVO vo = new SysOrganizationTreeGridVO();
			vo.setState("open");
			vo.setId(org.getOrgCode());			// 设置父子关系
			vo.setPid(org.getParentCode());		// 设置父子关系
			vo.setText(org.getOrgName());
			vo.setOrgName(org.getOrgName());
			vo.setOrgCode(org.getOrgCode());
			vo.setFirstManagerCode(org.getFirstManagerCode());
			vo.setSecondManagerCode(org.getSecondManagerCode());
			vo.setParentCode(org.getParentCode());
			vo.setFax(org.getFax());
			vo.setTel(org.getTel());
			vo.setRemark(org.getRemark());
			voList.add(vo);
		}
		List<SysOrganizationTreeGridVO> treeList = (List<SysOrganizationTreeGridVO>) TreeBuilder.buildByRecursive(voList);
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
	public List<SysOrganizationTreeGridVO> toAsyncTreeGridVO(List<SysOrganization> list) {
		if(ValidUtils.isEmpty(list)) return null;
		List<SysOrganizationTreeGridVO> ret = new ArrayList<SysOrganizationTreeGridVO>();
		for(SysOrganization org : list) {
			SysOrganizationTreeGridVO vo = new SysOrganizationTreeGridVO();
			vo.setState("closed");
			vo.setId(org.getId());
			vo.setOrgName(org.getOrgName());
			vo.setOrgCode(org.getOrgCode());
			vo.setFirstManagerCode(org.getFirstManagerCode());
			vo.setSecondManagerCode(org.getSecondManagerCode());
			vo.setParentCode(org.getParentCode());
			vo.setFax(org.getFax());
			vo.setTel(org.getTel());
			vo.setRemark(org.getRemark());
			ret.add(vo);
		}
		return ret;
	}
	
}

