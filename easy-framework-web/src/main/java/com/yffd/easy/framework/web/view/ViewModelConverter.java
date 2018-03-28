package com.yffd.easy.framework.web.view;

import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.converter.EasyModelConverter;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.ValidUtils;
import com.yffd.easy.framework.web.view.vo.DataGridVO;
import com.yffd.easy.framework.web.view.vo.SearchVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月12日 下午6:05:05 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class ViewModelConverter extends EasyModelConverter {
	public static final Long PAGE_LIMIT_DEFAUL = 30L;
	
	/**
	 * 将后台分页结果转换成EasyUI分页数据格式
	 * @Date	2017年10月12日 下午6:13:01 <br/>
	 * @author  zhangST
	 * @param pageResult
	 * @return
	 */
	public DataGridVO toDataGrid(PageResult<?> pageResult) {
		if(null==pageResult) return null;
		DataGridVO vo = new DataGridVO();
		if(null!=pageResult.getRecordList())
			vo.setRows(pageResult.getRecordList());
		Long total = (long) pageResult.getPageParam().getTotalRecord();
		vo.setTotal(total);
		return vo;
	}
	
	/**
	 * 将后台集合结果转换成EasyUI分页数据格式
	 * @Date	2017年10月12日 下午6:13:01 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public DataGridVO toDataGrid(List<?> list) {
		if(null==list) return null;
		DataGridVO vo = new DataGridVO();
		vo.setRows(list);
		Long total = (long) list.size();
		vo.setTotal(total);
		return vo;
	}
	
	/**
	 * 前端分页对象转换成后端分页对象
	 * @Date	2017年10月13日 上午10:06:32 <br/>
	 * @author  zhangST
	 * @param searchBoxVO
	 * @return
	 */
	public PageParam getPageParam(SearchVO searchBoxVO) {
		Long pageNum = 1L;
		Long pageLimit = PAGE_LIMIT_DEFAUL;
		if(null==searchBoxVO) {
			return new PageParam(pageNum, pageLimit);
		}
		
		Long _pageNum = searchBoxVO.getPage();
		Long _pageLimit = searchBoxVO.getRows();
		
		if(null!=_pageNum && _pageNum>0) {
			pageNum = _pageNum;
		}
		if(null!=_pageLimit && _pageLimit>0) {
			pageLimit = _pageLimit;
		}
		return new PageParam(pageNum, pageLimit);
	}
	
	/**
	 * 前端分页对象转换成后端分页对象
	 * @Date	2017年12月12日 下午5:21:41 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	public PageParam getPageParam(Map<String, Object> paramMap) {
		Long pageNum = 1L;
		Long pageLimit = PAGE_LIMIT_DEFAUL;
		if(null==paramMap) {
			return new PageParam(pageNum, pageLimit);
		}
		
		String pageNumStr = (String) paramMap.get("page");
		String pageLimitStr = (String) paramMap.get("rows");
		
		Long _pageNum = ValidUtils.isBlank(pageNumStr)?pageNum:Long.parseLong(pageNumStr);
		Long _pageLimit = ValidUtils.isBlank(pageLimitStr)?pageLimit:Long.parseLong(pageLimitStr);
		
		if(null!=_pageNum && _pageNum>0) {
			pageNum = _pageNum;
		}
		if(null!=_pageLimit && _pageLimit>0) {
			pageLimit = _pageLimit;
		}
		return new PageParam(pageNum, pageLimit);
	}
	
}

