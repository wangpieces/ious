
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改${name!''}密码</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no, email=no"/>
    <link rel="stylesheet" href="${iousRequestUrl}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${iousRequestUrl}/css/index.css?v=1.2.2"/>
    <link rel="stylesheet" href="${iousRequestUrl}/lib/css/layer.css" type="text/css">
    <script src="${iousRequestUrl}/lib/js/jquery-2.1.4.js"></script>
    <script src="${iousRequestUrl}/lib/js/jquery.base64.js"></script>
    <#--<script src="${iousRequestUrl}/js/wapframwork.js"></script>-->
</head>
<body>
<div class="modifypassword_title">
    <a href="${iousContextPath}/personal">
        <i class="icon"></i>
    </a>
    <span>修改${name!''}密码</span>
</div>
<form class="form_modify">
    <div class="input_group">
        <span class="input_des">原${name!''}密码</span>
        <input type="password" name="txtOldPwd" id="txtOldPwd" placeholder="输入密码"/>
    </div>
    <div class="input_group">
        <span class="input_des">新${name!''}密码</span>
        <input type="password" name="txtNewPwd" id="txtNewPwd" placeholder="${placeholder!''}"/>
    </div>
    <div class="input_group">
        <span class="input_des">确认新密码</span>
        <input type="password" name="txtReNewPwd" id="txtReNewPwd" placeholder="确认新密码"/>
    </div>
</form>
<button class="modifypassword_btn" id="modifypassword_btn" onclick="savePwd(${flag!1})">确认</button>
</body>


<script src="${iousRequestUrl}/lib/js/layer.js"></script>
<#--<script src="${iousRequestUrl}/js/loading.js"></script>-->
<#--<script src="${iousRequestUrl}/js/modify_password.js"></script>-->
<!--头部-->
<script type="text/javascript">

    function savePwd(flag){
        if(flag == 1 && validationLoginPassword()){
            commitSavePwd(flag);
        }else if(flag == 2 && validationTradingPassword()){
            commitSavePwd(flag);
        }
    }
    //提交保存密码
    function commitSavePwd(flag){
        //加密
        var oldPwd = $.base64.btoa($('#txtOldPwd').val(), true);
        var newPwd = $.base64.btoa($('#txtNewPwd').val(), true);
        $.ajax({
            type: "POST",
            url: "${iousContextPath}/api/savePassword",
            data:{
                oldPwd: oldPwd,
                newPwd: newPwd,
                flag: flag
            },
            beforeSend: function(request) {
                request.setRequestHeader("Authorization", "${token!''}");
            },
            success: function(data) {
                console.log(data);
                if(data.successful){
                    tip(data.message);
                    setTimeout(function(){
                        location.href='${iousContextPath}/personal';
                    },1000);
                }else {
                    tip(data.message);
                }
            }
        });
    }

    //验证登录密码
    function validationLoginPassword(){
        var  _$txtOldPwd = $('#txtOldPwd'), _$txtNewPwd = $('#txtNewPwd'), _$txtReNewPwd = $('#txtReNewPwd');
        var allNumReg = /^\d+$/;
        var allLetReg = /^[A-Za-z]*$/;
        var txt = "";
        //先做验证
        if ($.trim(_$txtOldPwd.val()) == "") {
            tip('请输入原登录密码');
            return false;
        }
        if ($.trim(_$txtNewPwd.val()) == "") {
            tip('请输入新的登录密码');
            return false;
        }
        else {
            var pwdStr = $.trim(_$txtNewPwd.val()).split(" ");
            if (pwdStr.length != 1) {
                tip('密码长度在6-20个字符之间，不能有空格');
                return false;
            }
            else {
                if ($.trim(_$txtNewPwd.val()).length < 6 || $.trim(_$txtNewPwd.val()).length > 20) {
                    tip('密码长度在6-20个字符之间，不能有空格');
                    return false;
                }
                else {
                    if (allNumReg.test($.trim(_$txtNewPwd.val()))) {
                        tip('密码不能全部为数字');
                        return false;
                    }
                    if (allLetReg.test($.trim(_$txtNewPwd.val()))) {
                        tip('密码不能全部为字母');
                        return false;
                    }
                }
            }
        }
        if ($.trim(_$txtReNewPwd.val()) != $.trim(_$txtNewPwd.val())) {
            tip('确认密码与新密码不一致');
            return false;
        }
        return true;
    }

    function validationTradingPassword(){
        var  _$txtOldPwd = $('#txtOldPwd'), _$txtNewPwd = $('#txtNewPwd'), _$txtReNewPwd = $('#txtReNewPwd');
        var allNumReg = /^\d+$/;
        //先做验证
        if ($.trim(_$txtOldPwd.val()) == "") {
            tip('请输入原交易密码');
            return false;
        }
        if ($.trim(_$txtNewPwd.val()) == "") {
            tip('请输入新的交易密码');
            return false;
        }
        else {
            var pwdStr = $.trim(_$txtNewPwd.val()).split(" ");
            if (pwdStr.length != 1) {
                tip('交易密码长度为6个位数字，且不能有空格');
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
            tip('确认密码与新密码不一致');
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
