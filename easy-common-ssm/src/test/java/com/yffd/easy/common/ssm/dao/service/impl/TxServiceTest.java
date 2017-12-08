package com.yffd.easy.common.ssm.dao.service.impl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yffd.easy.common.ssm.model.TestModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月12日 上午11:39:02 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:syscfg/spring-core-cfg.xml",
    "classpath:syscfg/test-spring-bean.xml"
})
public class TxServiceTest {

    @Autowired
    private TransactionServiceImpl transactionService;
    
    @Test
    public void txInsertAndUpdateTest() {
        String userName = "tx_22";
        String userCode = "tx_2_2";
        Date createTime = new Date();
        TestModel insert = new TestModel(userName, userCode, createTime);

        TestModel update = new TestModel("tx_33", "tx_3_3", new Date());
        update.setId("23");
        this.transactionService.txInsertAndUpdate(insert, update);
    }
}

