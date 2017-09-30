package org.yffd.easy.app.system.entity;

import java.util.List;

import org.yffd.easy.common.core.entity.PersistEntity;

/**
 * @Description  系统模块：功能表实体.
 * @Date		 2017年9月27日 下午3:41:11 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SysFunction extends PersistEntity {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 8507597194623600620L;

	public static final String TYPE_FUNCTION = "F";
	public static final String TYPE_OPRATION = "O";
	
	private String name;
	private String code;
	private String parentCode;
	private String type;
	private String url;
	private String icon;
	private Float sort;
	
	private SysFunction parent;
	private List<SysFunction> children;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public Float getSort() {
		return sort;
	}
	public void setSort(Float sort) {
		this.sort = sort;
	}
	public SysFunction getParent() {
		return parent;
	}
	public void setParent(SysFunction parent) {
		this.parent = parent;
	}
	public List<SysFunction> getChildren() {
		return children;
	}
	public void setChildren(List<SysFunction> children) {
		this.children = children;
	}
	
}

