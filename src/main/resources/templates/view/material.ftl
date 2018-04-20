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
                                <button id="batchAddSoft" type="button" class="btn btn-danger" data-toggle="modal"
                                >批量上传
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
                            <label class="col-sm-2 control-label">项目名称：</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="name1" name="name"
                                       required=""
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
                                       required=""
                                       aria-required="true" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">选择文件：</label>
                            <div class="input-group">
                                <input id="ctFile" type="file" class="form-control" name="ctFile">
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
<#--批量新增软件窗口-->
    <div class="modal inmodal fade" id="batchModal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">上传软件</h4>
                    <div style="margin-left: 70%;">
                	 <span style="display: inline-block;width:  15%;vertical-align: bottom;margin: 0px 10px;">
                            	<button type="button" onclick="add()" class="btn btn-w-m btn-primary">增加</button>
                      </span>

                    </div>
                </div>

                <div class="modal-body">
                    <div class="head" style="100%">
                        <span style="width: 20%; display: inline-block; text-align: center;">名称</span>
                        <span style="width: 20%; display: inline-block; text-align: center;">版本</span>
                        <span style="width: 10%; display: inline-block; text-align: center;">类型</span>
                        <span style="width: 25%; display: inline-block; text-align: center;">软件</span>
                        <span style="width: 20%; display: inline-block; text-align: right;">操作</span>
                    </div>
                    <div id="allyb">
                        <form class="form-horizontal m-t" id="batchForm" enctype="multipart/form-data"
                              action="/software/location/uploads" method="post">
                            <div class="form-group" id="up1">
                        	<span style="display: inline-block;width: 20%;">
	                            <div class="input-group">
	                                <input type="text" class="form-control" id="sampleName1" name="sampleName"
                                           required=""
                                           aria-required="false">
	                                <div class="input-group-btn">
	                                    <button id="btn1" type="button" class="btn btn-white dropdown-toggle"
                                                data-toggle="dropdown" style="left: -33px!important;">
	                                        <span class="caret"></span>
	                                    </button>
	                                    <ul id="ul"
                                            class="dropdown-menu dropdown-menu-right" role="menu">
	                                    </ul>
	                                </div>
	                            </div>
                        	</span>
                            <span style="display: inline-block;width:  20%;vertical-align: bottom;">
                            	 <select id="sel1"
                                         style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                    <option value="0">请选择版本</option>
                                </select>
                            	<input id="ver1" type="text"
                                       style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                <input id="addVer1" type="hidden" name="version">
                            </span>
                                <span style="display: inline-block;width:  10%;vertical-align: bottom;margin: 0px 10px;">
                            	<select name="type" id="se"
                                        style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                    <option value="1">样本</option>
                                    <option value="2">图标</option>
                                </select>
                            </span>
                                <span style="display: inline-block;width:  25%;vertical-align: bottom;margin: 0px 10px;">
                            	<input id="soft1" type="file" name="ctFile" style="display: inline-block;width: 78%;">
                            </span>

                                <span style="display: inline-block;width:  15%;vertical-align: bottom;margin: 0px 10px;">
                            	<button type="button" onclick="del('up1')" class="btn btn-w-m btn-primary">删除</button>
                      		</span>
                            </div>
                            <div class="form-group" id="up2">
                        	<span style="display: inline-block;width:  20%;">

	                            <div class="input-group">
	                                <input type="text" class="form-control" id="sampleName2" name="sampleName"
                                           required=""
                                           aria-required="true">
	                                <div class="input-group-btn">
	                                    <button id="btn2" type="button" class="btn btn-white dropdown-toggle"
                                                data-toggle="dropdown" style="left: -33px!important;">
	                                        <span class="caret"></span>
	                                    </button>
	                                    <ul id="ul"
                                            class="dropdown-menu dropdown-menu-right" role="menu">
	                                    </ul>
	                                </div>
	                            </div>
                        	</span>
                                <span style="display: inline-block;width:  20%;vertical-align: bottom;">

                            	 <select id="sel2"
                                         style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                    <option value="0">请选择版本</option>
                                </select>
                            	<input id="ver2" type="text"
                                       style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                <input id="addVer2" type="hidden" name="version">
                            </span>
                                <span style="display: inline-block;width:  10%;vertical-align: bottom;margin: 0px 10px;margin-top: auto;">

                            	<select name="type" id="se"
                                        style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                    <option value="1">样本</option>
                                    <option value="2">图标</option>
                                </select>
                            </span>
                                <span style="display: inline-block;width:  25%;vertical-align: bottom;margin: 0px 10px;margin-top: 0 auto;">
                            	<input id="soft2" type="file" name="ctFile" style="display: inline-block;width: 100%;">
                            </span>
                                <span style="display: inline-block;width:  15%;vertical-align: bottom;margin: 0px 10px;">
                            	<button type="button" onclick="del('up2')" class="btn btn-w-m btn-primary">删除</button>
                            </span>

                            </div>
                            <div class="form-group" id="up3">
                        	<span style="display: inline-block;width:  20%;">

	                            <div class="input-group">
	                                <input type="text" class="form-control" id="sampleName3" name="sampleName"
                                           required=""
                                           aria-required="true">
	                                <div class="input-group-btn">
	                                    <button id="btn3" type="button" class="btn btn-white dropdown-toggle"
                                                data-toggle="dropdown" style="left: -33px!important;">
	                                        <span class="caret"></span>
	                                    </button>
	                                    <ul id="ul"
                                            class="dropdown-menu dropdown-menu-right" role="menu">
	                                    </ul>
	                                </div>
	                            </div>
                        	</span>
                                <span style="display: inline-block;width:  20%;vertical-align: bottom;">

                                <select id="sel3"
                                        style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                    <option value="0">请选择版本</option>
                                </select>
                            	<input id="ver3" type="text"
                                       style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                <input id="addVer3" type="hidden" name="version">
                            </span>
                                <span style="display: inline-block;width:  10%;vertical-align: bottom;margin: 0px 10px;">

                            	<select name="type" id="se"
                                        style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                    <option value="1">样本</option>
                                    <option value="2">图标</option>
                                </select>
                            </span>
                                <span style="display: inline-block;width:  25%;vertical-align: bottom;margin: 0px 10px;">
                            	<input id="soft3" type="file" name="ctFile" style="display: inline-block;width: 100%;">
                            </span>
                                <span style="display: inline-block;width:  15%;vertical-align: bottom;margin: 0px 10px;">
                            	<button type="button" onclick="del('up3')" class="btn btn-w-m btn-primary">删除</button>
                      		</span>
                            </div>
                            <div class="form-group" id="up4">
                        	<span style="display: inline-block;width:  20%;">

	                            <div class="input-group">
	                                <input type="text" class="form-control" id="sampleName4" name="sampleName"
                                           required=""
                                           aria-required="true">
	                                <div class="input-group-btn">
	                                    <button id="btn4" type="button" class="btn btn-white dropdown-toggle"
                                                data-toggle="dropdown" style="left: -33px!important;">
	                                        <span class="caret"></span>
	                                    </button>
	                                    <ul id="ul"
                                            class="dropdown-menu dropdown-menu-right" role="menu">
	                                    </ul>
	                                </div>
	                            </div>
                        	</span>
                                <span style="display: inline-block;width:  20%;vertical-align: bottom;">

                                <select id="sel4"
                                        style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                    <option value="0">请选择版本</option>
                                </select>
                            	<input id="ver4" type="text"
                                       style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                <input id="addVer4" type="hidden" name="version">
                            </span>
                                <span style="display: inline-block;width:  10%;vertical-align: bottom;margin: 0px 10px;">

                            	<select name="type" id="se"
                                        style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                    <option value="1">样本</option>
                                    <option value="2">图标</option>
                                </select>
                            </span>
                                <span style="display: inline-block;width:  25%;vertical-align: bottom;margin: 0px 10px;">
                            	<input id="soft4" type="file" name="ctFile" style="display: inline-block;width: 100%;">
                            </span>
                                <span style="display: inline-block;width:  15%;vertical-align: bottom;margin: 0px 10px;">
                            	<button type="button" onclick="del('up4')" class="btn btn-w-m btn-primary">删除</button>
                      		</span>
                            </div>
                            <div class="form-group" id="up5">
                        	<span style="display: inline-block;width:20%;">
	                            <div class="input-group">
	                                <input type="text" class="form-control" id="sampleName5" name="sampleName"
                                           required=""
                                           aria-required="true">
	                                <div class="input-group-btn">
	                                    <button id="btn5" type="button" class="btn btn-white dropdown-toggle"
                                                data-toggle="dropdown" style="left: -33px!important;">
	                                        <span class="caret"></span>
	                                    </button>
	                                    <ul id="ul"
                                            class="dropdown-menu dropdown-menu-right" role="menu">
	                                    </ul>
	                                </div>
	                            </div>
                        	</span>
                                <span style="display: inline-block;width:  20%;vertical-align: bottom;">

                                <select id="sel5" class="selectpicker"
                                        style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                    <option value="0">请选择版本</option>
                                </select>
                            	<input id="ver5" type="text"
                                       style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                <input id="addVer5" type="hidden" name="version">
                            </span>
                                <span style="display: inline-block;width: 10%;vertical-align: bottom;margin: 0px 10px;">

                            	<select id="se" name="type"
                                        style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                    <option value="1">样本</option>
                                    <option value="2">图标</option>
                                </select>
                            </span>
                                <span style="display: inline-block;width:  25%;vertical-align: bottom;margin: 0px 10px;">
                            	<input id="soft5" type="file" name="ctFile" style="display: inline-block;width: 100%;">
                            </span>
                                <span style="display: inline-block;width:  15%;vertical-align: bottom;margin: 0px 10px;">
                            	<button type="button" onclick="del('up5')" class="btn btn-w-m btn-primary">删除</button>
                      		</span>
                            </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
                        <button id="submit" class="btn btn-primary" onclick="submits()" type="button">上传</button>
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
<script src="/static/js/ctfx/uploads.js"></script>
<script type="text/html" id="addhtml">
    <div class="form-group">
                        	<span style="display: inline-block;width:  20%;">
	                            <div class="input-group">
	                                <input type="text" class="form-control" id="sampleNameId" name="sampleName6"
                                           required=""
                                           aria-required="true">
	                                <div class="input-group-btn">
	                                    <button id="btn5" type="button" class="btn btn-white dropdown-toggle"
                                                data-toggle="dropdown" style="left: -33px!important;">
	                                        <span class="caret"></span>
	                                    </button>
	                                    <ul id="ul"
                                            class="dropdown-menu dropdown-menu-right" role="menu">
	                                    </ul>
	                                </div>
	                            </div>
                        	</span>
        <span style="display: inline-block;width:  20%;vertical-align: bottom;">

                                <select id="sel5" class="selectpicker"
                                        style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                    <option value="0">请选择版本</option>
                                </select>
                            	<input id="ver5" type="text"
                                       style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                <input id="addVer5" type="hidden" name="version">
                            </span>
        <span style="display: inline-block;width: 10%;vertical-align: bottom;margin: 0px 10px;">

                            	<select id="sel3"
                                        style="width: 100%;height: 34px;z-index: 2px;border: 1px solid #e5e6e7;">
                                    <option value="1">样本</option>
                                    <option value="2">图标</option>
                                </select>
                            </span>
        <span style="display: inline-block;width:  25%;vertical-align: bottom;margin: 0px 10px;">
                            	<input id="soft5" type="file" name="ctFile" style="display: inline-block;width: 100%;">
                            </span>
    </div>

</script>
<script type="application/javascript">
    /**
     * 页面加载完成之后隐藏掉DIV
     */
    window.onload = function () {
//	$("#upone").attr("style","display:none;");
        $("#up2").attr("style", "display:none;");
        $("#up3").attr("style", "display:none;");
        $("#up4").attr("style", "display:none;");
        $("#up5").attr("style", "display:none;");

    }

    function add() {
//	$("#up4").attr("style","display:block;");
        var up3 = $("#up3").css("display");
        var up4 = $("#up4").css("display");
        var up5 = $("#up5").css("display");
        var up2 = $("#up2").css("display");
        if (up2 == 'none') {
            $("#up2").attr("style", "display:block;");
        } else {
            if (up3 == "none") {
                $("#up3").attr("style", "display:block;");
            } else {
                if (up4 == "none") {
                    $("#up4").attr("style", "display:block;");
                } else {
                    if (up5 == "none") {
                        $("#up5").attr("style", "display:block;");
                    } else {
                    }
                }
            }

        }

    }

    function del(id) {
        $("#" + id).attr("style", "display:none;");
    }

    function submits() {
        var up3 = $("#up3").css("display");
        var up4 = $("#up4").css("display");
        var up5 = $("#up5").css("display");
        var up2 = $("#up2").css("display");
        var up1 = $("#up1").css("display");
        if (up1 == 'none') {
            $("#up1").remove();
        }
        if (up2 == 'none') {
            $("#up2").remove();
        }
        if (up3 == 'none') {
            $("#up3").remove();
        }
        if (up4 == 'none') {
            $("#up4").remove();
        }
        if (up5 == 'none') {
            $("#up5").remove();
        }

        $("#batchForm").submit()
    }
</script>

</body>
</html>