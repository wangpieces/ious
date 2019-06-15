<!DOCTYPE html>
<html>
<head lang="en">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no, email=no"/>
    <meta property="wb:webmaster" content="xxxxxxx"/>
    <meta charset="UTF-8">
    <title>登录</title>
    <script src="${iousRequestUrl}/lib/js/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="${iousRequestUrl}/js/wapframwork.js" type="text/javascript" charset="utf-8"></script>

    <script src="${iousRequestUrl}/lib/js/layer.js" type="text/javascript" charset="utf-8"></script>
    <!--密码加解密-->
    <script src="${iousRequestUrl}/lib/js/jquery.base64.js" type="text/javascript" charset="utf-8"></script>
    <!--头部-->
    <script src="${iousRequestUrl}/lib/js/RainbowBridge.js" type="text/javascript" charset="utf-8"></script>

    <link href="${iousRequestUrl}/css/loginreset.css" rel="stylesheet" type="text/css">
    <link href="${iousRequestUrl}/css/loginbase.css" rel="stylesheet" type="text/css">
    <link href="${iousRequestUrl}/css/header.css" rel="stylesheet" type="text/css">
    <link href="${iousRequestUrl}/css/login.css" rel="stylesheet" type="text/css"/>

    <script>
        //用户名密码校验需要这里处理。
        function check(){
            var temppassword = $.base64.btoa($('#temppassword').val(), true);
            $('#password').val(temppassword);
            return true;
        }

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
    </script>
</head>
<body>
<form action="${iousContextPath}/index" method="post" name="loginForm" id="loginForm" onsubmit="return check();">
    <#--<header><span onclick="javascript:history.go(-1)"></span>登录</header>-->
    <input type="hidden" value="${sureIousUrl!''}" name="sureIousUrl">
    <div class="head"></div>
    <form action="">
        <ul class="form">
            <li><input type="text" name="phone" id="phone" placeholder="请输入手机号" value="${phone!''}" minlength="11" maxlength="11" required/></li>
            <li><input type="password" id="temppassword" placeholder="请输入密码"  value="" minlength="6" maxlength="18" required/>
                <input type="hidden" id="password" name="password"/></li>
            <li><input type="submit" value="登&nbsp;&nbsp;&nbsp;&nbsp;录"/></li>
        </ul>
    </form>
    <ul class="fix miss">
        <li class="l"><a href="javascript:void(0)">忘记密码？</a></li>
        <li class="r"><a href="javascript:void(0)">账号注册</a></li>
    </ul>
    <div id="fifth" class="g6" style="color:orangered;text-align: center;">${errorMsg!""}</span></div>
    <#-- <div id="fifth" class="g6"><span class="mr20"></span>第三方登录<span class="ml20"></span></div>
    <ul class="df jcsb" id="footer">
       <li><img src="images/login/icon1.png" alt=""/><p>QQ</p></li>
       <li><img src="images/login/icon2.png" alt=""/><p>微信</p></li>
       <li><img src="images/login/icon3.png" alt=""/><p>微博</p></li>
   </ul>-->
</form>
</body>
</html>