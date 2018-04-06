package com.yffd.easy.uupm.web.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.yffd.easy.common.core.tree.TreeBuilder;
import com.yffd.easy.uupm.api.model.UupmTreeResourceModel;
import com.yffd.easy.uupm.web.vo.UupmResourceComboTreeVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月28日 下午5:03:15 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component
public class UupmResourceSupport {
	private TreeBuilder treeBuilder = new TreeBuilder();
	
	/**
	 * 同步tree数据转换
	 * @Date	2018年2月28日 下午5:12:45 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public List<UupmResourceComboTreeVO> toSyncTreeVO(List<UupmTreeResourceModel> list, String rootId) {
		if(null==list || list.isEmpty()) return null;
		List<UupmResourceComboTreeVO> voList = new ArrayList<UupmResourceComboTreeVO>();
		for(UupmTreeResourceModel model : list) {
			UupmResourceComboTreeVO vo = new UupmResourceComboTreeVO();
			vo.setId_(model.getNodeCode());			// treeNode:设置父子关系
			vo.setPid_(model.getParentNodeCode());	// treeNode:设置父子关系
			vo.setText(model.getNodeName());		// treeNode:设置文本
			vo.setState("open");
			vo.setId(model.getId());
			vo.setTenantCode(model.getTenantCode());
			vo.setNodeLabel(model.getNodeLabel());
			vo.setNodeCode(model.getNodeCode());
			vo.setNodeName(model.getNodeName());
			vo.setParentNodeCode(model.getParentNodeCode());
			vo.setParentNodeName(model.getParentNodeName());
			vo.setNodeLayer(model.getNodeLayer());
			vo.setNodeValue(model.getNodeValue());
			vo.setNodeValueType(model.getNodeValueType());
			vo.setNodeStatus(model.getNodeStatus());
			vo.setSeqNo(model.getSeqNo());
			vo.setRemark(model.getRemark());
			voList.add(vo);
		}
		List<UupmResourceComboTreeVO> treeList = (List<UupmResourceComboTreeVO>) treeBuilder.buildByRecursive(voList, rootId);
		return treeList;
	}
	
}

