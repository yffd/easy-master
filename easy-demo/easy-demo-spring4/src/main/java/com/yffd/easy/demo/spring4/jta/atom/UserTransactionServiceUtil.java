package com.yffd.easy.demo.spring4.jta.atom;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.XAConnection;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import javax.transaction.xa.XAResource;

import com.atomikos.datasource.xa.jdbc.JdbcTransactionalResource;
import com.atomikos.icatch.config.UserTransactionService;
import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

/**
 * @Description  使用Atomikos UserTransactionService。这是高级使用方式，可以控制事务服务的启动和关闭，并且可以控制资源的装配.
 * @Date		 2017年8月30日 上午11:42:48 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UserTransactionServiceUtil {
    
private static MysqlXADataSource masterDataSource;
    
    private static MysqlXADataSource getMasterDataSource() throws SQLException {
        if(masterDataSource != null) return masterDataSource;
        masterDataSource = new MysqlXADataSource();
        masterDataSource.setUser("root");
        masterDataSource.setPassword("root123");
        masterDataSource.setURL("jdbc:mysql://127.0.0.1:3306/test?autoReconnect=true");
        return masterDataSource;
    }

    public static XAConnection getMasterConnection() throws SQLException {
        MysqlXADataSource ds = getMasterDataSource();
        return ds.getXAConnection();
    }
    
    private static JdbcTransactionalResource masterJdbcResource; 

    public static JdbcTransactionalResource getMasterJdbcTransactionalResource() throws SQLException {
        if (masterJdbcResource != null)
            return masterJdbcResource;
        masterJdbcResource = new JdbcTransactionalResource("master", getMasterDataSource());
        return masterJdbcResource;
    }
    
    
    private static MysqlXADataSource slaveDataSource;
    
    private static MysqlXADataSource getSlaveDataSource() throws SQLException {
        if(slaveDataSource != null) return slaveDataSource;
        slaveDataSource = new MysqlXADataSource();
        slaveDataSource.setUser("root");
        slaveDataSource.setPassword("root123");
        slaveDataSource.setURL("jdbc:mysql://127.0.0.1:3306/test1?autoReconnect=true");
        return slaveDataSource;
    }
    
    public static XAConnection getSlaveConnection() throws SQLException {
        MysqlXADataSource ds = getSlaveDataSource();
        return ds.getXAConnection();
    }
    
    private static JdbcTransactionalResource slaveJdbcResource; 

    public static JdbcTransactionalResource getSlaveJdbcTransactionalResource() throws SQLException {
        if (slaveJdbcResource != null)
            return slaveJdbcResource;
        slaveJdbcResource = new JdbcTransactionalResource("slave", getSlaveDataSource());
        return slaveJdbcResource;
    }
    
    public static UserTransactionService getUserTransactionService() throws Exception {
        return new UserTransactionServiceImp();
    } 
    
    public static void main(String[] args) {
        try {
            UserTransactionService uts = getUserTransactionService();
                    
            uts.registerResource(getMasterJdbcTransactionalResource());
            uts.registerResource(getSlaveJdbcTransactionalResource());
            
            Properties p = new Properties();
            p.setProperty("com.atomikos.icatch.checkpoint_interval", "2000");
            uts.init(p);

            //TODO
            TransactionManager tm = null;
//            TransactionManager tm = uts.getTransactionManager();
            tm.setTransactionTimeout(60);

            boolean rollback = false;

            tm.begin();
            Transaction tx = tm.getTransaction();

            XAConnection masterXAConn = getMasterConnection();
            XAConnection slaveXAConn = getSlaveConnection();
            
            XAResource masterXares = masterXAConn.getXAResource();
            XAResource slaveXares = slaveXAConn.getXAResource();
            try {
                tx.enlistResource(masterXares);
                tx.enlistResource(slaveXares);

                Connection masterConn = masterXAConn.getConnection();
                Statement masterStmt = masterConn.createStatement();
                masterStmt.executeUpdate("insert into t_teacher (user_code, user_name) values ('code_234', 'zhangsan')");
                masterStmt.close();
                masterConn.close();
                
                Connection slaveConn = slaveXAConn.getConnection();
                Statement slaveStmt = slaveConn.createStatement();
                slaveStmt.executeUpdate("insert into t_teacher (user_code, user_name) values ('code_234', 'zhangsan')");
                slaveStmt.close();
                slaveConn.close();
            } catch (Exception e) {
                rollback = true;
                throw e;
            } finally {
                int flag = XAResource.TMSUCCESS;
                if (rollback)
                    flag = XAResource.TMFAIL;

                tx.delistResource(masterXares, XAResource.TMSUCCESS);
                tx.delistResource(slaveXares, XAResource.TMSUCCESS);

                masterXAConn.close();
                slaveXAConn.close();

                if (!rollback)
                    tm.commit();
                else
                    tm.rollback();
            }

            uts.shutdown(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

