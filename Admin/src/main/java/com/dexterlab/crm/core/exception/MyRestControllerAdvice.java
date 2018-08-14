package com.dexterlab.crm.core.exception;

import com.dexterlab.crm.core.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyRestControllerAdvice {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e){
        // http请求异常
        if (e instanceof HttpMessageConversionException){
            log.error("【Bad Request】",e.getMessage(),e);
            return Result.error("Bad Request");
        }
        /*if (e instanceof AuthenticationException){
            log.error("【无操作权限Shiro】",e.getMessage(),e);
            return Result.error(Result.CODE_1403,"未授权：操作失败，请联系管理员授权");
        }*/
        if (e instanceof DataAccessException){
            log.error("【数据库异常：spring-dao】",e.getMessage(),e);
            return Result.error("DB异常，请联系管理员");
        }
        log.error("【其他RuntimeException】",e.getMessage(),e);
        return Result.error();
    }


    /** 全局异常捕捉处理 */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        if (e instanceof HttpRequestMethodNotSupportedException){
            return Result.error("不支持的请求方式/"+e.getMessage());
        }
        log.error("【未知异常】："+e.getMessage(), e);
        return Result.error();
    }
}