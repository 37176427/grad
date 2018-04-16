<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <link rel="shortcut icon" type="image/x-icon" href="/static/img/logo.ico">
    <link href="/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="/static/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/static/css/animate.css" rel="stylesheet">
    <link href="/static/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <title>工具信息</title>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <!-- Panel Other -->
    <div class="ibox float-e-margins">
        <div class="ibox-content">
            <div class="row row-lg">
                <div class="col-sm-12">
                    <!-- Example Events -->
                    <div class="example-wrap">
                        <h4 class="example-title">用户信息</h4>
                        <div class="example">
                            <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
                                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal5">新增</button>
                                <button type="button" id ="edit" class="btn btn-warning" data-toggle="modal" >修改</button>
                                <button type="button" id ="delete" class="btn btn-warning" data-toggle="modal">删除</button>
                                <label style="height: 50px;margin-left: 10px;">名称：<input id="searchUserName" type="text" style="height: 34px;" placeholder="输入用户名称"></label>
                                <button id="search" style="left: 350px;overflow-x:visible;overflow-y:visible;" type="button" class="btn btn-primary">查询</button>
                                <button id="reset" type="button" style="left:370px;overflow-x:visible;overflow-y:visible;" class="btn btn-default">重置</button>
                            </div>
                            <table id="exampleTableEvents" data-mobile-responsive="true">
                                <thead>
                                <tr>
                                    <th data-field="state" data-checkbox="true"></th>
                                    <th data-field="id" data-switchable="false">用户ID</th>
                                    <th data-field="name">登录名</th>
                                    <th data-field="realName">姓名</th>
                                    <th data-field="password">密码</th>
                                    <th data-field="permission">权限</th>
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

    <div class="modal inmodal fade" id="myModal5" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">新增用户</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal m-t" id="commentForm2" name="commentForm2" action="/system/user/add" method="post">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户账号：</label>
                            <div class="col-sm-8">
                                <input id="userNameAdd"  name="name" minlength="1" maxlength="100" type="text" class="form-control" required="" aria-required="true" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">姓名：</label>
                            <div class="col-sm-8">
                                <input id="realName" type="text" maxlength="100" class="form-control" name="realName" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户密码：</label>
                            <div class="col-sm-8">
                                <input id="password" type="text" maxlength="100" class="form-control" name="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">权限：</label>
                            <div class="col-sm-8">
                                <input id="permission" type="number" min="0" max="2"  class="form-control" name="permission">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                            <button type="submit"  class="btn btn-primary" >添加</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="modal inmodal fade" id="myModal6" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">修改工具</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal m-t" id="commentForm" name="commentForm" action="/system/user/update" method="post">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户账号：</label>
                            <div class="col-sm-8">
                                <input id="userNameEdit"  name="name" minlength="1" maxlength="100" type="text" class="form-control" required="" aria-required="true">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">姓名：</label>
                            <div class="col-sm-8">
                                <input id="realName1" type="text" maxlength="100" class="form-control" name="realName" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户密码</label>
                            <div class="col-sm-8">
                                <input id="password1" type="text" maxlength="100" class="form-control" name="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">权限</label>
                            <div class="col-sm-8">
                                <input id="permission1" type="number" min="0" max="2" class="form-control" name="permission">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                            <button type="submit"  class="btn btn-primary" >保存</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!--<div class="modal inmodal fade" id="myModal7" tabindex="-1" role="dialog"  aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">查询工具(名称)</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal m-t" id="commentForm3" name="commentForm3" action="/system/sample/query" method="post">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">工具名称：</label>
                            <div class="col-sm-8">
                                <input id="cname"  name="sampleName" minlength="1" maxlength="100" type="text" class="form-control" required="" aria-required="true">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                            <button type="submit"  class="btn btn-primary" >保存</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>-->
</div>
<!-- 全局js -->
<script src="/static/js/jquery.min.js?v=2.1.4"></script>
<script src="/static/js/bootstrap.min.js?v=3.3.6"></script>
<!-- 自定义js -->
<script src="/static/js/content.js?v=1.0.0"></script>
<!-- Sweet alert -->
<script src="/static/js/plugins/sweetalert/sweetalert.min.js"></script>
<!-- Bootstrap table -->
<script src="/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<!-- jQuery Validation javascript-->
<script src="/static/js/plugins/validate/jquery.validate.min.js"></script>
<script src="/static/js/plugins/validate/messages_zh.min.js"></script>
<script src="/static/js/plugins/validate/form-validate-demo.js"></script>
<!-- jquery.form.js-->
<script src="/static/js/jquery.form.js"></script>
<!-- util JS-->
<script src="/static/js/util/dateFtt.js"></script>
<!--user.js-->
<script src="/static/js/ctfx/user.js"></script>
</body>
</html>