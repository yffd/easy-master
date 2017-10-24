package org.yffd.easy.app.system.service.impl;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.app.SpringBaseTestCase;
import org.yffd.easy.app.system.model.SysUser;
import org.yffd.easy.app.system.service.SysUserService;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月9日 上午9:57:12 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SysUserServiceImplTest extends SpringBaseTestCase {

	@Autowired
	private SysUserService sysUserService;
	
	@Test
	public void findListTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		String name = null;
		String code = null;
		String orgCode = null;
		SysUser sysUser = null;
		PageResult<SysUser> page = this.sysUserService.findList(sysUser, pageParam);
		System.out.println(page.getRecordList().size());
	}
	
	@Test
	public void addTest() {
		for(int i=0;i<35;i++) {
			SysUser model = new SysUser();
			model.setUserCode("code_"+i);
			model.setUserName("user_"+i);
			model.setAccount("admin");
			model.setPassword("123456");
			model.setOrgCode("2000");
			model.setTel("12345678");
			model.setEmail("test@com");
			model.setActive("A");
			model.setRemark("备注");
			model.setDefaultAdd("admin", new Date());
			this.sysUserService.add(model);
		}
	}
}

