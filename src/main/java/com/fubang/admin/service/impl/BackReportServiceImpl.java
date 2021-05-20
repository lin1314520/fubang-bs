package com.fubang.admin.service.impl;

import com.fubang.admin.entity.BackReport;
import com.fubang.admin.mapper.BackReportMapper;
import com.fubang.admin.service.BackReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 信息表 服务实现类
 * @author jcl
 * @since 2021-05-20
 */
@Service
@Transactional
public class BackReportServiceImpl extends ServiceImpl<BackReportMapper, BackReport> implements BackReportService {

    @Autowired
    private BackReportMapper backReportMapper;

    /**
    * @author jcl
    * @date 2021-05-20
    * @Description //TODO 查询单个的详细信息
    **/
    @Override
    public BackReport selectById(Integer id) {
            return backReportMapper.selectById(id);
            }

}