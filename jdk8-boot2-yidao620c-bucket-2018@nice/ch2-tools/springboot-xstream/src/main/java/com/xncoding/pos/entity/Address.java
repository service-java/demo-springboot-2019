package com.xncoding.pos.entity;

import lombok.Data;

/**
 * Description: 模块功能描述
 * <p>
 * User: luo0412
 * Date: 2020-03-31 16:45
 */
@Data
public class Address {
    private String area;
    private String city;
    private String state;
    private String country;
    private int pincode;


}
