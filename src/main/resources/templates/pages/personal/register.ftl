<!DOCTYPE html>
<html>
<head lang="en">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="format-detection" content="email=no"/>
    <meta charset="UTF-8">
    <script src="${iousRequestUrl}/lib/js/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="${iousRequestUrl}/js/wapframwork.js" type="text/javascript" charset="utf-8"></script>
    <script src="${iousRequestUrl}/lib/js/layer.js" type="text/javascript" charset="utf-8"></script>
    <!--密码加解密-->
    <script src="${iousRequestUrl}/lib/js/jquery.base64.js" type="text/javascript" charset="utf-8"></script>

    <link href="${iousRequestUrl}/css/loginreset.css" rel="stylesheet" type="text/css">
    <link href="${iousRequestUrl}/css/loginbase.css" rel="stylesheet" type="text/css">
    <link href="${iousRequestUrl}/css/header.css" rel="stylesheet" type="text/css">
    <link href="${iousRequestUrl}/css/register.css" rel="stylesheet" type="text/css"/>
    <title>注册</title>

</head>
<body>
    <header><span onclick="gotoLogin()"></span>注册</header>
    <form action="${iousContextPath}/saveRegister" method="post" name="registerForm" id="registerForm" onsubmit="return check();">
        <ul class="form">
            <li><input type="text" placeholder="请输入手机号" value="${(registerInfo.phone)!""}" id="phone" name="phone" minlength="11" maxlength="11" required/></li>
            <li><input type="text" placeholder="请输入验证码" value="${(registerInfo.code)!""}" id="code" name="code" minlength="6" maxlength="6" required style="width: 65%" /><button type="button" id="confirm" onclick="sendCode()">获取验证码</button></li>
            <li><input type="text" placeholder="用户真实姓名" value="${(registerInfo.name)!""}" id="name" name="name" minlength="2" maxlength="10" required/></li>
            <li><input type="password" placeholder="请输入登录密码(6-20个字符,不能有空格)" id="tempPassword" minlength="6" maxlength="20" required/>
                <input type="hidden" id="password" name="password"/></li>
            <li><input type="password" placeholder="请确认登录密码(6-20个字符,不能有空格)" id="tempSurePassword" minlength="6" maxlength="20" required/>
                <input type="hidden" id="surePassword" name="surePassword"/></li>
            <li><input type="password" placeholder="请输入交易密码(6位数字)" id="tempTradingPassword" minlength="6" maxlength="6" required/>
                <input type="hidden" id="tradingPassword" name="tradingPassword"/></li>
            <li><input type="password" placeholder="请确认交易密码(6位数字)" id="tempSureTradingPassword" minlength="6" maxlength="6" required/>
                <input type="hidden" id="sureTradingPassword" name="sureTradingPassword"/></li>
            <li><input type="submit" value="注&nbsp;&nbsp;&nbsp;&nbsp;册"/></li>
            <li style="color:red;text-align: center">${errorMsg!""}</li>
        </ul>
    </form>
<input type="hidden" id="requestId"/>
</body>
<script>
    (function (doc, win) {
        var docEl = doc.documentElement,
            resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
            recalc = function () {
                var clientWidth = docEl.clientWidth;
                if (!clientWidth) return;
                docEl.style.fontSize = 100 * (clientWidth / 750) + 'px';
            };
        if (!doc.addEventListener) return;
        win.addEventListener(resizeEvt, recalc, false);
        doc.addEventListener('DOMContentLoaded', recalc, false);
    })(document, window);

    function gotoLogin() {
        location.href = '${iousRequestUrl}/login'
    }
    //用户名密码校验需要这里处理。
    function check(){

        var phone = $('#phone').val();
        if(!isPoneAvailable(phone)){
            tip('请输入有效电话号码');
            return false;
        }

        if(!validationTradingPassword()){
            return false;
        }

        if(!validationPassword()){
            return false;
        }

        if(!checkSmsCode()){
            return false;
        }

        var tempTradingPassword = $.base64.btoa($('#tempTradingPassword').val(), true);
        $('#tradingPassword').val(tempTradingPassword);

        var tempSureTradingPassword = $.base64.btoa($('#tempSureTradingPassword').val(), true);
        $('#sureTradingPassword').val(tempSureTradingPassword);

        var tempPassword = $.base64.btoa($('#tempPassword').val(), true);
        $('#password').val(tempPassword);

        var tempSurePassword = $.base64.btoa($('#tempSurePassword').val(), true);
        $('#surePassword').val(tempSurePassword);

        return true;
    }

    function checkSmsCode(){
       var phone = $('#phone').val();
        var flag = false;
        $.ajax({
            type: "GET",
            async: false,//使用同步的方式,true为异步方式
            url: "${iousContextPath}/api/business/checkSmsCode",
            data:{phone: phone},
            success: function(data) {
                if(data.successful){
                    flag = true;
                }else {
                    tip(data.message);
                    flag = false;
                }
            }
        });
        return flag;
    }

    //发送验证码
    function sendCode() {
        var phone = $('#phone').val();
        if(!isPoneAvailable(phone)){
            tip('请输入有效电话号码');
            return;
        }
        //发送验证码
        $.ajax({
            type: "GET",
            url: "${iousContextPath}/api/business/sendSms",
            data:{phone:phone},
            success: function(data) {
                if(data.successful){
                    //倒计时
                    countdown();
                }else {
                    tip(data.message);
                }
            }
        });
    }

    function countdown(){
        var i = 60;
        $('#confirm').css('background','#ddd');
        $('#confirm').attr('disabled',true);
        var time = setInterval(function(){
            $('#confirm').html('重新发送(' + i + 's)');
            if(i == 0){
                $('#confirm').css('background','#2d9cff');
                $('#confirm').attr('disabled',false);
                $('#confirm').html('获取验证码');
                clearInterval(time);
            }
            i--;
        },1000)
    }

    function isPoneAvailable(phone) {
        var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
        if(phone){
            if (!myreg.test(phone)) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    function validationPassword(){
        var  _$txtNewPwd = $('#tempPassword'), _$txtReNewPwd = $('#tempSurePassword');
        var allNumReg = /^\d+$/;
        var allLetReg = /^[A-Za-z]*$/;
        //先做验证
        if ($.trim(_$txtNewPwd.val()) == "") {
            tip('请输入登录密码');
            return false;
        }
        else {
            var pwdStr = $.trim(_$txtNewPwd.val()).split(" ");
            if (pwdStr.length != 1) {
                tip('登录密码长度在6-20个字符之间，不能有空格');
                return false;
            }
            else {
                if ($.trim(_$txtNewPwd.val()).length < 6 || $.trim(_$txtNewPwd.val()).length > 20) {
                    tip('登录密码长度在6-20个字符之间，不能有空格');
                    return false;
                }
                else {
                    if (allNumReg.test($.trim(_$txtNewPwd.val()))) {
                        tip('登录密码不能全部为数字');
                        return false;
                    }
                    if (allLetReg.test($.trim(_$txtNewPwd.val()))) {
                        tip('登录密码不能全部为字母');
                        return false;
                    }
                }
            }
        }
        if ($.trim(_$txtReNewPwd.val()) != $.trim(_$txtNewPwd.val())) {
            tip('确认登录密码与登录密码不一致');
            return false;
        }
        return true;
    }
    //交易密码
    function validationTradingPassword(){
        var _$txtNewPwd = $('#tempTradingPassword'), _$txtReNewPwd = $('#tempSureTradingPassword');
        var allNumReg = /^\d+$/;
        //先做验证
        if ($.trim(_$txtNewPwd.val()) == "") {
            tip('请输入交易密码');
            return false;
        }
        else {
            var pwdStr = $.trim(_$txtNewPwd.val()).split(" ");
            if (pwdStr.length != 1) {
                tip('交易密码长度为6位数字，且不能有空格');
                return false;
            }
            else {
                if ($.trim(_$txtNewPwd.val()).length != 6) {
                    tip('交易密码长度为6位数字，且不能有空格');
                    return false;
                }
                else {
                    if (!allNumReg.test($.trim(_$txtNewPwd.val()))) {
                        tip('交易密码必须为6数字，且不能有空格');
                        return false;
                    }
                }
            }
        }
        if ($.trim(_$txtReNewPwd.val()) != $.trim(_$txtNewPwd.val())) {
            tip('确认交易密码与交易密码不一致');
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

</script>
</html>