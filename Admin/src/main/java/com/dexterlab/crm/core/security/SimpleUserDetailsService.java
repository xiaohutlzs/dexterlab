package com.dexterlab.crm.core.security;

import com.dexterlab.crm.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Userエンティティ
 */
@Service("simpleUserDetailsService")
public class SimpleUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public SimpleUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(final String userName) {
        // emailでデータベースからユーザーエンティティを検索する
        return userService.findByUsername(userName)
                .map(SimpleLoginUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

}
