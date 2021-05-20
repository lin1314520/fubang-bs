package com.fubang.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fubang.admin.entity.SysUserLogin;
import com.fubang.admin.entity.UserReport;
import com.fubang.admin.mapper.SysUserLoginMapper;
import com.fubang.admin.service.SysUserLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fubang.admin.service.UserReportService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Autowired
    private UserReportService userReportService;


    /**
    * @author jcl
    * @date 2021-05-18
    * @Description //TODO 查询单个的详细信息
    **/
    @Override
    public SysUserLogin selectById(Integer id) {
            return sysUserLoginMapper.selectById(id);
            }



    @Override
    public List<SysUserLogin> listData(List<SysUserLogin> records) {
        for (SysUserLogin s:records
             ) {
            int sumFenShu = 0;
            QueryWrapper<UserReport> wrapper=new QueryWrapper();
            wrapper.eq("user_id",s.getUserId()).eq("is_valid",0);
            List<UserReport> list = userReportService.list(wrapper);
            for (UserReport u:list
                 ) {
                sumFenShu = Integer.parseInt(u.getReportCredit())+sumFenShu;
            }
            s.setReportCredit(sumFenShu+"");
        }
        return records;
    }

}