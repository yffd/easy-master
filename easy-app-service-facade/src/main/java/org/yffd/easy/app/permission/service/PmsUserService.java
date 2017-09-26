package org.yffd.easy.app.permission.service;

import org.yffd.easy.app.permission.entity.PmsUser;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月15日 上午11:12:14 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface PmsUserService {
	
	PageResult<PmsUser> listPage(PageParam pageParam, PmsUser pmsUser);
	
	PmsUser findByName(String userName);
	
	PmsUser findByCode(String userCode);
	
	void deleteByCode(String userCode);
	
	void update(PmsUser pmsUser);
	
	void updatePwd(String userCode, String userPwd);

	void add(PmsUser pmsUser);
}

