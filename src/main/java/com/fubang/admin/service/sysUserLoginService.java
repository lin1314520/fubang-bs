package com.fubang.admin.service;

import com.fubang.admin.entity.sysUserLogin;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 登录用户表 服务类
 * @author jcl
 * @since 2021-05-18
 */
public interface sysUserLoginService extends IService<sysUserLogin> {

    sysUserLogin selectById(Integer id);
}