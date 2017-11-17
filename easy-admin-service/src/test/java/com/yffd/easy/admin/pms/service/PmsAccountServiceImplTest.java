package com.yffd.easy.admin.pms.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;

import com.yffd.easy.admin.pms.SpringBaseTestCase;
import com.yffd.easy.admin.pms.model.PmsAccount;
import com.yffd.easy.admin.pms.service.PmsAccountService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月9日 上午9:57:12 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmsAccountServiceImplTest extends SpringBaseTestCase {

	@Autowired
	private PmsAccountService pmsAccountService;
	
	@Test
	public void findListTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		PmsAccount account = null;
		PageResult<PmsAccount> page = this.pmsAccountService.findPage(account, pageParam);
		System.out.println(page.getRecordList().size());
	}
	
	@Test
	public void addTest() {
		PmsAccount model = new PmsAccount();
		model.setAccountName("admin");
		model.setAccountPwd("bd7a5f4585b7c1bce9a84f7da4187023");
		model.setSalt("7b0b85873b8b333b15b228aa4e2787d8");
		model.setNickName("管理员");
		model.setType("S");
		model.setStatus("A");
		this.setAddDefault(model, "test", new Date());
		this.pmsAccountService.save(model);
	}
}

