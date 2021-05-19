package com.fubang.admin.service;

import com.fubang.admin.entity.SchoolCollegeClassMajor;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 学校-学院-班级-专业表 服务类
 * @author jcl
 * @since 2021-05-19
 */
public interface SchoolCollegeClassMajorService extends IService<SchoolCollegeClassMajor> {

    SchoolCollegeClassMajor selectById(Integer id);
}