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
 * 用户提交报告表
 * @author jcl
 * @since 2021-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_report")
@ApiModel(value="UserReport对象", description="用户提交报告表")
public class UserReport implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "学校id")
    private Integer schoolId;

    @ApiModelProperty(value = "报告名称")
    private String reportName;

    @ApiModelProperty(value = "报告图片URL")
    private String imgUrl;

    @ApiModelProperty(value = "报告年份")
    private String reportYear;

    @ApiModelProperty(value = "报告状态")
    private String imgStatus;

    @ApiModelProperty(value = "报告类别")
    private String reportType;

    @ApiModelProperty(value = "报告排名")
    private String reportRanking;

    @ApiModelProperty(value = "报告学分")
    private String reportCredit;

    @ApiModelProperty(value = "报告原因备注")
    private String reportReason;

    @ApiModelProperty(value = "是否有效（0-有效，1-无效）")
    private Integer isValid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
