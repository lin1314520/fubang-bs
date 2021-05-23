package com.fubang.admin.service;

import com.fubang.admin.entity.SysUserLoginTemp;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 登录用户表中間表 服务类
 * @author jcl
 * @since 2021-05-23
 */
public interface SysUserLoginTempService extends IService<SysUserLoginTemp> {

    SysUserLoginTemp selectById(Integer id);
}