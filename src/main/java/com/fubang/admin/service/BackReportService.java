package com.fubang.admin.service;

import com.fubang.admin.entity.BackReport;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 信息表 服务类
 * @author jcl
 * @since 2021-05-20
 */
public interface BackReportService extends IService<BackReport> {

    BackReport selectById(Integer id);
}