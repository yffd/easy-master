package org.yffd.easy.common.core.exception;

/**
 * @Description  DAO异常封装.
 * @Date		 2017年9月22日 上午11:51:40 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class DaoException extends SysException {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -877612146007504246L;
	
	/**
	 * 数据库操作
	 */
	public static final SysException newInstance(String msgFormat, Object... args) {
		return new SysException(3001, msgFormat, args);
	}
	
	/**
	 * 数据库操作，插入返回结果为0
	 */
    public static final SysException DB_INSERT_RESULT_0(String Statement) {
    	return new SysException(3011, "数据库操作，插入返回结果为0.{%s}", Statement);
    }

    /**
     * 数据库操作，更新返回结果为0
     */
    public static final SysException DB_UPDATE_RESULT_0(String Statement) {
    	return new SysException(3021, "数据库操作，更新返回结果为0.{%s}", Statement);
    }
    
    /**
     * 数据库操作，删除返回结果为0
     */
    public static final SysException DB_DELETE_RESULT_0(String Statement) {
    	return new SysException(3031, "数据库操作，删除返回结果为0.{%s}", Statement);
    }

    /**
     * 数据库操作，单条查询返回结果为null
     */
    public static final SysException DB_SELECT_ONE_IS_NULL(String Statement) {
    	return new SysException(3041, "数据库操作，单条查询返回结果为null", Statement);
    }
    
    /**
     * 数据库操作，单条查询返回结果为多条
     */
    public static final SysException DB_SELECT_ONE_RESULT_MULTI(String Statement) {
    	return new SysException(3042, "数据库操作，单条查询返回结果为多条", Statement);
    }

    /**
     * 数据库操作，多条查询返回结果为null
     */
    public static final SysException DB_SELECT_LIST_IS_NULL(String Statement) {
    	return new SysException(3043, "数据库操作，多条查询返回结果为null", Statement);
    }

    /**
     * 数据库操作，序列生成超时
     */
    public static final SysException DB_GET_SEQ_NEXT_VALUE_ERROR(String Statement) {
    	return new SysException(3051, "数据库操作，序列生成超时", Statement);
    }
}

