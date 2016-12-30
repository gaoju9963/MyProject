<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/timeFormat_gaoju.js"></script>
    <script src="js/page_gaoju.js"></script>
    <script src="js/pop.js"></script>
    <script src="js/api.js"></script>
    <link rel="shortcut icon" href="picture/employee.ico">
    <title>员工</title>
</head>
<style type="text/css">
    a:link, a:visited, a:hover, .current, #info {
        border: 1px solid #DDD;
        background: #F2F2F2;
        display: inline-block;
        margin: 1px;
        text-decoration: none;
        font-size: 12px;
        width: 15px;
        height: 15px;
        text-align: center;
        line-height: 15px;
        color: #AAA;
        padding: 1px 2px;
    }

    a:hover {
        border: 1px solid #E5E5E5;
        background: #F9F9F9;
    }

    .current {
        border: 1px solid #83E7E4;
        background: #DFF9F8;
        margin: 1px;
        color: #27CBC7;
    }

    #info {
        width: auto;
    }

    .window {
        height: 200px;
        width: 600px;
        background-color: #d0def0;
        position: absolute;
        padding: 2px;
        margin: 5px;
        display: none;
    }

    .title img {
        float: right;
    }

    #addForm {
        margin: 0 auto;
        width: 230px;
        height: 100px;
    }

    form div {
        width: 600px;
        margin-bottom: 20px;
        margin-top: 20px;
    }

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

    /*a  upload */
    .a-upload {
        padding: 4px 10px;
        height: 20px;
        line-height: 20px;
        position: relative;
        cursor: pointer;
        color: #888;
        background: #fafafa;
        border: 1px solid #ddd;
        border-radius: 4px;
        overflow: hidden;
        display: inline-block;
        *display: inline;
        *zoom: 1
    }

    .a-upload input {
        position: absolute;
        font-size: 100px;
        right: 0;
        top: 0;
        opacity: 0;
        filter: alpha(opacity=0);
        cursor: pointer
    }

    .a-upload:hover {
        color: #444;
        background: #eee;
        border-color: #ccc;
        text-decoration: none
    }

    #empList {
        margin: 0 auto;
        width: 900px;
        height: 800px;
    }

    #head_div {
        margin: 0 auto;
        width: 900px;
    }
</style>
<body>
<div id="table">
    <div id="head_div">
        <a>姓名：</a>
        <input id="employeeName" type="text" placeholder="请输入姓名"/>
        <input type="button" value="添加_测试弹窗" id="btn_center"/>
        <div href="javascript:;" class="a-upload">
            <input type="file" name=""/>点击这里上传文件
        </div>
        <div href="javascript:;" class="file">选择文件
            <input type="file" name=""/>
        </div>
    </div>
    <div id="empList">
        <table>
            <thead>
            <th style="width:40px;">#</th>
            <th style="width:150px;">姓名</th>
            <th style="width:150px;">性别</th>
            <th style="width:150px;">年龄</th>
            <th style="width:250px;">时间</th>
            <th style="width:130px;">操作</th>
            </thead>
            <tbody id="tbody"></tbody>
        </table>
        <div id="setpage"></div>
    </div>
</div>
<%--弹框--%>
<div class="window" id="center">
    <div id="title" class="title">添加</div>
    <div id="addForm">
        <form id="form">
            <input type="hidden" id="empId" name="empId" style=""/>
            <div id="inputName">
                <label>姓名</label>
                <input type="text" name="name" id="name" class="required"/>
            </div>
            <div id="inputSex">
                <label>性别</label>
                <input type="text" name="sex" id="sex" class="required" class="required"/>
            </div>
            <div id="inputAge">
                <label>年龄</label>
                <input type="number" name="age" id="age" class="required"/>
            </div>
            <input type="button" id="cancer" value="取消"/>
            <input type="button" onclick="addEmployee()" value="提交"/>
        </form>
    </div>
</div>
<div id="pop_alert"></div>
<script src="js/ceshi.js"></script>
</body>
</html>

