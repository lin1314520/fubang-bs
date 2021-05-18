package com.fubang.admin.service.impl;

import com.fubang.admin.entity.SysUserLogin;
import com.fubang.admin.mapper.SysUserLoginMapper;
import com.fubang.admin.service.SysUserLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 登录用户表 服务实现类
 * @author jcl
 * @since 2021-05-18
 */
@Service
@Transactional
public class SysUserLoginServiceImpl extends ServiceImpl<SysUserLoginMapper, SysUserLogin> implements SysUserLoginService {

    @Autowired
    private SysUserLoginMapper sysUserLoginMapper;

    /**
    * @author jcl
    * @date 2021-05-18
    * @Description //TODO 查询单个的详细信息
    **/
    @Override
    public SysUserLogin selectById(Integer id) {
            return sysUserLoginMapper.selectById(id);
            }

}