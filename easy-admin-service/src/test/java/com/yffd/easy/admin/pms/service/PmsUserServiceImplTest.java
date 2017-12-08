package com.yffd.easy.admin.pms.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.admin.pms.SpringBaseTestCase;
import com.yffd.easy.admin.pms.model.PmsUser;
import com.yffd.easy.admin.pms.service.PmsUserService;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月9日 上午9:57:12 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmsUserServiceImplTest extends SpringBaseTestCase {

	@Autowired
	private PmsUserService pmsUserService;
	
	@Test
	public void findListTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		String name = null;
		String code = null;
		String orgCode = null;
		PmsUser user = null;
		PageResult<PmsUser> page = this.pmsUserService.findList(user, pageParam);
		System.out.println(page.getRecordList().size());
	}
	
	@Test
	public void findUserTest() {
		String userCode = "admin";
		PmsUser user = this.pmsUserService.findUser(userCode);
		System.out.println(user.getUserName());
		System.out.println(user.getPassword());
		System.out.println(user.getSalt());
	}
	@Test
	public void addTest() {
		for(int i=0;i<35;i++) {
			PmsUser model = new PmsUser();
			model.setUserCode("code_"+i);
			model.setUserName("user_"+i);
			model.setOrgCode("2000");
			model.setUserStatus("A");
			model.setTel("12345678");
			model.setEmail("test@com");
			model.setRemark("备注");
			this.setAddDefault(model, "test", new Date());
			this.pmsUserService.add(model);
		}
	}
}

