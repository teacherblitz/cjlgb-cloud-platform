package com.cjlgb.design.common.core.constant;

/**
 * @author WFT
 * @date 2019/5/18
 * description:HttpStatus
 */
public interface HttpStatus {

    /**
     * 请求成功
     */
    int OK = 200;

    /**
     * 请求未经授权（需要用户登陆）
     */
    int UNAUTHORIZED = 401;

    /**
     * 客户端请求有语法错误
     */
    int BAD_REQUEST = 400;

    /**
     * 服务器已经理解请求，但是拒绝执行它（权限不足）
     */
    int FORBIDDEN = 403;

    /**
     * 客户端授权证书已失效（需要重新登陆）
     */
    int CERT_OVERDUE = 407;

    /**
     * 请求失败,请求所希望得到的资源未被在服务器上发现
     */
    int NOT_FOUND = 404;

    /**
     * 请求超时
     */
    int REQUEST_TIMEOUT = 408;

    /**
     * 服务器遇到了一个未曾预料的状况,导致了它无法完成对请求的处理
     */
    int SERVER_ERROR = 500;
}
