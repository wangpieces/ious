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
    <style>
        .my_content_title li{
            float:left;
            width:2.44rem;
            height:1.5rem;
            text-align:center;
            font-size:0.35rem;
        }
        .my_content_item li{
            float:left;
            width:2.34rem;
            height:1rem;
            text-align:center;
            font-size:0.35rem;
            border:0px solid red;
            line-height:1rem;
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
            width:7.5rem;
            height:0.8rem;
            border:0px solid red;
            border-bottom:1px solid #DDD;
            border-radius:0px;
            margin-left:0rem;
            margin-top:0rem;
            background:white;
        }
        .next_step{width: 6.7rem;height: 0.92rem;line-height: 0.92rem;display: block;text-align: center;color: #fff;font-size: 0.32rem;margin:0.34rem auto;background-image: -webkit-linear-gradient(0deg,#40bfff 20%,#0197ff 40%,#40bfff 80%);border-radius: 0px;}
    </style>

</head>
<body style="background:#eff0f1;">
<!--页面主体内容部分-->
<div class="my_content" style="border:0px solid red;margin-top:0rem;">
    <div style="width:7.5rem;border-bottom:1px solid #eff0f1;height:2.5rem;background:#ffffff">
       <ul class="my_content_item" style="border-bottom:1px solid #DDD;width:7.1rem;height:1rem;margin-left:0.2rem;">
           <li >出借人:${ious.name}</li>
           <li></li>
           <li onclick="gotoDetail(${(ious.id)!0})">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${ious.purposeName} <img src="${iousRequestUrl}/img/right.png" style="width:0.3rem;height:0.3rem;position:relative;top:0.05rem;"></li>
       </ul>
       <ul class="my_content_title">
           <li>待还总额<p>${ious.principal} 元</p></li><li style="width:1px;height:1rem;border-left:1px solid #dbe4eb;margin-top:0.2rem;"></li>
           <li>借款本金<p>${ious.money} 元</p></li><li style="width:1px;height:1rem;border-left:1px solid #dbe4eb;margin-top:0.2rem;"></li>
           <li>利息<p>${ious.interest} 元</p></li>
       </ul>
    </div>
    <div style="width:7.5rem;border-bottom:1px solid #eff0f1;height:0.5rem;margin-top:0.2rem;">
        <ul class="my_content_title" id="my_content_title" style="border:0px solid red;background:white;height:0.8rem;line-height:0.8rem;">
            <li class="my_content_title_active" style="height:0.8rem;line-height:0.8rem;">待还金额</li>
            <li style="height:0.8rem;line-height:0.8rem;">展期历史</li>
            <!--  <li style="height:0.8rem;line-height:0.8rem;">待确认金额</li>-->
        </ul>
    </div>

    <div class="my_content_list" style="margin-top:0.31rem;" id="loanMoneyList">
        <ul class="ul_1">
            <li class="ul_1_li">
                <div style="width:6.6rem;height:0.8rem;margin-left:0.2rem;border-bottom:1px dotted #DDD;">
                    <table class="my_content_list_table_1" style="line-height:0.8rem;">
                        <tr>
                            <td>&nbsp;&nbsp;${ious.money}&nbsp;&nbsp;元</td>
                            <#if (ious.remainTime)?? && ((ious.remainTime)?number) lt 0 >
                                <td style="text-align:right;color:orange;">已逾期${(ious.remainTime)?abs}天</p></td>
                            <#else>
                                <td style="text-align:right;">剩余${ious.remainTime}天</p></td>
                            </#if>
                        </tr>
                    </table>
                </div>
            </li>
        </ul>
    </div>

    <div style="width:7.5rem;border-bottom:1px solid #eff0f1;min-height:0.8rem;background:#ffffff;margin-top:0.3rem;display:none;" id="postponeList">
        <ul class="my_content_title" style="height:0.8rem;">
            <li style="width:2rem;text-align:center;height:0.8rem;line-height:0.8rem;">金额</li>
            <li style="width:1.4rem;text-align:center;height:0.8rem;line-height:0.8rem;">利率</li>
            <li style="width:1.8rem;text-align:center;height:0.8rem;line-height:0.8rem;">时间</li>
            <li style="width:1.8rem;text-align:center;height:0.8rem;line-height:0.8rem;">状态</li>
        </ul>
        <#if postponeList??>
            <#list postponeList as postpone>
                <ul class="my_content_title" style="border:0px solid red;height:0.8rem;">
                    <li style="width:2rem;text-align:center;height:0.8rem;line-height:0.8rem;font-size:0.3rem;">${(postpone.money)!0} 元</li>
                    <li style="width:1.4rem;text-align:center;height:0.8rem;line-height:0.8rem;font-size:0.3rem;">${(postpone.postponeRateName)!''}</li>
                    <li style="width:1.8rem;text-align:center;height:0.8rem;line-height:0.8rem;font-size:0.3rem;">${(postpone.postponeTime)!''}</li>
                    <li style="width:1.8rem;text-align:center;height:0.8rem;line-height:0.8rem;font-size:0.3rem;">${(postpone.postponeStatusName)!''}</li>
                </ul>
            </#list>
        </#if>
    </div>
</div>
<!--	页面底部部分-->
<#if postpone??>
    <ul class="footer" style="background:white;padding-bottom:0.1rem;">
        <li class="footer-item">
        </li>
        <li class="footer-item">
        </li>
        <li class="footer-item">
            <a href="javascript:gotoSurePostpone('${(postpone.iousId)!0}');" class="next_step" style="width:1.7rem;float:right;margin:0.1rem 0.2rem 0rem 0rem;height:0.8rem;line-height:0.8rem;">确认展期</a>
        </li>
    </ul>
</#if>
<script type="text/javascript" charset="utf-8">
$(function(){
    //标题切换
    $('#my_content_title li').click(function(){
        $('.my_content_title li').removeClass('my_content_title_active');
        $(this).addClass('my_content_title_active');
        if($(this).index() == 0){//待还金额
            $('#loanMoneyList').show();
            $('#postponeList').hide();
        }else{//展期历史
            $('#loanMoneyList').hide();
            $('#postponeList').show();
        }
    });
});

//跳转到详细页面
function gotoDetail(id) {
    location.href='${iousContextPath}/business/loanItemDetail?iousId='+id;
}

//跳到确认展期页面
function gotoSurePostpone(id) {
    location.href='${iousContextPath}/business/surepostpone?iousId='+id;
}

</script>
</body>

</html>
