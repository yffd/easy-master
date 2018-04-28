package com.yffd.easy.framework.common.pojo.vo.factory;

import java.util.ArrayList;
import java.util.List;

import com.yffd.easy.common.core.exception.EasyCommonException;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyJavaBeanUtils;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年4月26日 下午5:39:31 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class CommonVoFactory<VO> implements ICommonVoFactory<VO> {

	/**
	 * VO后处理器
	 * @Date	2018年4月26日 下午4:50:36 <br/>
	 * @author  zhangST
	 * @param vo	vo实例
	 * @param obj	vo中已有数据的来源
	 */
	protected abstract void afterPropertiesSet(VO vo, Object obj);
	
	@Override
	public VO createVo(Object obj) {
		Class<?> voClazz = this.getVoClazz();
		if(null==obj || null==voClazz) return null;
		try {
			if(voClazz.isInstance(obj)) return (VO) obj;
			VO vo = EasyJavaBeanUtils.copyProperties(obj, voClazz);
			this.afterPropertiesSet(vo, obj);
			return vo;
		} catch (Exception e) {
			throw new EasyCommonException("po convert to vo error!", e);
		}
	}
	
	@Override
	public List<VO> createVoList(List<?> objList) {
		Class<?> voClazz = this.getVoClazz();
		if(null==objList || objList.size()==0 || null==voClazz) return null;
		Object tmp = objList.get(0);
		if(voClazz.isInstance(tmp)) {
			return (List<VO>) objList;
		} else {
			List<VO> voList = new ArrayList<VO>();
			for(Object obj : objList) {
				VO vo = this.createVo(obj);
				voList.add(vo);
			}
			return voList;
		}
	}
	
	@Override
	public PageResult<VO> createVoPage(PageResult<?> objPage) {
		Class<?> voClazz = this.getVoClazz();
		if(null==objPage || null==voClazz) return null;
		List<?> recordList = objPage.getRecordList();
		if(null==recordList || recordList.size()==0) {
			PageResult<VO> voPage = new PageResult<VO>();
			voPage.setPageParam(objPage.getPageParam());
			return voPage;
		} else {
			Object tmp = recordList.get(0);
			if(voClazz.isInstance(tmp)) {
				return (PageResult<VO>) objPage;
			} else {
				List<VO> voList = this.createVoList(recordList);
				PageResult<VO> voPage = new PageResult<VO>();
				voPage.setPageParam(objPage.getPageParam());
				voPage.setRecordList(voList);
				return voPage;
			}
		}
	}
	
	protected Class<?> getVoClazz() {
		Class<?> genericTypeClazz = this.getClass().getGenericInterfaces()[0].getClass();
		return genericTypeClazz;
	}
	
}

