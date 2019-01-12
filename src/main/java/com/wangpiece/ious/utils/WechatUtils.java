package com.wangpiece.ious.utils;

import com.wangpiece.ious.config.WechatConfigs;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-12 0:22
 */
public class WechatUtils {


    /**
     * 微信小程序获取openid url
     * @param code
     * @return
     */
    public static String getOpenSessionUrl(String code) {

        String url = WechatConfigs.GET_OPENID_URL;
        url = url.replace("APPID", WechatConfigs.APPID);
        url = url.replace("SECRET", WechatConfigs.APP_SECRET);
        url = url.replace("JSCODE", code);
        return url;
    }

    /**
     * 获取一定长度的随机字符串，范围0-9，a-z
     * @param length：指定字符串长度
     * @return 一定长度的随机字符串
     */
    public static String getRandomStr(int length) {

        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取真实的ip地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }


    /**
     * 将集合内非空参数值的参数按照参数名ASCII码从小到大排序（字典序），
     * 使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串
     * @param paramsMap
     * @return
     */
    public static String createTempSignStr(Map<String, String> paramsMap) {

        List<String> keys = new ArrayList<>(paramsMap.keySet());
        Collections.sort(keys);
        StringBuilder result = new StringBuilder();
        for(String key : keys) {
            String value = paramsMap.get(key);
            if(result != null && result.length() > 0) {
                result.append("&");
            }
            result.append(key).append("=").append(value);
        }
        //将商户平台的key值也拼接上
        result.append("key").append("=").append(WechatConfigs.KEY);
        return result.toString();
    }

    /**
     * 获取签名
     * @param tempSignStr
     * @return
     */
    public static String getSign(String tempSignStr) {
        try {
            byte[] signStrBytes = tempSignStr.getBytes("UTF-8");
            String sign = DigestUtils.md5DigestAsHex(signStrBytes);
            return sign.toLowerCase();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
