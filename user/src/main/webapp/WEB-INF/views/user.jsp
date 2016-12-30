<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="js/jquery-3.1.1.min.js"></script>
    <title>Title</title>
</head>
<body>
<table>
    <tbody>
    <tr>
        <th style="width:40px;">#</th>
        <th style="width:150px;">姓名</th>
        <th style="width:150px;">年龄</th>
        <th style="width:130px;">操作</th>
    </tr>
    <c:forEach items="${list}" var="user">
        <tr>
            <td style="text-align:center;">${user.id}</td>
            <td style="text-align:center;">${user.name}</td>
            <td style="text-align:center;">${user.age}</td>
            <td></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%--<input type="button" onclick="req()" value="请求">--%>

<script>
    function req() {
//        $.ajax({
//            url: " getUser
        ",
        // type: "POST",
        //// contentType: "application/x-wisely",
        // success: function (data) {
        // $("#resp").html(data);
        // }
        // });
        $.ajax({
            url: "getUser",
            type: "post",
            dataType: 'json',
            success: function (data) {
                $("#resp").html(data);
            }
        });

    }
</script>
</body>


</html>

