<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="js/jQuery-2.1.4.min.js"></script>
    <script src="js/tools/pop.js"></script>
    <script src="js/tools/api.js"></script>
    <title>登录</title>
    <style type="text/css">
        html body {
            background: rgb(243, 243, 243);
            background-repeat: no-repeat;
            background-position: top center;
        }

        [v-cloak] {
            display: none;
        }

        .row {
            padding: 35px;
        }

        .login-menu a {
            display: inline-block;
            width: 30%;
            padding: 0;
            text-align: right;
            color: #fff;
        }

        .logo-text {
            font-size: 24px;
            color: #FFFFFF;
            margin-left: 20px;
        }

        .btn-login {
            background: transparent;
            border: 1px solid #FFFFFF;
            color: #FFFFFF;
            margin-right: 20px;
        }

        .btn-register {
            background-color: rgb(34, 182, 252);
        }

        .btn-login,
        .btn-register {
            padding-left: 30px;
            padding-right: 30px;
            float: right;
        }

        .login-contain-title {
            color: #FFFFFF;
            text-align: center;
            margin-top: 30px;
        }

        .login-contain {
            margin-top: 100px;
            margin-top: 150px;
            text-align: center;
        }

        .login-contain .login-input {
            margin: 0 auto !important;
            width: 350px;;
            padding-top: 30px;
        }

        .login-foot span {
            margin-left: 20px;
            font-size: 24px;
        }

        .login-foot span:first-child {
            font-size: 12px;
        }

        ul,
        li {
            margin: 0;
            padding: 0;
            list-style: none;
        }

        body * {
            font-family: "微软雅黑";
        }

        body input {
            border-radius: 5px !important;
        }

        .btn-system {
            background-color: rgb(34, 182, 252);
            border-radius: 3px;
        }

        .register-contain {
            padding-top: 75px;
            text-align: center;
        }

        .register-contain form div {
            border-radius: 3px;
        }

        .register-contain .col-xs-12 {
            margin-top: 10px !important;
        }

        .register-top {
            margin-top: 20px !important;
        }

        .register-head span {
            margin-left: 20px;
            font-size: 28px;
        }

        .register-head * {
            vertical-align: middle;
        }

        .register-login {
            padding-top: 40px;
            text-align: center;
        }

        .register-login a {
            color: rgb(105, 105, 105);
            padding: 20px;
        }

        .register-login a:first-child {
            color: rgb(180, 180, 180);
            border-right: 1px solid rgb(180, 180, 180);
            padding: 0;
            padding-right: 20px;
            padding-left: 20px;
        }

        .register-phone div {
            width: 350px;
            margin: 0 auto;
        }

        .register-phone button {
            height: 40px !important;
            width: 100px !important;
            float: left !important;
        }

        .register-phone input {
            height: 40px !important;
            width: 250px !important;
        }

        .register-text {
            text-align: center;
        }

        .register-text input {
            width: 350px !important;
            height: 40px !important;
            margin: 0 auto;
        }

        .register-text button {
            height: 40px;
            width: 350px;
        }
        .back{
            opacity: 0.5;
        }
    </style>
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