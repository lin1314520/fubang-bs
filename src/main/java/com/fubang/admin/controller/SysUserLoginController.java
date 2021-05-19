package com.fubang.admin.controller;

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
import com.fubang.admin.service.SysUserLoginService;
import com.fubang.admin.entity.SysUserLogin;

import javax.websocket.server.PathParam;
import java.util.Arrays;

/**
 * 登录用户表
 *
 * @author jcl
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/admin/sysUserLogin")
@Api(tags = "登录用户表")
public class SysUserLoginController {

    @Autowired
    public SysUserLoginService sysUserLoginService;


    /**
     * 分页查询
     *
     * @param page
     * @param sysUserLogin
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
    @GetMapping(value = "/sysUserLogin")
    public Result selectByPage(@ApiIgnore Page<SysUserLogin> page, @ApiIgnore SysUserLogin sysUserLogin) {
        QueryWrapper<SysUserLogin> queryWrapper = new QueryWrapper(sysUserLogin);
        queryWrapper.eq("is_valid", 0).orderByDesc("create_time");
        Page<SysUserLogin> pageList = sysUserLoginService.page(page, queryWrapper);
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
            SysUserLogin byId = sysUserLoginService.selectById(id);
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
     * 单个详细信息查询[根据主键id]
     *
     * @param
     * @return
     */
    //登录接口
    @GetMapping("/login")
    public Result login( @ApiIgnore SysUserLogin sysUserLogin) {
        QueryWrapper<SysUserLogin> queryWrapper = new QueryWrapper(sysUserLogin);
        queryWrapper.eq("is_valid", 0).orderByDesc("create_time");
        Integer count= sysUserLoginService.count(queryWrapper);
        if(count ==1) {
            return Result.builder().code(200).message("登录成功").create();
        }else{
            return Result.builder().code(415).message("登录失败").create();
        }
    }

    /**
     * 保存
     *
     * @param sysUserLogin
     * @return
     */
    @ApiOperation(value = "新增", httpMethod = "POST", notes = "传有参数说明的参数")
    @PostMapping("/sysUserLogin")
    @Transactional(rollbackFor = Exception.class)
    public Result save(@ApiIgnore @RequestBody SysUserLogin sysUserLogin) {
        sysUserLogin.setIsValid(0);
        sysUserLoginService.save(sysUserLogin);
        return Result.builder().code(200).create();
    }

    /**
     * 修改
     *
     * @param sysUserLogin
     * @return
     */
    @ApiOperation(value = "修改", httpMethod = "PUT", notes = "传有参数说明的参数")
    @PutMapping("/sysUserLogin")
    @Transactional(rollbackFor = Exception.class)
    public Result update(@ApiIgnore @RequestBody SysUserLogin sysUserLogin) {
        QueryWrapper<SysUserLogin> query = Wrappers.query();
        query.eq("user_id", sysUserLogin.getUserId()).eq("is_valid", 0);
        SysUserLogin byId = sysUserLoginService.getOne(query);
        //修改字段


        sysUserLoginService.updateById(sysUserLogin);
        return Result.builder().code(200).create();
    }


    /**
     * 根据id删除，逻辑删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除", httpMethod = "DELETE", notes = "传有参数说明的参数")
    @DeleteMapping("/sysUserLogin/{id}")
    @Transactional(rollbackFor = Exception.class)
    public Result deleteById(@PathVariable("id") @ApiParam(name = "id", value = "id", required = true) Integer id) {
        QueryWrapper<SysUserLogin> query = Wrappers.query();
        query.eq("id", id).eq("is_valid", 0);
        SysUserLogin sysUserLogin = sysUserLoginService.getOne(query);
        if (ObjectUtil.isNull(sysUserLogin)) {
            return Result.error(405, "此数据已被删除");
        }
        sysUserLogin.setIsValid(1);
        sysUserLoginService.updateById(sysUserLogin);
        return Result.builder().code(200).create();
    }


}