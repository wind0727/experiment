package com.thinkgem.jeesite.modules.experiment.utils;

import java.util.List;



/**
 * 封装分页属性
 * @author zhaoqx
 *
 */
public class PageBean {
	private int currentPage;//当前页码
	private int pageSize;//每页显示的记录数
	private int total;//总记录数
	private List rows;//当前页需要展示的数据集合
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
}
