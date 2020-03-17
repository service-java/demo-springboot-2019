package com.xncoding.pos.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xncoding.pos.entity.TUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description: 模块功能描述
 *
 * User: luo0412
 * Date: 2020-03-17 15:54
 */
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {
    int deleteByPrimaryKey(Integer id);

    Integer insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
}
