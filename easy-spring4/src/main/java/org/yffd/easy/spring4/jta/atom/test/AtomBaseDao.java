package org.yffd.easy.spring4.jta.atom.test;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年8月29日 下午5:24:54 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository
public class AtomBaseDao {
    @Autowired
    private MasterAtomDatasource masterAtomDatasource;  
    @Autowired
    private SlaveAtomDataSource slaveAtomDataSource;
    
    private JdbcTemplate mastTemplate;  
    private JdbcTemplate slaveTemplate;  
      
    public JdbcTemplate getMastTemplate() {
        if(null==this.mastTemplate) {
            this.mastTemplate = new JdbcTemplate(masterAtomDatasource); 
        }
        return mastTemplate;  
    }  
    public JdbcTemplate getSlaveTemplate() {  
        if(null==this.slaveTemplate) {
            this.slaveTemplate = new JdbcTemplate(slaveAtomDataSource); 
        }
        return slaveTemplate;  
    }  
}

