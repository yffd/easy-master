package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.core.common.mapper.ICommonMapper;
import com.yffd.easy.framework.core.common.service.CommonTreeServiceAbstract;
import com.yffd.easy.framework.core.exception.BizException;
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.api.model.UupmDictionaryModel;
import com.yffd.easy.uupm.mapper.IUupmDictionaryMapper;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月10日 17时43分09秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmDictionaryService extends CommonTreeServiceAbstract<UupmDictionaryModel> {

	@Autowired
	private IUupmDictionaryMapper uupmDictionaryMapper;
	
	@Override
	public ICommonMapper<UupmDictionaryModel> getMapper() {
		return this.uupmDictionaryMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmDictionaryMapper.class;
	}

	@Override
	public boolean exsist(UupmDictionaryModel model, LoginInfo loginInfo) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getTenantCode()) 
				|| EasyStringCheckUtils.isEmpty(model.getKeyCode())) throw BizException.BIZ_PARAMS_IS_EMPTY();
		UupmDictionaryModel paramModel = new UupmDictionaryModel();
		paramModel.setKeyCode(model.getKeyCode());
		return super.exsist(model, loginInfo);
	}

}
