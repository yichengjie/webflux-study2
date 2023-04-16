package com.yicj.hello.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: yicj
 * @date: 2023/4/16 19:20
 */
@Data
public class User implements Serializable {

    /**
     * 姓名
     */
    private String name;
    /**
     * 描述
     */
    private String desc;
}
