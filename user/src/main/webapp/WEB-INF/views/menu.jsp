<%--
  Created by IntelliJ IDEA.
  User: pengshu
  Date: 2016/11/15
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="js/jquery-3.1.1.min.js"></script>
    <title>Title</title>
</head>
<body>
    <form action="./getUser">
        <input type="submit" value="点击进入用户列表"/>
    </form>

    <form action="./employeeView">
        <input type="submit" value="点击进入员工列表"/>
    </form>

    <form action="./tankuang">
        <input type="submit" value="点击进入弹框页面"/>
    </form>

    <form action="./import">
        <input type="submit" value="点击进入导入页面"/>
    </form>

    <form action="./scheduled">
        <input type="submit" value="点击进入定时任务页面"/>
    </form>
</body>
</html>
