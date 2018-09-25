package com.dexterlab.crm.domain.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PermissionBean {
    private final String code;
    private final int sort;
    private final String action;
    private final String className;
    private final String method;
    private final String methodType;
    @JsonCreator
    public PermissionBean(@JsonProperty("code") String code,
                          @JsonProperty("sort") int sort,
                          @JsonProperty("action") String action,
                          @JsonProperty("className") String className,
                          @JsonProperty("method") String method,
                          @JsonProperty("methodType") String methodType) {
        this.code = code;
        this.sort = sort;
        this.action = action;
        this.className = className;
        this.method = method;
        this.methodType = methodType;
    }

    public String getCode() {
        return code;
    }

    public int getSort() {
        return sort;
    }

    public String getAction() {
        return action;
    }

    public String getClassName() {
        return className;
    }

    public String getMethod() {
        return method;
    }

    public String getMethodType() {
        return methodType;
    }
}
