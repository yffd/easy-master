package org.yffd.easy.common.ssm.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yffd.easy.common.core.exception.SysException;
import org.yffd.easy.common.ssm.dao.IBaseDao;
import org.yffd.easy.common.ssm.model.TestModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月12日 上午10:54:38 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("transactionService")
public class TransactionServiceImpl {
    @Autowired
    private IBaseDao<TestModel> baseDao;
    
    @Transactional(rollbackFor = SysException.class)
//    @Transactional(value = "default_txManager", rollbackFor = BizException.class, readOnly = false, propagation = Propagation.REQUIRED)
    public void txInsertAndUpdate(TestModel insert, TestModel update) {
        int restult1 = this.baseDao.insert(insert);
        System.out.println(">>>result1="+restult1);
        
//        if(true) throw BizException.DB_INSERT_RESULT_0;
        
        int restult2 = this.baseDao.updateByPK(update);
        System.out.println(">>>result2="+restult2);
    }
}

