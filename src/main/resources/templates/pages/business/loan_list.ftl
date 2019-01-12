<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MF服务</title>
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
    <style>
        .my_content_title li{
            float:left;
            width:2.45rem;
            height:1.5rem;
            text-align:center;
            font-size:0.35rem;
            border:0px solid red;
        }
        .my_content_title p{
            font-size:0.25rem;
            margin-top:-0.4rem;
        }
        .my_content .search{
            width:7.5rem;
            height:1.1rem;
            border:0px solid red;
            background:#6666661f;
        }
        .my_content .search input{
            width:7rem;
            height:0.8rem;
            margin-left:0.2rem;
            margin-top:0.1rem;
            margin-bottom:0.1rem;
            border:1px solid #eff0f1;
            font-size:0.3rem;
            padding-left:0.1rem;
        }
        .my_content_list .ul_1 .ul_1_li{
            width:7.1rem;
            height:1.1rem;
            border:0px solid red;
            border-bottom:1px solid #DDD;
            border-radius:0px;
            margin-left:0.2rem;
            margin-top:0.1rem;
            background:white;
        }
    </style>

</head>
<body style="background:#ffffff;">
<!--<div style="width:7.5rem;height:2.8rem;border:0px solid red;z-index:-100;position:absolute;top:-0.2rem;background:white;"></div>-->
<!--页面主体内容部分-->
<div class="my_content" style="border:0px solid red;margin-top:0.2rem;">
    <div style="width:7.5rem;border-bottom:1px solid #eff0f1;height:1.5rem;">
       <ul class="my_content_title">
           <li class="my_content_title_active" style="">待还总额<p>${iousMoney.allMoney} 元</p></li>
           <li>7天待还<p>${iousMoney.day7Money} 元</p></li>
           <li>30天待还<p>${iousMoney.day30Money} 元</p></li>
       </ul>
    </div>
    <div class="search">
        <input type="text" name="search" id="search" placeholder="输入出借人姓名" onblur="search(this.value);"/>
        <input type="hidden" value="1" id="loadTimeType"/><!--当前选中的是那种数据类型-->
    </div>
    <div class="my_content_list_null" style="margin-top:0.2rem;">
        <p><img src="${iousRequestUrl}/img/ious.png" style="width:3rem;height:3rem;margin-top:0.6rem;"></p><br/>
        <p id="my_content_list_null_msg">暂无借条信息</p><!--暂无出借借条-->
    </div>
    <div class="my_content_list" style="display:none;margin-top:0.2rem;">
        <ul class="ul_1">
        </ul>
    </div>
</div>
<script type="text/javascript" charset="utf-8">
$(function(){
    //标题切换
    $('.my_content_title li').click(function(){
        $('.my_content_title li').removeClass('my_content_title_active');
        $(this).addClass('my_content_title_active');
        var keyWord = $('#search').val();
        if($(this).index() == 0){//待还总额
            $('#loadTimeType').val(1)
            getIousList(1,1,keyWord);
        }else if($(this).index() == 1){//7天待还
             $('#loadTimeType').val(2)
             getIousList(1,2,keyWord);
        }else if($(this).index() == 2){//30天待还
            $('#loadTimeType').val(3)
            getIousList(1,3,keyWord);
        }
    });
    getIousList(1,1,'');
});

/**
 * @param loadType 加载数据类型 1-借款人获取借条信息 4-出借人获取借条信息（这个值和type初始值一样）
 * @param loadTimeType 1-获取全部 2-获取借款/出借时间为7天 3-获取借款/出借时间为30天
 * @param keyWord 搜索关键字，目前通过名称搜索
 */
function getIousList(loadType,loadTimeType,keyWord){
    $.ajax({
        type: "GET",
        url: "${iousContextPath}/api/business/getIousList",
        beforeSend: function(request) {
            request.setRequestHeader("Authorization", "${token!''}");
        },
        data:{loadType:loadType,loadTimeType:loadTimeType,keyWord:keyWord},
        success: function(data) {
            if(data.successful && data.data.length > 0){
                setHtml(data.data,2);
            }else {
                $('.my_content_list').hide();
                $('.my_content_list_null').show();
                $('#my_content_list_null_msg').html('暂无借条信息');
            }
        },
        error:function(data){
            $('.my_content_list').hide();
            $('.my_content_list_null').show();
            $('#my_content_list_null_msg').html('暂无借条信息');
        }
    });
}

//type1-借款 2-出借
function setHtml(data,fromtype){
    var html= '';
    for(var i = 0; i < data.length; i++) {
         var remainTime = data[i].remainTime;
         var tempHtml = '';
         if(remainTime < 0){
            tempHtml = '<td style="text-align:right;color:orange;">已逾期'+Math.abs(remainTime)+'天 ></p>';
         }else{
            tempHtml = '<td style="text-align:right;">剩余'+remainTime+'天 <img src="${iousRequestUrl}/img/right.png" style="width:0.3rem;height:0.3rem;position:relative;top:0.05rem;"></p>';
         }

         html += '<li class="ul_1_li">'+
            '<div style="width:6.6rem;height:1.1rem;margin-left:0.2rem;border-bottom:1px dotted #DDD;">'+
               '<table class="my_content_list_table_1">'+
                    '<tr onclick="gotoDetail('+data[i].id+')">'+
                        '<td>'+
                            '<p style="color:#079bff;">'+data[i].money+'&nbsp;&nbsp;元</p>'+
                            '<p style="font-size:0.25rem;margin-top:-0.1rem;">'+data[i].name+' - '+data[i].purposeName+'</p>'+
                        '</td>'+ tempHtml +
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
function gotoDetail(id) {
    //跳转到详细页面
    location.href='${iousContextPath}/business/loanListItem?iousId='+id+'&type=1';
}
//关键字搜索
function search(keyWord){
    var loadTimeType = $('#loadTimeType').val();
    getIousList(1,loadTimeType,keyWord);
}

</script>
</body>

</html>
