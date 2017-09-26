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
	SUCCESS("ok", "成功"),
	ERROR("faild", "失败"),
	YES("1", "是"),
	NO("0", "否"),
	ACTIVE("1", "激活"),
	UNACTIVE("0", "冻结"),
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

