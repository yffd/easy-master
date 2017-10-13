package org.yffd.easy.common.core.view.support;

import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;
import org.yffd.easy.common.core.view.vo.DataGridVO;
import org.yffd.easy.common.core.view.vo.SearchBoxVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月12日 下午6:05:05 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class ViewModelSupport {

	/**
	 * 将后台分页结果转换成EasyUI分页数据格式
	 * @Date	2017年10月12日 下午6:13:01 <br/>
	 * @author  zhangST
	 * @param pageResult
	 * @return
	 */
	public DataGridVO toDataGrid(PageResult<?> pageResult) {
		DataGridVO vo = new DataGridVO();
		vo.setRows(pageResult.getRecordList());
		Long total = (long) pageResult.getPageInfo().getTotalRecord();
		vo.setTotal(total);
		return vo;
	}
	
	/**
	 * 前端分页对象转换成后台分页对象
	 * @Date	2017年10月13日 上午10:06:32 <br/>
	 * @author  zhangST
	 * @param paginationVO
	 * @return
	 */
	public PageParam getPageParam(SearchBoxVO searchBoxVO) {
		Long currentPage = 1L;
		Long numPerPage = PageParam.DEFAULT_NUM_PER_PAGE;
		if(null==searchBoxVO) {
			return new PageParam(currentPage, numPerPage);
		}
		
		Long _currentPage = searchBoxVO.getPage();
		Long _numPerPage = searchBoxVO.getRows();
		
		if(null!=_currentPage && _currentPage>0) {
			currentPage = _currentPage;
		}
		if(null!=_numPerPage && _numPerPage>0) {
			numPerPage = _numPerPage;
		}
		return new PageParam(currentPage, numPerPage);
	}
}

