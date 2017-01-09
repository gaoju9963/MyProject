<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../public/head.jsp"></jsp:include>
    <script src="js/app4.js"></script>
    <link href="css/mycss/notice.css" rel="stylesheet">
    <link rel="stylesheet" href="css/notice/notice.css">
    <link rel="stylesheet" href="css/notice/font-awesome.min.css">
    <script src="js/notice/jquery.newsTicker.js"></script>
</head>
<style>
    body {
        padding-top: 0px;
    }

    .close a {
        float: right;
        margin: 0 10px 0 0 !important;
    }

    #nt-example2 li {
        border: 1px solid;
    }
</style>

<body>
<div>
    <div id="gg">
        <div class="close_notice">
            <a href="javascript:;" onclick="$('#gg').slideUp('slow');" title="关闭">×</a>
            <div id="feedb">
                <a href="#" title="欢迎订阅本站" target="_blank" class="image">
                    <img src="picture/feed.gif" style="" alt="feed"/>
                </a>
            </div>
            <div class="bulletin">
                <ul style="margin-top: 0px;">
                    <li>
                        <a href="#" title="文艺范早安晚安心语：黑夜里代替阳光的东西是信念">也许上帝让你在遇见那个适合的人之前遇见很多错误的人，所以，当这一切发生的时候，你应该心存感激</a>
                    </li>
                    <li>
                        <a href="#" title="关于青春、海边唯美的图片">关于青春、海边唯美的图片——我想给你全部全部的希望及光芒，免你忧伤赐你一腔孤勇闯向远方</a>
                    </li>
                    <li>
                        <a href="#" title="[优美文章]过一个冷暖自知的冬天">[优美文章]过一个冷暖自知的冬天——守着自己的小小天地，过着属于自己的小日子，亦可清欢。</a>
                    </li>
                    <li>
                        <a href="#" title="文艺范手绳编织技巧，各种精美好看的绳结~">文艺范手绳编织技巧，各种精美好看的绳结~</a>
                    </li>
                    <li>
                        <a href="#" title="原创句子吧 关于爱情的经典语录">横眉冷对千夫指 俯首甘为孺子牛</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>


    <div class="white">
        <div class="container">
            <div class="row">
                <div class="col-md-6 centered">
                    <input type="button" class="btn btn-info btn-system" onclick="creatNotice()" style="float: left"
                           value="添加公告"/>
                    <div id="nt-example2-container">
                        <ul id="nt-example2">
                            <li data-infos="Cras sagittis nulla sit amet feugiat pulvinar. Fusce scelerisque aliquet purus, sit amet rutrum augue euismod ut. Aliquam erat volutpat. Integer convallis, ligula non bibendum dictum, ante lectus fringilla nunc, at euismod neque enim sit amet ante. In risus velit, porttitor blandit magna vel, adipiscing semper libero. ">
                                <i class="fa fa-fw fa-play state"></i>
                                <span class="hour">08:12</span> Etiam imperdiet volutpat libero eu tristique.
                            </li>
                        </ul>
                        <div id="nt-example2-infos-container">
                            <div id="nt-example2-infos-triangle"></div>
                            <div id="nt-example2-infos" class="row">
                                <div class="col-xs-4 centered">
                                    <div class="infos-hour">
                                        08:12
                                    </div>
                                    <i class="fa fa-arrow-left" id="nt-example2-prev"></i>
                                    <i class="fa fa-arrow-right" id="nt-example2-next"></i>
                                </div>
                                <div class="col-xs-8">
                                    <div class="infos-text">Cras sagittis nulla sit amet feugiat pulvinar. Fusce scelerisque aliquet purus, sit amet rutrum augue euismod ut. Aliquam erat volutpat. Integer convallis, ligula non bibendum dictum, ante lectus fringilla nunc, at euismod neque enim sit amet ante. In risus velit, porttitor blandit magna vel, adipiscing semper libero.</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <%--<table cellspacing=0 cellpadding=1 width="100%" border=0>--%>
        <%--<tr>公告：--%>
        <%--<th bgcolor=#CCCCCC>--%>
        <%--<marquee direction=left height=20 width=100% id=m onmouseout=m.start() onMouseOver=m.stop()--%>
        <%--scrollamount=10--%>
        <%--align="center">--%>
        <%--<p><a href="http:" target="_blank">祝大家鸡年大吉！2017，祝大家事业有成！--%>
        <%--大家看见了吗？爱在哪里？大家把这里改成自己需要的内容</a>--%>
        <%--</marquee>--%><%--</th>--%><%--</tr>--%><%--</table>--%>
    </div>
</div>
</body>
<script type="text/javascript">
    /*用法*/
    $(document).ready(function () {
        $(".bulletin").Scroll({line: 1, speed: 1000, timer: 2000});//修改此数字调整滚动状态

        getNotices();
    });
    var nt_example2 = $('#nt-example2').newsTicker({
        row_height: 60,
        max_rows: 3,
        speed: 300,
        duration: 5000,
        prevButton: $('#nt-example2-prev'),
        nextButton: $('#nt-example2-next'),
        hasMoved: function () {
            $('#nt-example2-infos-container').fadeOut(200, function () {
                $('#nt-example2-infos .infos-hour').text($('#nt-example2 li:first span').text());
                $('#nt-example2-infos .infos-text').text($('#nt-example2 li:first').data('infos'));
                $(this).fadeIn(400);
            });
        },
        pause: function () {
            $('#nt-example2 li i').removeClass('fa-play').addClass('fa-pause');
        },
        unpause: function () {
            $('#nt-example2 li i').removeClass('fa-pause').addClass('fa-play');
        }
    });
    $('#nt-example2-infos').hover(function () {
        nt_example2.newsTicker('pause');
    }, function () {
        nt_example2.newsTicker('unpause');
    });

    function creatNotice() {
        $.ajax({
            url: "createNotice",
            type: "post",
            dataType: "json",
            success: function (value) {
                if (value.success) {
                    alert("success");
                }
            }
        });
    }

    function getNotices() {
        $.ajax({
            url: "getNotices",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.success) {

                    var list = "";
                    for (var i = 0; i < data.value.length; i++) {
                        var createDate = (new Date(data.value[i].createDate)).format("HH:mm");
                        list += "<li data-infos=' " + data.value[i].content + " '>" +
                                "<i class='fa fa-fw fa-play state'></i>" +
                                "<span class='hour'>" + createDate + "</span>" + "&nbsp" +
                                data.value[i].title +
                                "</li>";
                    }
                    $("#nt-example2").append(list)
                }
            }
        });
    }
</script>
</html>
<%out.flush(); %>