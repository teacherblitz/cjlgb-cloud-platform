package com.cjlgb.design.common.security.handler;

import com.cjlgb.design.common.security.annotation.PreAuthorize;
import com.cjlgb.design.common.security.bean.Authentication;
import com.cjlgb.design.common.security.context.SecurityContextHolder;
import com.cjlgb.design.common.security.exception.AccessDeniedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

/**
 * @author WFT
 * @date 2020/5/6
 * description: 授权程序处理器
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizeAspectHandler {

    @Before(value = "@annotation(preAuthorize)")
    public void before(PreAuthorize preAuthorize){
        //  获取当前用户认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //  权限校验
        if (preAuthorize.value().length > 0){
            boolean result = authentication.getAuthorities().stream()
                    .filter(StringUtils::hasText)
                    .anyMatch(x -> PatternMatchUtils.simpleMatch(preAuthorize.value(), x));
            if (!result) {
                throw AccessDeniedException.getInstance();
            }
        }
    }
}
