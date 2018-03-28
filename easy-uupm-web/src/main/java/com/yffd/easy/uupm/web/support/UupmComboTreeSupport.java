package com.yffd.easy.uupm.web.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.yffd.easy.common.core.tree.TreeBuilder;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.uupm.api.model.UupmResourceModel;
import com.yffd.easy.uupm.web.vo.UupmResourceComboTreeVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年3月9日 上午11:48:17 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component
public class UupmComboTreeSupport {
	private TreeBuilder treeBuilder = new TreeBuilder();
	
	/**
	 * 同步tree数据转换--应用资源
	 * @Date	2018年3月9日 上午11:53:27 <br/>
	 * @author  zhangST
	 * @param list
	 * @param rootPid
	 * @return
	 */
	public List<UupmResourceComboTreeVO> toSyncTreeVO(List<UupmResourceModel> list, String rootPid) {
		if(null==list || list.isEmpty()) return null;
		List<UupmResourceComboTreeVO> voList = new ArrayList<UupmResourceComboTreeVO>();
		for(UupmResourceModel model : list) {
			UupmResourceComboTreeVO vo = new UupmResourceComboTreeVO();
			vo.setId_(model.getRsCode());			// treeNode:设置父子关系
			vo.setPid_(model.getParentCode());		// treeNode:设置父子关系
			vo.setText(model.getRsName());		// treeNode:设置文本
			vo.setState("open");
			vo.setId(model.getId());
			vo.setAppCode(model.getAppCode());
			vo.setRsCode(model.getRsCode());
			vo.setRsName(model.getRsName());
			vo.setParentCode(model.getParentCode());
			vo.setRsPath(model.getRsPath());
			vo.setRsType(model.getRsType());
			vo.setRsStatus(model.getRsStatus());
			vo.setRemark(model.getRemark());
			voList.add(vo);
		}
		if(null!=rootPid && !EasyStringCheckUtils.isEmpty(rootPid)) {
			List<UupmResourceComboTreeVO> treeList = (List<UupmResourceComboTreeVO>) treeBuilder.buildByRecursive(voList, rootPid);
			return treeList;
		}
		List<UupmResourceComboTreeVO> treeList = (List<UupmResourceComboTreeVO>) treeBuilder.buildByRecursive(voList);
		return treeList;
	}
	
}

