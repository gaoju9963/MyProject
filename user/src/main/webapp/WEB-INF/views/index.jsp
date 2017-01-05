<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="js/jQuery-2.1.4.min.js"></script>
    <script src="js/tools/pop.js"></script>
    <script src="js/tools/api.js"></script>
    <link href="css/mycss/index.css" rel="stylesheet">
    <title>登录</title>
</head>
<body>
<div class="container">
    <form id="loginController">
        <div class="row register-contain">
            <div class="col-xs-12 register-head"><span>ruyi</span></div>
            <div class="col-xs-12 register-login">
                <a style="color: #555 !important;">用户登录</a>
                <a style="color: #aaa !important;" href="javascript:void(0);"
                   onclick="javascript:window.location.href='registerPage'">注册用户</a></div>
            <div class="register-text register-top">
                <div class="form-group">
                    <input type="text" id="mobile" name="login_name" placeholder="用户名/手机号" class="form-control"
                    <%--输入框禁止自动填充--%>
                           readonly onfocus="this.removeAttribute('readonly');" autocomplete="off"/>
                </div>
                <div class="form-message">&nbsp;</div>
            </div>
            <div class="col-xs-12 register-text">
                <div class="form-group">
                    <input type="password" id="password" name="password" placeholder="密码" class="form-control"
                           readonly onfocus="this.removeAttribute('readonly');" autocomplete="off"/>
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
</div>
</body>
<script>
    $(document).ready(function () {
        initEvent();
    });
    function initEvent() {
        $("#loginController").on("click", ".btn-system", function () {
            if (yanzheng()) {
                login();
            }
            return false;
        });
        //回车键绑定按钮点击事件
        $(function () {
            $(document).keydown(function (event) {
                if (event.keyCode == 13) {
                    $(".btn-system").click();
                }
            });
        });
    }
    function login() {
        var mobileOrUserName = $("#mobile").val();
        var password = $("#password").val();
        ryapi.invock("login", {mobileOrUserName: mobileOrUserName, password: password},
                function (rult) {
                    if (rult.apiRult.success) {
                        $.popMsg.Alert("登录成功");
                        window.location.href = 'homePage';//把用户名传给主页面
                    } else {
                        $.popMsg.Alert(rult.apiRult.message);
                    }
                });
    }
    function yanzheng() {
//        if ($('#mobile').val() == "") {
//            var errorMsg = '请输入手机号.';
//            $.popMsg.Alert(errorMsg);
//            return false;
//        }
        /*$('#mobile').val() == "" || ( $('#mobile').val() != "" && !/^0?1[3|4|5|8][0-9]\d{8}$/.test($('#mobile').val()))*/
        if ($('#mobile').val() == "") {
            var errorMsg = '请输入手机号或用户名.';
            $.popMsg.Alert(errorMsg);
            return false;
        }
        if ($('#password').val() == "") {
            var errorMsg = '请输入密码.';
            $.popMsg.Alert(errorMsg);
            return false;
        }
        if ($('#password').val() != "" && !/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/.test($('#password').val())) {
            var errorMsg = '请输入6~12位数字和字母组合.';
            $.popMsg.Alert(errorMsg);
            return false;
        }
        return true;
    }
</script>
</html>