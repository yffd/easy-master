package com.yffd.easy.admin.pms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;

import com.yffd.easy.admin.pms.SpringBaseTestCase;
import com.yffd.easy.admin.pms.model.PmsRole;
import com.yffd.easy.admin.pms.service.PmsRoleService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月9日 上午9:57:12 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmsRoleServiceImplTest extends SpringBaseTestCase {

	@Autowired
	private PmsRoleService pmsRoleService;
	
	@Test
	public void findListTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		String name = null;
		String code = null;
		String orgCode = null;
		PmsRole role = null;
		PageResult<PmsRole> page = this.pmsRoleService.findPage(role, pageParam);
		System.out.println(page.getRecordList().size());
	}
	
	@Test
	public void addTest() {
		for(int i=0;i<35;i++) {
			PmsRole model = new PmsRole();
			model.setRoleCode("code_"+i);
			model.setRoleName("role_"+i);
			model.setRemark("备注");
			this.setAddDefault(model, "test", new Date());
			this.pmsRoleService.add(model);
		}
	}
	
	@Test
	public void saveRoleResourceTest() {
		String roleCode = "code_0";
		List<String> rsCodes = new ArrayList<String>();
		rsCodes.add("code_0_0");
		rsCodes.add("code_0_2");
//		funcCodes.add("code_0_3");
//		funcCodes.add("code_0_4");
		this.pmsRoleService.saveRoleResource(roleCode, rsCodes);
	}
	
	@Test
	public void saveRoleUserTest() {
		String userCode = "zhangsan";
		List<String> rsCodes = new ArrayList<String>();
		rsCodes.add("code_32");
		rsCodes.add("code_33");
		rsCodes.add("code_34");
		this.pmsRoleService.saveRoleUser(userCode, rsCodes);
	}
	
	@Test
	public void findByUserTest() {
		String userCode = "zhangsan";
		List<PmsRole> list = this.pmsRoleService.findByUser(userCode);
		System.out.println(list.size());
	}
}

