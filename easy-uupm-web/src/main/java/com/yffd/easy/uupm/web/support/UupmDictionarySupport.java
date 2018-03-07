package com.yffd.easy.uupm.web.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.yffd.easy.common.core.tree.TreeBuilder;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.uupm.api.model.UupmDictionaryModel;
import com.yffd.easy.uupm.web.vo.UupmDictionaryTreeVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月28日 下午5:03:15 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component()
public class UupmDictionarySupport {
	private TreeBuilder treeBuilder = new TreeBuilder();
	
	/**
	 * 同步tree数据转换
	 * @Date	2018年2月28日 下午5:12:45 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public List<UupmDictionaryTreeVO> toSyncTreeVO(List<UupmDictionaryModel> list, String rootPid) {
		if(null==list || list.isEmpty()) return null;
		List<UupmDictionaryTreeVO> voList = new ArrayList<UupmDictionaryTreeVO>();
		for(UupmDictionaryModel model : list) {
			UupmDictionaryTreeVO vo = new UupmDictionaryTreeVO();
			vo.setId_(model.getItemCode());			// treeNode:设置父子关系
			vo.setPid_(model.getParentCode());		// treeNode:设置父子关系
			vo.setText(model.getItemName());		// treeNode:设置文本
			vo.setState("open");
			vo.setId(model.getId());
			vo.setItemName(model.getItemName());
			vo.setItemCode(model.getItemCode());
			vo.setParentCode(model.getParentCode());
			vo.setDataScope(model.getDataScope());
			vo.setDataLabel(model.getDataLabel());
			vo.setSeqNo(model.getSeqNo());
			vo.setRemark(model.getRemark());
			voList.add(vo);
		}
		if(null!=rootPid && !EasyStringCheckUtils.isEmpty(rootPid)) {
			List<UupmDictionaryTreeVO> treeList = (List<UupmDictionaryTreeVO>) treeBuilder.buildByRecursive(voList, rootPid);
			return treeList;
		}
		List<UupmDictionaryTreeVO> treeList = (List<UupmDictionaryTreeVO>) treeBuilder.buildByRecursive(voList);
//		if(null!=treeList && treeList.size()>0) {
//			UupmDictionaryTreeVO first = treeList.get(0);
//			first.setState("open");
//		}
		return treeList;
	}
	
}

