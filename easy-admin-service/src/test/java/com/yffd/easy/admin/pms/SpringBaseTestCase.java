package com.yffd.easy.admin.pms;

import java.util.Date;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.common.core.model.PersistModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 上午10:40:12 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:spring/spring-context-service.xml"
})
//@TransactionConfiguration(transactionManager="default_txManager") //可选，默认为transactionManager
//@Transactional
public class SpringBaseTestCase {
	
	protected void setAddDefault(PersistModel model, String user, Date time) {
		model.setVersion(0);
		model.setDelFlag("0");
		model.setCreater(user);
		model.setCreateTime(time);
		model.setEditor(user);
		model.setEditTime(time);
	}
	
	protected void setUpdateDefault(PersistModel model, String user, Date time) {
		model.setEditor(user);
		model.setEditTime(time);
	}
}

