package com.yffd.easy.uupm.dao;

import com.yffd.easy.framework.common.dao.bak.BakMybatisCommonExtDaoImpl;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年4月28日 上午10:23:03 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmCommonDao<E> extends BakMybatisCommonExtDaoImpl<E> {

	@Override
	protected String getStatement(String sqlId) {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().getSimpleName()).append(".").append(sqlId);
		return sb.toString();
	}

}

