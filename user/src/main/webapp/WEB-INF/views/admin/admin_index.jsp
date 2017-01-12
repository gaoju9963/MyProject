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
            <div class="col-xs-12 register-login register-text">
                <div class="form-group">
                    <input type="text" id="name" name="login_name" placeholder="用户名/手机号" class="form-control"/>
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
            <%--<div class="col-xs-12">--%>
            <%--<div class="line1" style="width: 350px;"></div>--%>
            <%--</div>--%>
        </div>
        <div id="pop_alert" style="color: red"></div>
    </form>
</div>
</body>
<script>
    $(document).ready(function () {
        initEvent();
    });
    function initEvent() {
        $("#loginController").on("click", ".btn-system", function () {
            adminLogin();
        });
    }
    function adminLogin() {
        var name = $("#name").val();
        var password = $("#password").val();
        ryapi.invock("/admin/adminLogin", {name: name, password: password},
                function (rult) {
                    if (rult.apiRult.success) {
                        $.popMsg.Alert("登录成功");
                    } else {
                        $.popMsg.Alert(rult.apiRult.message);
                    }
                });
    }
</script>
</html>
