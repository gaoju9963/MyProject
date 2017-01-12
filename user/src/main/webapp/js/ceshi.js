/**
 * Created by gaoju on 2016/11/19.
 */
//var totalpage, pagesize, initpage, count, curcount, outstr;
//初始化
var totalpage;
var initpage = 1;
var pagesize = 5;
$(document).ready(function () {
    getEmployee(pagesize, initpage);
    initEvent();
});

function getEmployee(pagesize, pageNum) {
    initpage = pageNum;
    var name = $("#employeeName").val();
    $.ajax({
        url: "getEmployee",
        data: {pageNum: pageNum, pageSize: pagesize, name: name},
        type: "post",
        dataType: "json",
        success: function (data) {
            if (data.success) {
                var tbody = [];
                totalpage = data.value.totalPage;
                $.each(data.value.list, function (obj) {

                    var em = data.value.list;
                    var name = em[obj].name;

                    var createDate;
                    if (em[obj].createDate != null) {
                        createDate = (new Date(em[obj].createDate)).format("yyyy-MM-dd HH:mm:ss");
                    }
                    else {
                        createDate = "";
                    }
                    tbody.push('<tr><td style="text-align:center;">' + em[obj].empId + '</td>',
                        '<td style="text-align:center;">' + em[obj].name + '</td>',
                        '<td style="text-align:center;">' + em[obj].sex + '</td>',
                        '<td style="text-align:center;">' + em[obj].age + '</td>',
                        '<td style="text-align:center;">' + createDate + '</td>',
                        '<td style="text-align:center;">',
                        '<input type="button" id="' + em[obj].empId + '" class="delButton" value="删除" />',
                        '<input type="button" id="' + em[obj].empId + '" class="editButton" value="编辑" /></td>',
                        '</tr>');
                });
                $("#tbody").html(tbody.join(''));//拼接
                setpage(totalpage, pagesize, pageNum);
            } else {
                alert(data.message);
            }
        },
        error: function (e) {

            // alert(e.apiRult.success());
        }
    });

    // ryapi.invock("getEmployee", {pageNum: pageNum, pageSize: pagesize, name: name},
    //     function (rult) {
    //         // alert(rult.apiRult.message);
    //             if (rult.apiRult.success) {
    //                 alert("成功了");
    //             } else {
    //                 alert(rult.apiRult.message);
    //             }
    //     });
}

function initEvent() {
    $("#tbody").on("click", ".delButton", function (e) {
        var id = $(this).attr("id");
        del(id);
        return false;
    });

    $("#tbody").on("click", ".editButton", function (e) {
        var id = $(this).attr("id");
        popCenterWindow(id);
    });

    $("#btn_center").click(function () {
        popCenterWindow(0);
    });

    $("#employeeName").bind("input propertychange", function queryEmployee() {
        getEmployee(pagesize, 1);
    });
}

function addEmployee() {


    $("form :input.required").trigger('blur');
    var numError = $('form .onError').length;
    if (numError) {
        return false;
    }
    alert("验证通过");

    var empId = $("#empId").val();
    var name = $("#name").val();
    var sex = $("#sex").val();
    var age = $("#age").val();
    if (empId == "") {
        $.ajax({
            async: false,
            url: "add",
            data: {name: name, sex: sex, age: age},
            type: "post",
            dataType: "json",
            success: function (data) {
                $("#center").hide();

                $("#empId").val("");
                $("#name").val("");
                $("#sex").val("");
                $("#age").val("");

                // $("#tbody").children("tr").remove();
                getEmployee(pagesize, initpage);
                $.popMsg.Alert("添加成功！");
                return false;
            }
        });
    }
    else {
        if (confirm("确定要修改？")) {
            $.ajax({
                url: "edit",
                data: {empId: empId, name: name, sex: sex, age: age},
                type: "post",
                dataType: "json",
                success: function (data) {
                    $("#center").hide();

                    $("#empId").val("");
                    $("#name").val("");
                    $("#sex").val("");
                    $("#age").val("");

                    // $("#tbody").children("tr").remove();
                    getEmployee(pagesize, initpage);
                    $.popMsg.Alert("更新成功！");
                    return false;
                }
            });
        }
    }
}


function gotopage(pageNum) {//把页面计数定位到第几页
    //调用显示页面函数显示第几页,这个功能是用在页面内容用ajax载入的情况
    //reloadpage(target);
    // $("#tbody").children("tr").remove();
    initpage = pageNum;
    var name = $("#employeeName").val();
    getEmployee(this.pagesize, pageNum, name);
}

function del(id) {
    var result = confirm("确定要删除？");
    if (result) {
        $.ajax({
            url: "delEmployeeById",
            data: {id: id},
            type: "post",
            dataType: "json",
            success: function () {
                getEmployee(pagesize, initpage);
                alert("删除成功！");
            },
            error: function () {
                alert("出错");
            }
        });
    }
}

//获取窗口的高度
var windowHeight;
//获取窗口的宽度
var windowWidth;
//获取弹窗的宽度
var popWidth;
//获取弹窗高度
var popHeight;
function init() {
    windowHeight = $(window).height();
    windowWidth = $(window).width();
    popHeight = $(".window").height();
    popWidth = $(".window").width();
}
//定义弹出居中窗口的方法
function popCenterWindow(id) {
    if (id != 0) {
        $("#title").html("修改");
        $.ajax({
            url: "getEmployeeById",
            data: {id: id},
            type: "post",
            dataType: "json",
            success: function (data) {
                $("#empId").val(id);
                $("#name").val(data.name);
                $("#sex").val(data.sex);
                $("#age").val(data.age);
            }
        });
    } else {
        $("#title").html("添加");
    }
    init();
    //计算弹出窗口的左上角Y的偏移量
    var popY = (windowHeight - popHeight) / 2;
    var popX = (windowWidth - popWidth) / 2;
    //alert('jihua.cnblogs.com');
    //设定窗口的位置
    $("#center").css("top", popY).css("left", popX).show();
    closeWindow();
}
//关闭窗口的方法
function closeWindow() {
    $("#cancer").click(function () {
        $("#empId").val("");
        $("#name").val("");
        $("#sex").val("");
        $("#age").val("");
        $("#center").hide();
    });
}

$(function () {
    /*
     *思路大概是先为每一个required添加必填的标记，用each()方法来实现。
     *在each()方法中先是创建一个元素。然后通过append()方法将创建的元素加入到父元素后面。
     *这里面的this用的很精髓，每一次的this都对应着相应的input元素，然后获取相应的父元素。
     *然后为input元素添加失去焦点事件。然后进行用户名、邮件的验证。
     *这里用了一个判断is()，如果是用户名，做相应的处理，如果是邮件做相应的验证。
     *在jQuery框架中，也可以适当的穿插一写原汁原味的javascript代码。比如验证用户名中就有this.value，和this.value.length。对内容进行判断。
     *然后进行的是邮件的验证，貌似用到了正则表达式。
     *然后为input元素添加keyup事件与focus事件。就是在keyup时也要做一下验证，调用blur事件就行了。用triggerHandler()触发器，触发相应的事件。
     *最后提交表单时做统一验证
     *做好整体与细节的处理
     */
    //如果是必填的，则加红星标识.
    $("form :input.required").each(function () {
        var $required = $("<strong class='high'> *</strong>"); //创建元素
        $(this).parent().append($required); //然后将它追加到文档中
    });
    //文本框失去焦点后
    $('form :input').blur(function () {
        var $parent = $(this).parent();
        $parent.find(".formtips").remove();
        //验证用户名
        if ($(this).is('#name')) {
            if (this.value == "" || this.value.length < 6) {
                var errorMsg = '请输入至少6位的用户名.';
                $parent.append('<span class="formtips onError" style="color: red">' + errorMsg + '</span>');
            } else {
                var okMsg = '输入正确.';
                $parent.append('<span class="formtips onSuccess">' + okMsg + '</span>');
            }
        }
    }).keyup(function () {
        $(this).triggerHandler("blur");
    }).focus(function () {
        $(this).triggerHandler("blur");
    });//end blur

});