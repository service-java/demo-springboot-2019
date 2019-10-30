package com.xncoding.pos.entity;

import lombok.Data;

import java.util.Date;

/**
 * Description: 模块功能描述
 * <p>
 * User: luo0412
 * Date: 2019-10-29 10:59
 */
@Data
public class DozerBaseEntity {
    /**
     * 主键
     */
    private String id;

    /**
     * 创建时间
     */
    private Date createTime;
}
