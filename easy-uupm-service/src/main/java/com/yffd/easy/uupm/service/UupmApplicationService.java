package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.common.mapper.ICommonMapper;
import com.yffd.easy.framework.common.service.CommonServiceAbstract;
import com.yffd.easy.uupm.mapper.IUupmApplicationMapper;
import com.yffd.easy.uupm.api.model.UupmApplicationModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年03月30日 11时26分47秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmApplicationService extends CommonServiceAbstract<UupmApplicationModel> {

	@Autowired
	private IUupmApplicationMapper uupmApplicationMapper;
	
	@Override
	public ICommonMapper<UupmApplicationModel> getMapper() {
		return this.uupmApplicationMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmApplicationMapper.class;
	}

}
