package com.yffd.easy.uupm.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.core.common.mapper.ICommonMapper;
import com.yffd.easy.framework.core.common.service.CommonServiceAbstract;
import com.yffd.easy.uupm.mapper.IUupmOrganizationMapper;
import com.yffd.easy.uupm.pojo.entity.UupmOrganizationEntity;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月04日 10时09分53秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmOrganizationService extends CommonServiceAbstract<UupmOrganizationEntity> {

	@Autowired
	private IUupmOrganizationMapper uupmOrganizationMapper;
	
	@Override
	public ICommonMapper<UupmOrganizationEntity> getMapper() {
		return this.uupmOrganizationMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmOrganizationMapper.class;
	}

	@Override
	public int addOne(UupmOrganizationEntity paramPo) {
		if("root".equals(paramPo.getParentCode())) {
			paramPo.setDataPath("root." + paramPo.getOrgCode());
		} else {
			UupmOrganizationEntity param = new UupmOrganizationEntity();
			param.setOrgCode(paramPo.getParentCode());
			UupmOrganizationEntity parent = this.findOne(param);
			paramPo.setDataPath(parent.getDataPath()  + "." + paramPo.getOrgCode());
		}
		return super.addOne(paramPo);
	}
	
	public String findParentNamePath(UupmOrganizationEntity paramPo) {
		UupmOrganizationEntity result = this.findOne(paramPo, null);
		if(null==result) return "";
		String dataPath = result.getDataPath();
		String[] orgCodes = dataPath.split("\\");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgCodeList", Arrays.asList(orgCodes));
		List<UupmOrganizationEntity> resultList = this.findList(null, paramMap);
		if(null==resultList || resultList.size()==0) return "";
		StringBuilder sb = new StringBuilder();
		for(String orgCode : orgCodes) {
			for(UupmOrganizationEntity model : resultList) {
				if(orgCode.equals(model.getOrgCode())) {
					sb.append(model.getOrgName()).append("\\");
				}
			}
		}
		return sb.length()>0 ? sb.substring(0, sb.length()-1) : sb.toString();
	}

}
