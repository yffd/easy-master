package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.base.service.GenericService;
import com.yffd.easy.uupm.dao.UupmDictionaryDao;
import com.yffd.easy.uupm.api.model.UupmDictionaryModel;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月02日 17时50分41秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmDictionaryService extends GenericService<UupmDictionaryModel> {

	@Autowired
	private UupmDictionaryDao uupmDictionaryDao;
	
	@Override
	public GenericDao<UupmDictionaryModel> getBindDao() {
		return this.uupmDictionaryDao;
	}

}
