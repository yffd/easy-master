package com.yffd.easy.web.admin.pms.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.core.view.tree.TreeBuilder;

import com.yffd.easy.admin.pms.model.PmsResource;
import com.yffd.easy.web.admin.pms.vo.PmsResourceTreeGridVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月20日 下午3:22:36 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component("pmsResourceSupport")
public class PmsResourceSupport {
	
	/**
	 * 同步treegrid数据转换
	 * @Date	2017年10月24日 下午2:18:08 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public List<PmsResourceTreeGridVO> toSyncTreeGridVO(List<PmsResource> list) {
		if(ValidUtils.isEmpty(list)) return null;
		List<PmsResourceTreeGridVO> voList = new ArrayList<PmsResourceTreeGridVO>();
		for(PmsResource model : list) {
			PmsResourceTreeGridVO vo = new PmsResourceTreeGridVO();
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
		List<PmsResourceTreeGridVO> treeList = (List<PmsResourceTreeGridVO>) TreeBuilder.buildByRecursive(voList);
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
	public List<PmsResourceTreeGridVO> toAsyncTreeGridVO(List<PmsResource> list) {
		if(ValidUtils.isEmpty(list)) return null;
		List<PmsResourceTreeGridVO> ret = new ArrayList<PmsResourceTreeGridVO>();
		for(PmsResource model : list) {
			PmsResourceTreeGridVO vo = new PmsResourceTreeGridVO();
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

