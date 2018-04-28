package com.yffd.easy.framework.common.pojo.converter;

import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.exception.EasyCommonException;
import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年4月23日 下午4:08:29 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface ICommonConverterPojo {

	<VO, PO> PO vo2po(VO vo, Class<PO> poClazz) throws EasyCommonException;
	
	<VO, PO> VO po2vo (PO po, Class<VO> voClazz) throws EasyCommonException;
	
}

