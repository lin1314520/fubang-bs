package com.fubang.admin.service.impl;

import com.fubang.admin.entity.UserReport;
import com.fubang.admin.mapper.UserReportMapper;
import com.fubang.admin.service.UserReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户提交报告表 服务实现类
 * @author jcl
 * @since 2021-05-19
 */
@Service
@Transactional
public class UserReportServiceImpl extends ServiceImpl<UserReportMapper, UserReport> implements UserReportService {

    @Autowired
    private UserReportMapper userReportMapper;

    /**
    * @author jcl
    * @date 2021-05-19
    * @Description //TODO 查询单个的详细信息
    **/
    @Override
    public UserReport selectById(Integer id) {
            return userReportMapper.selectById(id);
            }

}