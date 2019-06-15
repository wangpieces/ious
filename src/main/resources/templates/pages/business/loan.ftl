
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>借款</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
    <meta name="format-detection" content="telephone=no, email=no" />
    <link rel="stylesheet" href="${iousRequestUrl}/lib/css/layer.css" type="text/css">
    <link rel="stylesheet" href="${iousRequestUrl}/css/common.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${iousRequestUrl}/css/personalCenter.css"/>
    <script src="${iousRequestUrl}/lib/js/jquery-2.1.4.js"></script>wa
    <script src="${iousRequestUrl}/lib/js/jquery.base64.js"></script>
    <script src="${iousRequestUrl}/lib/js/layer.js"></script>
    <script src="${iousRequestUrl}/js/wapframwork.js"></script>
    <script src="${iousRequestUrl}/js/common.js"></script>
    <style>
        .tradingPassword{width:4.8rem;height:1rem;border:1px solid #e6e6e6;text-align:center;font-size:0.3rem;}
        .forgetTradingPassword{float:right;margin-top:0.2rem;margin-right:0.4rem;}
    </style>
</head>
<body>
<div class="find_loginPwd_title header">
    <a href="${iousContextPath}/business/index">
        <i class="icon"></i>
    </a>
    <span>我是借款人</span>
</div>
<section class="find_loginPwd_wrap">
    <form action="${iousContextPath}/api/business/saveIousData" method="post" name="saveIousDataForm" id="saveIousDataForm">
        <input name="type" id="type" type="hidden" value="1">
        <ul class="list">
            <li class="list_item">
                <label>
                    <span class="item_text">借款金额</span>
                    <input type="text" name="money"  id="money" minlength="1" maxlength="9" placeholder="借款金额" onblur="caculatPrincipal()">元
                </label>
            </li>
            <li class="list_item">
                <label>
                    <span class="item_text">借款用途</span>
                    <select name="purpose" id="purpose">
                    <#if purposeList??>
                        <#list purposeList as purpose>
                            <option value="${purpose.value}">${purpose.name}</option>
                        </#list>
                    </#if>
                </select>
                </label>
            </li>
            <li class="list_item">
                <label>
                    <span class="item_text">借款日期</span>
                    <input type="date" name="loanTime" id="loanTime" placeholder="借款日期"  style="width:5.0rem;" value="${dateTime}">
                </label>
            </li>
            <li class="list_item">
                <label>
                    <span class="item_text">还歀日期</span>
                    <input type="date" name="returnTime" id="returnTime" placeholder="借款日期" style="width:5.0rem;" value="${dateTime}">
                </label>
            </li>
            <li class="list_item">
                <label>
                    <span class="item_text">年化利率</span>
                    <select  name="rate" id="rate">
                        <#if rateList??>
                            <#list rateList as rate>
                                <option value="${rate.value}">${rate.name}</option>
                            </#list>
                        </#if>
                    </select>
                </label>
            </li>
            <li class="list_item">
                <label style="float:right;margin-right:0.3rem;font-size:0.3rem;color:#888;">
                    本金<span id="principal">0</span>+利息<span id="interest">0</span>=到期本息<span id="totalMoney" style="color:red;">0</span>元
                </label>
            </li>
            <li class="list_item">
                <label>
                    <span class="item_text" style="width:2.7rem;">对方姓名(出借人)</span>
                    <input type="text" name="lender" id="lender" placeholder="对方姓名(出借人)" minlength="1" maxlength="10" style="width:4rem;">
                </label>
            </li>
            <li class="list_item">
                <label style="font-size:0.3rem;">
                    <input type="checkbox"  onclick="agreeAgreement()" name="agree" id="agree"style="width:0.5rem;margin-left:0.2rem;">已同意并阅读<a href="javascript:loanAgreement()" style="color:red;">《借款协议》</a><a href="javascript:otherAgreement()" style="color:red;">《居间服务协议》</a>
                </label>
            </li>
        </ul>
        <input type="reset" id="reset" style="display:none;" />
    </form>
    <p class="text_desc" style="color:red;">提示：<br/>请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，
        请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，请输入您在投呗绑定的手机号，
        我们会将您的密码发送至您的，请输入您在投呗绑定的手机号，我们会将您的密码发送至您的
    <a href="javascript:initiate();" class="next_step_click_before" id="nextstep">确定</a>
</section>

</body>

<script type="text/javascript">

    //发起借条
    function initiate() {
        var isChecked = $('#agree').prop('checked');
        if(isChecked && checkInfo()){
            layer.open({
                content: '<p style="color:red;font-weight:bold;">借条是为已完成的借贷行为补一张借条，做为电子凭证，请确保你们线下交易完成</p><br/>'+
                         '对方确认后借条即刻生效，要发起该借条吗？'
                ,btn: ['发起', '放弃']
                ,yes: function(index){
                    layer.close(index);
                    setTimeout(function(){
                        inputPassword();
                    },300);
                }
                , style: 'font-size:0.30rem;text-align:center;'
              });
        }
    }
    //输入密码
    function inputPassword() {

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
                        saveIousData();
                   }else {
                        tip('密码不对');
                   }
                }
            });
        }
    }

    function saveIousData() {

       var targetUrl = $("#saveIousDataForm").attr("action");
       var data = $("#saveIousDataForm").serialize();
       $.ajax({
            type:'post',
            url:targetUrl,
            cache: false,
            data:data,
            dataType:'json',
            beforeSend: function(request) {
                request.setRequestHeader("Authorization", "${token!''}");
            },
            success:function(data){
               if(data.successful){
                    //清空当前页面数据 关闭弹窗
                    $('#reset').click();
                    layer.closeAll();
                    //跳转到详细页面
                    location.href='${iousContextPath}/business/detail?iousId='+data.data+'&fromtype=1';
               }else{
                  tip('发起借条失败，请重试');
               }
            },
            error:function(){
                tip("请求失败")
            }
       });
    }
    //校验必填信息是否正确
    function checkInfo(){
        var money = $('#money').val();
        if(!money){
            tip('借款金额不能为空')
            return false;
        }
        if(!validationNumber(money,2)){
            tip('借款金额只能为数字且最多精确到两位小数');
            return false;
        }

        var lender = $('#lender').val();
        if(!lender){
            tip('出借人姓名不能为空')
            return false;
        }
        if(lender.length < 1 || lender.length > 10){
            tip('出借人姓名长度必须介于1到10个字符之间')
            return false;
        }

        return true;
    }


</script>
</html>
