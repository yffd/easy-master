package com.yffd.easy.framework.common.pojo.converter;

import com.yffd.easy.common.core.exception.EasyCommonException;
import com.yffd.easy.common.core.util.EasyJavaBeanUtils;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年4月23日 下午4:49:09 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class CommonConverterPojo implements ICommonConverterPojo {

	@Override
	public <VO, PO> PO vo2po(VO vo, Class<PO> poClazz) throws EasyCommonException {
		if(null==vo || null==poClazz) return null;
		try {
			return EasyJavaBeanUtils.copyProperties(vo, poClazz);
		} catch (Exception e) {
			throw new EasyCommonException("vo convert to po error!", e);
		}
	}

	@Override
	public <VO, PO> VO po2vo(PO po, Class<VO> voClazz) throws EasyCommonException {
		if(null==po || null==voClazz) return null;
		try {
			return EasyJavaBeanUtils.copyProperties(po, voClazz);
		} catch (Exception e) {
			throw new EasyCommonException("po convert to vo error!", e);
		}
	}


}

