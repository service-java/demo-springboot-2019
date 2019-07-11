package com.authority.service;

import com.authority.dao.IPager;
import com.authority.model.UserLoginLog;
import com.authority.utils.ListResult;

public interface LoginLogService extends BaseService<UserLoginLog,String> {

    public ListResult<UserLoginLog> list(IPager pager);
}
