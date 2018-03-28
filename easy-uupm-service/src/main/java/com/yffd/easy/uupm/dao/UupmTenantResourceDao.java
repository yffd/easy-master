package com.yffd.easy.uupm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yffd.easy.uupm.api.model.UupmTenantResourceModel;
import com.yffd.easy.uupm.base.UupmBaseDao;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月22日 10时29分51秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Repository
public class UupmTenantResourceDao extends UupmBaseDao<UupmTenantResourceModel> {
	
	public List<Map<String, Object>> selectTenantResourceListBy(Map<String, Object> paramMap) {
		return this.getSqlSession().selectList(this.getStatement("selectTenantResourceBy"), paramMap);
	}
}
