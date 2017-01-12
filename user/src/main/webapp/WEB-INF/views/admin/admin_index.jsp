<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="/js/jQuery-2.1.4.min.js"></script>
    <script src="/js/tools/pop.js"></script>
    <script src="/js/tools/api.js"></script>
    <link href="/css/mycss/index.css" rel="stylesheet">
    <link href="/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="/css/mycss/login.css" rel="stylesheet">
    <title>后台管理首页</title>
</head>
<body>
<div class="container">
    <form id="loginController">
        <div class="row register-contain">
            <div class="col-xs-12 register-head"><span>后台管理</span></div>
            <div class="col-xs-12 register-login">
                <a style="color: #555 !important;">用户登录</a>
                <a style="color: #aaa !important;" href="javascript:void(0);"
                   onclick="javascript:window.location.href='registerPage'">注册用户</a></div>
            <div class="col-xs-12 register-login register-text">
                <div class="form-group">
                    <input type="text" id="mobile" name="login_name" placeholder="用户名/手机号" class="form-control"/>
                    <%--输入框禁止自动填充--%>
                    <%--readonly onfocus="this.removeAttribute('readonly');" autocomplete="off"/>--%>
                </div>
                <div class="form-message">&nbsp;</div>
            </div>
            <div class="col-xs-12 register-text">
                <div class="form-group">
                    <input type="password" id="password" name="password" placeholder="密码" class="form-control"/>
                    <input type="password" id="input_temp" name="password" placeholder="密码" class="form-control"
                           readonly onfocus="this.removeAttribute('readonly');" autocomplete="off"
                           style="display: none"/>
                </div>
                <div class="form-message">&nbsp;</div>
            </div>
            <div class="col-xs-12 register-text">
                <input type="button" class="btn btn-info btn-system" value="登录"/>
            </div>
            <div class="col-xs-12">
                <div class="line1" style="width: 350px;"></div>
            </div>
            <div class="col-xs-12 login-register">
                <a href="javascript:void(0);" onclick="gotoforgetpsd()">忘记密码？</a>
                <a href="javascript:void(0);" onclick="javascript:window.location.href='registerPage'">免费注册</a>
            </div>
        </div>
        <div id="pop_alert" style="color: red"></div>
    </form>
</div>
</body>
</html>
