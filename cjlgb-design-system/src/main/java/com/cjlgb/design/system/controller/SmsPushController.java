package com.cjlgb.design.system.controller;

import com.cjlgb.design.common.system.dto.SmsPushDto;
import com.cjlgb.design.common.system.entity.SmsProvider;
import com.cjlgb.design.common.system.entity.SmsTemplate;
import com.cjlgb.design.system.service.SmsFactory;
import com.cjlgb.design.system.service.SmsProviderService;
import com.cjlgb.design.system.service.SmsTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author WFT
 * @date 2020/5/14
 * description:短信推送控制器
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/sms")
public class SmsPushController {

    private final SmsProviderService providerService;
    private final SmsFactory smsFactory;
    private final SmsTemplateService templateService;
    private final RedisTemplate<String,String> redisTemplate;

    /**
     * 短信验证码缓存中的Key
     */
    private final static String SMS_CODE_KEY = "cjLgb:sms:code:%s:%s";

    /**
     * 短信验证码有效期
     */
    private final static long SMS_CODE_TIMEOUT = 1000 * 60 * 5;

    /**
     * 异步发送短信验证码
     * @param dto com.cjlgb.design.common.system.dto.SmsPushDto
     */
    @PostMapping(value = "/send/code")
    public void sendCode(@RequestBody @Validated SmsPushDto dto){
        //  获取短信模板
        SmsTemplate template = this.templateService.getTemplateByType(dto.getType());
        Assert.notNull(template,"短信模板编号不存在");
        //  TODO 生成6位数字验证码
        String code = "123456";
        //  短信文本
        String context = template.getSign() + String.format(template.getTemplate(),code);
        //  获取可用的短信服务商
        SmsProvider provider = this.providerService.getUsableProvider();
        //  获取短信服务
        this.smsFactory.getInterface(provider.getProviderName()).push(provider,dto.getMobile(),context);
        //  将验证码保存到Redis缓存中,并设置有效期
        String cacheKey = String.format(SMS_CODE_KEY,dto.getType(),dto.getMobile());
        this.redisTemplate.opsForValue().set(cacheKey,code,SMS_CODE_TIMEOUT, TimeUnit.MILLISECONDS);
    }


    /**
     * 短信验证码校验
     * @param mobile 手机号码
     * @param type 短信类型
     * @param code 验证码
     */
    @GetMapping(value = "/check/code/{mobile}/{type}/{code}")
    public void checkCode(@PathVariable(value = "mobile") String mobile,
                             @PathVariable(value = "type") Integer type,
                             @PathVariable(value = "code") String code){
        //  获取缓存中的验证码
        String cacheKey = String.format(SMS_CODE_KEY,type,mobile);
        String cacheCode = this.redisTemplate.opsForValue().get(cacheKey);
        //  验证码校验
        Assert.notNull(cacheCode,"请先获取验证码!");
        Assert.isTrue(cacheCode.equalsIgnoreCase(code),"验证码有误!");
        //  删除验证码缓存
        this.redisTemplate.delete(cacheKey);
    }

}
