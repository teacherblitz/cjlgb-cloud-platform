package com.cjlgb.design.common.oauth.feign;

import com.cjlgb.design.common.core.bean.ResultBean;
import com.cjlgb.design.common.oauth.entity.OauthUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author WFT
 * @date 2020/7/2
 * description:资源Api接口
 */
@FeignClient(value = "cjlgb-design-oauth",contextId = "oauthUserFeign")
public interface OauthUserFeign {

    /**
     * 获取用户信息
     * @param accessToken 访问令牌
     * @param flag 标识
     * @return com.cjlgb.design.common.oauth.entity.OauthUser
     */
    @GetMapping(value = "/user/getMyInfo")
    ResultBean<OauthUser> getMyInfo(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String accessToken,
            @RequestHeader(value = "flag") String flag);

}
