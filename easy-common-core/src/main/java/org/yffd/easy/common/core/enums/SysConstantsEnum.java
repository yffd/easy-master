package org.yffd.easy.common.core.enums;

/**
 * @Description  系统常量枚举类.
 * @Date		 2017年9月22日 下午2:13:03 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public enum SysConstantsEnum {
	SUCCESS("OK", "成功"),
	ERROR("FAILD", "失败"),
	YES("Y", "是"),
	NO("N", "否"),
	ACTIVE("A", "激活"),
	INACTIVE("I", "冻结"),
	DEL_FLAG_UNENABLE("0", "未删除"),
	DEL_FLAG_ENABLE("1", "已删除"),
	REQUEST_ASYNC("async", "异步"),
	REQUEST_SYNC("sync", "同步"),
	;
	
	/** 值 */
	private String value;
	/** 描述 */
	private String desc;
    
    private SysConstantsEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}

