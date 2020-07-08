package com.cjlgb.design.common.core.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author WFT
 * @date 2020/5/6
 * description: 返回值类型
 */
@Getter
@NoArgsConstructor
public class ResultBean<Data> implements java.io.Serializable {

    /**
     * 状态码
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private Data data;

    private ResultBean(int code,String msg,Data data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultBean<T> build(int code,String msg){
        return build(code,msg,null);
    }

    public static <T> ResultBean<T> build(int code,String msg,T data){
        return new ResultBean<>(code,msg,data);
    }

}
