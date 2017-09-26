package org.yffd.easy.spring4.jta.atom.test;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年8月29日 下午5:27:46 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class AtomTransactionServiceImpl implements AtomTransactionService {

    public void insertTest(ApplicationContext ctx) throws Exception {
        AtomBaseDao baseDao = ctx.getBean(AtomBaseDao.class);  
        String user_code = "code_123";  
        String user_name = "name_123";  
        String masterSql = "insert into t_teacher (user_code, user_name) values "+"('"+user_code+"', '"+user_name+"')";  
        String slaveSql = "insert into t_teacher (user_code, user_name) values "+"('"+user_code+"', '"+user_name+"')";  
        baseDao.getMastTemplate().execute(masterSql);  
        baseDao.getSlaveTemplate().execute(slaveSql);  
        throw new Exception();
    }

}

