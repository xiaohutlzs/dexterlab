package com.dexterlab.crm.service;

import com.baomidou.mybatisplus.service.IService;
import com.dexterlab.crm.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService  extends IService<User> {
    List<User> findAllUser();

    Optional<User> findByUsername(String userName);
}
