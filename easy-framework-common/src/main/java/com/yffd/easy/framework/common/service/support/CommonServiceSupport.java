package com.yffd.easy.framework.common.service.support;

import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年4月20日 下午6:03:05 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class CommonServiceSupport<M, E> {

	protected E model2entity(M model) {
		return null;
	}
	
	protected M entity2model(E entity) {
		return null;
	}
	
	protected List<E> modelList2entityList(List<M> modelList) {
		return null;
	}
	
	protected List<M> entityList2modelList(List<E> entityList) {
		return null;
	}
	
	protected PageResult<M> entityPage2modelPage(PageResult<E> entityPage) {
		return null;
	}
	
	protected Map<String, Object> noneEntityPropertys(M model) {
		return null;
	}
	
	protected String makeOrderBy(String[] orderPropertyNames, String[] orderByTypes) {
		if(null==orderPropertyNames) return null;
		String defaultType = "asc";
		StringBuilder sb = new StringBuilder();
		if(null==orderByTypes) {
			for(String name : orderPropertyNames) {
				sb.append(name).append(" ").append(defaultType).append(", ");
			}
		} else {
			Integer len1 = orderPropertyNames.length;
			Integer len2 = orderByTypes.length;
			for(Integer i=0;i<len1;i++) {
				sb.append(orderPropertyNames[i]).append(" ");
				if(i<len2) {
					sb.append(orderByTypes[i]).append(", ");
				} else {
					sb.append(defaultType).append(", ");
				}
			}
		}
		return sb.length()>0 ? sb.substring(0, sb.lastIndexOf(",")) : null;
	}
}

