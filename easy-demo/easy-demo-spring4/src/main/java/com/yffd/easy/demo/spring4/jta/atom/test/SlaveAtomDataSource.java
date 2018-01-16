package com.yffd.easy.demo.spring4.jta.atom.test;

import java.util.Properties;

import org.springframework.stereotype.Repository;

import com.atomikos.jdbc.AtomikosDataSourceBean;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年8月29日 下午5:24:04 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository
public class SlaveAtomDataSource extends AtomikosDataSourceBean {
    
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * @since JDK 1.7+
     */
    private static final long serialVersionUID = 3750944489672897569L;

    public SlaveAtomDataSource(){  
        Properties prop = new Properties();  
        prop.put("user", "root");  
        prop.put("password", "root123");  
        prop.put("URL", "jdbc:mysql://127.0.0.1:3306/test1?autoReconnect=true");  
        setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");  
        setUniqueResourceName("mysql_ds2");  
        setPoolSize(5);  
        setXaProperties(prop);  
    }
    
}

