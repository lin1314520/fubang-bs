package com.fubang.admin.service;

import com.fubang.admin.entity.UserReport;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 用户提交报告表 服务类
 * @author jcl
 * @since 2021-05-19
 */
public interface UserReportService extends IService<UserReport> {

    UserReport selectById(Integer id);
}