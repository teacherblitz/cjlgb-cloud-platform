package com.cjlgb.design.system.service.impl;

import com.cjlgb.design.common.core.util.JsonUtils;
import com.cjlgb.design.common.system.entity.SmsProvider;
import com.cjlgb.design.system.exception.SmsPushException;
import com.cjlgb.design.system.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

/**
 * @author WFT
 * @date 2020/5/13
 * description:云片网短信推送接口实现类
 */
@Service
@RequiredArgsConstructor
public class YpwSmsServiceImpl implements SmsService {

    private final RestTemplate restTemplate;

    /**
     * 请求头
     */
    private final static HttpHeaders HTTP_HEADERS = new HttpHeaders();

    static {
        HTTP_HEADERS.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HTTP_HEADERS.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    @Override
    public String getProviderName() {
        return "YPW";
    }

    @Override
    public void push(SmsProvider provider, String mobile, String context) throws SmsPushException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("apikey", provider.getAppPrivateKey());
        params.add("mobile", mobile);
        params.add("text", context);
        String apiUrl = "https://sms.yunpian.com/v2/sms/single_send.json";
        try {
            //  发送短信
            this.restTemplate.postForObject(apiUrl, new HttpEntity<>(params, HTTP_HEADERS), String.class);
        } catch (HttpClientErrorException.BadRequest badRequest){
            //  参数错误
            Map resultMap = JsonUtils.toBean(badRequest.getResponseBodyAsString(),Map.class);
            throw new SmsPushException(resultMap.get("detail").toString());
        } catch (HttpClientErrorException e){
            //  其他错误
            throw new SmsPushException(e.getMessage());
        }
    }


}
