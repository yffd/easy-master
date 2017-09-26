package org.yffd.easy.app.permission.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yffd.easy.common.core.exception.SysException;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月12日 下午5:01:38 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class AuthException extends SysException {
    
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * @since JDK 1.7+
     */
    private static final long serialVersionUID = 27767584461739825L;

    private static final Logger logger = LoggerFactory.getLogger(AuthException.class);
    /** 该用户没有分配菜单权限 */
    public static final Integer PERMISSION_USER_NOT_MENU = 1001;
    /** 根据角色查询菜单出现错误 **/
    public static final Integer PERMISSION_QUERY_MENU_BY_ROLE_ERROR = 1002;
    /** 分配菜单权限时，角色不能为空 **/
    public static final Integer PERMISSION_ASSIGN_MENU_ROLE_NULL = 1003;
    /** 网络异常 **/
    public static final Integer PERMISSION_NETWORK_EXCEPTION = 1004;

    public AuthException() {
    }

    public AuthException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public AuthException(int code, String msg) {
        super(code, msg);
    }

    /**
     * 
     * newInstance:实例化权限异常. <br/>
     * @Date	2017年9月12日 下午5:04:18 <br/>
     * @author  zhangST
     * @param msgFormat
     * @param args
     * @return
     * @see org.yffd.easy.common.core.exception.BizException#newInstance(java.lang.String, java.lang.Object[])
     */
    public AuthException newInstance(String msgFormat, Object... args) {
        return new AuthException(this.code, msgFormat, args);
    }

    public AuthException print() {
        logger.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }
    
}

