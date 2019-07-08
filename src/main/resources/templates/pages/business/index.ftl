<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>众联成服务</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
    <meta name="format-detection" content="telephone=no, email=no" />
    <link rel="stylesheet" href="${iousRequestUrl}/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${iousRequestUrl}/css/index.css?v=1.2.1"/>
    <link rel="stylesheet" type="text/css" href="${iousRequestUrl}/css/myindex.css"/>
    <link rel="stylesheet" href="${iousRequestUrl}/css/personalCenter.css?v=1.2.2" type="text/css">
    <link rel="stylesheet" type="text/css" href="${iousRequestUrl}/lib/css/layer.css"/>
    <script src="${iousRequestUrl}/lib/js/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
    <script src="${iousRequestUrl}/lib/js/jquery.base64.js" type="text/javascript" charset="utf-8"></script>
    <script src="${iousRequestUrl}/lib/js/layer.js" type="text/javascript" charset="utf-8"></script>
    <script src="${iousRequestUrl}/js/wapframwork.js" type="text/javascript" charset="utf-8"></script>
    <script src="${iousRequestUrl}/js/myindex.js" type="text/javascript" charset="utf-8"></script>
</head>
<body style="background:#eff0f1;">
<!--页面头部-->
<div class="my_header">
    <div class="my_headerbottom">
        <div class="left" onclick="gotoLoanList()">
            <p style="font-weight:bold;" id="loanMoney">0.00</p><br>
            <p>待还金额</p>
            <p class="num"><span id="waitNum_num"></span><span id="waitNum_dec" class="num_deci"></span></p>
        </div>
        <div class="right" onclick="gotoLendList()">
            <p style="font-weight:bold;" id="lendMoney">0.00</p><br>
            <p>待收金额</p>
            <p class="num"><span id="returnIn_num"></span><span id="returnIn_dec" class="num_deci"></span></p>
        </div>
    </div>
</div>
<div style="width:7.5rem;height:4.4rem;border:0px solid red;z-index:-100;position:absolute;top:-0.2rem;background:white;"></div>
<!--页面主体内容部分-->
<div class="my_content" style="border:0px solid red;margin-top:0.2rem;">
    <div style="width:7.5rem;border-bottom:1px solid #eff0f1;height:1rem;">
       <ul class="my_content_title">
           <li class="my_content_title_active">正在借款</li>
           <li>正在出借</li>
       </ul>
    </div>
    <div class="my_content_list_null">
        <p><img src="${iousRequestUrl}/img/ious.png" style="width:3rem;height:3rem;margin-top:0.6rem;"></p><br/>
        <p id="my_content_list_null_msg">暂无借款借条</p><!--暂无出借借条-->
        <p style="font-size:0.25rem;margin-top:0.15rem;">如需补借条，可点击下方按钮</p>
    </div>
    <div class="my_content_list" style="display:none;">
            <ul class="ul_1">
            </ul>
        </div>
</div>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<!--	页面底部部分-->
<ul class="footer">
    <li class="footer-item">
        <a href="${iousContextPath}/business/index" class="">
            <i class="icon icon_index active"></i>
            <p class="icon_title" style="color: #f23029">首页</p>
        </a>
    </li>
    <li class="footer-item">
        <a href="javascript:chooseOperator()" class="">
            <i class="icon icon_invest"></i>
            <p class="icon_title">补借条</p>
        </a>
    </li>
    <li class="footer-item">
        <a href="javascript:gotoPersonal()" class="">
            <i class="icon icon_my"></i>
            <p class="icon_title">我的</p>
        </a>
    </li>
</ul>
<script type="text/javascript" charset="utf-8">
$(function(){
    //标题切换
    $('.my_content_title li').click(function(){
        $('.my_content_title li').removeClass('my_content_title_active');
        $(this).addClass('my_content_title_active');
        if($(this).index() == 0){//正在借款
            loanMoneyList();
        }else{//正在出借
            lendMoneyList();

        }
    });
    getMoneys();
    loanMoneyList();
});


function chooseOperator(){
    //页面层-自定义
       layer.open({
         content: '<a class="my_layer_btn1" href="${iousContextPath}/business/loan">我是借款人</a><a class="my_layer_btn2" href="${iousContextPath}/business/lend">我是出借人</a>'
        ,className: 'my_layer_content'
        ,btn: ['取消']
        ,skin: 'footer'
        ,yes: function(index){
          layer.closeAll();
        }
      });
}

/**
 * 获取待还金额和代收金额
 */
function getMoneys() {

    $.ajax({
        type: "GET",
        url: "${iousContextPath}/api/business/getMoneys",
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", "${token!''}");
        },
        success: function(data) {
            if(data.successful){
                $('#lendMoney').html(data.data.lendMoney);
                $('#loanMoney').html(data.data.loanMoney);
            }
        }
    });
}

/**
 * 正在出借
 */
function lendMoneyList(){
    $.ajax({
        type: "GET",
        url: "${iousContextPath}/api/business/lendMoneyList",
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", "${token!''}");
        },
        success: function(data) {
            if(data.successful && data.data.length > 0){
                setHtml(data.data,2);
            }else {
                $('.my_content_list').hide();
                $('.my_content_list_null').show();
                $('#my_content_list_null_msg').html('暂无借款借条');
            }
        },
        error:function(data){
            $('.my_content_list').hide();
            $('.my_content_list_null').show();
            $('#my_content_list_null_msg').html('暂无借款借条');
        }
    });
}
/**
 * 正在借款
 */
function loanMoneyList(){
    $.ajax({
        type: "GET",
        url: "${iousContextPath}/api/business/loanMoneyList",
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", "${token!''}");
        },
        success: function(data) {
            if(data.successful && data.data.length > 0){
                setHtml(data.data,1);
            }else {
                $('.my_content_list').hide();
                $('.my_content_list_null').show();
                $('#my_content_list_null_msg').html('暂无出借借条');
            }
        },
        error:function(data){
            $('.my_content_list').hide();
            $('.my_content_list_null').show();
            $('#my_content_list_null_msg').html('暂无出借借条');
        }
    });
}
//type1-借款 2-出借
function setHtml(data,fromtype){
    var html= '';
    for(var i = 0; i < data.length; i++) {
         html += '<li class="ul_1_li">'+
            '<div style="width:6.6rem;height:1.3rem;margin-left:0.2rem;border-bottom:1px dotted #DDD;">'+
               '<table class="my_content_list_table_1">'+
                    '<tr onclick="gotoDetail('+data[i].id+','+fromtype+')">'+
                        '<td>'+
                            '<p style="font-weight:bold;">'+data[i].name+'</p>'+
                            '<p style="font-size:0.25rem;">剩余'+data[i].remainTime+'天</p>'+
                        '</td>'+
                        '<td style="text-align:right;">未支付 <img src="${iousRequestUrl}/img/right.png" style="width:0.3rem;height:0.3rem;position:relative;top:0.05rem;"></td>'+
                    '</tr>'+
               '</table>'+
            '</div>'+
            '<div style="width:6.6rem;height:1.3rem;margin-left:0.2rem;">'+
               '<table class="my_content_list_table_2">'+
                   '<tr>'+
                       '<td>'+
                           '<p>'+data[i].money+'元</p>'+
                           '<p style="font-size:0.25rem;">筹款金额</p>'+
                       '</td>'+
                       '<td style="text-align:center;">'+
                           '<p>'+data[i].timeLength+'天</p>'+
                           '<p style="font-size:0.25rem;">借款时间</p>'+
                       '</td>'+
                       '<td style="text-align:right;">'+
                           '<p>'+data[i].rate+'%</p>'+
                           '<p style="font-size:0.25rem;">年利率</p>'+
                       '</td>'+
                   '</tr>'+
               '</table>'+
            '</div>'+
        '</li>';
    }

    $('.my_content_list .ul_1').html('');
    $('.my_content_list .ul_1').html(html);
    $('.my_content_list').show();
    $('.my_content_list_null').hide();
}
//点击列表查看详情
function gotoDetail(id,fromtype) {
    //跳转到详细页面
    location.href='${iousContextPath}/business/detail?iousId='+id+'&fromtype='+fromtype+'&from=index';
}
//待收金额列表
function gotoLendList(){
     location.href='${iousContextPath}/business/lendList';
}

//待还金额列表
function gotoLoanList(){
     location.href='${iousContextPath}/business/loanList';
}

//我的
function gotoPersonal(){
    location.href='${iousContextPath}/personal';
}

</script>
</body>

</html>
