package com.wangpiece.ious.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.wangpiece.ious.dto.SmsResult;
import com.wangpiece.ious.mapper.SmsMapper;
import com.wangpiece.ious.runner.SystemConfigRunner;
import com.wangpiece.ious.service.ISmsService;
import com.wangpiece.ious.utils.DateUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements ISmsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Autowired
    private SmsMapper smsMapper;

    @Override
    public String sendSms(String phone) {
        String regionId = SystemConfigRunner.SMS_CONFIG_MAP.get("regionId");
        String accessKeyId = SystemConfigRunner.SMS_CONFIG_MAP.get("accessKeyId");
        String secret = SystemConfigRunner.SMS_CONFIG_MAP.get("secret");
        String domain = SystemConfigRunner.SMS_CONFIG_MAP.get("domain");
        String version = SystemConfigRunner.SMS_CONFIG_MAP.get("version");
        String action = SystemConfigRunner.SMS_CONFIG_MAP.get("action");
        String signName = SystemConfigRunner.SMS_CONFIG_MAP.get("SignName");
        String templateCode = SystemConfigRunner.SMS_CONFIG_MAP.get("TemplateCode");

        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(version);
        request.setAction(action);
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);

        String code = getCode();
        request.putQueryParameter("TemplateParam", "{ \"code\":" + code + "}");

        try {
            CommonResponse response = client.getCommonResponse(request);
            String responseData = response.getData();
            JSONObject jsonData = JSONObject.fromObject(responseData);
            String message = jsonData.getString("Message");
            String bizId = jsonData.getString("BizId");
            String requestId = jsonData.getString("RequestId");
            String responseCode = jsonData.getString("Code");

            if("OK".equals(message)){
                SmsResult smsResult = new SmsResult();
                smsResult.setBizId(bizId);
                smsResult.setMessage(message);
                smsResult.setRequestId(requestId);
                smsResult.setResponseCode(responseCode);
                smsResult.setCode(code);
                smsResult.setInvalidFlag(1);
                smsResult.setPhone(phone);
                String currentDate = DateUtils.getCurrentDate();
                smsResult.setCreateTime(currentDate);
                smsResult.setInvalidTime(DateUtils.getPlusMinutes(currentDate,5));
                smsMapper.save(smsResult);
                return requestId;
            }
            return "-1";
        } catch (ClientException e) {
            LOGGER.error("发送短信验证码失败", e, phone);
            return "-1";
        }
    }

    /**
     * 保存验证码信息
     *
     * @param smsResult
     */
    @Override
    public void save(SmsResult smsResult) {

    }

    /**
     * 获取验证码信息
     *
     * @param phone 电话号码
     * @return
     */
    @Override
    public SmsResult getSmsInfoByPhone(String phone) {
        return smsMapper.getSmsInfoByPhone(phone);
    }

    /**
     * 更新是否失效状态
     *
     * @param id
     */
    @Override
    public void updateSmsInvalidFlag(Integer id) {
        smsMapper.updateSmsInvalidFlag(id);
    }

    /**
     * 生成随机码
     * @return
     */
    private static String getCode(){
        String code = "";
        for (int i = 0; i < 6; i++) {
            code = code + (int)(Math.random() * 9);
        }
        return code;
    }


    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIVgPGWCHD4kds", "xpftpqBTNdwdOsDCvnjqVv5JpJogpI");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", "18060484449");
        request.putQueryParameter("SignName", "众联成");
        request.putQueryParameter("TemplateCode", "SMS_169897959");
        request.putQueryParameter("TemplateParam", "{ \"code\":\"1234\"}");

        try {
            CommonResponse response = client.getCommonResponse(request);
            String responseData = response.getData();
            System.out.println(responseData);
            JSONObject object = JSONObject.fromObject(responseData);
            System.out.println(object);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }


}
