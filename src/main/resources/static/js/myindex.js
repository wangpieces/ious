/*

$(function(){
    //标题切换
    $('.my_content_title li').click(function(){
        $('.my_content_title li').removeClass('my_content_title_active');
        $(this).addClass('my_content_title_active');
        if($(this).index() == 0){//正在借款
            borrowMoney();
        }else{//正在出借
            loanMoney();
        }
    });

    getMoneys();
});

function getMoneys() {
    alert('${openId}');
}

*/
/**
 * 借款
 *//*

function borrowMoneyList(){
    $.get("test.cgi", { name: "John", time: "2pm" },
              function(data){
              alert("Data Loaded: " + data);
    });
}
*/
/**
 *出借
 *//*

function loanMoneyList(){
}*/

function getOs() {
    var u = navigator.userAgent, app = navigator.appVersion;
    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //g
    var isIOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    if (isAndroid) {
        //这个是安卓操作系统
        return 'android';
    }else if (isIOS) {
        //这个是ios操作系统
        return 'ios';
    }
    return '';
}

