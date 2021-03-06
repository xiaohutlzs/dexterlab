package com.dexterlab.crm.service.security;

import com.dexterlab.crm.core.utils.JSONResult;
import com.dexterlab.crm.core.utils.SpringContextUtils;
import com.dexterlab.crm.service.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class TokenAuthenticationService {
    private static final long EXPIRATION_TIME = 432_000_000;     // 5天
    private static final String SECRET = "P@ssw02d";            // JWT密码
    private static final String TOKEN_PREFIX = "Bearer";        // Token前缀
    private static final String HEADER_STRING = "Authorization";// 存放Token的Header Key
    private static RedisService template = SpringContextUtils.getBean(RedisService.class);
    public static void addAuthentication(HttpServletResponse response, Authentication auth) {
        // 生成JWT
        String JWT = Jwts.builder()
                // 保存权限（角色）
                .claim("authorities", "ROLE_ADMIN,AUTH_WRITE")
                // 用户名写入标题
                .setSubject(auth.getName())
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        // 将 JWT 写入 body
        try {
            template.set("token:"+JWT, JWT);
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getOutputStream().println(JSONResult.fillResultString(0, "", JWT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Authentication getAuthentication(HttpServletRequest request) throws IOException {
        // 从Header中拿到token\
        String token = template.get("token:"+(request.getHeader(HEADER_STRING)==null?"":request.getHeader(HEADER_STRING)).replace(TOKEN_PREFIX, "").trim());
        if (token != null) {
            // 解析 Token
            Claims claims = Jwts.parser()
                    // 验签
                    .setSigningKey(SECRET)
                    // 去掉 Bearer
                    .parseClaimsJws(token)
                    .getBody();
            // 拿用户名
            String user = claims.getSubject();

            // 得到 权限（角色）
            List<GrantedAuthority> authorities =  AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
            if(StringUtils.isEmpty(user))return null;
            // 返回验证令牌
//            list.stream().filter(permission -> permission.getCode().equals())
            return new UsernamePasswordAuthenticationToken(user, null, authorities);
        }
        return null;
    }
}
