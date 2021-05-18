package com.fubang.admin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.slf4j.MDC;

import java.io.Serializable;

/**
 * 响应结果返回类
 */
@Getter
public class Result implements Serializable {

    private static final long serialVersionUID = 5245758467689718735L;

    public Result() {
    }

    @ApiModelProperty(value = "返回状态码")
    private int code;
    @ApiModelProperty(value = "返回信息")
    private String message;
    @ApiModelProperty(value = "返回json数据")
    private Object data;
    private String seqid;

    protected Result(Builder builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int code = GlobalConsts.SUCCESS_CODE;
        private String message = GlobalConsts.SUCCESS_MSG;
        private Object data;

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Result create() {
            return new Result(this);
        }
    }

    public static Result success() {
        return builder().create();
    }

    public static Result success(Object data) {
        return builder().data(data).create();
    }

    public static Result error(int code, String message) {
        return builder().code(code).message(message).create();
    }

    public static Result error(int code, String message, Object object) {
        return builder().code(code).message(message).data(object).create();
    }


}


