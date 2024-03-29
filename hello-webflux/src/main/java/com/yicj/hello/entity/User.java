package com.yicj.hello.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: yicj
 * @date: 2023/4/16 19:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
