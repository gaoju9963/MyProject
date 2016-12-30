<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>test页面</title>
</head>
<body>
<%--最外层的标签Id统一为main--%>
<div id="main" class="container-fluid">
    <div class="row">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">测试</h2>
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>年龄</th>
                        <th>时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td id="name">测试</td>
                        <td id="sex">测试</td>
                        <td id="age">测试</td>
                        <td id="time">测试</td>
                        <td>测试</td>
                    </tr>
                    </tbody>
                </table>

                <nav>
                    <ul class="pagination">
                        <li><a href="#">&laquo;上一页</a></li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">下一页&raquo;</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        getEmployee();
    });
    function getEmployee(pagesize, pageNum) {
//        initpage = pageNum;
//        var name = $("#employeeName").val();
        $.ajax({
            url: "getEmployee",
//            data: {pageNum: pageNum, pageSize: pagesize, name: name},
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.success) {
//                    var tbody = [];
//                    totalpage = data.value.totalPage;
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
                        $("#name").html(em[obj].name);
//                        tbody.push('<tr><td style="text-align:center;">' + em[obj].empId + '</td>',
//                                '<td style="text-align:center;">' + em[obj].name + '</td>',
//                                '<td style="text-align:center;">' + em[obj].sex + '</td>',
//                                '<td style="text-align:center;">' + em[obj].age + '</td>',
//                                '<td style="text-align:center;">' + createDate + '</td>',
//                                '<td style="text-align:center;">',
//                                '<input type="button" id="' + em[obj].empId + '" class="delButton" value="删除" />',
//                                '<input type="button" id="' + em[obj].empId + '" class="editButton" value="编辑" /></td>',
//                                '</tr>');
                    });
//                    $("#tbody").html(tbody.join(''));//拼接
//                    setpage(totalpage, pagesize, pageNum);
                } else {
                    alert(data.message);
                }
            },
        });
    }
</script>
</body>
</html>
