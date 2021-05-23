package com.fubang.admin.service.impl;

import com.fubang.admin.entity.SysUserLoginTemp;
import com.fubang.admin.mapper.SysUserLoginTempMapper;
import com.fubang.admin.service.SysUserLoginTempService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 登录用户表中間表 服务实现类
 * @author jcl
 * @since 2021-05-23
 */
@Service
@Transactional
public class SysUserLoginTempServiceImpl extends ServiceImpl<SysUserLoginTempMapper, SysUserLoginTemp> implements SysUserLoginTempService {

    @Autowired
    private SysUserLoginTempMapper sysUserLoginTempMapper;

    /**
    * @author jcl
    * @date 2021-05-23
    * @Description //TODO 查询单个的详细信息
    **/
    @Override
    public SysUserLoginTemp selectById(Integer id) {
            return sysUserLoginTempMapper.selectById(id);
            }

}