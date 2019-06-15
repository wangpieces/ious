
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
    <meta name="format-detection" content="telephone=no, email=no" />
    <link rel="stylesheet" href="${iousRequestUrl}/lib/css/layer.css" type="text/css">
    <link rel="stylesheet" href="${iousRequestUrl}/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${iousRequestUrl}/css/index.css"/>
    <script src="${iousRequestUrl}/lib/js/jquery-2.1.4.js"></script>
    <script src="${iousRequestUrl}/lib/js/jquery.base64.js"></script>
    <script src="${iousRequestUrl}/lib/js/layer.js"></script>
    <script src="${iousRequestUrl}/js/wapframwork.js"></script>
</head>
<body>
<div class="setting_title">
    <a href="${iousContextPath}/business/index">
        <i class="icon"></i>
    </a>
    <span>个人中心</span>
</div>
<div class="setting_content">
    <#--<div class="jifen">
        <a href="myjf.html">
            <div>
                <span class="icon_wrap icon_wrap_jifen">
                    <i class="icon icon_jifen"></i>
                </span>
                <span class="menu_item">我的积分</span>
                <span class="vip_class"><img class="vip_logo" src="../img/vip.png"/><span class="vip_detail"></span></span>
                <i class="icon_zhankai"></i>
            </div>
        </a>
    </div>-->
    <ul class="setting_menu">
        <li class="setting_menu_item">
            <a href="javascript:void(0)" onclick="modifyPassword(1)">
                <div>
                    <span class="icon_wrap">
                        <i class="icon icon_aqsz"></i>
                    </span>
                    <span class="item_title">登录密码修改</span>
                    <i class="icon_zhankai"></i>
                </div>
            </a>
        </li>
        <li class="setting_menu_item">
            <a href="javascript:void(0)" onclick="modifyPassword(2)">
                <div>
                    <span class="icon_wrap">
                        <i class="icon icon_aqsz"></i>
                    </span>
                    <span class="item_title">交易密码修改</span>
                    <i class="icon_zhankai"></i>
                </div>
            </a>
        </li>
        <li class="setting_menu_item">
            <a href="javascript:void(0);" onclick="gotoContactUs()">
                <div>
                    <span class="icon_wrap">
                        <i class="icon icon_contact"></i>
                    </span>
                    <span class="item_title">联系我们</span>
                    <span class="work_time">法定工作日：9:00-18:00</span>
                    <i class="icon_zhankai"></i>
                </div>
            </a>
        </li>
    </ul>
</div>
<a href="javascript:void(0);" class="setting_logout" onclick="logout()">
    安全退出
</a>
</body>
<script type="text/javascript">

    function logout(){
        location.href = '${iousContextPath}/logout'
    }
    //联系我们
    function gotoContactUs(){
        location.href='${iousContextPath}/contactUs';
    }

    //修改密码 flag =1 登录密码 flag = 2交易密码
    function modifyPassword(flag){
        location.href='${iousContextPath}/modifyPassword/'+flag;
    }
</script>
</html>
