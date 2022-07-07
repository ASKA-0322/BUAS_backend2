package com.buas_team.buas_backend2.exception;

import com.buas_team.buas_backend2.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result<?> handler(RuntimeException e){
        log.error("运行时异常--------{}",e);
        return Result.error(400,e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value =  MethodArgumentNotValidException.class)
    public Result<?> handler(MethodArgumentNotValidException e){
        log.error("实体捕获异常  ：-----------------{}",e);
        BindingResult bindingException = e.getBindingResult();
        //多个异常返回第一个
        ObjectError objectError = bindingException.getAllErrors().stream().findFirst().get();
        return Result.error(400,objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result<?> handler(IllegalArgumentException e){
        log.error("Assert异常：--------------------{}",e);
        return Result.error(400,e.getMessage());
    }
}
