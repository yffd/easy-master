package com.yffd.easy.demo.spring4.jta.atom;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.transaction.UserTransaction;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.jdbc.AtomikosDataSourceBean;

/**
 * @Description  使用JDBC/JMS和UserTransaction，这是最直接和最简单的使用方式，使用Atomikos内置的JDBC、JMS适配器.
 * @Date		 2017年8月30日 上午10:23:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UserTransactionUtil {

    private static AtomikosDataSourceBean masterDataSource;
    
    private static AtomikosDataSourceBean getMasterDataSource() {
        if(masterDataSource != null) return masterDataSource;
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName("mysql_ds1");
        ds.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        Properties p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "root123");
        p.setProperty("URL", "jdbc:mysql://127.0.0.1:3306/test?autoReconnect=true");
        ds.setXaProperties(p);
        ds.setPoolSize(5);
        masterDataSource = ds;
        return masterDataSource;
    }
    
    public static Connection getMasterConnection() throws SQLException {
        Connection conn = getMasterDataSource().getConnection();
        return conn;
    }
    
    
    private static AtomikosDataSourceBean slaveDataSource; 

    private static AtomikosDataSourceBean getSlaveDataSource() {
        if(slaveDataSource != null) return slaveDataSource;
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName("mysql_ds2");
        ds.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        Properties p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "root123");
        p.setProperty("URL", "jdbc:mysql://127.0.0.1:3306/test1?autoReconnect=true");
        ds.setXaProperties(p);
        ds.setPoolSize(5);
        slaveDataSource = ds;
        return slaveDataSource;
    }

    public static Connection getSlaveConnection() throws SQLException {
        Connection conn = getSlaveDataSource().getConnection();
        return conn;
    }
    
    public static UserTransaction getUserTransaction() { 
        UserTransaction utx = new UserTransactionImp(); 
        return utx; 
    }
    
    public static void main(String[] args) {
        UserTransaction utx = getUserTransaction();
        boolean rollback = false;
        try {
            // begin a transaction
            utx.begin();

            // execute db operation
            Connection masterConn = null;
            Statement masterStmt = null;
            Connection slaveConn = null;
            Statement slaveStmt = null;
            try {
                masterConn = getMasterConnection();
                masterStmt = masterConn.createStatement();
                masterStmt.executeUpdate("insert into t_teacher (user_code, user_name) values ('code_zhangsan', 'zhangsan')");
               
                slaveConn = getSlaveConnection();
                slaveStmt = slaveConn.createStatement();
                slaveStmt.executeUpdate("insert into t_teacher (user_code, user_name) values ('code_zhangsan', 'zhangsan')");
                
                throw new Exception(">>>>>>test distribute transaction rollback<<<<<<<<<<<");
            } catch (Exception e) {
                throw e;
            } finally {
                if(masterStmt != null) masterStmt.close();
                if(masterConn != null) masterConn.close();
                if(slaveStmt != null) slaveStmt.close();
                if(slaveConn != null) slaveConn.close();
            }
        } catch (Exception e) {
            // an exception means we should not commit
            rollback = true;
            e.printStackTrace();
        } finally {
            try {
                // commit or rollback the transaction
                if(!rollback)
                    utx.commit();
                else
                    utx.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}

