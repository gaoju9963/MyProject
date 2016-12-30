<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/tankuang.js"></script>
    <title>模拟alert和confirm提示框</title>
</head>
<body>
<input id="add" type="button" value="添加"/>
<input id="delete" type="button" value="删除"/>
<input id="update" type="button" value="修改"/>

<script type="text/javascript">
    $("#add").bind("click", function () {
        $.MsgBox.Alert("消息", "哈哈，添加成功！");
    });
    //回调函数可以直接写方法function(){}
    $("#delete").bind("click", function () {
        $.MsgBox.Confirm("温馨提示", "执行删除后将无法恢复，确定继续吗？温馨提示", function () {
            alert("你居然真的删除了...");
        });
    });
    function test() {
        alert("你点击了确定,进行了修改");
    }
    //也可以传方法名 test
    $("#update").bind("click", function () {
        $.MsgBox.Confirm("温馨提示", "确定要进行修改吗？", test);
    });
    //当然你也可以不给回调函数,点击确定后什么也不做，只是关闭弹出层
    //$("#update").bind("click", function () { $.MsgBox.Confirm("温馨提示", "确定要进行修改吗？"); });
</script>
</body>
</html>