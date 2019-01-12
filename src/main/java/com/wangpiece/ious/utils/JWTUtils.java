package com.wangpiece.ious.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wangpiece.ious.dto.User;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-15 16:26
 */
public class JWTUtils {

    public static final String TOKEN_PWD = "@WSX3edc$RFV^TFCnhy$";

    /**
     * 获取token
     * @param user
     * @return
     */
    public static String getToken(User user){
        String token = JWT.create().withAudience(user.getPhone()).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
