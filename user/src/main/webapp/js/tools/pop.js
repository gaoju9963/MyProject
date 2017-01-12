/**
 * Created by pengshu on 2016/11/23.
 */
(function () {
    $.popMsg = {
        Alert: function (msg) {
            Test("alert", msg);
        },
        Confirm: function (msg, callback) {
            Test("confirm", msg);
            btnOk(callback);
            btnNo(callback);
        }

    }

    var cler;
    var i = 0;
    var count = 5;

    function Test(type, msg) {
        var _html = "";
        _html += '<div id="div_alert">';
        _html += '<div id="mb_con"><span id="mb_tit"></span>';
        _html += '<div id="mb_msg"></div>';
        if (type == "confirm") {
            _html += '<input id="mb_btn_ok" type="button" value="确定" />';
            _html += '<input id="mb_btn_no" type="button" value="取消" />';
        }
        _html += '</div>';
        _html += '</div>';
        $("#pop_alert").html(_html);
        GenerateCss();

        if (!(cler == null || cler == undefined)) {
            window.clearInterval(cler);
        }
        document.getElementById("div_alert").style.display = "block";
        document.getElementById("mb_tit").innerHTML = "消息";
        document.getElementById("mb_msg").innerHTML = msg;

        if (type == "alert") {
            i = 0;
            count = 2;
            timerun();
        }

    }

    function timerun() {

        if (i >= count) {
            document.getElementById("div_alert").style.display = "none";
            window.clearInterval(cler);

        }
        else {
            cler = window.setTimeout(timerun, 500);

        }
        i++;
    }

    function GenerateCss() {
        $("#mb_con").css({
            zIndex: '999999', width: '300px', position: 'fixed',
            backgroundColor: '#DDD', borderRadius: '15px'
        });
        $("#mb_tit").css({
            display: 'block', textAlign: 'center',fontSize: '12px', color: '#444',
            padding: '5px 15px', backgroundColor: '#DDD', borderRadius: '15px 15px 0 0',
            borderBottom: '3px solid #009BFE', fontWeight: 'bold'
        });
        $("#mb_msg").css({
            padding: '20px', lineHeight: '20px', textAlign: 'center',
            fontSize: '23px', fontFamily: '微软雅黑'
        });
        $("#mb_btn_ok,#mb_btn_no").css({width: '85px', height: '30px', color: 'white', border: 'none'});
        $("#mb_btn_ok").css({backgroundColor: '#168bbb'});
        $("#mb_btn_no").css({backgroundColor: 'gray', marginLeft: '20px'});
        var _widht = document.documentElement.clientWidth;  //屏幕宽
        var _height = document.documentElement.clientHeight; //屏幕高
        var boxWidth = $("#mb_con").width();
        var boxHeight = $("#mb_con").height();
        //让提示框居中
        $("#mb_con").css({top: 10 + "px", left: (_widht - boxWidth) / 2 + "px"});
    }

    //确定按钮事件
    var btnOk = function (callback) {
        $("#mb_btn_ok").click(function () {
            $("#div_alert").remove();
            if (typeof (callback) == 'function') {
                callback(true);
            }
        });
    }
    //取消按钮事件
    var btnNo = function (callback) {
        $("#mb_btn_no").click(function () {
            $("#div_alert").remove();
            if (typeof (callback) == 'function') {
                callback(false);
            }
        });
    }

})();
