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
import com.fubang.admin.service.SchoolCollegeClassMajorService;
import com.fubang.admin.entity.SchoolCollegeClassMajor;

import java.util.Arrays;

/**
 * 学校-学院-班级-专业表
 * @author jcl
 * @since 2021-05-19
 */
@RestController
@RequestMapping("/admin/SchoolCollegeClassMajor")
@Api(tags = "学校-学院-班级-专业表")
public class SchoolCollegeClassMajorController {

@Autowired
public SchoolCollegeClassMajorService schoolCollegeClassMajorService;


/**
 * 分页查询
 * @param page
 * @param schoolCollegeClassMajor
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
@GetMapping(value = "/schoolCollegeClassMajor")
public Result selectByPage(@ApiIgnore Page<SchoolCollegeClassMajor> page,@ApiIgnore SchoolCollegeClassMajor schoolCollegeClassMajor){
        QueryWrapper<SchoolCollegeClassMajor> queryWrapper=new QueryWrapper( schoolCollegeClassMajor);
        queryWrapper.eq("is_valid",0).orderByDesc("create_time");
        Page<SchoolCollegeClassMajor> pageList=schoolCollegeClassMajorService.page(page,queryWrapper);
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
    SchoolCollegeClassMajor byId= schoolCollegeClassMajorService.selectById(id);
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
 * @param schoolCollegeClassMajor
 * @return
 */
@ApiOperation(value = "新增", httpMethod = "POST", notes = "传有参数说明的参数")
@PostMapping("/schoolCollegeClassMajor")
@Transactional(rollbackFor = Exception.class)
public Result save(@ApiIgnore @RequestBody SchoolCollegeClassMajor schoolCollegeClassMajor){
    schoolCollegeClassMajor.setIsValid(0);
    schoolCollegeClassMajorService.save(schoolCollegeClassMajor);
        return Result.builder().code(200).create();
        }

/**
 * 修改
 * @param schoolCollegeClassMajor
 * @return
 */
@ApiOperation(value = "修改", httpMethod = "PUT", notes = "传有参数说明的参数")
@PutMapping("/schoolCollegeClassMajor")
@Transactional(rollbackFor = Exception.class)
public Result update(@ApiIgnore @RequestBody SchoolCollegeClassMajor schoolCollegeClassMajor){
        QueryWrapper<SchoolCollegeClassMajor> query=Wrappers.query();
        query.eq("id",schoolCollegeClassMajor.getId()).eq("is_valid",0);
    SchoolCollegeClassMajor byId= schoolCollegeClassMajorService.getOne(query);
        //修改字段


    schoolCollegeClassMajorService.updateById(schoolCollegeClassMajor);
        return Result.builder().code(200).create();
        }


/**
* 根据id删除，逻辑删除
* @param id
* @return
*/
@ApiOperation(value = "删除", httpMethod = "DELETE", notes = "传有参数说明的参数")
@DeleteMapping("/schoolCollegeClassMajor/{id}")
@Transactional(rollbackFor = Exception.class)
public Result deleteById(@PathVariable("id") @ApiParam(name = "id", value = "id", required = true) Integer id){
        QueryWrapper<SchoolCollegeClassMajor> query=Wrappers.query();
        query.eq("id",id).eq("is_valid",0);
    SchoolCollegeClassMajor schoolCollegeClassMajor = schoolCollegeClassMajorService.getOne(query);
        if(ObjectUtil.isNull(schoolCollegeClassMajor)){
        return Result.error(405,"此数据已被删除");
        }
    schoolCollegeClassMajor.setIsValid(1);
    schoolCollegeClassMajorService.updateById(schoolCollegeClassMajor);
        return Result.builder().code(200).create();
        }


        }