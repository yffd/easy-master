package org.yffd.easy.pmi.service.impl;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;
import org.yffd.easy.pmi.SpringBaseTestCase;

import com.yffd.easy.pmi.model.PmiUser;
import com.yffd.easy.pmi.service.PmiUserService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月9日 上午9:57:12 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmiUserServiceImplTest extends SpringBaseTestCase {

	@Autowired
	private PmiUserService pmiUserService;
	
	@Test
	public void findListTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		String name = null;
		String code = null;
		String orgCode = null;
		PmiUser user = null;
		PageResult<PmiUser> page = this.pmiUserService.findList(user, pageParam);
		System.out.println(page.getRecordList().size());
	}
	
	@Test
	public void addTest() {
		for(int i=0;i<35;i++) {
			PmiUser model = new PmiUser();
			model.setUserCode("code_"+i);
			model.setUserName("user_"+i);
			model.setOrgCode("2000");
			model.setUserState("A");
			model.setTel("12345678");
			model.setEmail("test@com");
			model.setRemark("备注");
			model.setDefaultAdd("admin", new Date());
			this.pmiUserService.add(model);
		}
	}
}

