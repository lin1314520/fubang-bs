package com.fubang.config;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static cn.hutool.core.util.NumberUtil.roundStr;

/**
 * 方法作用：定时任务执行
 * @author jiangchanglin
 * @date 2021/4/27 9:56
 * @param
 * @return
 **/
@Component
@Slf4j
public class TimedTask {

    public static final String DD = DateUtil.format(DateUtil.date(), "dd");
    public static final String MM = DateUtil.format(DateUtil.date(), "MM");
    public static final String YYYY = DateUtil.format(DateUtil.date(), "yyyy");

    @Value("${wx.mp.configs.appId}")
    private String appId;

    @Value("${wx.mp.configs.secret}")
    private String secret;
}

