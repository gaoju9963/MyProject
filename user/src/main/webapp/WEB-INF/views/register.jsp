<!DOCTYPE html>
<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="js/jquery-3.1.1.min.js" charset="utf-8"></script>
    <script src="js/tools/api.js"></script>
    <script src="js/tools/pop.js"></script>
    <title>注册</title>
    <style type="text/css">
        html body {
            background: rgb(243, 243, 243);
        }
    </style>
    <style type="text/css">
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
            width: 360px;
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

        .register-success span {
            margin-left: 30px;
        }

        .modal-footer a {
            margin-right: 50px;
        }

        .form-message {
            width: 350px;
            margin: 0 auto;
            text-align: left;
            color: red;
        }
    </style>
</head>
<body id="body">
<div class="container">
    <form id="loginController">
        <div class="row register-contain">
            <div class="col-xs-12 register-head" onclick="javascript:window.location.href='gotoLogin'"><span>ruyi</span>
            </div>
            <div class="col-xs-12 register-login">
                <a href="javascript:void(0);" onclick="javascript:window.location.href='gotoLogin'">用户登录</a>
                <a>注册用户</a></div>
            <div class="register-top register-phone">
                <div class="form-group">
                    <div class="input-group input-group-sm">
                        <input type="text" style="font-size: 14px;" class="form-control" id="cellPhone" name="cellPhone"
                               placeholder="手机" readonly onfocus="this.removeAttribute('readonly');"
                               autocomplete="off"/>
                        <span class="input-group-btn">
                      <button onclick="message()" class="btn btn-info btn-system" type="button">获取验证码</button>
                    </span>
                    </div><!-- /input-group -->
                    <div class="form-message">&nbsp;</div>
                </div>
            </div>
            <div class="col-xs-12 register-text">
                <div class="form-group">
                    <input type="text" id="code" name="code" placeholder="验证码" class="form-control"
                           readonly onfocus="this.removeAttribute('readonly');" autocomplete="off"/>
                </div>
                <div class="form-message">&nbsp;</div>
            </div>
            <div class="col-xs-12 register-text">
                <div class="form-group">
                    <input type="text" id="userName" name="login_name" placeholder="用户名" class="form-control"
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
                <input type="button" class="btn btn-info btn-system btn-success"
                       value="注册"/>
            </div>
        </div>
        <div id="pop_alert" style="color: red"></div>
    </form>
</div>
</body>
<script>
    /**
     * Created by gaoju on 2016/12/10.
     */
    $(document).ready(function () {
        initEvent();
    });
    function initEvent() {
        yanzheng();
    }
    function message() {
        var numError = $('form .mobileOnError').length;
        if (numError) {
            return false;
        }
        var phoneNumber = $("#cellPhone").val();
        ryapi.invock("addPhoneCode", {phoneNumber: phoneNumber},
                function (rult) {
                    if (rult.apiRult.success) {
                        $.popMsg.Alert("验证码已经成功发送，请输入。。。");
                    } else {
                        $.popMsg.Alert(rult.apiRult.message);
                    }
                });
    }
    function register() {
        var phoneNumber = $("#cellPhone").val();
        var code = $("#code").val();
        var userName = $("#userName").val();
        var password = $("#password").val();
        // ryapi.invock(
        //     "register",
        //     {phoneNumber: phoneNumber, code: code, userName: userName, password: password},
        //     function (rult) {
        //         if (rult.apiRult.success) {
        //             $.popMsg.Alert("注册成功,请登录");
        //             location.href = 'gotoLogin';
        //         } else {
        //             $.popMsg.Alert(rult.apiRult.message);
        //         }
        //     });
        $.ajax({
            url: "register",
            data: {phoneNumber: phoneNumber, code: code, userName: userName, password: password},
            type: "post",
            dataType: "json",
            success: function (data) {
                console.log(data);
                if (data.success) {
                    console.log(data);
                    $.popMsg.Alert("注册成功,请登录");
                    location.href = 'gotoLogin';
                } else {
                    console.log(data);
                    $.popMsg.Alert(data.message);
                }
            }
        });
    }

    function yanzheng() {

        $('form :input').blur(function () {
            var $parent = $(this).parent().next();
            $parent.find(".formtips").remove();
            if ($(this).is('#cellPhone')) {
                if (this.value == "" || ( this.value != "" && !/^0?1[3|4|5|8][0-9]\d{8}$/.test(this.value) )) {
                    var errorMsg = '请输入正确的手机号.';
                    $parent.append('<small class="formtips onError page mobileOnError">' + errorMsg + '</small>');
                }
            }
            if ($(this).is('#code')) {
                if (this.value == "" || this.value.length < 6) {
                    var errorMsg = '请输入6位验证码.';
                    $parent.append('<small class="formtips onError">' + errorMsg + '</small>');
                } else {
                    var okMsg = '';
                    $parent.append('<small class="formtips onSuccess">' + okMsg + '</small>');
                }
            }
            //验证用户名
            if ($(this).is('#userName')) {
                if (this.value == "") {
                    var errorMsg = '请输入用户名.';
                    $parent.append('<small class="formtips onError">' + errorMsg + '</small>');
                    return;
                }
                else {
                    userNameIsHave(this.value);
                    // $parent.append('<small class="formtips onSuccess">' + nameIsHave + '</small>');
                }
            }
            if ($(this).is('#password')) {
                if (this.value == "") {
                    var errorMsg = '请输入密码.';
                    $parent.append('<small class="formtips onError">' + errorMsg + '</small>');
                }
                if (this.value != "" && !/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/.test(this.value)) {
                    var errorMsg = '请输入6~12位数字和字母组合.';
                    $parent.append('<small class="formtips onError">' + errorMsg + '</small>');
                }
                else {
                    var okMsg = '';
                    $parent.append('<small class="formtips onSuccess">' + okMsg + '</small>');
                }
            }
        }).keyup(function () {
            $(this).triggerHandler("blur");
        }).focus(function () {
            $(this).triggerHandler("blur");
        });
        $("#loginController").on("click", ".btn-success", function () {
            $("form :input").trigger('blur');
            var numError = $('form .onError').length;
            if (numError) {
                return false;
            }
            register();
        });

        function userNameIsHave(username) {
            $.ajax({
                url: "userNameIsHave",
                data: {userName: username},
                type: "post",
                dataType: "json",
                success: function (data) {
                    if (data.value != null) {
                        $("#userName").parent().next().append('<small class="formtips onSuccess">' + '用户已被注册' + '</small>');
                    } else {
                        $("#userName").parent().next().append('<small class="formtips onSuccess">' + '' + '</small>');
                    }
                }
            });
        }
    }

</script>
</html>
<%out.flush(); %>
