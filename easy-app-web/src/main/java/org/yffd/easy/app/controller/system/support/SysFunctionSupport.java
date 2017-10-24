package org.yffd.easy.app.controller.system.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.yffd.easy.app.controller.system.vo.SysFunctionTreeGridVO;
import org.yffd.easy.app.system.model.SysFunction;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.core.view.tree.TreeBuilder;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月20日 下午3:22:36 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component("sysFunctionSupport")
public class SysFunctionSupport {
	
	/**
	 * 同步treegrid数据转换
	 * @Date	2017年10月24日 下午2:18:08 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public List<SysFunctionTreeGridVO> toSyncTreeGridVO(List<SysFunction> list) {
		if(ValidUtils.isEmpty(list)) return null;
		List<SysFunctionTreeGridVO> voList = new ArrayList<SysFunctionTreeGridVO>();
		for(SysFunction model : list) {
			SysFunctionTreeGridVO vo = new SysFunctionTreeGridVO();
			vo.setState("open");					// treeNode:
			vo.setId(model.getFuncCode());			// treeNode:设置父子关系
			vo.setPid(model.getParentCode());		// treeNode:设置父子关系
			vo.setText(model.getFuncName());		// treeNode:
			vo.setFuncName(model.getFuncName());
			vo.setFuncCode(model.getFuncCode());
			vo.setParentCode(model.getParentCode());
			vo.setUrl(model.getUrl());
			vo.setType(model.getType());
			vo.setActive(model.getActive());
			vo.setSort(model.getSort());
			vo.setIconCls(model.getIconCls());
			vo.setRemark(model.getRemark());
			voList.add(vo);
		}
		List<SysFunctionTreeGridVO> treeList = (List<SysFunctionTreeGridVO>) TreeBuilder.buildByRecursive(voList);
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
	public List<SysFunctionTreeGridVO> toAsyncTreeGridVO(List<SysFunction> list) {
		if(ValidUtils.isEmpty(list)) return null;
		List<SysFunctionTreeGridVO> ret = new ArrayList<SysFunctionTreeGridVO>();
		for(SysFunction model : list) {
			SysFunctionTreeGridVO vo = new SysFunctionTreeGridVO();
			vo.setFuncName(model.getFuncName());
			vo.setFuncCode(model.getFuncCode());
			vo.setParentCode(model.getParentCode());
			vo.setUrl(model.getUrl());
			vo.setType(model.getType());
			vo.setActive(model.getActive());
			vo.setSort(model.getSort());
			vo.setIconCls(model.getIconCls());
			vo.setRemark(model.getRemark());
			if("M".equals(model.getType())) {
				vo.setState("closed");
			} else {
				vo.setState("open");
			}
			ret.add(vo);
		}
		return ret;
	}
	
}

