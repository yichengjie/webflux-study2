package com.yicj.hello.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yicj
 * @date 2023年04月18日 14:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    /**
     * 姓名
     */
    private String name;
    /**
     * 描述
     */
    private String desc;
}
