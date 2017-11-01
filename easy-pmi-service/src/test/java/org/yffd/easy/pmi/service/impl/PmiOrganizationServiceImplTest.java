package org.yffd.easy.pmi.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.pmi.SpringBaseTestCase;

import com.yffd.easy.pmi.model.PmiOrganization;
import com.yffd.easy.pmi.service.PmiOrganizationService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月9日 上午9:57:12 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmiOrganizationServiceImplTest extends SpringBaseTestCase {

	@Autowired
	private PmiOrganizationService pmiOrganizationService;
	
	@Test
	public void findByParentCodeTest() {
		List<PmiOrganization> list = this.pmiOrganizationService.findByParentCode("1002");
		for(PmiOrganization entity:list) {
			System.out.println(entity.getOrgCode());
		}
		System.out.println(list.size());
	}
	
	@Test
	public void addTest() {
		for(int i=0;i<5;i++) {
			PmiOrganization entity = new PmiOrganization();
			entity.setOrgCode("100"+i);
			entity.setOrgName("一级机构_"+i);
			entity.setParentCode("-1");
			entity.setDefaultAdd("admin", new Date());
			this.pmiOrganizationService.add(entity);
		}
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				PmiOrganization entity = new PmiOrganization();
				entity.setOrgCode(i+2+"00"+j);
				entity.setOrgName("二级机构_"+i+"_"+j);
				entity.setParentCode("100"+i);
				entity.setDefault();
				entity.setDefaultAdd("admin", new Date());
				this.pmiOrganizationService.add(entity);
			}
		}
	}
}

