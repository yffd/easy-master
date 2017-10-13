package org.yffd.easy.app.system.service.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.app.SpringBaseTestCase;
import org.yffd.easy.app.system.model.SysOrganization;
import org.yffd.easy.app.system.service.SysOrganizationService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月9日 上午9:57:12 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SysOrganizationServiceImplTest extends SpringBaseTestCase {

	@Autowired
	private SysOrganizationService sysOrganizationService;
	
	@Test
	public void findByParentCodeTest() {
		List<SysOrganization> list = this.sysOrganizationService.findByParentCode("1002");
		for(SysOrganization entity:list) {
			System.out.println(entity.getOrgCode());
		}
		System.out.println(list.size());
	}
	
	@Test
	public void addTest() {
		for(int i=0;i<5;i++) {
			SysOrganization entity = new SysOrganization();
			entity.setOrgCode("100"+i);
			entity.setOrgName("一级机构_"+i);
			entity.setParentCode("-1");
			entity.setShortName("一级机构");
			entity.setIcon("icon-sys");
			entity.setDefault();
			this.sysOrganizationService.add(entity);
		}
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				SysOrganization entity = new SysOrganization();
				entity.setOrgCode(i+2+"00"+j);
				entity.setOrgName("二级机构_"+i+"_"+j);
				entity.setParentCode("100"+i);
				entity.setShortName("二级机构");
				entity.setIcon("icon-sys");
				entity.setDefault();
				this.sysOrganizationService.add(entity);
			}
		}
	}
}

