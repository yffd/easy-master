package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.common.mapper.ICommonMapper;
import com.yffd.easy.framework.common.service.CommonServiceAbstract;
import com.yffd.easy.uupm.mapper.IUupmUserMapper;
import com.yffd.easy.uupm.api.model.UupmUserModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月04日 16时09分08秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmUserService extends CommonServiceAbstract<UupmUserModel> {

	@Autowired
	private IUupmUserMapper uupmUserMapper;
	
	@Override
	public ICommonMapper<UupmUserModel> getMapper() {
		return this.uupmUserMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmUserMapper.class;
	}

}
