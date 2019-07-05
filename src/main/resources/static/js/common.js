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

//计算本息
function caculatPrincipal() {
    var loanTime = $('#loanTime').val();
    var returnTime = $('#returnTime').val();
    var money = $('#money').val();
    var rate = $('#rate').val();

    var tempLoanDate = new Date(loanTime);
    var tempReturnDate = new Date(returnTime);
    // console.log(tempLoanDate.getTime(),tempReturnDate.getTime());
    // 本金<span id="principal">0</span>+利息<span id="interest">0</span>=到期本息<span id="totalMoney" style="color:red;">0</span>元
    if(!money || !validationNumber(money,2)){
        $('#principal').html(0);
        $('#interest').html(0);
    }else{
        $('#principal').html(Number(money || 0));
    }

    if(!money || !validationNumber(money,2) || (tempLoanDate.getTime() > tempReturnDate.getTime())){
        $('#totalMoney').html(0);
        $('#interest').html(0);
        return;
    }

    //计算利息
    var day = ((tempReturnDate.getTime() - tempLoanDate.getTime()) / (24 * 3600 * 1000)) + 1;
    console.log('day',day);
    var interest = (((Number(money || 0)  * Number(rate || 0) / 100)) / 365) * Number(day || 0);
    console.log('interest',interest);
    $('#interest').html(interest.toFixed(2));
    var totalMoney = (Number(money || 0) + Number(interest || 0)).toFixed(2);
    $('#totalMoney').html(totalMoney);

}