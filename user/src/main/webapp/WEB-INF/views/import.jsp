<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="js/jquery-3.1.1.min.js"></script>
    <title>Title</title>
</head>
<style>

    .file {
        position: relative;
        display: inline-block;
        background: #D0EEFF;
        border: 1px solid #99D3F5;
        border-radius: 4px;
        padding: 4px 12px;
        overflow: hidden;
        color: #1E88C7;
        text-decoration: none;
        text-indent: 0;
        line-height: 20px;
    }

    .file input {
        position: absolute;
        font-size: 100px;
        right: 0;
        top: 0;
        opacity: 0;
    }

    .file:hover {
        background: #AADFFD;
        border-color: #78C3F3;
        color: #004974;
        text-decoration: none;
    }
</style>
<body>
<div href="javascript:;" class="file">选择文件


</div>
<input id="file" type="text" name=""/>
<input type="button" onclick="importEmployee()" value="上传">
<%--<button onclick="importUser()" >上传user</button>--%>
<script>

    function importEmployee() {
        var filePath = $("#file").val();
        $.ajax({
            url: "importEmployee",
            data: {filePath: filePath},
            type: "post",
            dataType: "json",
            success: function (data) {
                alert("导入成功！")
            }
        });
    }

    function importUser() {
        $.ajax({
            url: "importUser",
            type: "post",
            dataType: "json",
            success: function (data) {
                alert("成功！")
            }
        });
    }
</script>
</body>
</html>
