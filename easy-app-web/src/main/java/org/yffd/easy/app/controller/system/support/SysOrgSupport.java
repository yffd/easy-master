package org.yffd.easy.app.controller.system.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.yffd.easy.app.controller.system.vo.SysOrgVO;
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
@Component("sysOrgSupport")
public class SysOrgSupport {
	
	public List<ComboTreeVO> toListTreeVO(List<SysOrganization> list) {
		if(ValidUtils.isEmpty(list)) return null;
	    
		List<ComboTreeVO> voList = new ArrayList<ComboTreeVO>();
		for(SysOrganization org : list) {
			ComboTreeVO vo = new ComboTreeVO();
			vo.setId(org.getCode());
			vo.setPid(org.getParentCode());
			vo.setText(org.getName());
			vo.setChecked(false);
			vo.setState("closed");
			voList.add(vo);
		}
		List<ComboTreeVO> treeList = (List<ComboTreeVO>) TreeBuilder.buildByRecursive(voList);
		if(ValidUtils.isEmpty(treeList)) return null;
		return treeList;
	}
	
	public List<SysOrgVO> toListVO(List<SysOrganization> list) {
		if(ValidUtils.isEmpty(list)) return null;
		List<SysOrgVO> ret = new ArrayList<SysOrgVO>();
		for(SysOrganization org : list) {
			SysOrgVO vo = new SysOrgVO();
			vo.setState("closed");
			vo.setId(org.getId());
			vo.setName(org.getName());
			vo.setCode(org.getCode());
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

