<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../public/head.jsp"></jsp:include>
    <style type="text/css">
        .form-inline .form-control {
            width: 33.3%
        }

        body {
            padding-top: 0px;
        }

        .col-sm-1 {
            width: 13%;
        }

        .row-selected {
            color: #FFFFFF !important;
            background-color: #CCC !important;
        }

        /*使弹出框在最上层*/
        .modal-backdrop {
            z-index: 0 !important;
        }
    </style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12">
            <div class="box" style="margin-bottom:0px !important">
                <div class="box-body">
                    <div class="form-inline">
                        <div class="form-group">
                            <label class="control-label">&nbsp;下拉菜单 &nbsp;</label>
                            <select name="firstParty" id="firstParty" style="width:120px" class="form-control">
                                <option value="">请选择</option>
                                <c:forEach items="${dropDownMenus}" var="dropDownMenus">
                                    <option value="${dropDownMenus.value}">${dropDownMenus.value}</option>
                                </c:forEach>
                            </select>

                            <label class="control-label">&nbsp;下拉 &nbsp;</label>
                            <select name="productScope" id="productScopeSearch" style="width:120px"
                                    class="form-control">
                                <option value="">请选择</option>
                                <option value="1">菜单一</option>
                                <option value="2">菜单二</option>
                            </select>

                            <label class="control-label">&nbsp;日期&nbsp;</label>
                            <input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="signingDateBegin"
                                   style="width:120px" type="text" class="form-control"/>
                            —<input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="signingDateEnd"
                                    style="width:120px" type="text" class="form-control"/>
                        </div>
                    </div>
                    <div id="toolbar" class="form-inline">
                        <label class="control-label ml10">
                            <input id="searchTextInput" style="width:150px" type="text" class="form-control"
                                   placeholder="搜索">
                        </label>
                        <button class=" btn btn-info  living-btn-info ml10" onclick="searchTest()">
                            <i class="fa fa-search"></i> 查询
                        </button>
                        <button class=" btn btn-info  living-btn-info ml10" onclick="removeSearchCondition()">
                            <i class="fa fa-remove"></i> 重置
                        </button>
                        <!-- Button trigger modal -->
                        <button type="button" class="ml20 btn btn-info living-btn-info" onclick="testEdit()">
                            <i class="glyphicon glyphicon-plus"></i> 添加
                        </button>
                    </div>

                    <table class="table table-hover dataTable" id="table"
                           data-toolbar="#toolbar"
                           data-search="false"
                           data-show-refresh="true"
                           data-show-columns="true"
                           data-show-export="true"
                           data-query-params-type="notlimit"
                           data-minimum-count-columns="1"
                           data-show-pagination-switch="true"
                           data-page-number="1"
                           data-page-size="10"
                           data-side-pagination="server"
                           data-pagination="true"
                           data-id-field="id"
                           data-page-list="[5, 10, 15, 25, 50]"
                           data-show-footer="false"
                           data-side-pagination="server"
                           data-unique-id="rowId"
                           data-detail-view="false"
                           data-toolbar-align="left"
                           data-search-align="left"
                           data-buttons-align="right"
                    >
                        <thead>
                        <th data-field="rowId" data-sortable="true" data-order="asc">id</th>
                        <th data-field="name" data-sortable="true" data-order="asc"
                            data-formatter="detailFormatter">名称
                        </th>
                        <th data-field="sex" data-sortable="true" data-order="asc">性别</th>
                        <th data-field="age" data-sortable="true" data-order="asc">年龄</th>
                        <th data-field="createDate" data-formatter="formatCreateDate">创建日期</th>
                        <th data-field="updateDate" data-formatter="formatUpdateDate">修改日期</th>
                        <th data-formatter="operatorFormatter">操作</th>
                        </thead>
                    </table>
                </div><!-- /.box-body -->
            </div><!-- /.box -->
        </div>
    </div><!-- /.row -->
</div>

<div class="dn" id="editSupplierModal" style="display: none">
    <form class="form-horizontal" id="userInfoForm"
          data-fv-framework="bootstrap"
          data-fv-message="请填写信息"
          data-fv-locale="zh_CN"
          data-fv-icon-valid="glyphicon glyphicon-ok"
          data-fv-icon-invalid="glyphicon glyphicon-remove"
          data-fv-icon-validating="glyphicon glyphicon-refresh"
          data-fv-live="enabled"
    >
        <div class="form-group form-inline">
            <div class="form-group col-sm-6">
                <label for="name" class="col-sm-3 control-label" placeholder="姓名">姓名</label>
                <div class="col-sm-8">
                    <input type="text" name="name" class="form-control col-xs-3"
                           style="width:100%" id="name" placeholder="姓名"
                           data-fv-notempty="true"
                           data-fv-notempty-message="请输入姓名"
                    />
                </div>
            </div>
            <div class="form-group col-sm-6">
                <label for="sex" class="col-sm-3 control-label" placeholder="性别">性别</label>
                <div class="col-sm-8">
                    <select name="sex" class="form-control col-sm-6" style="width:100%" id="sex">
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group form-inline">
            <div class="form-group col-sm-6">
                <label for="age" class="col-sm-3 control-label" placeholder="年龄">年龄</label>
                <div class="col-sm-8">
                    <input type="text" name="age" class="form-control col-xs-3"
                           style="width:100%" id="age" placeholder="年龄"
                           data-fv-notempty="true"
                           data-fv-notempty-message="请输入年龄"
                           data-fv-stringlength="true"
                           data-fv-stringlength-max="2"
                           data-fv-stringlength-min="1"
                           data-fv-regexp="true"
                           data-fv-regexp-regexp="^[0-9]*$"
                           data-fv-regexp-message="只能输入数字"
                    />
                </div>
            </div>
        </div>
    </form>
</div>

<div id="pop_alert"></div>
</div>

</body>
<script>
    $(document).ready(function () {
        var $table = $('#table');
        $("#table").bootstrapTable({
            method: "post",
            url: "getEmployeeTest"
        });
    });
    function formatCreateDate(value) {
        var createDate;
        if (value != null) {
            createDate = (new Date(value)).format("yyyy-MM-dd HH:mm:ss");
        } else {
            createDate = null;
        }
        return createDate;
    }
    function formatUpdateDate(value) {
        var updateDate;
        if (value != null) {
            updateDate = (new Date(value)).format("yyyy-MM-dd HH:mm:ss");
        } else {
            updateDate = null;
        }
        return updateDate;
    }
    function operatorFormatter(value, row, index) {
        return [
            '<a class="btn   btn-default btn-xs " data-toggle="modal" data-target="#myModal" href="javascript:void(0)" title="修改" onclick="testEdit(' + '\'' + row.rowId + '\'' + ')" title="修改">',
            '<i class="glyphicon glyphicon-edit"></i>',
            '</a>  ',
            '<a class="btn ml10  btn-default btn-xs" href="javascript:void(0)" onclick="removeTest(' + '\'' + row.rowId + '\'' + ')" title="删除">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join('');
    }
    function searchTest() {
        var searchText = $("#searchTextInput").val();
        var cp = $('#table').bootstrapTable('getOptions');
        var data = {
            pageNumber: cp.pageNumber,
            pageSize: cp.pageSize,
            sortOrder: cp.sortOrder,
            sortName: cp.sortName,
            searchText: searchText
        };

        /**
         *  为了和bootstrap分页传参数的格式保持一致
         *  这里必须这样写
         *  dataType: "json",
         *  data: JSON.stringify(data),
         *  contentType: "application/json",
         */
        $.ajax({
            type: "post",
            url: "getEmployeeTest",
            dataType: "json",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (data) {
                $('#table').bootstrapTable('load', data);
            }
        });
    }

    function detailFormatter(value, row, index) {
        return [
            '<a  href="javascript:void(0)" onclick="detailTest(' + '\'' + row.rowId + '\'' + ')">' + value,
            '</a>'
        ].join('');
    }
    function detailTest(inx) {
        BootstrapDialog.show({
            id: "supplierInfoModal",
            type: BootstrapDialog.TYPE_DEFAULT,
            title: '测试详情',
            message: $("#editSupplierModal").html(),
            onshow: function (dialog) {
                $('#supplierInfoModal').remove();//弹框之前，先清除表单数据
            },
            onshown: function (dialog) {
                //表单赋值
                var data = $('#table').bootstrapTable('getRowByUniqueId', inx);
                $("#supplierInfoModal form").autofill(data);
                $("#supplierInfoModal").find("#name").attr("readOnly", true);
                $("#supplierInfoModal").find("#sex").attr("disabled", true);
                $("#supplierInfoModal").find("select[name='contractType']").val(data.sex);
                $("#supplierInfoModal").find("#age").attr("readOnly", true);
            },
            nl2br: false,
            buttons: [{
                label: '返回',
                action: function (dialog) {
                    dialog.close();
                }
            }]
        })
    }
    function testEdit(inx) {
        var title = "";
        if (inx != null && inx != undefined) {
            title = "测试修改";
        } else {
            title = "测试添加";
        }

        BootstrapDialog.show({
            id: "supplierInfoModal",
            type: BootstrapDialog.TYPE_DEFAULT,
            title: title,
            message: $("#editSupplierModal").html(),
            onshow: function (dialog) {
                $('#supplierInfoModal').remove();//弹框之前，先清除表单数据
            },
            onshown: function (dialog) {
                if (inx != null && inx != undefined) {
                    var data = $('#table').bootstrapTable('getRowByUniqueId', inx);
                    $("#supplierInfoModal form").autofill(data);
                }
            },
            nl2br: false,
            buttons: [{
                id: "submitSupplier",
                label: '确定',
                cssClass: 'btn-success',
                action: function (dialog) {
                    //非空验证 初始化表单
                    var re = $("#supplierInfoModal form").formValidation().formValidation('validate');
                    var validateRes = $("#supplierInfoModal form").data('formValidation').isValid();
                    if (!validateRes) {
                        return;
                    }
                    var url = "addTest";
                    if (inx != null && inx != undefined) {
                        url = "updateTest" + "?rowId=" + inx;
                    } else {
                        url = "addTest";
                    }
                    $.ajax({
                        url: url,
                        data: $("#supplierInfoModal form").serialize(),
                        type: "post",
                        dataType: "json",
                        success: function (value) {
                            if (value.success) {
                                dialog.close();
                                Livingsoil.msgSucc("操作结果", "操作成功", function () {
                                    $("#table").bootstrapTable("refresh", {silent: true});
                                });
                            }
                        }
                    });
                }
            },
                {
                    label: '取消',
                    action: function (dialog) {
                        dialog.close();
                    }
                }
            ]
        })
        ;
    }
    function removeTest(inx) {
        Livingsoil.confirm('确定要删除吗?', function (result) {
            if (result) {
                Livingsoil.ajax("deleteTestByRowId", "post", function (res) {
                    Livingsoil.msgSucc("删除结果", "删除成功", function () {
                        $("#table").bootstrapTable("refresh", {silent: true});
                    });
                }, {rowId: inx});
            }
        });
    }
</script>
</html>
<%--解决页面显示不全的问题，这是Tomcat的bug--%>
<% out.flush(); %>