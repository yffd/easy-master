package com.yffd.easy.workflow.activiti.db;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月19日 下午4:17:46 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class InitDB {

	public void init() {
		ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		configuration.setJdbcDriver("com.mysql.jdbc.Driver");
		configuration.setJdbcUrl("jdbc:mysql://localhost:3306/easy-activiti-a?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true");
		configuration.setJdbcUsername("root");
		configuration.setJdbcPassword("root123");
		configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP);
		
		//通过创建引擎，完成数据库表初始化
		ProcessEngine processEngine = configuration.buildProcessEngine();
	}
	
	public static void main(String[] args) {
		InitDB db = new InitDB();
		db.init();
	}
}

