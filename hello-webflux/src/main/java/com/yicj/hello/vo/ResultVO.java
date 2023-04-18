package com.yicj.hello.vo;

import lombok.Data;

/**
 * @author yicj
 * @date 2023年04月18日 16:37
 */
@Data
public class ResultVO<T> {

    private String code ;

    private T data ;

    public static <T> ResultVO<T> success(T data){
        ResultVO<T> vo = new ResultVO<>() ;
        vo.setCode("200");
        vo.setData(data);
        return vo ;
    }

    public static ResultVO<String> fail(String message){
       return fail("-1", message) ;
    }

    public static ResultVO<String> fail(String code, String message){
        ResultVO<String> vo = new ResultVO<>() ;
        vo.setCode(code);
        vo.setData(message);
        return vo ;
    }
}
