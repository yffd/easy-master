package com.yffd.easy.common.core.page;

import java.io.Serializable;

/**
 * @Description  分页信息基类对象.
 * @Date		 2017年12月6日 下午3:17:29 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PageParamBak implements Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 6844568491810672063L;

	/** 默认为第一页 */
    public static final Long DEFAULT_PAGE_NUM = 1L;
    /** 默认每页记录数(15) */
    public static final Long DEFAULT_NUM_PER_PAGE = 15L;
    /** 最大每页记录数(100) */
    public static final Long MAX_PAGE_SIZE = 100L;
    
    private Long pageNum; // 当前页数
    private Long pageLimit; // 每页记录数
    private Long pageStartRow; // 每页开始行号
    private Long pageEndRow; // 每页结束行号
    private Long pageTotal; // 总页数
    private Long totalRecord; // 总记录数
    
    /** 是否进行自动count计算总条数，默认为自动计算：true:是，false:否 */
    private boolean autoCount = true;
    
	public PageParamBak() {
	}

	public PageParamBak(Long pageNum, Long pageLimit) {
		pageNum = this.countPageNum(pageNum);
		pageLimit = this.countPageLimit(pageLimit);
		this.pageNum = pageNum;
		this.pageLimit = pageLimit;
		this.pageStartRow = this.countPageStartRow(pageNum, pageLimit);
		this.pageEndRow = this.countPageEndRow(pageNum, pageLimit);
		
	}
    
	public Long getPageNum() {
		return pageNum;
	}

	public void setPageNum(Long pageNum) {
		this.pageNum = pageNum;
	}

	public Long getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(Long pageLimit) {
		this.pageLimit = pageLimit;
	}

	public Long getPageStartRow() {
		return pageStartRow;
	}

	public void setPageStartRow(Long pageStartRow) {
		this.pageStartRow = pageStartRow;
	}

	public Long getPageEndRow() {
		return pageEndRow;
	}

	public void setPageEndRow(Long pageEndRow) {
		this.pageEndRow = pageEndRow;
	}

	public Long getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Long pageTotal) {
		this.pageTotal = pageTotal;
	}

	public Long getTotalRecord() {
		return totalRecord;
	}

	/**
	 * 设置总记录数，并同时设置总分页数
	 * @Date	2017年12月7日 下午2:22:54 <br/>
	 * @author  zhangST
	 * @param totalRecord
	 */
	public void setTotalRecord(Long totalRecord) {
		Long pageTotal = this.countPageTotal(totalRecord, this.pageLimit); // 根据记录总条数和每页数，计算总页数
		this.pageTotal = pageTotal;
		this.totalRecord = totalRecord;
	}

	public boolean isAutoCount() {
		return autoCount;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}

	/************************************************************************************************************/
	/************************************************************************************************************/
	
	/**
     * 计算当前页码号
     * 1、先根据总记录数totalRecord和每页记录数pageLimit，计算出总页数pageTotal.<br/>
     * 2、判断页面提交过来的当前页数pageNum是否大于总页数pageTotal，大于则返回pageTotal.<br/>
     * 3、判断pageNum是否小于1，小于则返回1.<br/>
     * 4、其它则直接返回pageNum .
     * @Date	2017年12月6日 下午3:47:24 <br/>
     * @author  zhangST
     * @param pageNum		输入的当前页数
     * @param pageLimit		每页记录数
     * @param totalRecord	总记录数
     * @return
     */
    public Long countPageNum(Long pageNum, Long pageLimit, Long totalRecord) {
    	Long pageTotal = this.countPageTotal(totalRecord, pageLimit); // 最大页数
        if (pageNum > pageTotal) {
            // 如果页面提交过来的页数大于总页数，则将当前页设为总页数
            // 此时要求pageTotal要大于获等于1
            if (pageTotal < 1) {
                return 1L;
            }
            return pageTotal;
        } else if (pageNum < 1) {
            return 1L; // 当前页不能小于1（避免页面输入不正确值）
        } else {
            return pageNum;
        }
    }
    
	/**
	 * 计算开始行号
	 * @Date	2017年12月6日 下午3:27:29 <br/>
	 * @author  zhangST
	 * @param pageNum
	 * @param pageLimit
	 * @return
	 */
	public Long countPageStartRow(Long pageNum, Long pageLimit) {
        return (pageNum - 1) * pageLimit;
    }
    
    /**
     * 计算结束行号
     * @Date	2017年12月6日 下午3:28:13 <br/>
     * @author  zhangST
     * @param pageNum
     * @param pageLimit
     * @return
     */
    public Long countPageEndRow(Long pageNum, Long pageLimit) {
        return pageNum * pageLimit;
    }
    
    /**
     * 计算总页数
     * @Date	2017年12月6日 下午3:29:16 <br/>
     * @author  zhangST
     * @param totalRecord	总记录数
     * @param pageLimit		每页记录数
     * @return				总页数
     */
    private Long countPageTotal(Long totalRecord, Long pageLimit) {
        if (totalRecord % pageLimit == 0) {// 刚好整除
            return totalRecord / pageLimit;
        } else { // 不能整除则总页数为：商 + 1
            return totalRecord / pageLimit + 1;
        }
    }

    /**
     * 计算当前页码号，如果小于1，则返回1，否则返回当前值.
     * @Date	2017年12月6日 下午3:30:43 <br/>
     * @author  zhangST
     * @param pageNum	输入的当前页数
     * @return
     */
    public Long countPageNum(Long pageNum) {
        return pageNum < 1 ? 1 : pageNum;
    }
    
    /**
     * 计算每页条数
     * 1、当页面输入的每页记录数pageLimit大于允许的最大每页记录数MAX_PAGE_SIZE时，返回MAX_PAGE_SIZE.
     * 2、如果pageLimit小于1，则返回默认的每页记录数DEFAULT_PAGE_SIZE.
     * @Date	2017年12月6日 下午3:39:34 <br/>
     * @author  zhangST
     * @param pageLimit		输入的每页记录数
     * @return
     */
    public Long countPageLimit(Long pageLimit) {
    	if (pageLimit > PageParamBak.MAX_PAGE_SIZE) {
            return PageParamBak.MAX_PAGE_SIZE;
        } else if (pageLimit < 1) {
            return PageParamBak.DEFAULT_NUM_PER_PAGE;
        } else {
            return pageLimit;
        }
    }
    
    
}

