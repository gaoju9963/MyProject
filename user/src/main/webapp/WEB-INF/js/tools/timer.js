/**
 * Created by pengshu on 2016/12/13.
 */
var insertTime = new Date();
var startTime = insertTime.getTime();
$(document).ready(function () {
    var time = 5;
    console.log(time + "秒钟后启动定时任务");
    window.setTimeout(timerun(), time * 1000);
    setInterval(print, 1000);
});

function timerun() {
    /*定时任务操作*/
}

function print() {
    var currentTime = new Date().getTime();
    var time_distance = currentTime - startTime;
    if (time_distance > 0) {
        // 天时分秒换算
        var int_day = Math.floor(time_distance / 86400000)
        time_distance -= int_day * 86400000;

        var int_hour = Math.floor(time_distance / 3600000)
        time_distance -= int_hour * 3600000;

        var int_minute = Math.floor(time_distance / 60000)
        time_distance -= int_minute * 60000;

        var int_second = Math.floor(time_distance / 1000)
        // 时分秒为单数时、前面加零
        if (int_day < 10) {
            int_day = "0" + int_day;
        }
        if (int_hour < 10) {
            int_hour = "0" + int_hour;
        }
        if (int_minute < 10) {
            int_minute = "0" + int_minute;
        }
        if (int_second < 10) {
            int_second = "0" + int_second;
        }
        // 显示时间
        $("#time_d").html("在线时长：" + int_day + "天");
        $("#time_h").html(int_hour + "时");
        $("#time_m").html(int_minute + "分");
        $("#time_s").html(int_second + "秒");

    } else {
        $("#time_d").html("在线时长：" + '00' + "天");
        $("#time_h").html('00' + "时");
        $("#time_m").html('00' + "分");
        $("#time_s").html('00' + "秒");
    }
}