/**
 *
 */

$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

var setValue = function (name, val) {
    if (val != null) {
        var htmlType = $("[name='" + name + "']").attr("type");
        if (htmlType == "text" || htmlType == "textarea"
            || htmlType == "select-one" || htmlType == "hidden"
            || htmlType == "button") {
            $("[name='" + name + "']").val(val);
        } else if (htmlType == "radio") {
            $("input[type=radio][name='" + name + "'][value='" + val + "']")
                .attr("checked", true);
        } else if (htmlType == "checkbox") {
            var vals = val.toString().split(",");
            for (var i = 0; i < vals.length; i++) {
                $(
                    "input[type=checkbox][name='" + name + "'][value='"
                    + vals[i] + "']").attr("checked", true);
            }
        }
    }
};

var getValue = function (name, index) {
    var htmlType = $("[name='" + name + "']").attr("type");
    if (htmlType == "text" || htmlType == "textarea"
        || htmlType == "select-one" || htmlType == "hidden"
        || htmlType == "button") {
        if (index) {
            if ($("[name='" + name + "']")[index]) {
                return $("[name='" + name + "']")[index].value;
            }
        }
        return $("[name='" + name + "']").val();
    } else if (htmlType == "radio") {
        return $("input[type=radio][name='" + name + "']").val();
    } else if (htmlType == "checkbox") {
        var checkboxs = $("input[type=checkbox][name='" + name + "']")
        var value = [];
        $.each(checkboxs, function (index, obj) {
            if (obj.checked) {
                value.push(obj.value);
            }
        });
        return value.join(",");
    }
}

var Livingsoil = {};

Livingsoil.msgFlg = false;


Livingsoil.msgSucc = function (title, msg, closeCallback) {
    title = title == null || title == undefined ? "通知" : title;
    var bd = BootstrapDialog.show({
        id:"qqqqq",
        closable: false,
        type: BootstrapDialog.TYPE_SUCCESS,
        cssClass: 'BootstrapDialogMsgTitle',
        title: "<div style='text-align:center'>" + title + "</div>",
        message: "<div style='text-align:center'>" + msg + "</div>"
    });

    setTimeout(function () {
        bd.close();
        if (closeCallback) {
            closeCallback();
        }
    }, 1800);
}

Livingsoil.msgError = function (title, msg, closeCallback) {
    title = title == null || title == undefined ? "错误通知" : title;
    var bd = BootstrapDialog.show({
        closable: false,
        type: BootstrapDialog.TYPE_DANGER,
        cssClass: 'BootstrapDialogMsgTitle',
        title: "<div style='text-align:center'>" + title + "</div>",
        message: "<div style='text-align:center'>" + msg + "</div>"
    });

    setTimeout(function () {
        bd.close();
        if (closeCallback) {
            closeCallback();
        }
    }, 1800);
}

Livingsoil.confirm = function (msg, callback) {
    var bd = BootstrapDialog.show({
        closable: false,
        type: BootstrapDialog.TYPE_DANGER,
        cssClass: 'BootstrapDialogMsgTitle',
        title: "<div style='text-align:center'>警告</div>",
        message: "<div style='text-align:center'>" + msg + "</div>",
        buttons: [{
            id: "submitUser",
            label: '确定',
            cssClass: 'btn-danger',
            action: function (dialog) {
                dialog.close();
                callback(true);
            }
        }, {
            label: '取消',
            action: function (dialog) {
                dialog.close();
                callback(false);
            }
        }]
    });
}

Livingsoil.confirmInput = function (title, callback) {
    var myDate = new Date();
    var textareId = "ta" + myDate.getMilliseconds();
    var bd = BootstrapDialog.show({
        closable: false,
        type: BootstrapDialog.TYPE_DANGER,
        cssClass: 'BootstrapDialogMsgTitle',
        title: "<div style='text-align:center'>" + title + "</div>",
        message: $('<textarea rows="6 " id="' + textareId + '" class="form-control" placeholder="请输入驳回原因"></textarea>'),
        buttons: [{
            id: "enterConfirmInput",
            label: '确定',
            cssClass: 'btn-danger',
            action: function (dialog) {
                dialog.close();
                callback(true, $("#" + textareId).val());
            }
        }, {
            label: '取消',
            action: function (dialog) {
                dialog.close();
                callback(false, $("#" + textareId).val());
            }
        }]
    });
}

Livingsoil.formValidate = function (container) {
    var inputs = null;
    if (container) {
        inputs = $("#" + container + " input,select,textarea");
    } else {
        inputs = $("form input,select,textarea");
    }
    for (var i = 0; i < inputs.length; i++) {
        if (!inputs[i].validity.valid) {
            inputs[i].focus();
            return false;
        }
    }
    return true;
}

Livingsoil.loadingDialog = null;

Livingsoil.loading = function () {
    Livingsoil.loadingDialog = null;
    if (typeof BootstrapDialog != "undefined") {
        Livingsoil.loadingDialog = new BootstrapDialog(
            {
                message: function (dialogRef) {
                    var $message = $('<div class="overlay" style="color:white;font-size:40px;">  <i class="fa fa-refresh fa-spin"></i> </div>');
                    return $message;
                },
                closable: false
            });
        Livingsoil.loadingDialog.realize();
        Livingsoil.loadingDialog.getModalHeader().hide();
        Livingsoil.loadingDialog.getModalFooter().hide();
        Livingsoil.loadingDialog.getModalBody().css('background', 'transparent');
        // dialog.getModalBody().css('color', 'transparent');
        Livingsoil.loadingDialog.getModalContent().css("background-color", "transparent");
        Livingsoil.loadingDialog.getModalContent().css("-webkit-box-shadow", "none");
        Livingsoil.loadingDialog.getModalContent().css("text-align", "center");
        Livingsoil.loadingDialog.open();
    }
}

Livingsoil.loaded = function () {
    if (Livingsoil.loadingDialog) {
        Livingsoil.loadingDialog.close();
        Livingsoil.loadingDialog = null;
    }
}
Livingsoil.GlobalDialog = null;

Livingsoil.addGlobalLoadingListener = function () {
    EventBus.addEventListener("loading", function (event) {
        if (Livingsoil.GlobalDialog != null) {
            return;
        }
        if (typeof BootstrapDialog != "undefined") {
            Livingsoil.GlobalDialog = new BootstrapDialog(
                {
                    message: function (dialogRef) {
                        var $message = $('<div class="overlay" style="color:white;font-size:40px;">  <i class="fa fa-refresh fa-spin"></i> </div>');
                        return $message;
                    },
                    closable: false
                });

            Livingsoil.GlobalDialog.realize();
            Livingsoil.GlobalDialog.getModalHeader().hide();
            Livingsoil.GlobalDialog.getModalFooter().hide();
            Livingsoil.GlobalDialog.getModalBody().css('background', 'transparent');
            // dialog.getModalBody().css('color', 'transparent');

            Livingsoil.GlobalDialog.getModalContent().css("background-color", "transparent");
            Livingsoil.GlobalDialog.getModalContent().css("-webkit-box-shadow", "none");
            Livingsoil.GlobalDialog.getModalContent().css("text-align", "center");
            Livingsoil.GlobalDialog.open();
        }

        setTimeout(function () {
            if (Livingsoil.GlobalDialog != null) {
                Livingsoil.GlobalDialog.close();
                Livingsoil.GlobalDialog = null;
            }
        }, 800);

    });
}


Livingsoil.ajax = function (url, type, callback, parms) {
    var dialog = null;
    if (typeof BootstrapDialog != "undefined") {
        dialog = new BootstrapDialog(
            {
                message: function (dialogRef) {
                    var $message = $('<div class="overlay" style="color:white;font-size:40px;">  <i class="fa fa-refresh fa-spin"></i> </div>');
                    return $message;
                },
                closable: false
            });
        dialog.realize();
        dialog.getModalHeader().hide();
        dialog.getModalFooter().hide();
        dialog.getModalBody().css('background', 'transparent');
        // dialog.getModalBody().css('color', 'transparent');
        dialog.getModalContent().css("background-color", "transparent");
        dialog.getModalContent().css("-webkit-box-shadow", "none");
        dialog.getModalContent().css("text-align", "center");
        dialog.open();
    }
    $.ajax({
        type: type || "post",
        data: parms,
        dataType: 'json',
        url: url,
        success: function (data) {
            if (data.statusCode === 200 || data.message == "ok") {//这里高举做了修改，原代码为下行注释
                //if (data.statusCode === 200) {
                callback(data);
            } else if (data.statusCode === 401) {//没有登录
                Livingsoil.msgError("错误信息", "还没有登录哦！");
            } else if (data.statusCode === 403) {//没有登录
                Livingsoil.msgError("错误信息", "还没有权限哦！");
            } else {
                Livingsoil.msgError("错误信息", data.msg);
            }
            if (dialog) {
                dialog.close();
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            Livingsoil.msgError("请联系管理员", textStatus + errorThrown);
            if (dialog) {
                dialog.close();
            }
        }
    });
}

Livingsoil.getQueryString = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

// 全选
$('table.table').on('click', '#set_clickAll', function () {
    if (this.checked) {
        $('input[name="tmodclick"]').each(function () {
            this.checked = true;
        });
    } else {
        $('input[name="tmodclick"]').each(function () {
            this.checked = false;
        });
    }
    return this;
});

// 反选
$('table.table').on('click', 'input[name="tmodclick"]', function () {
    var isAll = true;
    var list = $('input[name="tmodclick"]');
    $.each(list, function (index, obj) {
        isAll = isAll && obj.checked;
    });
    $("#set_clickAll")[0].checked = isAll;
});

if ($(".timeInput").length) {
    $('.timeInput').datepicker({
        language: 'zh-CN'
    });
}

/**
 * 菜单导航
 *
 * @param target
 */
function setNav(target) {
    var mark = false;
    var items = $("#sidebar-menu a");
    if (items.length > 0) {
        items.each(function (i, o) {
            if ($(o).attr("href").indexOf(target + ".jsp") > -1) {
                var parentLi = $(o).parentsUntil("#sidebar-menu").filter("li")
                    .attr("class", "active");
                window.sessionStorage.setItem("nav", target);
                mark = true;
                return;
            }
        });
        if (!mark) {
            var preNav = window.sessionStorage.getItem("nav");
            if (preNav) {
                setNav(preNav);
            }
        }
    }
}


function triggerPrevPage() {
    $(".pagination").trigger('prevPage');
}

function triggerNextPage() {
    $(".pagination").trigger('nextPage');
}

function setbar(text) {
    $("a:contains('" + text + "')").parent().parent().show();

}

var LivingSoilTable = {};
LivingSoilTable.SexFormat = function (value, row, index) {
    if (value == 1) {
        return '男';
    } else {
        return '女';
    }
}

Livingsoil.EventParamType = {
    CallIframe: "CrossIFrame"
}

Livingsoil.EventBus = {
    CrossIFrame: "CrossIFrame"
}

Livingsoil.tips = function (id, content, maxWidth) {
    $("#" + id).focus(function (e) {
        layer.tips(content, "#" + id, {
            tips: [3, '#3c8dbc'],
            time: 1115000,
            maxWidth: maxWidth
        });
    });

    $("#" + id).blur(function (e) {
        layer.closeAll();
    });
}
Livingsoil.fmoney = function (str) {//s:待转化数字，n每几位格式化
    var newStr = "";
    var count = 0;

    if (str.indexOf(".") == -1) {
        for (var i = str.length - 1; i >= 0; i--) {
            if (count % 3 == 0 && count != 0) {
                newStr = str.charAt(i) + "," + newStr;
            } else {
                newStr = str.charAt(i) + newStr;
            }
            count++;
        }
        str = newStr + ".00"; //自动补小数点后两位
    }
    else {
        for (var i = str.indexOf(".") - 1; i >= 0; i--) {
            if (count % 3 == 0 && count != 0) {
                newStr = str.charAt(i) + "," + newStr;
            } else {
                newStr = str.charAt(i) + newStr; //逐个字符相接起来
            }
            count++;
        }
        str = newStr + (str + "00").substr((str + "00").indexOf("."), 3);
    }
    return str;
}

String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

//与数组中的某个数比对
//array.in_array('值')
Array.prototype.in_array = function (e) {
    var r = new RegExp(',' + e + ',');
    return (r.test(',' + this.join(this.S) + ','));
}

function arcSelect2() {
    $('select').on('mouseover', function () {
        if ($(this).attr("class").indexOf("un-select2") == -1) {
            $(this).select2();
        }
    });
}

function moneyFormatter(value, row, index) {
    return Livingsoil.fmoney(value.toString());
}

//日期插件
Livingsoil.datePickerValidator = {
    dateFmt: 'yyyy-MM-dd',
    onpicked: function () {
        $(this).parents('form:first').formValidation('revalidateField', this.name)
    },
    oncleared: function () {
        $(this).parents('form:first').formValidation('revalidateField', this.name)
    }
}