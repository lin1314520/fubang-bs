package com.fubang.admin.service;

import com.fubang.admin.entity.SysUserLogin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 登录用户表 服务类
 * @author jcl
 * @since 2021-05-18
 */
public interface SysUserLoginService extends IService<SysUserLogin> {

    SysUserLogin selectById(Integer id);

    List<SysUserLogin> listData(List<SysUserLogin> records);
}