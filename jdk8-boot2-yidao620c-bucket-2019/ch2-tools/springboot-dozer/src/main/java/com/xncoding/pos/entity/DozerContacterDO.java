package com.xncoding.pos.entity;

import lombok.Data;

/**
 * Description: 模块功能描述
 * <p>
 * User: luo0412
 * Date: 2019-10-29 10:59
 */
@Data
public class DozerContacterDO extends DozerBaseEntity {

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（0，女；1，男）
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age ;

    /**
     * 电话
     */
    private  String phone;

    /**
     * 地址
     */
    private String location;

    /**
     * 删除（0，未删除；1，删除）
     */
    private String dflag ;
}

