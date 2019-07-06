package com.wangpiece.ious.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;


public class IMessageServiceImpl {

    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIVgPGWCHD4kds", "xpftpqBTNdwdOsDCvnjqVv5JpJogpI");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("QuerySendDetails");
        request.putQueryParameter("PhoneNumber", "18060484449");
        request.putQueryParameter("SendDate", "2019-07-06");
        request.putQueryParameter("PageSize", "1");
        request.putQueryParameter("CurrentPage", "1");

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
