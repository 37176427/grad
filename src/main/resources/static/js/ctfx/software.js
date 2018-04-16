/**
 * 软件位置处理js
 * @param a
 * @returns {boolean}
 */
//下载函数处理
function js_download(a) {
    var url = a.name;
    $.ajax({
        type: "get",
        url: "/software/location/checkFile",
        data: {url: url},
        dataType: "json",
        success: function (data) {
            if (data.result) {
                window.location.href = "/software/location/download?url=" + url;
            }
            if (!data.result) {
                swal({
                    title: "警告",
                    text: "您要下载的软件不存在",
                    type: "warning",
                    confirmButtonColor: "#DD6B55",
                    closeOnConfirm: true
                }, function () {
                });
            }
        }
    });
    return false;
}

//时间格式化处理
function dateFtt(fmt, date) {
    date = new Date(Date.parse(date));
    var o = {
        "M+": date.getMonth() + 1,                 //月份
        "d+": date.getDate(),                    //日
        "h+": date.getHours(),                   //小时
        "m+": date.getMinutes(),                 //分
        "s+": date.getSeconds(),                 //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

$(function () {
    //进行初始化表格数据
    initTable(null, null);
    //重置按钮
    $("#reset").click(function () {
        initTable(null, null);
        $("#searchName").val("");
        $("#searchVersion").val("");
    });

    $("#searchVersion").focus(function () {
        var sampleName = $("#searchName").val();
        if (sampleName.length === 0 || sampleName === null) {
            swal({
                title: "警告",
                text: "请先输入软件名称，再输入软件版本",
                type: "warning",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            });
        }
    });
    //搜索按钮
    $("#search").click(function () {
        var sampleName = $("#searchName").val();
        var version = $("#searchVersion").val();
        if (sampleName.length === 0) {
            swal({
                title: "警告",
                text: "请输入名称进行查询",
                type: "warning",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            });
        }
        if (sampleName.length > 0 && sampleName !== null) {
            if (version.length > 0 && version !== null) {
                initTable(sampleName, version);
            } else {
                initTable(sampleName, null);
            }
        }
    });


    //添加标签的界面优化
    $("input#sampleName.form-control").attr("style", "width:291px");
    $("button.btn.btn-white.dropdown-toggle").attr("style", "left:-400px");

    //添加操作
    $("#addSoft").click(function () {
        $("#uploadSoft").modal('show');
        $("#adVersion").hide();
    });
    //提交添加表单
    $('#commentForm').ajaxForm(function (data) {
        var msg = data.msg;
        if (data.result) {
            $('#uploadSoft').modal('hide');
            $('#sampleName').val("");
            $('#version').val("");
            $('#ctFile').val("");
            swal({
                title: "成功",
                text: "工具上传成功",
                type: "success",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            });
            //重新加载表格数据
            $('#exampleTableEvents').bootstrapTable("refresh", {
                url: "/software/location/initPaging"
            });
        }
        if (!data.result) {
            swal({
                title: "",
                text: msg,
                type: "warning",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            });
        }
    });
    //输入建议函数
    var sampleName = $("#sampleName").val();
    $("#sampleName").bsSuggest({
        indexId: 1, //每组数据的第几个数据，作为input输入框的 data-id，设为 -1 且 idField 为空则不设置此值
        indexKey: 0, //data.value 的第几个数据，作为input输入框的内容
        allowNoKeyword: false, //是否允许无关键字时请求数据
        multiWord: false, //以分隔符号分割的多关键字支持
        separator: ",", //多关键字支持时的分隔符，默认为空格
        getDataMethod: "url", //获取数据的方式，总是从 URL 获取
        effectiveFields: ["sampleName"],
        effectiveFieldsAlias: {sampleName: "工具名称"},
        showHeader: true,
        url: '/software/location/query?sampleName=' + sampleName,
        processData: function (json) {
            // url 获取数据时，对数据的处理，作为 getData 的回调函数
            var i, len, data = {value: []};
            if (!json || json.length === 0) {
                return false;
            }
            len = json.length;
            for (i = 0; i < len; i++) {
                data.value.push({
                    "sampleName": json[i]
                });
            }
            return data;
        }
    });
    //完成输入后 查询版本信息
    $("#sampleName").blur(function () {
        var sampleName = $("#sampleName").val();
        $.ajax({
            type: "post",
            url: "/software/location/queryVersion",
            data: {sampleName: sampleName},
            dataType: "json",
            success: function (data) {
                $('#version').empty();
                var option = null;
                if (data.result) {
                    $("#editable-select").empty();
                    var json = data.data.versions;
                    if (json.length > 0) {
                        $.each(json, function (i, tool) {
                            option += "<option value='" + tool.version + "'>" + tool.version + "</option>"
                        });
                        $("#adVersion").hide();
                        $("#editable-select").show();
                    }
                    if (json.length === 0) {
                        $("#editable-select").hide();
                        $("#adVersion").show();
                    }
                    $("#editable-select").html(option);
                }
            }
        });
    });

    //初始化表格数据
    function initTable(toolName, version) {
        //初始化表格数据
        $("#exampleTableEvents").bootstrapTable("destroy");
        $("#exampleTableEvents").bootstrapTable({
            method: "get",  //使用get请求到服务器获取数据
            url: "/software/location/initPaging",
            editable: true,//开启编辑模式
            //search: true,
            pagination: true,
            showRefresh: true,
            showToggle: true,
            showColumns: true,
            iconSize: 'outline',
            pageSize: 10,  //每页显示的记录数
            pageNumber: 1, //当前第几页
            pageList: [10, 15, 20, 30, 40],  //记录数可选列表
            sidePagination: "server", //表示服务端请求
            queryParamsType: "undefined",
            toolbar: '#exampleTableEventsToolbar',
            icons: {
                refresh: 'glyphicon-repeat',
                toggle: 'glyphicon-list-alt',
                columns: 'glyphicon-list'
            },
            queryParams: function queryParams(params) {   //设置查询参数
                var param = {};
                if (toolName !== null && toolName.length !== 0) {
                    if (version !== null && version.length !== 0) {
                        param = {
                            pageNumber: params.pageNumber,
                            pageSize: params.pageSize,
                            sampleName: toolName,
                            version: version
                        };
                    } else {
                        param = {
                            pageNumber: params.pageNumber,
                            pageSize: params.pageSize,
                            sampleName: toolName
                        };
                    }
                }
                else {
                    param = {
                        pageNumber: params.pageNumber,
                        pageSize: params.pageSize
                    }
                }
                return param;
            },
            responseHandler: function (e) {
                if (e.data && e.data.rows && e.data.rows.length > 0) {
                    $('#exampleTableEvents').bootstrapTable('hideColumn', 'id');
                    var rows = e.data.rows;
                    for (var i = 0; i < rows.length; i++) {
                        var createTime = rows[i].createTime;
                        rows[i].createTime = dateFtt("yyyy-MM-dd hh:mm:ss", createTime);
                    }
                    return {"rows": e.data.rows, "total": e.data.total};
                }
                else {
                    return {"rows": [], "total": 0};
                }
            },
            onLoadSuccess: function () {  //加载成功时执行
            },
            onLoadError: function () {  //加载失败时执行
            }
        });
    }
});