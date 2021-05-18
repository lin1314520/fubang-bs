package com.fubang.admin.api;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fubang.admin.entity.Result;
import com.fubang.admin.entity.SysUserLogin;
import com.fubang.admin.entity.UserLoginPoJo;
import com.fubang.admin.entity.SysUserLogin;
import com.fubang.admin.service.SysUserLoginService;
import com.fubang.util.AuthUtil;
import io.swagger.annotations.*;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @auth lwl
 * @since 2021/3/17
 */
@RestController
@RequestMapping("/api/login")
@Api(tags = "登录")
public class LoginController {


    @Value("${wx.mp.configs.appId}")
    private String appId;

    @Value("${wx.mp.configs.secret}")
    private String secret;
    @Autowired
    public SysUserLoginService sysUserLoginService;

    /**
     * 微信授权登录
     *
     * @param userLoginPoJo
     * @return
     */
    @ApiOperation(value = "登录", httpMethod = "POST", notes = "传有参数说明的参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "验证码/微信code", dataType = "string"),
            @ApiImplicitParam(name = "accessToken", value = "token", dataType = "string")
    })
    @PostMapping("/login")
    public Result login(@RequestBody @ApiIgnore UserLoginPoJo userLoginPoJo,
                        HttpServletRequest request) {
        return wxCodeLogin(userLoginPoJo.getCode(), request, userLoginPoJo.getAccessToken());
    }

    /**
     * 微信登录
     */
    public Result wxCodeLogin(String code, HttpServletRequest request, String accessToken) {
        WxMpUser wxMpUser = null;
        SysUserLogin user = null;
        Map<String, Object> returnMap = new HashMap<>();
        //根据token验证token
        //如果redis中不存在token
        try {
            // 根据appId 获取授权信息
            //拼接url
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret="
                    + secret + "&code=" + code + "&grant_type=authorization_code";
            JSONObject jsonObject = AuthUtil.doGetJson(url);
            //1.获取微信用户的openid
            String openid = jsonObject.getString("openid");
            //2.获取获取access_token
            String access_token = jsonObject.getString("access_token");
            String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid
                    + "&lang=zh_CN";
            //3.获取微信用户信息
            JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
//            判断是否有微信授权登录的open_id。
            if (ObjectUtil.isNotNull(userInfo.getString("openid")) || ObjectUtil.isNotEmpty(userInfo.getString("openid"))) {
                //查询用户信息
                QueryWrapper<SysUserLogin> query = Wrappers.query();
                //根据openID查询数据库是否已经存在
                query.eq("open_id", openid).eq("is_valid", 0);
                user = sysUserLoginService.getOne(query);

            } else {
                return Result.error(401, "登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return login(request);
    }

    //设置token到redis中
    private Result login(HttpServletRequest request) {

        Map<String, Object> returnMap = new HashMap<>();

        returnMap.put("value", true);
        returnMap.put("message", "登录成功");
        return Result.success(returnMap);
    }


    /**
     * JSAPI
     *
     * @param
     * @return
     */
    @ApiOperation(value = "获取jsAPi", httpMethod = "POST", notes = "传有参数说明的参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "验证码/微信code", dataType = "string"),
            @ApiImplicitParam(name = "accessToken", value = "token", dataType = "string")
    })
    @PostMapping("/config")
    public Result config(@RequestBody @ApiIgnore String url) {
        url = JSONObject.parseObject(url).get("url").toString();
        Map<String, Object> sign = new HashMap<String, Object>();
        try {
            sign = this.sign(url);
        } catch (Exception e) {
            e.printStackTrace();
            Result.builder().code(500).data(e.getMessage()).create();
        }
        return Result.builder().code(200).data(sign).create();


    }


    public Map<String, Object> sign(String url) throws IOException {
        // 根据appId 获取授权信息
        //拼接url
        String wxUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + secret;
        JSONObject jsonObject = AuthUtil.doGetJson(wxUrl);
        //1.获取获取access_token
        String access_token = jsonObject.getString("access_token");
        String infoUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
        //3.获取微信授权信息
        JSONObject ticketInfo = AuthUtil.doGetJson(infoUrl);
        String ticket = ticketInfo.getString("ticket");
        Map<String, Object> ret = new HashMap<String, Object>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println(string1);

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] jsApiList = new String[0];
        ret.put("debug", false);
        ret.put("appId", appId);
        ret.put("timestamp", timestamp);
        ret.put("nonceStr", nonce_str);
        ret.put("signature", signature);
        ret.put("jsApiList", jsApiList);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

}