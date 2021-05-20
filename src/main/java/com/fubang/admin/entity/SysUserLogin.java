package com.fubang.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * 登录用户表
 * @author jcl
 * @since 2021-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_login")
@ApiModel(value="SysUserLogin对象", description="登录用户表")
public class SysUserLogin implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "学号")
    private String userNum;

    @ApiModelProperty(value = "登录账号")
    private String loginName;

    @ApiModelProperty(value = "登录密码")
    private String passWord;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "联合id")
    private String unionId;

    @ApiModelProperty(value = "微信openId")
    private String openId;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "是否有效（0-有效，1-无效）")
    private Integer isValid;

    @ApiModelProperty(value = "班级Id")
    private Integer classId;

    @ApiModelProperty(value = "用户类型,超级管理员：admin，辅导员：counselor，评审员:reviewers")
    private String userType;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
