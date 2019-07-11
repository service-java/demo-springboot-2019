package com.authority.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.authority.dao.IPager;

public class Pager implements IPager {

	private Integer page;
	private String sort;
	private String order;
	private Integer rows;
	private Map params = new HashMap();

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setParams(Map params) {
		this.params = params;
	}

	@Override
	public Integer getLimit() {
		return rows; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public Integer getPage() {
		return page; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public String getSort() {
		return sort; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public String getOrder() {
		return order; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public Map getParams() {
		return params; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public Integer getStart() {
		if (getLimit() != null && getPage() != null) {
			return (getPage() - 1) * getLimit();
		}
		return 0; // To change body of implemented methods use File | Settings |
					// File Templates.
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

}
