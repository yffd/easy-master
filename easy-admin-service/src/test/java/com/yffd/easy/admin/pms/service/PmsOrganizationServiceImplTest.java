package com.yffd.easy.admin.pms.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.admin.pms.SpringBaseTestCase;
import com.yffd.easy.admin.pms.model.PmsOrganization;
import com.yffd.easy.admin.pms.service.PmsOrganizationService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月9日 上午9:57:12 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmsOrganizationServiceImplTest extends SpringBaseTestCase {

	@Autowired
	private PmsOrganizationService pmsOrganizationService;
	
	@Test
	public void findByParentCodeTest() {
		List<PmsOrganization> list = this.pmsOrganizationService.findByParentCode("1002");
		for(PmsOrganization entity:list) {
			System.out.println(entity.getOrgCode());
		}
		System.out.println(list.size());
	}
	
	@Test
	public void addTest() {
		for(int i=0;i<5;i++) {
			PmsOrganization entity = new PmsOrganization();
			entity.setOrgCode("100"+i);
			entity.setOrgName("一级机构_"+i);
			entity.setParentCode("-1");
			this.setAddDefault(entity, "test", new Date());
			this.pmsOrganizationService.add(entity);
		}
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				PmsOrganization entity = new PmsOrganization();
				entity.setOrgCode(i+2+"00"+j);
				entity.setOrgName("二级机构_"+i+"_"+j);
				entity.setParentCode("100"+i);
				entity.setDefault();
				this.setAddDefault(entity, "test", new Date());
				this.pmsOrganizationService.add(entity);
			}
		}
	}
}

