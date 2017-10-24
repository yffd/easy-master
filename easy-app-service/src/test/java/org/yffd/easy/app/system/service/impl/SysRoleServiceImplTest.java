package org.yffd.easy.app.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.app.SpringBaseTestCase;
import org.yffd.easy.app.system.model.SysFunction;
import org.yffd.easy.app.system.model.SysRole;
import org.yffd.easy.app.system.service.SysRoleService;
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
public class SysRoleServiceImplTest extends SpringBaseTestCase {

	@Autowired
	private SysRoleService sysRoleService;
	
	@Test
	public void findListTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		String name = null;
		String code = null;
		String orgCode = null;
		SysRole sysRole = null;
		PageResult<SysRole> page = this.sysRoleService.findPage(sysRole, pageParam);
		System.out.println(page.getRecordList().size());
	}
	
	@Test
	public void addTest() {
		for(int i=0;i<35;i++) {
			SysRole model = new SysRole();
			model.setRoleCode("code_"+i);
			model.setRoleName("role_"+i);
			model.setRemark("备注");
			model.setDefaultAdd("admin", new Date());
			this.sysRoleService.add(model);
		}
	}
	
	@Test
	public void saveRoleFuncTest() {
		String roleCode = "code_0";
		List<String> funcCodes = new ArrayList<String>();
		funcCodes.add("code_0_0");
		funcCodes.add("code_0_2");
//		funcCodes.add("code_0_3");
//		funcCodes.add("code_0_4");
		this.sysRoleService.saveRoleFunc(roleCode, funcCodes);
	}
	
	@Test
	public void saveRoleUserTest() {
		String userCode = "zhangsan";
		List<String> roleCodes = new ArrayList<String>();
		roleCodes.add("code_32");
		roleCodes.add("code_33");
		roleCodes.add("code_34");
		this.sysRoleService.saveRoleUser(userCode, roleCodes);
	}
	
	@Test
	public void findByUserTest() {
		String userCode = "zhangsan";
		List<SysRole> list = this.sysRoleService.findByUser(userCode);
		System.out.println(list.size());
	}
}

