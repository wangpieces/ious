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
    <a href="${iousContextPath}/business/index">
        <i class="icon"></i>
    </a>

    <span>借条详情</span>
</div>-->
<div class="find_loginPwd_title header" style="background:none;color:black;text-align:left;">
    <span style="border-left:0.1rem solid #40bfff;margin-left:0.2rem;">&nbsp;&nbsp;借条详情</span>
</div>
<section class="find_loginPwd_wrap" style="text-align:center;">
    <p style="font-size:0.3rem;margin-top:0.5rem;">没找对应的借条信息,请<a href="javascript:goback()" style="color:blue">返回</a></p>
</section>
</body>

<script type="text/javascript">

    function goback(){
        location.href="${iousContextPath}/business/index";
    }
</script>
</html>
