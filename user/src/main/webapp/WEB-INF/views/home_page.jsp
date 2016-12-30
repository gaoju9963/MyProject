<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="public/head.jsp"></jsp:include>
    <script src="js/tools/timer.js"></script>
    <style>
        .sidebar {
            background-color: aliceblue;
        }

        .nav-sidebar {
            margin-bottom: 1px;
        }

        .user-panel > .image > img {
            width: 100%;
            max-width: 45px;
            height: auto;
        }

        .user-panel > .info {
            padding: 5px 5px 5px 15px;
            line-height: 1;
            position: absolute;
            left: 65px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="test" target="mainframe">Project name</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li></li>
                <li><a href="">Settings</a></li>
                <li><a href="">Profile</a></li>
                <li><a href="gotoLogin">退出登录</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <span id="time_d" class="time"></span>
                <span id="time_h" class="time"></span>
                <span id="time_m" class="time"></span>
                <span id="time_s" class="time"></span>
                <input type="text" class="form-control" placeholder="Search...">
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <%--用户头像--%>
            <div class="panel-group table-responsive">
                <div class="user-panel">
                    <div class="pull-left image">
                        <img src="picture/user2-160x160.jpg" class="img-circle tupian" alt="User Image">
                    </div>
                    <div class="pull-left info">
                        <p id="userName">${userName}</p>
                        <a href="#" style="color: green"> Online</a>
                    </div>
                </div>
            </div>
            <%--菜单--%>
            <ul class="nav nav-sidebar" id="collapseListGroupHeading1" data-toggle="collapse"
                data-target="#collapseListGroup1" role="tab">
                <li class="active"><a href="#">Overview</a></li>
            </ul>
            <ul id="collapseListGroup1" class="nav nav-sidebar panel-collapse collapse">
                <li><a href="testEmployeePage" target="mainframe">员工测试</a></li>
                <li><a href="employeeView" target="mainframe">Export</a></li>
                <li><a href="#">Export</a></li>
            </ul>
            <ul class="nav nav-sidebar" id="collapseListGroupHeading2" data-toggle="collapse"
                data-target="#collapseListGroup2" role="tab">
                <li class="active"><a href="#">标题二</a></li>
            </ul>
            <ul id="collapseListGroup2" class="nav nav-sidebar panel-collapse collapse">
                <li><a href="test" target="mainframe">测试二</a></li>
                <li><a href="#">Export</a></li>
                <li><a href="#">Export</a></li>
            </ul>
        </div>

    </div>
</div>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <iframe style="height: 850px; width: 100%" name="mainframe" src="test" frameborder="0" scrolling="no"></iframe>
</div>
</body>
</html>
<%out.flush(); %>