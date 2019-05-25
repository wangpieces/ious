//校验数字
function validationNumber(value, num) {
    var regu = /^[0-9]+\.?[0-9]+$/;
    if (value) {
        if (!regu.test(value)) {
            return false;
        } else {
            if(num > 0){
                if (value.indexOf('.') > -1) {
                    if (value.split('.')[1].length > num) {
                        return false;
                    }
                }
            }
        }
    }
    return true;
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

//提示
function tip(msg){
    layer.open({
        content: msg
        ,skin: 'msg'
        ,time: 2 //2秒后自动关闭
    });
}

/**
 * 计算本息
 */
function caculatPrincipal(){
    var money = $('#money').val();
    var rate = $('#rate').val();
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

//同意协议
function agreeAgreement(obj) {
    var isChecked = $('#agree').prop('checked');
    if(isChecked){
        $('#nextstep').removeClass('next_step_click_before');
        $('#nextstep').addClass('next_step');
    }else {
        $('#nextstep').removeClass('next_step');
        $('#nextstep').addClass('next_step_click_before');
    }
}

//借款协议
function loanAgreement(){
    layer.open({
        content: '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
            '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
            '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
            '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
            '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
            '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
            '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
            '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
            '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'
        ,btn: '确定'
        ,title: '借款协议'
    });
}
//居间服务协议
function otherAgreement(){
    layer.open({
        content: '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
            '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
            '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
            '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
            '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
            '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
            '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
            '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'+
            '请输入您在投呗绑定的手机号，我们会将您的密码发送至您的，'
        ,btn: '确定'
        ,title: '居间服务协议'
    });
}