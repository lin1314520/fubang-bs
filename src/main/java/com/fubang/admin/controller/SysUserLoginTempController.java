package com.fubang.admin.controller;

import com.fubang.admin.entity.SysUserLogin;
import com.fubang.admin.service.SysUserLoginService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import springfox.documentation.annotations.ApiIgnore;
import cn.hutool.json.JSONUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fubang.admin.entity.Result;

import java.time.LocalDateTime;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import com.fubang.admin.service.SysUserLoginTempService;
import com.fubang.admin.entity.SysUserLoginTemp;

import java.util.Arrays;

/**
 * 登录用户表中間表
 *
 * @author jcl
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/admin/SysUserLoginTemp")
@Api(tags = "登录用户表中間表")
public class SysUserLoginTempController {

    @Autowired
    public SysUserLoginTempService sysUserLoginTempService;
    @Autowired
    public SysUserLoginService sysUserLoginService;


    /**
     * 分页查询
     *
     * @param page
     * @param sysUserLoginTemp
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页显示条数", required = true, paramType = "query", dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok")
    })
    @ApiOperation(value = "分页查询", httpMethod = "GET", notes = "传有参数说明的参数")
    @GetMapping(value = "/sysUserLoginTemp")
    public Result selectByPage(@ApiIgnore Page<SysUserLoginTemp> page, @ApiIgnore SysUserLoginTemp sysUserLoginTemp) {
        QueryWrapper<SysUserLoginTemp> queryWrapper = new QueryWrapper(sysUserLoginTemp);
        queryWrapper.eq("is_valid", 0).orderByDesc("create_time");
        Page<SysUserLoginTemp> pageList = sysUserLoginTempService.page(page, queryWrapper);
        return Result.builder().code(200).data(pageList).create();
    }

    /**
     * 单个详细信息查询[根据主键id]
     *
     * @param
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true, paramType = "query", dataType = "int")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok")
    })
    @ApiOperation(value = "根据id单个详细信息查询", httpMethod = "GET", notes = "传有参数说明的参数")
    @GetMapping(value = "/selectById/{id}")
    public Result selectById(@ApiIgnore @PathVariable Integer id) {
        try {
            SysUserLoginTemp byId = sysUserLoginTempService.selectById(id);
            if (ObjectUtil.isNull(byId)) {
                return Result.error(415, "此条数据不存在");
            }
            return Result.builder().code(200).data(byId).create();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Result.error(500, "系统异常");
        }
    }

    /**
     * 保存
     *
     * @param sysUserLoginTemp
     * @return
     */
    @ApiOperation(value = "新增", httpMethod = "POST", notes = "传有参数说明的参数")
    @PostMapping("/sysUserLoginTemp")
    @Transactional(rollbackFor = Exception.class)
    public Result save(@ApiIgnore @RequestBody SysUserLoginTemp sysUserLoginTemp) {
        sysUserLoginTemp.setIsValid(0);
        sysUserLoginTempService.save(sysUserLoginTemp);
        return Result.builder().code(200).create();
    }

    /**
     * 修改
     *
     * @param sysUserLoginTemp
     * @return
     */
    @ApiOperation(value = "修改", httpMethod = "PUT", notes = "传有参数说明的参数")
    @PutMapping("/sysUserLoginTemp")
    @Transactional(rollbackFor = Exception.class)
    public Result update(@ApiIgnore @RequestBody SysUserLoginTemp sysUserLoginTemp) {
        QueryWrapper<SysUserLoginTemp> query = Wrappers.query();
        query.eq("id", sysUserLoginTemp.getId()).eq("is_valid", 0);
        SysUserLoginTemp byId = sysUserLoginTempService.getOne(query);
        //修改字段
        if(sysUserLoginTemp.getStatusType().equals("1")) {
            SysUserLogin sysUserLogin = new SysUserLogin();
            sysUserLogin.setUserId(sysUserLoginTemp.getUserId());
            sysUserLogin.setUserName(sysUserLoginTemp.getUserName());
            sysUserLogin.setUserSex(sysUserLoginTemp.getUserSex());
            sysUserLogin.setUserNum(sysUserLoginTemp.getUserNum());
            sysUserLogin.setClassId(sysUserLoginTemp.getClassId());
            sysUserLoginService.updateById(sysUserLogin);
        }
        sysUserLoginTempService.updateById(sysUserLoginTemp);
        return Result.builder().code(200).create();
    }


    /**
     * 根据id删除，逻辑删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除", httpMethod = "DELETE", notes = "传有参数说明的参数")
    @DeleteMapping("/sysUserLoginTemp/{id}")
    @Transactional(rollbackFor = Exception.class)
    public Result deleteById(@PathVariable("id") @ApiParam(name = "id", value = "id", required = true) Integer id) {
        QueryWrapper<SysUserLoginTemp> query = Wrappers.query();
        query.eq("id", id).eq("is_valid", 0);
        SysUserLoginTemp sysUserLoginTemp = sysUserLoginTempService.getOne(query);
        if (ObjectUtil.isNull(sysUserLoginTemp)) {
            return Result.error(405, "此数据已被删除");
        }
        sysUserLoginTemp.setIsValid(1);
        sysUserLoginTempService.updateById(sysUserLoginTemp);
        return Result.builder().code(200).create();
    }


}