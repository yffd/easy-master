package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.core.common.mapper.ICommonMapper;
import com.yffd.easy.framework.core.common.service.CommonServiceAbstract;
import com.yffd.easy.uupm.mapper.IUupmRoleMapper;
import com.yffd.easy.uupm.pojo.entity.UupmRoleEntity;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月04日 16时46分57秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmRoleService extends CommonServiceAbstract<UupmRoleEntity> {

	@Autowired
	private IUupmRoleMapper uupmRoleMapper;
	
	@Override
	public ICommonMapper<UupmRoleEntity> getMapper() {
		return this.uupmRoleMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmRoleMapper.class;
	}

}
