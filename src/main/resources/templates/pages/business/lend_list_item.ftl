<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>众联成服务</title>
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
    <style>
        .my_content_title li{
            float:left;
            width:2.44rem;
            height:1.5rem;
            text-align:center;
            font-size:0.35rem;
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

        .my_content_title p{
            font-size:0.25rem;
            margin-top:-0.4rem;
        }
        .my_content .search{
            width:7.5rem;
            height:1.1rem;
            border:0px solid red;
            background:#6666661f;
        }
        .my_content .search input{
            width:7rem;
            height:0.8rem;
            margin-left:0.2rem;
            margin-top:0.1rem;
            margin-bottom:0.1rem;
            border:1px solid #eff0f1;
            font-size:0.3rem;
            padding-left:0.1rem;
        }
        .my_content_list .ul_1 .ul_1_li{
            width:7.5rem;
            height:0.8rem;
            border:0px solid red;
            border-bottom:1px solid #DDD;
            border-radius:0px;
            margin-left:0rem;
            margin-top:0rem;
            background:white;
        }
        .next_step{width: 6.7rem;height: 0.92rem;line-height: 0.92rem;display: block;text-align: center;color: #fff;font-size: 0.32rem;margin:0.34rem auto;background-image: -webkit-linear-gradient(0deg,#40bfff 20%,#0197ff 40%,#40bfff 80%);border-radius: 0.1rem;}
        .tradingPassword{width:4.8rem;height:1rem;border:1px solid #e6e6e6;text-align:center;font-size:0.3rem;}
        .forgetTradingPassword{float:right;margin-top:0.2rem;margin-right:0.4rem;}
    </style>

</head>
<body style="background:#eff0f1;">
<!--页面主体内容部分-->
<div class="my_content" style="border:0px solid red;margin-top:0rem;">
    <div style="width:7.5rem;border-bottom:1px solid #eff0f1;height:2.5rem;background:#ffffff">
       <ul class="my_content_item" style="border-bottom:1px solid #DDD;width:7.1rem;height:1rem;margin-left:0.2rem;">
           <li >借款人:${ious.name}</li>
           <li></li>
           <li onclick="gotoDetail(${(ious.id)!0})">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${ious.purposeName} <img src="${iousRequestUrl}/img/right.png" style="width:0.3rem;height:0.3rem;position:relative;top:0.05rem;"></li>
       </ul>
       <ul class="my_content_title">
           <li>待收总额<p>${ious.principal} 元</p></li><li style="width:1px;height:1rem;border-left:1px solid #dbe4eb;margin-top:0.2rem;"></li>
           <li>出借本金<p>${ious.money} 元</p></li><li style="width:1px;height:1rem;border-left:1px solid #dbe4eb;margin-top:0.2rem;"></li>
           <li>利息<p>${ious.interest} 元</p></li>
       </ul>
    </div>
    <div style="width:7.5rem;border-bottom:1px solid #eff0f1;height:0.5rem;margin-top:0.2rem;">
        <ul class="my_content_title" id="my_content_title" style="border:0px solid red;background:white;height:0.8rem;line-height:0.8rem;">
            <li class="my_content_title_active" style="height:0.8rem;line-height:0.8rem;">待收金额</li>
           <!-- <li style="height:0.8rem;line-height:0.8rem;">收款记录</li>
            <li style="height:0.8rem;line-height:0.8rem;">待确认金额</li>-->
        </ul>
    </div>

    <div class="my_content_list" style="margin-top:0.31rem;">
        <ul class="ul_1">
            <li class="ul_1_li">
                <div style="width:6.6rem;height:0.8rem;margin-left:0.2rem;border-bottom:1px dotted #DDD;">
                    <table class="my_content_list_table_1" style="line-height:0.8rem;">
                        <tr>
                            <td>&nbsp;&nbsp;${ious.money}&nbsp;&nbsp;元</td>
                            <#if (ious.remainTime)?? && ((ious.remainTime)?number) lt 0 >
                                <td style="text-align:right;color:orange;">已逾期${(ious.remainTime)?abs}天</p></td>
                            <#else>
                                <td style="text-align:right;">剩余${ious.remainTime}天</p></td>
                            </#if>
                        </tr>
                    </table>
                </div>
            </li>
        </ul>
    </div>
</div>
<!--	页面底部部分-->
<#if ious.cancelStatus == 1>
    <ul class="footer" style="background:white;padding-bottom:0.1rem;">
        <li class="footer-item">
        </li>
        <li class="footer-item">
            <a href="javascript:inputPassword('${(ious.id)!0}');" class="next_step" style="width:1.7rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">销账</a>
        </li>
        <li class="footer-item">
            <a href="javascript:gotoPostpone('${(ious.id)!0}');" class="next_step" style="width:1.7rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">展期</a>
        </li>
    </ul>
</#if>
<script type="text/javascript" charset="utf-8">
//到发起展期页面
function gotoPostpone(id){
    location.href='${iousContextPath}/business/postpone?iousId='+id;
}

//输入密码
function inputPassword(id) {

     layer.open({
        title: [
          '输入交易密码',
          'background-color: #40bfff; color:#fff;'
        ]
        ,content: '<input type="tel" style="-webkit-text-security:disc" maxlength="6" class="tradingPassword" name="tradingPassword" id="tradingPassword" placeholder="输入6位交易密码"/>'/*+
                  '<br/><a href="javascript:void(0)" class="forgetTradingPassword">忘记密码</a>'*/
        ,btn: ['确定', '取消']
        ,shadeClose: false
        ,yes: function(index){
              checkTradingPassword(id);
         }
      });
}

//检查输入密码是否正确
function checkTradingPassword(id){
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
                    cancel(id);
               }else {
                    tip(data.message);
               }
            }
        });
    }
}

//销账
function cancel(id) {
    $.ajax({
        type: "GET",
        url: "${iousContextPath}/api/business/cancel",
        data:{id:id},
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", "${token!''}");
        },
        success: function(data) {
           if(data.successful){
                tip('销账成功');
                setTimeout(function(){location.reload()},1000);
           }else {
                tip(data.message);
           }
        }
    });
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



//提示
function tip(msg){
    layer.open({
        content: msg
        ,skin: 'msg'
        ,time: 2 //2秒后自动关闭
    });
}

//跳转到详细页面
function gotoDetail(id) {
    location.href='${iousContextPath}/business/lendItemDetail?iousId='+id;
}

</script>
</body>

</html>
