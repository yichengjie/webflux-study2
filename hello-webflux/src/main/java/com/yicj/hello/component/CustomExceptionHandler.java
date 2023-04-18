package com.yicj.hello.component;

import com.yicj.hello.exception.AppException;
import com.yicj.hello.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author yicj
 * @date 2023年04月18日 17:42
 */
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public ResultVO<String> handleCustomException(Exception ex){
        log.error("捕获异常：", ex);
        if (ex instanceof AppException){
            AppException appException = (AppException) ex;
            return ResultVO.fail(appException.getCode(), ex.getMessage());
        }
        return ResultVO.fail("系统异常");
    }
}
