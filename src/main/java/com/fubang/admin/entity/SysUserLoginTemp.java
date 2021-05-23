package com.fubang.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * 登录用户表中間表
 * @author jcl
 * @since 2021-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_login_temp")
@ApiModel(value="SysUserLoginTemp对象", description="登录用户表中間表")
public class SysUserLoginTemp implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
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

    @ApiModelProperty(value = "用户类型,超级管理员：admin，辅导员：counselor，评审员:reviewers")
    private String userType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "班级id")
    private Integer classId;

    @ApiModelProperty(value = "用戶性別")
    private String userSex;

    @ApiModelProperty(value = "原因")
    private String reason;

    @ApiModelProperty(value = "退回原因")
    private String backReason;

    @ApiModelProperty(value = "是否通过(1-通过，2-不通过)")
    private String statusType;


}
