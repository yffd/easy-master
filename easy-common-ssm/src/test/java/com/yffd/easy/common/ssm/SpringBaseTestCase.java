package org.yffd.easy.common.ssm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月7日 下午4:26:18 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:syscfg/spring-core-cfg.xml"
})
@TransactionConfiguration(transactionManager="default_txManager") //可选，默认为transactionManager
@Transactional
public class SpringBaseTestCase {

    
    @Test
    public void test1() {
        String configLocation = "/src/main/resources/syscfg/spring-core-cfg.xml";
        ApplicationContext context = new FileSystemXmlApplicationContext(configLocation);
        String applicationName = context.getApplicationName();
        System.out.println(applicationName);
        System.out.println(">>>>");
    }
    
    @Test
    public void test2() {
        String[] configLocation = new String[]{"/src/main/resources/syscfg/spring-core-dao-cfg.xml",
                "/src/main/resources/syscfg/spring-core-datasource-cfg.xml"};
        ApplicationContext context = new FileSystemXmlApplicationContext(configLocation);
        String applicationName = context.getApplicationName();
        System.out.println(applicationName);
        System.out.println(">>>>");
    }
    
    @Test
    public void test3() {
    }
    
}

