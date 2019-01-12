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
<!--<div class="find_loginPwd_title header">
    &lt;!&ndash;从主页过来的&ndash;&gt;
   &lt;!&ndash; <if from?? && from!="">1</if>
    <if !(from??)>2</if>&ndash;&gt;

    <#if from?? && from=='index'>
        <a href="${iousContextPath}/business/index">
            <i class="icon"></i>
        </a>
    <#elseif fromtype==1>
         &lt;!&ndash;借款&ndash;&gt;
        <a href="${iousContextPath}/business/index">
            <i class="icon"></i>
        </a>
    <#elseif fromtype==2>
        &lt;!&ndash;出借&ndash;&gt;
        <a href="${iousContextPath}/business/index">
            <i class="icon"></i>
        </a>
    </#if>

    <span>借条详情</span>
</div>-->
<div class="find_loginPwd_title header" style="background:none;color:black;text-align:left;">
    <span style="border-left:0.1rem solid #40bfff;margin-left:0.2rem;">&nbsp;&nbsp;借条详情</span>
</div>
<section class="find_loginPwd_wrap">
    <input name="id" id="id" type="hidden" value="${ious.id}">
    <ul class="list">
        <li class="list_item">
            <label>
                <span class="item_text">　借款人</span>
                <span class="showcontent">${(ious.loaner)!""}<a href="javascript:loanAgreement()">&nbsp;查看详情</a></span>
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
    <!--
    借款人发起借条：
            借款人									出借人
    1 删除1,支付借条费用，生成二维码					    -
      删除1,支付借条费用，生成二维码					    (扫描二维码)确认借条1
    2.(查看详情,扫二维码)返回首页						(查看详情,扫二维码)返回首页

    出借人发起借条
    3.   -											删除2，生成二维码2
        (扫描二维码)确认借条2							删除2，生成二维码2
    4.(确认借条2)支付借条费用，生成二维码				    删除2，确认借条1
    5.(查看详情,扫二维码)返回首页						(查看详情,扫二维码)返回首页
    -->
    <#if ious.type == 2 || ious.type==5>
        <!--借条已经确认且生效-->
        <a href="javascript:goback();" class="next_step" style="width:1.7rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">返回</a>
    <#else>
        <!--type为1时-->
        <#if ious.type == 1>
            <!--当前登录人与借款人id一样，则为借款人-->
            <#if ious.loanUserId == user.id>
                <a href="javascript:sendSure();" class="next_step" style="width:2rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">生成二维码</a>
                <a href="javascript:payIous();" class="next_step" style="width:2.5rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">支付借条费用</a>
                <a href="javascript:deleteIous('${(ious.id)!0}');" class="next_step" style="width:1.7rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">删除</a>
            <#else>
                <!--出借人-->
                <a href="javascript:surePay('${(ious.id)!0}',2);" class="next_step" style="width:2rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">确认借条</a>
            </#if>
        </#if>
        <!--type为3时-->
        <#if ious.type == 3>
            <!--当前登录人与出借人id一样，则为出借人-->
            <#if ious.lendUserId == user.id>
                <a href="javascript:sendSure();" class="next_step" style="width:2rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">生成二维码</a>
                <a href="javascript:deleteIous('${(ious.id)!0}');" class="next_step" style="width:1.7rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">删除</a>
                <#else>
                <a href="javascript:updateLoanUserId('${(ious.id)!0}');" class="next_step" style="width:2rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">确认借条</a>
            </#if>
        </#if>

        <!--type为4时-->
        <#if ious.type == 4>
            <!--当前登录人与出借人id一样，则为出借人-->
            <#if ious.lendUserId == user.id>
                <!--<a href="javascript:sendSure();" class="next_step" style="width:2rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">生成二维码2</a>-->
                <!--出借人-->
                <a href="javascript:surePay('${(ious.id)!0}',5);" class="next_step" style="width:2rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">确认借条</a>
                <a href="javascript:deleteIous('${(ious.id)!0}');" class="next_step" style="width:1.7rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">删除</a>
            </#if>
            <#if ious.loanUserId == user.id>
                <a href="javascript:payIous();" class="next_step" style="width:2.5rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">支付借条费用</a>
               <!-- <a href="javascript:sendSure();" class="next_step" style="width:2rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">生成二维码</a>-->
            </#if>
        </#if>
    </#if>
</section>
<br/>
<br/>
<br/>
<br/>
</body>

<script type="text/javascript">

    //出借人发起二维码，借款人确认后才能支付费用
    function sureIous(iousId){
        layer.open({
             content: '<span style="color:red;font-size:0.3rem;">确认后即表示你对该借条认可且不可更改</span>'
            ,btn: ['确定', '取消']
            ,title: '确认借条'
            ,yes: function(index){
                layer.close(index);
                updateLoanUserId(iousId);
            }
        });
    }
    //更改用户借条信息，将该借条与该用户绑定
    function updateLoanUserId(iousId){
        $.ajax({
            type: "GET",
            url: "${iousContextPath}/api/business/updateLoanUserId",
            data:{id:iousId},
            beforeSend: function(request) {
                request.setRequestHeader("Authorization", "${token!''}");
            },
            success: function(data) {
               if(data.successful){
                    tip('确认成功');
                    setTimeout(function(){location.reload()},1000);
               }else {
                    tip(data.message);
               }
            }
        });
    }


     //确认借条
    function surePay(iousId,type){
        layer.open({
             content: '<span style="color:red;font-size:0.3rem;">确认后即表示该借条费用已经支付且该借条即刻起生效</span>'
            ,btn: ['确定', '取消']
            ,title: '确认借条'
            ,yes: function(index){
                layer.close(index);
                updateStatus(iousId,type);
            }
        });
    }

    //更新借条状态
    function updateStatus(iousId,type){
        $.ajax({
            type: "GET",
            url: "${iousContextPath}/api/business/updateStatus",
            data:{id:iousId,type:type},
            beforeSend: function(request) {
                request.setRequestHeader("Authorization", "${token!''}");
            },
            success: function(data) {
               if(data.successful){
                    tip('确认成功');
                    setTimeout(function(){goback();},1000);
               }else {
                    tip('确认失败，请重试');
               }
            }
        });
    }

    //删除借条
    function deleteIous(id){
       layer.open({
             content: '<span style="color:red;font-size:0.3rem;">如果删除该借条，已支付借条费用也无法退还，确认删除该借条吗？</span>'
            ,btn: ['确定', '取消']
            ,title: '删除借条'
            ,yes: function(index){
                layer.close(index);
                deletes(id);
            }
        });
    }
    //删除借条
    function deletes(id){
        $.ajax({
            type: "GET",
            url: "${iousContextPath}/api/business/delete",
            data:{id:id},
            beforeSend: function(request) {
                request.setRequestHeader("Authorization", "${token!''}");
            },
            success: function(data) {
               if(data.successful){
                    tip('删除成功');
                    setTimeout(function(){goback();},1000);
               }else {
                    tip('删除失败，请重试');
               }
            }
        });
    }

    //支付借条费用
    function payIous(){
       layer.open({
             content: '<img src="${iousRequestUrl}/img/wechat.png" onclick="wechatPay();"/>&nbsp;&nbsp;<img src="${iousRequestUrl}/img/zfb.png" onclick="zfbPay();"/>'
            ,btn: '关闭'
            ,title: '请选择付款方式'
        });
    }

    function wechatPay() {
        layer.closeAll();
        layer.open({
             content: '<img src="${iousRequestUrl}/img/1545753149.png"/><br/>请将二维码保存后打开微信支付'
            ,btn: '关闭'
            ,title: '微信支付'
        });
    }

    function zfbPay() {
        layer.closeAll();
        layer.open({
             content: '<img src="${iousRequestUrl}/img/1545753183.png" /><br/>请将二维码保存后打开支付宝支付'
            ,btn: '关闭'
            ,title: '支付宝支付'
        });
    }

    //生成二维码
    function sendSure(){
       layer.open({
             content: '<span style="font-size:0.3rem;color:red;">生成二维码前请确保借条费用已支付完成，二维码生成后请截图发给对方<span>'
            ,btn: ['生成', '取消']
            ,title: '生成二维码'
            ,yes: function(index){
                layer.close(index);
                getQrcode();
            }
        });
    }
    //生成二维码
    function getQrcode(){

        layer.open({
             content: '<span id="qrcode"></span>'
             ,btn: '关闭'
        });
        $('#qrcode').qrcode({
            render: "canvas", //也可以替换为table
            width: 200, //宽度
            height:200, //高度
            text: "${iousRequestUrl}/business/detail?iousId=${ious.id}&fromtype=${fromtype}&qrcode=qrcode"
        });

        var mycanvas1=document.getElementsByTagName('canvas')[0];

        //将转换后的img标签插入到html中
        var img=convertCanvasToImage(mycanvas1);

         $('#qrcode').html(img);//imagQrDiv表示你要插入的容器id

    }

    //从 canvas 提取图片 image
    function convertCanvasToImage(canvas) {
        //新Image对象，可以理解为DOM
        var image = new Image();
        // canvas.toDataURL 返回的是一串Base64编码的URL，当然,浏览器自己肯定支持
        // 指定格式 PNG
        image.src = canvas.toDataURL("image/png");
        return image;
    }


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
    //提示
    function tip(msg){
        layer.open({
            content: msg
            ,skin: 'msg'
            ,time: 3 //2秒后自动关闭
        });
    }

    function goback(){

       <#if from?? && from=='index'>
            //从主页过来的
           location.href="${iousContextPath}/business/index";
        <#elseif fromtype==1>
            <!--借款-->
            //location.href="${iousContextPath}/business/loan";
            location.href="${iousContextPath}/business/index";
        <#elseif fromtype==2>
            <!--出借-->
            //location.href="${iousContextPath}/business/lend";
            location.href="${iousContextPath}/business/index";
        </#if>
    }
</script>
</html>
