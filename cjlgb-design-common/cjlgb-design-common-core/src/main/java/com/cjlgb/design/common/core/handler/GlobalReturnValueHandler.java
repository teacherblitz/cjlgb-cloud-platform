package com.cjlgb.design.common.core.handler;

import com.cjlgb.design.common.core.bean.ResultBean;
import com.cjlgb.design.common.core.constant.HttpStatus;
import com.cjlgb.design.common.core.util.JsonUtils;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author WFT
 * @date 2020/5/6
 * description: 全局返回值处理器
 */
public class GlobalReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(@NonNull MethodParameter returnType) {
        Class<?> clazz = returnType.getContainingClass();
        returnType.getMethodAnnotation(ResponseBody.class);

        return clazz.isAnnotationPresent(RestController.class)
                || clazz.isAnnotationPresent(ResponseBody.class)
                || returnType.getMethodAnnotation(ResponseBody.class) != null;
    }

    @Override
    public void handleReturnValue(Object result,
                                  @NonNull MethodParameter returnType,
                                  @NonNull ModelAndViewContainer mavContainer,
                                  @NonNull NativeWebRequest webRequest) throws Exception {
        // 标识请求是否已经在该方法内完成处理
        mavContainer.setRequestHandled(true);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        if (null != response) {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(JsonUtils.toJson(ResultBean.build(HttpStatus.OK,null,result)));
            printWriter.close();
        }
    }
}
