package org.yffd.easy.spring4.jta.atom;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.XAConnection;
import javax.transaction.Transaction;
import javax.transaction.xa.XAResource;

import com.atomikos.icatch.jta.UserTransactionManager;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

/**
 * @Description  使用JTA TransactionManager。这种方式不需要Atomikos内置的JDBC、JMS适配器，但需要在JTA/XA级别上添加、删除XA资源实例.
 * @Date		 2017年8月30日 上午11:00:55 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class TransactionManagerUtil {
    
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

    public static UserTransactionManager getUserTransactionManager() throws Exception {
        return new UserTransactionManager();
    }
    
    public static void main(String[] args) {
        try {
            UserTransactionManager tm = getUserTransactionManager();

            XAConnection masterXAConn = getMasterConnection();
            XAConnection slaveXAConn = getSlaveConnection();

            boolean rollback = false;
            try {
                tm.begin();
                Transaction tx = tm.getTransaction();

                XAResource masterXares = masterXAConn.getXAResource();
                XAResource slaveXares = slaveXAConn.getXAResource();

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

                tx.delistResource(masterXares, XAResource.TMSUCCESS);
                tx.delistResource(slaveXares, XAResource.TMSUCCESS);
                
                throw new Exception(">>>>>>test distribute transaction rollback<<<<<<<<<<<");
                
            } catch (Exception e) {
                rollback = true;
                throw e;
            } finally {
                if (rollback)
                    tm.rollback();
                else
                    tm.commit();

                masterXAConn.close();
                slaveXAConn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

