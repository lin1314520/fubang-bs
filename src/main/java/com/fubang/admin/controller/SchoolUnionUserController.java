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
import com.fubang.admin.service.SchoolUnionUserService;
import com.fubang.admin.entity.SchoolUnionUser;

import java.util.Arrays;

/**
 * 学校和人员关系表
 * @author jcl
 * @since 2021-05-19
 */
@RestController
@RequestMapping("/admin/SchoolUnionUser")
@Api(tags = "学校和人员关系表")
public class SchoolUnionUserController {

@Autowired
public SchoolUnionUserService schoolUnionUserService;


/**
 * 分页查询
 * @param page
 * @param schoolUnionUser
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
@GetMapping(value = "/schoolUnionUser")
public Result selectByPage(@ApiIgnore Page<SchoolUnionUser> page,@ApiIgnore SchoolUnionUser schoolUnionUser){
        QueryWrapper<SchoolUnionUser> queryWrapper=new QueryWrapper( schoolUnionUser);
        queryWrapper.eq("is_valid",0).orderByDesc("create_time");
        Page<SchoolUnionUser> pageList=schoolUnionUserService.page(page,queryWrapper);
        return Result.builder().code(200).data(pageList).create();
        }

/**
    * 单个详细信息查询[根据主键id]
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
public Result selectById(@ApiIgnore @PathVariable Integer id){
        try{
    SchoolUnionUser byId= schoolUnionUserService.selectById(id);
        if(ObjectUtil.isNull(byId)){
        return Result.error(415,"此条数据不存在");
        }
        return Result.builder().code(200).data(byId).create();
        }catch(NumberFormatException e){
        e.printStackTrace();
        return Result.error(500,"系统异常");
        }
        }

/**
 * 保存
 * @param schoolUnionUser
 * @return
 */
@ApiOperation(value = "新增", httpMethod = "POST", notes = "传有参数说明的参数")
@PostMapping("/schoolUnionUser")
@Transactional(rollbackFor = Exception.class)
public Result save(@ApiIgnore @RequestBody SchoolUnionUser schoolUnionUser){
    schoolUnionUser.setIsValid(0);
    schoolUnionUserService.save(schoolUnionUser);
        return Result.builder().code(200).create();
        }

/**
 * 修改
 * @param schoolUnionUser
 * @return
 */
@ApiOperation(value = "修改", httpMethod = "PUT", notes = "传有参数说明的参数")
@PutMapping("/schoolUnionUser")
@Transactional(rollbackFor = Exception.class)
public Result update(@ApiIgnore @RequestBody SchoolUnionUser schoolUnionUser){
        QueryWrapper<SchoolUnionUser> query=Wrappers.query();
        query.eq("id",schoolUnionUser.getId()).eq("is_valid",0);
    SchoolUnionUser byId= schoolUnionUserService.getOne(query);
        //修改字段


    schoolUnionUserService.updateById(schoolUnionUser);
        return Result.builder().code(200).create();
        }


/**
* 根据id删除，逻辑删除
* @param id
* @return
*/
@ApiOperation(value = "删除", httpMethod = "DELETE", notes = "传有参数说明的参数")
@DeleteMapping("/schoolUnionUser/{id}")
@Transactional(rollbackFor = Exception.class)
public Result deleteById(@PathVariable("id") @ApiParam(name = "id", value = "id", required = true) Integer id){
        QueryWrapper<SchoolUnionUser> query=Wrappers.query();
        query.eq("id",id).eq("is_valid",0);
    SchoolUnionUser schoolUnionUser = schoolUnionUserService.getOne(query);
        if(ObjectUtil.isNull(schoolUnionUser)){
        return Result.error(405,"此数据已被删除");
        }
    schoolUnionUser.setIsValid(1);
    schoolUnionUserService.updateById(schoolUnionUser);
        return Result.builder().code(200).create();
        }


        }