package com.dexterlab.crm.controller.admin;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author xiaohu
 * @since 2018-09-19
 */
@RestController
@RequestMapping("/api/admin/permission")
public class PermissionController {

    @PostMapping
    public void add(){

    }
}

