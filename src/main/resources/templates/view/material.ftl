<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <link rel="shortcut icon" type="image/x-icon" href="/static/img/logo.ico">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/static/css/animate.css" rel="stylesheet">
    <link href="/static/css/style.css" rel="stylesheet">
    <link href="/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <title>项目材料</title>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content">
            <div class="row row-lg">
                <div class="col-sm-12">
                    <!-- Example Events -->
                    <div class="example-wrap">
                        <h4 class="example-title">项目材料管理</h4>
                        <div class="example">
                            <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
                                <button id="addMaterial" type="button" class="btn btn-success" data-toggle="modal"
                                >上传材料
                                </button>
                                <label style="height: 50px;margin-left: 10px;">名称：<input id="searchProjectName"
                                                                                         type="text"
                                                                                         style="height: 34px;"
                                                                                         placeholder="输入项目名称"></label>
                                <button id="search" style="left: 350px;overflow-x:visible;overflow-y:visible;"
                                        type="button" class="btn btn-primary">查询
                                </button>
                                <button id="reset" type="button"
                                        style="left: 370px;overflow-x:visible;overflow-y:visible;"
                                        class="btn btn-default">重置
                                </button>
                            </div>
                            <table id="exampleTableEvents" data-mobile-responsive="true">
                                <thead>
                                <tr>
                                    <th data-field="state" data-checkbox="true"></th>
                                    <th data-field="id" >项目ID</th>
                                    <th data-field="number" data-switchable="false">项目编号</th>
                                    <th data-field="name" data-switchable="false">项目名称</th>
                                    <th data-field="manager" data-switchable="false">负责人</th>
                                    <th data-field="createUser">创建人</th>
                                    <th data-field="savePath">下载材料</th>
                                    <th data-field="createTime" data-visible="false">创建时间</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                    <!-- End Example Events -->
                </div>
            </div>
        </div>
    </div>

<#--新增软件窗口-->
    <div class="modal inmodal fade" id="uploadMaterial" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-lg" style="min-width: 900px!important;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">上传材料</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal m-t" id="commentForm" enctype="multipart/form-data"
                          action="/material/upload" method="post">
                        <div class="form-group">
                            <div class="input-group">
                                <input id="id1" type="hidden" class="form-control" name="id"  required="required" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">项目名称：</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="name1" name="name"
                                       required="required"
                                       aria-required="true">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-white dropdown-toggle"
                                            data-toggle="dropdown">
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">创建人：</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="createUser1" name="createUser"
                                       required="required"
                                       aria-required="true" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">选择文件：</label>
                            <div class="input-group">
                                <input id="ctFile" type="file" class="form-control" name="ctFile" required="required" >
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                            <button id="submit" class="btn btn-primary" type="submit">上传</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 全局js -->
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/jquery.form.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/plugins/suggest/bootstrap-suggest.min.js"></script>

<!-- Bootstrap table -->
<script src="/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<!-- jQuery Validation plugin javascript-->
<script src="/static/js/plugins/validate/jquery.validate.min.js"></script>
<script src="/static/js/plugins/validate/messages_zh.min.js"></script>

<script src="/static/js/plugins/validate/form-validate-demo.js"></script>
<!-- 自定义js -->
<script src="/static/js/content.js"></script>
<!-- Sweet alert -->
<script src="/static/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/ctfx/material.js"></script>
</body>
</html>