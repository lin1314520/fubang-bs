package com.fubang.admin.service.impl;

import com.fubang.admin.entity.SchoolCollegeClassMajor;
import com.fubang.admin.mapper.SchoolCollegeClassMajorMapper;
import com.fubang.admin.service.SchoolCollegeClassMajorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学校-学院-班级-专业表 服务实现类
 * @author jcl
 * @since 2021-05-19
 */
@Service
@Transactional
public class SchoolCollegeClassMajorServiceImpl extends ServiceImpl<SchoolCollegeClassMajorMapper, SchoolCollegeClassMajor> implements SchoolCollegeClassMajorService {

    @Autowired
    private SchoolCollegeClassMajorMapper schoolCollegeClassMajorMapper;

    /**
    * @author jcl
    * @date 2021-05-19
    * @Description //TODO 查询单个的详细信息
    **/
    @Override
    public SchoolCollegeClassMajor selectById(Integer id) {
            return schoolCollegeClassMajorMapper.selectById(id);
            }

}