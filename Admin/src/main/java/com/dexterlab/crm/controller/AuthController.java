package com.dexterlab.crm.controller;

import com.dexterlab.crm.core.exception.UsernameIsExitedException;
import com.dexterlab.crm.dao.UserMapper;
import com.dexterlab.crm.domain.entity.User;
import com.dexterlab.crm.service.UserService;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthController {

    private final UserMapper userMapper;
    private final UserService userService;

    public AuthController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    /**
     * 注册用户 默认开启白名单
     * @param user
     */
    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        User bizUser = userMapper.findByUsername(user.getUserName());
        if(null != bizUser){
            throw new UsernameIsExitedException("用户已经存在");
        }
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword()).getBytes()));
        return userService.insert(user)?user:null;
    }
}
