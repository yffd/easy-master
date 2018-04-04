package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.common.mapper.ICommonMapper;
import com.yffd.easy.framework.common.service.CommonServiceAbstract;
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.mapper.IUupmOrganizationMapper;
import com.yffd.easy.uupm.api.model.UupmOrganizationModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月04日 10时09分53秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmOrganizationService extends CommonServiceAbstract<UupmOrganizationModel> {

	@Autowired
	private IUupmOrganizationMapper uupmOrganizationMapper;
	
	@Override
	public ICommonMapper<UupmOrganizationModel> getMapper() {
		return this.uupmOrganizationMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmOrganizationMapper.class;
	}

	@Override
	public int addOne(UupmOrganizationModel paramPo, LoginInfo loginInfo) {
		if("root".equals(paramPo.getParentCode())) {
			paramPo.setDataPath("root." + paramPo.getOrgCode());
		} else {
			UupmOrganizationModel param = new UupmOrganizationModel();
			param.setOrgCode(paramPo.getParentCode());
			UupmOrganizationModel parent = this.findOne(param, loginInfo);
			paramPo.setDataPath(parent.getDataPath()  + "." + paramPo.getOrgCode());
		}
		return super.addOne(paramPo, loginInfo);
	}

}
