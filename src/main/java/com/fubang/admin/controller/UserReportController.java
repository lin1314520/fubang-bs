package com.fubang.admin.controller;

import com.fubang.admin.entity.SchoolCollegeClassMajor;
import com.fubang.admin.entity.SysUserLogin;
import com.fubang.admin.service.SchoolCollegeClassMajorService;
import com.fubang.admin.service.SysUserLoginService;
import com.fubang.util.WordUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;
import cn.hutool.json.JSONUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fubang.admin.entity.Result;

import java.io.*;
import java.time.LocalDateTime;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import com.fubang.admin.service.UserReportService;
import com.fubang.admin.entity.UserReport;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户提交报告表
 *
 * @author jcl
 * @since 2021-05-19
 */
@RestController
@RequestMapping("/admin/UserReport")
@Api(tags = "用户提交报告表")
public class UserReportController {

    @Autowired
    public UserReportService userReportService;
    @Autowired
    public SysUserLoginService sysUserLoginService;
    @Autowired
    public SchoolCollegeClassMajorService schoolCollegeClassMajorService;


    /**
     * 分页查询
     *
     * @param page
     * @param userReport
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
    @GetMapping(value = "/userReport")
    public Result selectByPage(@ApiIgnore Page<UserReport> page, @ApiIgnore UserReport userReport) {
        QueryWrapper<UserReport> queryWrapper = new QueryWrapper(userReport);
        queryWrapper.eq("is_valid", 0).orderByDesc("create_time");
        if(ObjectUtil.isNotNull(userReport.getReportYear())) {
            queryWrapper.like("report_year", userReport.getReportYear());
            userReport.setReportYear(null);
        }
        Page<UserReport> pageList = userReportService.page(page, queryWrapper);
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
            UserReport byId = userReportService.selectById(id);
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
     * @param userReport
     * @return
     */
    @ApiOperation(value = "新增", httpMethod = "POST", notes = "传有参数说明的参数")
    @PostMapping("/userReport")
    @Transactional(rollbackFor = Exception.class)
    public Result save(@ApiIgnore @RequestBody UserReport userReport) {
        userReport.setIsValid(0);
        userReportService.save(userReport);
        return Result.builder().code(200).create();
    }

    /**
     * 修改
     *
     * @param userReport
     * @return
     */
    @ApiOperation(value = "修改", httpMethod = "PUT", notes = "传有参数说明的参数")
    @PutMapping("/userReport")
    @Transactional(rollbackFor = Exception.class)
    public Result update(@ApiIgnore @RequestBody UserReport userReport) {
        QueryWrapper<UserReport> query = Wrappers.query();
        query.eq("id", userReport.getId()).eq("is_valid", 0);
        UserReport byId = userReportService.getOne(query);
        //修改字段


        userReportService.updateById(userReport);
        return Result.builder().code(200).create();
    }


    /**
     * 根据id删除，逻辑删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除", httpMethod = "DELETE", notes = "传有参数说明的参数")
    @DeleteMapping("/userReport/{id}")
    @Transactional(rollbackFor = Exception.class)
    public Result deleteById(@PathVariable("id") @ApiParam(name = "id", value = "id", required = true) Integer id) {
        QueryWrapper<UserReport> query = Wrappers.query();
        query.eq("id", id).eq("is_valid", 0);
        UserReport userReport = userReportService.getOne(query);
        if (ObjectUtil.isNull(userReport)) {
            return Result.error(405, "此数据已被删除");
        }
        userReport.setIsValid(1);
        userReportService.updateById(userReport);
        return Result.builder().code(200).create();
    }

    //文件上传接口
    @ResponseBody
    @RequestMapping("/home/updateInfoForm")
    public Map<String, Object> update(MultipartFile imgFile, @RequestParam Map<String, Object> map, HttpServletRequest request) {
        if (imgFile != null) {
//            -----------------------------------------------------
            //获取文件输入流
            InputStream inputStream = null;
            try {
                inputStream = imgFile.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //获取服务器存放图片的真实路径
//            String realPath = this.getRealPath("/upload", request);
            String realPath = "D://fromThree/Img";

            //获取文件名称并且进行重命名
            String originalFilename = imgFile.getOriginalFilename();
            String newName = this.getNewName(originalFilename);

            //在服务器上创建新文件
            FileOutputStream fileOutputStream = null;
            File file = new File(realPath, newName);
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //文件复制
            try {
                IOUtils.copy(inputStream, fileOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //将路径保存到map数据库中
            map.put("avatar", realPath + "/" + newName);
            return map;
        }
        return map;
    }

    //防止文件重名，重命名
    private String getNewName2(String oldName) {
        UUID uuid = UUID.randomUUID();
        String newName = uuid + oldName;
        return newName;
    }
    //防止文件重名，重命名
    private String getNewName(String oldName) {
        UUID uuid = UUID.randomUUID();
        String newName = uuid + oldName.substring(oldName.lastIndexOf("."));
        return newName;
    }
    /**
     * 下载
     *
     * @param
     * @return
     */
    @ApiOperation(value = "下载", httpMethod = "POST", notes = "传有参数说明的参数")
    @PostMapping("/downWord")
    @Transactional(rollbackFor = Exception.class)
    public Result downWord(@RequestParam(value = "userId") Integer userId,@RequestParam(value = "reportYear") String reportYear) throws Exception {
        Map<String, Object> returnData =  new HashMap<>();
        String template = "C:\\Users\\g1121\\Desktop\\导出Word表格样式.docx";
        SysUserLogin sysUserLogin = sysUserLoginService.getById(userId);
        SchoolCollegeClassMajor schoolCollegeClassMajor = schoolCollegeClassMajorService.selectById(sysUserLogin.getClassId());
        QueryWrapper<UserReport> queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_valid", 0).orderByDesc("create_time").eq("user_id",userId).like("report_year",reportYear);
        List<UserReport> list = userReportService.list(queryWrapper);
        if (list.size() == 0) {
        return Result.builder().code(415).message("没有此学年报告").create();
        }

        int sumFenShu =0;
        for (UserReport u:list
        ) {
             sumFenShu = Integer.parseInt(u.getReportCredit()) + sumFenShu;
        }
        int i = Integer.parseInt(reportYear) + 1;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("${gradeS}",reportYear);
        paramMap.put("${gradeE}", i+"");
        paramMap.put("${studentName}",sysUserLogin.getUserName());
        paramMap.put("${studentNumber}",sysUserLogin.getUserNum());
        paramMap.put("${studentSex}", sysUserLogin.getUserSex());
        SchoolCollegeClassMajor schoolCollegeClassMajor1 = schoolCollegeClassMajorService.selectById(schoolCollegeClassMajor.getPid());
//        if(ObjectUtils.isNotEmpty(schoolCollegeClassMajor1)) {
            paramMap.put("${grade}",  sysUserLogin.getUserYear());
//        }
        SchoolCollegeClassMajor schoolCollegeClassMajor2 = schoolCollegeClassMajorService.selectById(schoolCollegeClassMajor1.getPid());
        if(ObjectUtils.isNotEmpty(schoolCollegeClassMajor2)) {
            paramMap.put("${major}", schoolCollegeClassMajor2.getNikeName());
        }
        paramMap.put("${studentClass}", schoolCollegeClassMajor.getNikeName());

        paramMap.put("${resultClass0}", list.get(0).getReportType());
        paramMap.put("${resultClass1}", "1");
        paramMap.put("${resultClass2}", "2");
        paramMap.put("${resultClass3}", "3");

        paramMap.put("${resultName0}",  list.get(0).getReportName());
        paramMap.put("${resultName1}", "Java开发5");
        paramMap.put("${resultName2}", "Java开发6");
        paramMap.put("${resultName3}", "Java开发7");

        paramMap.put("${resultTime0}", list.get(0).getReportYear());
        paramMap.put("${resultTime1}", "Java开发9");
        paramMap.put("${resultTime2}", "Java开发0");
        paramMap.put("${resultTime3}", "Java开发11");

        paramMap.put("${rank0}", list.get(0).getReportRanking());
        paramMap.put("${rank1}", "Java开发1111");
        paramMap.put("${rank2}", "Java开发11111");
        paramMap.put("${rank3}", "Java开发222");

        paramMap.put("${score0}", list.get(0).getReportCredit());
        paramMap.put("${score1}", "Java开发333");
        paramMap.put("${score2}", "Java开发444");
        paramMap.put("${score3}", "Java开发55");
        paramMap.put("${scoreAll}", sumFenShu+"");

        // 模板填充
        XWPFDocument doc = WordUtil.generateWord(paramMap, template);
        String newName = this.getNewName2("A");
        String s = "C:\\Users\\g1121\\Desktop\\" + newName+".doc";
        FileOutputStream fopts = new FileOutputStream(s);
        doc.write(fopts);
        fopts.close();

        returnData.put("pathUrl",s);
        return Result.builder().code(200).data(returnData).create();
    }

}