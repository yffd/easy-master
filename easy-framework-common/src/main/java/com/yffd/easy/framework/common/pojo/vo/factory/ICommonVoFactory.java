package com.yffd.easy.framework.common.pojo.vo.factory;

import java.util.List;

import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年4月26日 下午6:20:15 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface ICommonVoFactory<VO> {

	VO createVo(Object obj);
	
	List<VO> createVoList(List<?> objList);
	
	PageResult<VO> createVoPage(PageResult<?> objPage);
	
}

