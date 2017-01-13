<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../../public/head.jsp"></jsp:include>
    <!-- Custom styles for this template -->
    <link href="/css/bootstrap/navbar-fixed-top.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>yonghu</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12">
            <div class="box" style="margin-bottom:0px !important">
                <div class="box-body">
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
                            data-formatter="detailFormatter">账号
                        </th>
                        <th data-field="password" data-sortable="true" data-order="asc">密码</th>
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
</body>
</html>
