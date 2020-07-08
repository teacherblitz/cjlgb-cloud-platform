package com.cjlgb.design.common.core.handler;

import com.cjlgb.design.common.core.bean.ResultBean;
import com.cjlgb.design.common.core.constant.HttpStatus;
import com.cjlgb.design.common.core.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeoutException;

/**
 * @author WFT
 * @date 2020/5/6
 * description: 全局异常处理器
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获运行时异常
     * @param e java.lang.RuntimeException
     * @return com.cjlgb.design.common.core.bean.ResultBean<?>
     */
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public ResultBean<?> runTimeException(RuntimeException e){
        log.error(e.getMessage(),e);
        return ResultBean.build(HttpStatus.SERVER_ERROR,e.getMessage());
    }

    /**
     * 捕获业务异常
     * @param e com.cjlgb.design.common.core.exception.BizException
     * @return com.cjlgb.design.common.core.bean.ResultBean<?>
     */
    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public ResultBean<?> bizException(BizException e){
        return ResultBean.build(e.getCode(),e.getMessage());
    }

    /**
     * 捕获400异常
     * @param e java.lang.Exception
     * @return com.cjlgb.design.common.core.bean.ResultBean<?>
     */
    @ResponseBody
    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            MissingRequestHeaderException.class,
            HttpMessageNotReadableException.class
    })
    public ResultBean<?> assertionError(Exception e){
        return ResultBean.build(HttpStatus.BAD_REQUEST,e.getMessage());
    }

    /**
     * 捕获404异常（方法参数无效异常）
     * @param e org.springframework.web.bind.MethodArgumentNotValidException
     * @return com.cjlgb.design.common.core.bean.ResultBean<?>
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultBean<?> methodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResultBean.build(HttpStatus.BAD_REQUEST,e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 捕获请求超时异常
     * @param e java.lang.Exception
     * @return com.cjlgb.design.common.core.bean.ResultBean<?>
     */
    @ResponseBody
    @ExceptionHandler(value = TimeoutException.class)
    public ResultBean<?> timeoutException(Exception e){
        return ResultBean.build(HttpStatus.REQUEST_TIMEOUT,e.getMessage());
    }

}
