package com.dexterlab.crm.service.security;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dexterlab.crm.core.utils.SpringContextUtils;
import com.dexterlab.crm.domain.bean.PermissionBean;
import com.dexterlab.crm.domain.entity.Account;
import com.dexterlab.crm.service.AccountService;
import com.dexterlab.crm.service.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义身份认证验证组件
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final AccountService accountService;

    public CustomAuthenticationProvider(AccountService accountService) {
        this.accountService = accountService;
    }

    private RedisService template = SpringContextUtils.getBean(RedisService.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        EntityWrapper<Account> ew = new EntityWrapper<Account>();
//        ew.setEntity(new Account());
        ew.where("account={0}", name);
        Account account = accountService.selectOne(ew);
        if (account == null) {
            throw new BadCredentialsException("账号错误~");
        }
        // 认证逻辑
        if (passwordEncoder().matches(password, account.getPassword())) {

            // 这里设置权限和角色
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//            authorities.add( new GrantedAuthorityImpl("ROLE_ADMIN") );
            authorities.add(new GrantedAuthorityImpl(account.getType().name()));
            ObjectMapper objectMapper = new ObjectMapper();
            List<PermissionBean> permissionList = accountService.getMyPermission(account.getRoleId()).stream().map(permission -> new PermissionBean(permission.getCode(), permission.getSort(), permission.getAction(), permission.getClassName(), permission.getMethod(), permission.getMethodType())).collect(Collectors.toList());
            try {
                template.set(account.getAccount() + ":permission", objectMapper.writeValueAsString(permissionList));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            // 生成令牌
            return new UsernamePasswordAuthenticationToken(name, password, authorities);
        } else {
            throw new BadCredentialsException("密码错误~");
        }
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    /**
     * 密码加密
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}