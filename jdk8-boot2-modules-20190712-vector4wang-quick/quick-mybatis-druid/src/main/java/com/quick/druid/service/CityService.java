package com.quick.druid.service;

import com.quick.druid.entity.City;

/**
 * @Author: wangxc
 * @GitHub: https://github.com/vector4wang
 * @CSDN: http://blog.csdn.net/qqhjqs?viewmode=contents
 * @BLOG: http://blog.wangxc.club
 * @wxid: BMHJQS
 */
public interface CityService {

    City getCityById(int id);

    void saveTransaction();
}
