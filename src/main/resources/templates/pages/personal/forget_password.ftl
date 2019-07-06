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
    <title>忘记密码</title>

</head>
<body>
<header><span onclick="gotoLogin()"></span>忘记密码</header>
<form action="${iousContextPath}/saveForgetPassword" method="post" name="registerForm" id="registerForm" onsubmit="return check();">
    <ul class="form">
        <li><input type="text" placeholder="请输入手机号" value="${(registerInfo.phone)!""}" id="phone" name="phone" minlength="11" maxlength="11" required/></li>
        <li><input type="text" placeholder="请输入验证码" value="${(registerInfo.code)!""}" id="code" name="code" minlength="4" maxlength="4" required style="width: 65%" /><span id="confirm">获取验证码</span></li>
        <li><input type="password" placeholder="请输入新密码(6-20个字符,不能有空格)" id="tempPassword" minlength="6" maxlength="20" required/>
            <input type="hidden" id="password" name="password"/></li>
        <li><input type="password" placeholder="请确认新密码(6-20个字符,不能有空格)" id="tempSurePassword" minlength="6" maxlength="20" required/>
            <input type="hidden" id="surePassword" name="surePassword"/></li>
        <li><input type="submit" value="修&nbsp;&nbsp;&nbsp;&nbsp;改"/></li>
        <li style="color:red;text-align: center">${errorMsg!""}</li>
    </ul>
</form>
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

        if(!validationPassword()){
            return false;
        }

        var tempPassword = $.base64.btoa($('#tempPassword').val(), true);
        $('#password').val(tempPassword);

        var tempSurePassword = $.base64.btoa($('#tempSurePassword').val(), true);
        $('#surePassword').val(tempSurePassword);
        return true;
    }

    function validationPassword(){
        var  _$txtNewPwd = $('#tempPassword'), _$txtReNewPwd = $('#tempSurePassword');
        var allNumReg = /^\d+$/;
        var allLetReg = /^[A-Za-z]*$/;
        //先做验证
        if ($.trim(_$txtNewPwd.val()) == "") {
            tip('请输入新密码');
            return false;
        }
        else {
            var pwdStr = $.trim(_$txtNewPwd.val()).split(" ");
            if (pwdStr.length != 1) {
                tip('新密码长度在6-20个字符之间，不能有空格');
                return false;
            }
            else {
                if ($.trim(_$txtNewPwd.val()).length < 6 || $.trim(_$txtNewPwd.val()).length > 20) {
                    tip('新密码长度在6-20个字符之间，不能有空格');
                    return false;
                }
                else {
                    if (allNumReg.test($.trim(_$txtNewPwd.val()))) {
                        tip('新密码不能全部为数字');
                        return false;
                    }
                    if (allLetReg.test($.trim(_$txtNewPwd.val()))) {
                        tip('新密码不能全部为字母');
                        return false;
                    }
                }
            }
        }
        if ($.trim(_$txtReNewPwd.val()) != $.trim(_$txtNewPwd.val())) {
            tip('确认新密码与新密码不一致');
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