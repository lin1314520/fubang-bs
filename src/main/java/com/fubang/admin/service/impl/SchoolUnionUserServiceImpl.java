package com.fubang.admin.service.impl;

import com.fubang.admin.entity.SchoolUnionUser;
import com.fubang.admin.mapper.SchoolUnionUserMapper;
import com.fubang.admin.service.SchoolUnionUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学校和人员关系表 服务实现类
 * @author jcl
 * @since 2021-05-19
 */
@Service
@Transactional
public class SchoolUnionUserServiceImpl extends ServiceImpl<SchoolUnionUserMapper, SchoolUnionUser> implements SchoolUnionUserService {

    @Autowired
    private SchoolUnionUserMapper schoolUnionUserMapper;

    /**
    * @author jcl
    * @date 2021-05-19
    * @Description //TODO 查询单个的详细信息
    **/
    @Override
    public SchoolUnionUser selectById(Integer id) {
            return schoolUnionUserMapper.selectById(id);
            }

}