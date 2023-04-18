package com.yicj.hello.exception;

import lombok.Data;

/**
 * @author yicj
 * @date 2023年04月18日 16:59
 */
@Data
public class AppException extends RuntimeException{

    private String code ;

    public AppException(String code, String message){
        super(message);
        this.code = code ;
    }
}
