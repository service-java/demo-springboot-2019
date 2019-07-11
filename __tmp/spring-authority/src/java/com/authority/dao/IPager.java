package com.authority.dao;

import java.util.Map;

public interface IPager {

	public abstract Integer getLimit();

	public Integer getPage();

	public String getSort();

	public String getOrder();

	public Map getParams();

	public Integer getStart();

}
