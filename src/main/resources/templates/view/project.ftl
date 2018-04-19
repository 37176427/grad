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

    <title>项目信息</title>
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
                        <h4 class="example-title">项目信息</h4>
                        <div class="example">
                            <div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
                                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal5">项目申报</button>
                                <button type="button" id ="edit" class="btn btn-warning" data-toggle="modal" >项目修改</button>
                                <button type="button" id="delProject"  class="btn btn-danger" data-toggle="modal">删除项目</button>
                                <button type="button" id="myProject" class="btn btn-primary" data-toggle="modal">我创建的</button>
                                <#if Session.user?exists>
                                <input type="hidden" id="nowUserName" class="nowUserName" value="${Session.user.realName}">
                                </#if>
                                <label style="height: 50px;margin-left: 10px;">名称：<input id="searchProjectName" type="text" style="height: 34px;" placeholder="输入项目名称"></label>
                                <button id="search" style="left: 350px;overflow-x:visible;overflow-y:visible;" type="button" class="btn btn-primary">查询</button>
                                <button id="reset" type="button" style="left:370px;overflow-x:visible;overflow-y:visible;" class="btn btn-default">重置</button>
                            </div>
                            <table id="exampleTableEvents" data-mobile-responsive="true">
                                <thead>
                                <tr>
                                    <th data-field="state" data-checkbox="true"></th>
                                    <th data-field="number">项目编号</th>
                                    <th data-field="name">项目名称</th>
                                    <th data-field="manager">负责人</th>
                                    <th data-field="member">项目成员</th>
                                    <th data-field="nature">项目类型</th>
                                    <th data-field="desc">具体描述</th>
                                    <th data-field="awards">获奖情况</th>
                                    <th data-field="createTime">申请时间</th>
                                    <th data-field="status">审核情况</th>
                                    <th data-field="createUser">创建人</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                        <label style="height: 50px;left:390px;overflow-x:visible;overflow-y:visible">审核情况描述：0:未审核***1:审核通过***2:审核不通过</label>
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
                    <h4 class="modal-title">项目申报</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal m-t" id="commentForm2" name="commentForm2" action="/project/pro/add" method="post">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">项目编号：</label>
                            <div class="col-sm-8">
                                <input id="number"  name="number" min="1" minlength="1" maxlength="20" type="number" class="form-control" required="" aria-required="true" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">项目名称</label>
                            <div class="col-sm-8">
                                <input id="name" type="text" minlength="1" maxlength="50" class="form-control" name="name" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">负责人：</label>
                            <div class="col-sm-8">
                                <input id="manager" type="text" minlength="1" maxlength="12" class="form-control" name="manager" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">项目成员：</label>
                            <div class="col-sm-8">
                                <input id="member" type="text" minlength="1"  class="form-control" name="member">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">项目类型：</label>
                            <div class="col-sm-8">
                                <input id="nature" type="text" minlength="1" class="form-control" name="nature">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">具体描述：</label>
                            <div class="col-sm-8">
                                <textarea id="desc" type="text" rows="5" class="form-control" name="desc"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">获奖情况：</label>
                            <div class="col-sm-8">
                                <input id="awards" type="text" minlength="1" class="form-control" name="awards">
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
                    <h4 class="modal-title">修改项目</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal m-t" id="commentForm" name="commentForm" action="/project/pro/edit" method="post">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">项目编号：</label>
                            <div class="col-sm-8">
                                <input id="number1"  name="number" minlength="1" maxlength="20" type="number" readonly="true" class="form-control"  required="" aria-required="true">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">项目名称：</label>
                            <div class="col-sm-8">
                                <input id="name1" type="text" maxlength="100" class="form-control" name="name" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">负责人</label>
                            <div class="col-sm-8">
                                <input id="manager1" type="text" maxlength="100" class="form-control" name="manager">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">项目成员</label>
                            <div class="col-sm-8">
                                <input id="member1" type="text" maxlength="100" class="form-control" name="member">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">项目性质</label>
                            <div class="col-sm-8">
                                <input id="nature1" type="text"  maxlength="100" class="form-control" name="nature">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">项目描述</label>
                            <div class="col-sm-8">
                                <textarea id="desc1" type="text" rows="5" class="form-control" name="desc"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">获奖情况</label>
                            <div class="col-sm-8">
                                <input id="awards1" type="text" maxlength="100" class="form-control" name="awards">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8">
                                <input id="createUser1" type="hidden" maxlength="100" class="form-control" name="createUser">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8">
                                <input id="status1" type="hidden" maxlength="100" class="form-control" name="status">
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
<#--删除modal-->
    <div class="modal inmodal fade" id="delModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">确定删除这个项目 ?</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal m-t" id="delForm"
                          action="/project/pro/del" method="post">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">项目名：</label>
                            <div class="input-group">
                                <input id="delName" type="url" class="form-control" name="name"
                                       required="" readonly="readonly"
                                       aria-required="true" style="width: 650px;">
                                <input id="delId" type="hidden" class="form-control" name="id"
                                       required="" readonly="readonly"
                                       aria-required="true">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                            <button id="submit" class="btn btn-primary" type="submit">确定</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="modal inmodal fade" id="batchDelModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">确定要删除这些项目吗 ?</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal m-t" id="batchDelForm"
                          action="/project/pro/batchDel" method="post">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">项目名：</label>
                            <div class="input-group">
                                <textarea id="batchName" type="text" class="form-control" name="name" rows="10"
                                          required="" readonly="readonly" style="width: 650px;"
                                ></textarea>
                                <input id="batchId" type="hidden" class="form-control" name="ids"
                                       required="" readonly="readonly"
                                       aria-required="true">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                            <button class="btn btn-primary" type="submit">确定</button>
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
<script src="/static/js/ctfx/project.js"></script>
</body>
</html>