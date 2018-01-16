package com.yffd.easy.demo.spring4.jta.atom.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年8月29日 下午5:28:26 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class AtomTest {
    
    public AtomTest(){  
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-jta-atomikos.xml");  
        AtomTransactionService service = ctx.getBean(AtomTransactionService.class);  
        try {  
            service.insertTest(ctx);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    public static void main(String[] args) {  
        AtomTest test = new AtomTest();  
        System.out.println("done.....");  
    }
}

