package org.yffd.easy.app.system.model;

import java.util.List;

import org.yffd.easy.common.core.model.PersistEntity;

/**
 * @Description  系统模块：菜单实体.
 * @Date		 2017年9月27日 下午3:41:11 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SysMenu extends PersistEntity {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 8507597194623600620L;

	private String name;
	private String code;
	private String parentCode;
	private String url;
	private String icon;
	private Float sort;
	private String status;
	
	private SysMenu parent;
	private List<SysMenu> children;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public SysMenu getParent() {
		return parent;
	}
	public void setParent(SysMenu parent) {
		this.parent = parent;
	}
	public List<SysMenu> getChildren() {
		return children;
	}
	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}
	
}

