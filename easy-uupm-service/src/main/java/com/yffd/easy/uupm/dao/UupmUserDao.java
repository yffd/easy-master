package com.yffd.easy.uupm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.uupm.api.model.UupmUserModel;
import com.yffd.easy.uupm.base.UupmBaseDao;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月23日 11时01分34秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Repository
public class UupmUserDao extends UupmBaseDao<UupmUserModel> {

	public PageResult<Map<String, Object>> selectUserListPage(Map<String, Object> paramMap, PageParam pageParam) {
		return this.selectPage(paramMap, pageParam, 
				this.getStatement("selectUserListBy"), this.getStatement("selectUserCountBy"));
	}
	
	public List<Map<String, Object>> selectUserListBy(Map<String, Object> paramMap) {
		return this.getSqlSession().selectList(this.getStatement("selectUserListBy"), paramMap);
	}
	
	public Long selectUserCountBy(Map<String, Object> paramMap) {
		return this.getSqlSession().selectOne(this.getStatement("selectUserCountBy"), paramMap);
	}
}
