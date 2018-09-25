package com.dexterlab.crm.core.config;

import com.dexterlab.crm.core.filter.JWTAuthenticationFilter;
import com.dexterlab.crm.core.filter.JWTLoginFilter;
import com.dexterlab.crm.service.AccountService;
import com.dexterlab.crm.service.security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountService accountService;

    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf验证
        http.csrf().disable()
                // 对请求进行认证
                .authorizeRequests()
                // 所有OPTIONS请求全部放行
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 所有 / 的所有请求 都放行
                .antMatchers("/").permitAll()
                // 所有 /login 的POST请求 都放行
                .antMatchers(HttpMethod.POST, "/login").permitAll()
// 权限检查
                .antMatchers(HttpMethod.POST, "/api/admin/account").permitAll()
//                .antMatchers("/account").hasAuthority("AUTH_WRITE")
                // 角色检查
//                .antMatchers("/account").hasAnyRole("ADMIN")
//                .antMatchers("/api/admin").hasAnyRole("ADMIN")
                // 对Rest请求需要身份认证, 放行OPTIONS
            .antMatchers(HttpMethod.POST).authenticated()
            .antMatchers(HttpMethod.PUT).authenticated()
            .antMatchers(HttpMethod.DELETE).authenticated()
            .antMatchers(HttpMethod.GET).authenticated()
                .and().logout().logoutUrl("/logout")
                .and()
                // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
        auth.authenticationProvider(new CustomAuthenticationProvider(accountService));

    }
}

