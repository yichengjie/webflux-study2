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

    private String id ;

    private String name ;

    private String address ;

    public UserVO(String name, String address){
       this.name = name ;
       this.address = address ;
    }

}
