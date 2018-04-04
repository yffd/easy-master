package com.yffd.easy.uupm.service;

import org.springframework.stereotype.Service;

import com.yffd.easy.uupm.api.model.UupmTreeDictionaryModel;
import com.yffd.easy.uupm.mapper.IUupmTreeDictionaryMapper;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月02日 10时30分38秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmTreeDictionaryService extends UupmTreeNodeService<UupmTreeDictionaryModel> {

	@Override
	protected String getStatement(String sqlId) {
		String namespace = IUupmTreeDictionaryMapper.class.getName();
		StringBuilder sb = new StringBuilder();
		sb.append(namespace).append(".").append(sqlId);
		return sb.toString();
	}
	
}
