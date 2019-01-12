package com.wangpiece.ious.config;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-11 23:46
 */
public class WechatConfigs {

    /**
     * APPID
     */
    public static String APPID = "wxe089941df435f5ef";
    /**
     * AppSecret
     */
    public static String APP_SECRET = "d60ecea01fd24fb7b1c258673b1dde10";

    /**
     * 商户号
     */
    public static String MCH_ID = "1230000109";

    /**
     * 商户平台的key值
     */
    public static  String KEY = "1230000109";

    /**
     * 获取微信小程序url
     */
    public static String GET_OPENID_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

    /**
     * 获取鉴权获取code接口
     */
    public static String GET_AUTH_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
    /**
     * 获取鉴权获取code接口中重定向地址
     */
    public static String REDIRECT_URL = "http://wangpiece.oicp.net/ious/wechat/auth";
    /**
     * 通过code换取网页授权access_token url
     */
    public static String AUTH_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    /**
     * 统一下单接口
     */
    public static String PAY_URL ="https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 统一下单接口后的通知地址
     */
    public static String NOTIFY_URL = "http://www.baidu.com";

    /**
     * 小程序支付类型
     */
    public static String TRADE_TYPE_JSAPI = "JSAPI";
}
