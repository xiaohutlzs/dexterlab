package com.dexterlab.crm.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dexterlab.crm.dao.UserMapper;
import com.dexterlab.crm.domain.entity.User;
import com.dexterlab.crm.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<User> findAllUser() {
        return baseMapper.findAllUser();
    }

    @Override
    public Optional<User> findByUsername(String userName) {
        return Optional.ofNullable(baseMapper.findByUsername(userName));
    }

}