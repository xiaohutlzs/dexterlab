package com.dexterlab.crm.service;

import com.baomidou.mybatisplus.service.IService;
import com.dexterlab.crm.domain.entity.User;

import java.util.List;

public interface UserService  extends IService<User> {
    List<User> findAllUser();
}
