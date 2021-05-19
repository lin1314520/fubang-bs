package com.fubang.admin.service;

import com.fubang.admin.entity.SchoolUnionUser;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 学校和人员关系表 服务类
 * @author jcl
 * @since 2021-05-19
 */
public interface SchoolUnionUserService extends IService<SchoolUnionUser> {

    SchoolUnionUser selectById(Integer id);
}