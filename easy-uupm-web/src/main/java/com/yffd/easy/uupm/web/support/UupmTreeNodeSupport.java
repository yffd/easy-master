package com.yffd.easy.uupm.web.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.yffd.easy.common.core.tree.TreeBuilder;
import com.yffd.easy.uupm.api.model.UupmTreeNodeModel;
import com.yffd.easy.uupm.web.vo.UupmTreeNodeComboTreeVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月28日 下午5:03:15 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component
public class UupmTreeNodeSupport {
	private TreeBuilder treeBuilder = new TreeBuilder();
	
	/**
	 * 同步tree数据转换
	 * @Date	2018年2月28日 下午5:12:45 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public List<UupmTreeNodeComboTreeVO> toSyncTreeVO(List<? extends UupmTreeNodeModel> list, String rootId) {
		if(null==list || list.isEmpty()) return null;
		List<UupmTreeNodeComboTreeVO> voList = new ArrayList<UupmTreeNodeComboTreeVO>();
		for(UupmTreeNodeModel model : list) {
			UupmTreeNodeComboTreeVO vo = new UupmTreeNodeComboTreeVO();
			vo.setId_(model.getNodeCode());			// treeNode:设置父子关系
			vo.setPid_(model.getParentNodeCode());	// treeNode:设置父子关系
			vo.setText(model.getNodeName());		// treeNode:设置文本
			vo.setState("open");
			vo.setId(model.getId());
			vo.setNodeLabel(model.getNodeLabel());
			vo.setNodeCode(model.getNodeCode());
			vo.setNodeName(model.getNodeName());
			vo.setParentNodeCode(model.getParentNodeCode());
			vo.setParentNodeName(model.getParentNodeName());
			vo.setNodeLayer(model.getNodeLayer());
			vo.setNodeValue(model.getNodeValue());
			vo.setSeqNo(model.getSeqNo());
			vo.setRemark(model.getRemark());
			voList.add(vo);
		}
		List<UupmTreeNodeComboTreeVO> treeList = (List<UupmTreeNodeComboTreeVO>) treeBuilder.buildByRecursive(voList, rootId);
		return treeList;
	}
	
}

