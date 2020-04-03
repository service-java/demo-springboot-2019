package com.xncoding.pos.entity;

import lombok.Data;

/**
 * Description: 模块功能描述
 * <p>
 * User: luo0412
 * Date: 2020-03-31 16:45
 */
@Data
public class Student {
    private StudentName name;
    private String firstName;
    private String lastName;
    private int rollNo;
    private String className;
    private Address address;

    public Student(String firstName, String lastName) {
        this.name = new StudentName(firstName, lastName);
    }
}
