package com.yffd.easy.uupm.service;

import org.springframework.stereotype.Service;

import com.yffd.easy.uupm.api.model.UupmTreeResourceModel;
import com.yffd.easy.uupm.mapper.IUupmTreeResourceMapper;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月03日 14时01分25秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmTreeResourceService extends UupmTreeNodeService<UupmTreeResourceModel> {

	@Override
	protected String getStatement(String sqlId) {
		String namespace = IUupmTreeResourceMapper.class.getName();
		StringBuilder sb = new StringBuilder();
		sb.append(namespace).append(".").append(sqlId);
		return sb.toString();
	}
}
