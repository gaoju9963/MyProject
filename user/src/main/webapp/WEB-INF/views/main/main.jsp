<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../public/head.jsp"></jsp:include>
    <script src="js/app4.js"></script>
</head>
<style>
    body {
        padding-top: 0px;
    }
</style>

<body>
<div id="gg">
    <div class="close">
        <a href="javascript:;" onclick="$('#gg').slideUp('slow');" title="关闭">×</a>
        <div id="feedb">
            <a href="#" title="欢迎订阅本站" target="_blank" class="image">
                <img src="picture/feed.gif" alt="feed"/>
            </a>
        </div>
        <div class="bulletin">
            <ul style="margin-top: 0px;">
                <li>
                    <a href="#" title="文艺范早安晚安心语：黑夜里代替阳光的东西是信念">文艺范早安晚安心语：黑夜里代替阳光的东西是信念——写的真好，详情点击这里</a>
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
                    <a href="#" title="原创句子吧 关于爱情的经典语录">原创句子吧 关于爱情的经典语录</a>
                </li>
            </ul>
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
    <%--</marquee>--%>
    <%--</th>--%>

    <%--</tr>--%>

    <%--</table>--%>

    <div class="row">
        <h1 class="page-header">Dashboard</h1>

        <div class="row placeholders">
            <div class="col-xs-6 col-sm-3 placeholder">
                <img data-src="holder.js/200x200/auto/sky" class="img-responsive"
                     alt="Generic placeholder thumbnail">
                <h4>Label</h4>
                <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
                <img data-src="holder.js/200x200/auto/vine" class="img-responsive"
                     alt="Generic placeholder thumbnail">
                <h4>Label</h4>
                <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
                <img data-src="holder.js/200x200/auto/sky" class="img-responsive"
                     alt="Generic placeholder thumbnail">
                <h4>Label</h4>
                <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
                <img data-src="holder.js/200x200/auto/vine" class="img-responsive"
                     alt="Generic placeholder thumbnail">
                <h4>Label</h4>
                <span class="text-muted">Something else</span>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    /*用法*/
    $(document).ready(function () {
        $(".bulletin").Scroll({line: 1, speed: 1000, timer: 2000});//修改此数字调整滚动状态
    });
</script>
</html>