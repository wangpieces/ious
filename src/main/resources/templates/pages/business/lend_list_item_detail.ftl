<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>借条详情</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
    <meta name="format-detection" content="telephone=no, email=no" />
    <link rel="stylesheet" href="${iousRequestUrl}/lib/css/layer.css" type="text/css">
    <link rel="stylesheet" href="${iousRequestUrl}/css/common.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${iousRequestUrl}/css/personalCenter.css"/>
    <script src="${iousRequestUrl}/lib/js/jquery-2.1.4.js"></script>
    <script src="${iousRequestUrl}/lib/js/jquery.base64.js"></script>
    <script src="${iousRequestUrl}/lib/js/layer.js"></script>
    <script src="${iousRequestUrl}/js/wapframwork.js"></script>
    <!--生成二维码-->
    <script src="${iousRequestUrl}/lib/js/jquery.qrcode.min.js"></script>
    <style>
        .showcontent{
            float:right;text-align:right;margin-right:0.2rem;font-size:0.3rem;
        }
        .showcontent a{
            color:red;
        }
    </style>
</head>
<body style="background:white;">
<div class="find_loginPwd_title header" style="background:none;color:black;text-align:left;">
    <span style="border-left:0.1rem solid #40bfff;margin-left:0.2rem;">&nbsp;&nbsp;借条详情</span>
</div>
<section class="find_loginPwd_wrap">
    <input name="id" id="id" type="hidden" value="${ious.id}">
    <ul class="list">
        <li class="list_item">
            <label>
                <span class="item_text">　借款人</span>
                <span class="showcontent">${(ious.loaner)!""}<#--<a href="javascript:loanAgreement()">&nbsp;查看详情</a>--></span>
            </label>
        </li>
        <li class="list_item">
            <label>
                <span class="item_text">　出借人</span>
                <span class="showcontent">${(ious.lender)!""}</span>
            </label>
        </li>
        <li class="list_item">
            <label>
                <span class="item_text">借款金额</span>
                <span class="showcontent">${(ious.money)!0}&nbsp;元</span>
            </label>
        </li>
        <li class="list_item">
            <label>
                <span class="item_text">借款日期</span>
                <span class="showcontent">${(ious.loanTime)!""}</span>
            </label>
        </li>
        <li class="list_item">
            <label>
                <span class="item_text">还歀日期</span>
                <span class="showcontent">${(ious.returnTime)!""}</span>
            </label>
        </li>
        <li class="list_item">
            <label>
                <span class="item_text">年化利率</span>
                <span class="showcontent">${(ious.rateName)!""}</span>
            </label>
        </li>
        <li class="list_item">
            <label>
                <span class="item_text">借款用途</span>
                <span class="showcontent">${(ious.purposeName)!""}</span>
            </label>
        </li>
        <li class="list_item">
            <label>
                <span class="item_text">补充说明</span>
                <span class="showcontent">${(ious.moreInfo)!""}</span>
            </label>
        </li>
        <li class="list_item">
            <label>
                <span class="item_text">借款协议</span>
                <span class="showcontent"><a href="javascript:loanAgreement()">查看详情</a></span>
            </label>
        </li>
        <li class="list_item">
            <label>
                <span class="item_text" style="width:2rem;margin-left:0.2rem;">居间服务协议</span>
                <span class="showcontent"><a href="javascript:void(0)" onclick="otherAgreement();">查看详情</a></span>
            </label>
        </li>
    </ul>
    <br/>
    <br/>
</section>
</body>

<script type="text/javascript">

    //借款协议
    function loanAgreement(){
        layer.open({
             content: '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
                      '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
                      '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
                      '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
                      '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
                      '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
                      '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
                      '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
                      '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'
            ,btn: '确定'
            ,title: '借款协议'
        });
    }
    //居间服务协议
    function otherAgreement(){
        layer.open({
            content: '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
                  '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
                  '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
                  '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
                  '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
                  '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
                  '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
                  '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
                  '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'
            ,btn: '确定'
            ,title: '居间服务协议'
        });
    }

</script>
</html>
