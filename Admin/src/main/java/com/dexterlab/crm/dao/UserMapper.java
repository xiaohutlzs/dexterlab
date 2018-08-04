package com.dexterlab.crm.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dexterlab.crm.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {
    List<User> findAllUser();
}
