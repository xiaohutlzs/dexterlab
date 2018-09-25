package com.dexterlab.crm;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dexterlab.crm.core.annotation.ControllerAnnotation;
import com.dexterlab.crm.dao.PermissionMapper;
import com.dexterlab.crm.domain.bean.BooleanType;
import com.dexterlab.crm.domain.entity.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.time.LocalDateTime;
import java.util.Map;

@Component
public class AppApplicationRunner implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${permission.refresh}")
    private boolean refresh = false;
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private PermissionMapper mapper;
    @Override
    public void run(ApplicationArguments applicationArguments)
    {
        if(!refresh)return;
        Permission permission = new Permission();
        permission.setCreator(Long.MIN_VALUE);
        EntityWrapper ew=new EntityWrapper();
        ew.setEntity(permission);
        mapper.delete(ew);
        permission.setModifyer(Long.MIN_VALUE);
        permission.setCreateTime(LocalDateTime.now());
        permission.setModifyTime(LocalDateTime.now());
        permission.setDeleted(BooleanType.FALSE.ordinal());
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            ControllerAnnotation annotation = method.getMethodAnnotation(ControllerAnnotation.class);
            if(annotation == null) continue;
            permission.setCode(annotation.permissionCode());
            PatternsRequestCondition p = info.getPatternsCondition();

            for (String url : p.getPatterns()) {
                permission.setAction(url);
                break;
            }
            permission.setClassName(method.getMethod().getDeclaringClass().getName());
            permission.setMethod(method.getMethod().getName());
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            String type = methodsCondition.toString();
            if (type != null && type.startsWith("[") && type.endsWith("]")) {
                type = type.substring(1, type.length() - 1);
                permission.setMethodType(type);
            }
            permission.setSort(annotation.sort());
            mapper.insert(permission);
        }
    }
}