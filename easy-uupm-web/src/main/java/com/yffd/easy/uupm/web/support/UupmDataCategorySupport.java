package com.yffd.easy.uupm.web.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.yffd.easy.common.core.tree.TreeBuilder;
import com.yffd.easy.uupm.api.model.UupmDataCategoryModel;
import com.yffd.easy.uupm.web.vo.UupmDataCategoryTreeVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月28日 下午5:03:15 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component()
public class UupmDataCategorySupport {
	private TreeBuilder treeBuilder = new TreeBuilder();
	
	/**
	 * 同步tree数据转换
	 * @Date	2018年2月28日 下午5:12:45 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public List<UupmDataCategoryTreeVO> toSyncTreeVO(List<UupmDataCategoryModel> list) {
		if(null==list || list.isEmpty()) return null;
		List<UupmDataCategoryTreeVO> voList = new ArrayList<UupmDataCategoryTreeVO>();
		for(UupmDataCategoryModel model : list) {
			UupmDataCategoryTreeVO vo = new UupmDataCategoryTreeVO();
			vo.setId(model.getCategoryCode());			// treeNode:设置父子关系
			vo.setPid(model.getParentCode());			// treeNode:设置父子关系
			vo.setText(model.getCategoryName());		// treeNode:设置文本
			vo.setState("closed");
			voList.add(vo);
		}
		List<UupmDataCategoryTreeVO> treeList = (List<UupmDataCategoryTreeVO>) treeBuilder.buildByRecursive(voList);
		if(null!=treeList && treeList.size()>0) {
			UupmDataCategoryTreeVO first = treeList.get(0);
			first.setState("open");
		}
		return treeList;
	}
	
	/**
	 * 异步tree数据转换
	 * @Date	2018年2月28日 下午5:15:12 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public List<UupmDataCategoryTreeVO> toAsyncTreeVO(List<UupmDataCategoryModel> list) {
		if(null==list || list.isEmpty()) return null;
		List<UupmDataCategoryTreeVO> voList = new ArrayList<UupmDataCategoryTreeVO>();
		for(UupmDataCategoryModel model : list) {
			UupmDataCategoryTreeVO vo = new UupmDataCategoryTreeVO();
			vo.setId(model.getCategoryCode());			// treeNode:设置父子关系
			vo.setPid(model.getParentCode());			// treeNode:设置父子关系
			vo.setText(model.getCategoryName());		// treeNode:设置文本
			vo.setState("closed");						
			voList.add(vo);
		}
		return voList;
	}
}

