package com.yffd.easy.uupm.web.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.yffd.easy.common.core.tree.TreeBuilder;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.uupm.api.model.UupmOrganizationModel;
import com.yffd.easy.uupm.web.vo.UupmOrganizationComboTreeVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月28日 下午5:03:15 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component
public class UupmOrganizationSupport {
	private TreeBuilder treeBuilder = new TreeBuilder();
	
	/**
	 * 同步tree数据转换
	 * @Date	2018年2月28日 下午5:12:45 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public List<UupmOrganizationComboTreeVO> toSyncTreeVO(List<UupmOrganizationModel> list, String rootPid) {
		if(null==list || list.isEmpty()) return null;
		List<UupmOrganizationComboTreeVO> voList = new ArrayList<UupmOrganizationComboTreeVO>();
		for(UupmOrganizationModel model : list) {
			UupmOrganizationComboTreeVO vo = new UupmOrganizationComboTreeVO();
			vo.setId_(model.getOrgCode());			// treeNode:设置父子关系
			vo.setPid_(model.getParentCode());		// treeNode:设置父子关系
			vo.setText(model.getOrgName());		// treeNode:设置文本
			vo.setState("open");
			vo.setId(model.getId());
			vo.setOrgName(model.getOrgName());
			vo.setOrgCode(model.getOrgCode());
			vo.setParentCode(model.getParentCode());
			vo.setSeqNo(model.getSeqNo());
			vo.setRemark(model.getRemark());
			voList.add(vo);
		}
		if(null!=rootPid && !EasyStringCheckUtils.isEmpty(rootPid)) {
			List<UupmOrganizationComboTreeVO> treeList = (List<UupmOrganizationComboTreeVO>) treeBuilder.buildByRecursive(voList, rootPid);
			return treeList;
		}
		List<UupmOrganizationComboTreeVO> treeList = (List<UupmOrganizationComboTreeVO>) treeBuilder.buildByRecursive(voList);
		return treeList;
	}
	
}

