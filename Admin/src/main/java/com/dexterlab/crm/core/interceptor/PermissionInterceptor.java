package com.dexterlab.crm.core.interceptor;

import com.dexterlab.crm.core.annotation.ControllerAnnotation;
import com.dexterlab.crm.core.utils.AuthUtil;
import com.dexterlab.crm.service.RedisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;

public class PermissionInterceptor  implements HandlerInterceptor {
    private RedisService template;

    public PermissionInterceptor(RedisService template) {
        this.template = template;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        ControllerAnnotation annotation = method.getMethodAnnotation(ControllerAnnotation.class);
        if(annotation == null) throw new RuntimeException("不合法的地址");
        ObjectMapper objectMapper = new ObjectMapper();
        String permissions = template.get(AuthUtil.getCurrentUser().getName() + ":permission");
        if(StringUtils.isEmpty(permissions))throw new RuntimeException("您没有访问权限");//没有任何权限
        List<LinkedHashMap> list = objectMapper.readValue(permissions, List.class);
        if(list.stream().filter(permission -> permission.getOrDefault("code","").equals(annotation.permissionCode())).count()<1){
            throw new RuntimeException("您没有访问权限");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}