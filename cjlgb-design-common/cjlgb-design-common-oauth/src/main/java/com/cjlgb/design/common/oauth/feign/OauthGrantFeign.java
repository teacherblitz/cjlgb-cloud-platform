package com.cjlgb.design.common.oauth.feign;

import com.cjlgb.design.common.core.bean.ResultBean;
import com.cjlgb.design.common.oauth.dto.OauthParameter;
import com.cjlgb.design.common.security.bean.AccessToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author WFT
 * @date 2020/6/14
 * description:Oauth2认证接口
 */
@FeignClient(value = "cjlgb-design-oauth",contextId = "oauthGrantFeign")
public interface OauthGrantFeign {

    /**
     * 获取访问令牌
     * @param parameter 请求参数
     * @param basicAuth base编码（客户端Id:客户端密钥）
     * @param flag 标记
     * @return com.cjlgb.design.common.security.bean.AccessToken
     */
    @PostMapping(value = "/grant/token")
    ResultBean<AccessToken> getToken(
            @RequestBody OauthParameter parameter,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String basicAuth,
            @RequestHeader(value = "flag") String flag);

}
