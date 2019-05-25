<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no, email=no"/>
    <meta property="wb:webmaster" content="xxxxxxx"/>
    <link rel="stylesheet" href="${iousRequestUrl}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${iousRequestUrl}/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${iousRequestUrl}/lib/css/layer.css"/>
    <script src="${iousRequestUrl}/lib/js/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="${iousRequestUrl}/js/wapframwork.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<form action="${iousContextPath}/index" method="post" name="loginForm" id="loginForm" onsubmit="return check();">
    <div class="login_bg">
        <img src="${iousRequestUrl}/img/login_bg.png">
        <input type="hidden" value="${sureIousUrl!''}" name="sureIousUrl">
        <div>
            <div class="login_title" style="display: none;">
                <a href="index.html">
                    <i class="icon"></i>
                </a>
                <a href="reg.html">
                    <span>注册</span>
                </a>
            </div>
            <!--登录内容部分-->
            <div class="login_content">
                <div class="username">
                    <i class="icon icon_user" style="top:0.7rem;"></i>
                    <input type="text" name="phone" id="phone" placeholder="手机号" value="${phone!'18060484449'}" minlength="11" maxlength="11" required/>
                </div>
                <div class="password">
                    <i class="icon icon_pwd" style="top:0.7rem;"></i>
                    <input type="password" id="temppassword" placeholder="密码"  value="123456" minlength="6" maxlength="18" required/>
                    <input type="hidden" id="password" name="password"/>
                    <img class="icon_eye" src="${iousRequestUrl}/img/bkj.png"/>
                </div>
                <button type="submit" class="btn login_btn">登录</button>
                <p class="forget_btn">
                    <a href="forgetPwd.html">忘记密码?</a>
                </p>
                <p class="forget_btn">
                   ${errorMsg!""}
                </p>
            </div>
        </div>
    </div>
</form>
</body>

<script src="${iousRequestUrl}/lib/js/layer.js" type="text/javascript" charset="utf-8"></script>
<!--密码加解密-->
<script src="${iousRequestUrl}/lib/js/jquery.base64.js" type="text/javascript" charset="utf-8"></script>
<!--头部-->
<script src="${iousRequestUrl}/lib/js/RainbowBridge.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    setTitle(".login_title", {'title': '登录'});

    //用户名密码校验需要这里处理。
    function check(){
       var temppassword = $.base64.btoa($('#temppassword').val(), true);
       $('#password').val(temppassword);
       return true;
    }

</script>

</html>
