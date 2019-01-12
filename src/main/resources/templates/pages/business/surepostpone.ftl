<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MF服务</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
    <meta name="format-detection" content="telephone=no, email=no" />
    <link rel="stylesheet" href="${iousRequestUrl}/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${iousRequestUrl}/css/index.css?v=1.2.1"/>
    <link rel="stylesheet" type="text/css" href="${iousRequestUrl}/css/myindex.css"/>
    <link rel="stylesheet" href="${iousRequestUrl}/css/personalCenter.css?v=1.2.2" type="text/css">
    <link rel="stylesheet" type="text/css" href="${iousRequestUrl}/lib/css/layer.css"/>
    <script src="${iousRequestUrl}/lib/js/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="${iousRequestUrl}/lib/js/jquery.base64.js" type="text/javascript" charset="utf-8"></script>
    <script src="${iousRequestUrl}/lib/js/layer.js" type="text/javascript" charset="utf-8"></script>
    <script src="${iousRequestUrl}/js/wapframwork.js" type="text/javascript" charset="utf-8"></script>
    <script src="${iousRequestUrl}/js/myindex.js" type="text/javascript" charset="utf-8"></script>
    <!--生成二维码-->
    <script src="${iousRequestUrl}/lib/js/jquery.qrcode.min.js"></script>
    <style>
        .my_content_title li{
            float:left;
            width:3.55rem;
            height:0.8rem;
            float:left;
            text-align:left;
            font-size:0.3rem;
            border-bottom:0px solid red;
            padding-left:0.2rem;
            line-height:0.8rem;
        }
        .my_content_item li{
            float:left;
            width:2.34rem;
            height:1rem;
            text-align:center;
            font-size:0.35rem;
            border:0px solid red;
            line-height:1rem;
        }

        .next_step{width: 6.7rem;height: 0.92rem;line-height: 0.92rem;display: block;text-align: center;color: #fff;font-size: 0.32rem;margin:0.34rem auto;background-image: -webkit-linear-gradient(0deg,#40bfff 20%,#0197ff 40%,#40bfff 80%);border-radius: 0px;}
        .tradingPassword{width:4.8rem;height:1rem;border:1px solid #e6e6e6;text-align:center;font-size:0.3rem;}
        .forgetTradingPassword{float:right;margin-top:0.2rem;margin-right:0.4rem;}
    </style>

</head>
<body style="background:#eff0f1;">
<!--页面主体内容部分-->
<div class="my_content" style="border:0px solid red;margin-top:0rem;">
    <div style="width:7.5rem;border-bottom:1px solid #eff0f1;height:3.5rem;background:#ffffff">
        <ul class="my_content_item" style="border-bottom:1px solid #DDD;width:7.5rem;height:1rem;margin-left:0rem;">
            <li><span style="border-left:0.1rem solid #40bfff;margin-left:0rem;">&nbsp;&nbsp;借条详情</span></li>
        </ul>
        <ul class="my_content_title">
            <li><span style="color:#999;">借款人</span><span style="padding-left:0.2rem;color:black;">${(ious.loaner)!""}</span></li>
            <li><span style="color:#999;">借款日期</span><span style="padding-left:0.2rem;color:black;">${(ious.loanTime)!""}</span></li>
            <li><span style="color:#999;">出借人</span><span style="padding-left:0.2rem;color:black;">${(ious.lender)!""}</span></li>
            <li><span style="color:#999;">还歀日期</span><span style="padding-left:0.2rem;color:black;">${(ious.returnTime)!""}</span></li>
            <li><span style="color:#999;">借款金额</span><span style="padding-left:0.2rem;color:black;">${(ious.money)!0}&nbsp;元</span></li>
            <li><span style="color:#999;">借款利率</span><span style="padding-left:0.2rem;color:black;">${(ious.rateName)!""}</span></li>
        </ul>
    </div>

    <div style="width:7.5rem;border-bottom:1px solid #eff0f1;min-height:0.8rem;background:#ffffff;margin-top:0.2rem;">
        <ul class="my_content_item" style="border-bottom:1px solid #DDD;width:7.5rem;height:1rem;margin-left:0rem;">
            <li><span style="border-left:0.1rem solid #40bfff;margin-left:0rem;">&nbsp;&nbsp;展期信息</span></li>
        </ul>
        <ul class="my_content_title" style="height:0.8rem;">
            <li style="width:1.7rem;text-align:center;">金额</li>
            <li style="width:1.7rem;text-align:center;">利率</li>
            <li style="width:1.7rem;text-align:center;">时间</li>
            <li style="width:1.6rem;text-align:center;">状态</li>
        </ul>
        <#if postpone??>
            <ul class="my_content_title" style="border:0px solid red;height:0.8rem;">
                <li style="width:1.7rem;text-align:center;">${(postpone.money)!0} 元</li>
                <li style="width:1.7rem;text-align:center;">${(postpone.postponeRateName)!''}</li>
                <li style="width:1.7rem;text-align:center;">${(postpone.poastponeTime)!''}</li>
                <li style="width:1.6rem;text-align:center;">${(postpone.postponeStatusName)!''}</li>
            </ul>
        </#if>
    </div>
</div>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<ul class="footer" style="background:white;padding-bottom:0.1rem;">
    <li class="footer-item">
    </li>
    <li class="footer-item">
    </li>
    <li class="footer-item">
        <a href="javascript:payIous();" class="next_step" style="width:1.7rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">支付</a>
    </li>
</ul>
<script type="text/javascript" charset="utf-8">
function doPostPone(){
   layer.open({
         content: '<span style="color:red;font-size:0.3rem;">展期到'+$('#poastponeTime').val()+',利率为'+$('#postponeRate').val()+'%</span>'
        ,btn: ['确定', '取消']
        ,title: '发起展期'
        ,yes: function(index){
            layer.close(index);
            inputPassword();
        }
    });
}


//输入密码
function inputPassword() {

     layer.open({
        title: [
          '输入交易密码',
          'background-color: #40bfff; color:#fff;'
        ]
        ,content: '<input type="tel" style="-webkit-text-security:disc" maxlength="6" class="tradingPassword" name="tradingPassword" id="tradingPassword" placeholder="输入6位交易密码"/>'+
                  '<br/><a href="javascript:void(0)" class="forgetTradingPassword">忘记密码</a>'
        ,btn: ['确定', '取消']
        ,shadeClose: false
        ,yes: function(index){
              checkTradingPassword();
         }
      });
}

//检查输入密码是否正确
function checkTradingPassword(){
    if(checkPwd()){
        //加密
        var tradingPassword = $.base64.btoa($('#tradingPassword').val(), true);
        $.ajax({
            type: "GET",
            url: "${iousContextPath}/api/business/checkTradingPassword",
            data:{tradingPassword:tradingPassword},
            beforeSend: function(request) {
                request.setRequestHeader("Authorization", "${token!''}");
            },
            success: function(data) {
               if(data.successful){
                    postpone();
               }else {
                    tip('密码不对');
               }
            }
        });
    }
}

//判断是否是数字
function checkNumber(value) {
    var regular = "^[0-9]*$";
    var reg = new RegExp(regular);
    if (!reg.test(value)){
        //不是数字
        return false;
    }else{
         return true;
    }
}
//判断交易密码是否合法
function checkPwd(){
    var tradingPassword = $('#tradingPassword').val();
    if(!tradingPassword){
        tip('交易密码不能为空');
        return false;
    }

    if(tradingPassword.length < 6 || tradingPassword.length > 6 || !checkNumber(tradingPassword)){
        tip('交易密码为6位数字');
        return false;
    }

    return true;
}
//发起展期
function postpone(){

    var poastponeTime = $('#poastponeTime').val();
    var postponeRate = $('#postponeRate').val();
    var id = $('#id').val();

    $.ajax({
        type: "POST",
        url: "${iousContextPath}/api/business/postpone",
        data:{poastponeTime:poastponeTime,postponeRate:postponeRate,iousId:id},
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", "${token!''}");
        },
        success: function(data) {
           if(data.successful){
                tip('展期成功');
                setTimeout(function(){goback();},1000);
           }else {
                tip('展期失败，请重试');
           }
        }
    });
}

//生成二维码
function getQrcode(id, iousId){

    layer.open({
         content: '<span id="qrcode"></span>'
         ,btn: '关闭'
    });
    $('#qrcode').qrcode({
        render: "canvas", //也可以替换为table
        width: 200, //宽度
        height:200, //高度
        text: "${iousRequestUrl}/business/detail?iousId=iousId"
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

//提示
function tip(msg){
    layer.open({
        content: msg
        ,skin: 'msg'
        ,time: 2 //2秒后自动关闭
    });
}
</script>
</body>

</html>
