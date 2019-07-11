/*
 * 
 */
package com.authority.utils;

import java.util.ArrayList;
import java.util.List;
public class ListResult<T> {
    private Object count =0;
    private List<T> dataList = new ArrayList<T>();

    public Object getCount() {
        return count;
    }

    public void setCount(Object count) {
        if(count != null)
        {
            this.count = count;
        }
    }
    public void setDataList(List<T> dataList) {
    	if(dataList != null)
        {
            this.dataList = dataList;
        }
	}

	public List<T> getDataList() {
        return dataList;
    }
}
