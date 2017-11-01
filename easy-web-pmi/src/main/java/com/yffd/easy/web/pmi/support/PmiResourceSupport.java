package com.yffd.easy.web.pmi.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.core.view.tree.TreeBuilder;

import com.yffd.easy.pmi.model.PmiResource;
import com.yffd.easy.web.pmi.vo.PmiResourceTreeGridVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月20日 下午3:22:36 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component("pmiResourceSupport")
public class PmiResourceSupport {
	
	/**
	 * 同步treegrid数据转换
	 * @Date	2017年10月24日 下午2:18:08 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public List<PmiResourceTreeGridVO> toSyncTreeGridVO(List<PmiResource> list) {
		if(ValidUtils.isEmpty(list)) return null;
		List<PmiResourceTreeGridVO> voList = new ArrayList<PmiResourceTreeGridVO>();
		for(PmiResource model : list) {
			PmiResourceTreeGridVO vo = new PmiResourceTreeGridVO();
			vo.setState("open");					// datagrid:
			vo.setIconCls("icon-sys");				// datagrid:
			vo.setId(model.getRsCode());			// treeNode:设置父子关系
			vo.setPid(model.getParentCode());		// treeNode:设置父子关系
			vo.setText(model.getRsName());			// treeNode:设置文本
			
			vo.setRsName(model.getRsName());
			vo.setRsCode(model.getRsCode());
			vo.setParentCode(model.getParentCode());
			vo.setInUrl(model.getInUrl());
			vo.setRsType(model.getRsType());
			vo.setRsNum(model.getRsNum());
			vo.setRsState(model.getRsState());
			vo.setRemark(model.getRemark());
			voList.add(vo);
		}
		List<PmiResourceTreeGridVO> treeList = (List<PmiResourceTreeGridVO>) TreeBuilder.buildByRecursive(voList);
		if(ValidUtils.isEmpty(treeList)) return null;
		return treeList;
	}
	
	/**
	 * 异步treegrid数据转换
	 * @Date	2017年10月24日 下午2:18:08 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public List<PmiResourceTreeGridVO> toAsyncTreeGridVO(List<PmiResource> list) {
		if(ValidUtils.isEmpty(list)) return null;
		List<PmiResourceTreeGridVO> ret = new ArrayList<PmiResourceTreeGridVO>();
		for(PmiResource model : list) {
			PmiResourceTreeGridVO vo = new PmiResourceTreeGridVO();
			vo.setState("open");					// datagrid:
			vo.setIconCls("icon-sys");				// datagrid:
			vo.setId(model.getRsCode());			// treeNode:设置父子关系
			vo.setPid(model.getParentCode());		// treeNode:设置父子关系
			vo.setText(model.getRsName());			// treeNode:
			
			vo.setRsName(model.getRsName());
			vo.setRsCode(model.getRsCode());
			vo.setParentCode(model.getParentCode());
			vo.setInUrl(model.getInUrl());
			vo.setRsType(model.getRsType());
			vo.setRsNum(model.getRsNum());
			vo.setRsState(model.getRsState());
			vo.setRemark(model.getRemark());
			if("O".equals(model.getRsType())) {
				vo.setState("open");
			} else {
				vo.setState("closed");
			}
			ret.add(vo);
		}
		return ret;
	}
	
}

