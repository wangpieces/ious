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
