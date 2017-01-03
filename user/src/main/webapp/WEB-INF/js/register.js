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
    var numError = $('form .onError').length;
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
                $parent.append('<small class="formtips onError page">' + errorMsg + '</small>');
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
