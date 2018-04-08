package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.common.mapper.ICommonMapper;
import com.yffd.easy.framework.common.service.CommonServiceAbstract;
import com.yffd.easy.uupm.mapper.IUupmAccountMapper;
import com.yffd.easy.uupm.api.model.UupmAccountModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月06日 13时40分09秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmAccountService extends CommonServiceAbstract<UupmAccountModel> {

	@Autowired
	private IUupmAccountMapper uupmAccountMapper;
	
	@Override
	public ICommonMapper<UupmAccountModel> getMapper() {
		return this.uupmAccountMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmAccountMapper.class;
	}

}
