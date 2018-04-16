/**
 * 工具跟踪处理js
 */
$(function () {
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

    //初始化表格数据
    initTable(null);
    $("#exampleTableEvents").bootstrapTable('hideColumn', 'id');
    //重置按钮
    $("#reset").click(function () {
        //进行初始化表格数据
        $("#searchName").val("");
        initTable(null);
        $("#exampleTableEvents").bootstrapTable('hideColumn', 'id');
    });
    //查询按钮
    $("#search").click(function () {
        //判断是否已经输入值
        var sampleName = $("#searchName").val();
        if (sampleName !== null && sampleName !== "") {
            //进行初始化表格数据
            initTable(sampleName);
            $("#exampleTableEvents").bootstrapTable('hideColumn', 'id');
        } else {
            swal({
                title: "警告",
                text: "请输入名称之后再次点击搜索按钮",
                type: "warning",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            })
        }
    });
    // 提交批量删除表单
    $("#batchDelForm").ajaxForm(function (data) {
        var msg = data.msg;
        if (data.result) {
            swal({
                title: "恭喜",
                text: msg,
                type: "warning",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            });
            //关闭显示窗口
            $("#batchDelModal").modal("hide");
            //重新加载表格数据
            $('#exampleTableEvents').bootstrapTable("refresh", {
                url: "/tool/trace/limit"
            });
        }
        if (!data.result) {
            swal({
                title: "警告",
                text: msg,
                type: "warning",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            });
        }
    });

    // 提交删除表单
    $("#delForm").ajaxForm(function (data) {
        var msg = data.msg;
        if (data.result) {
            swal({
                title: "恭喜",
                text: msg,
                type: "warning",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            });
            //关闭显示窗口
            $("#delModal").modal("hide");
            //重新加载表格数据
            $('#exampleTableEvents').bootstrapTable("refresh", {
                url: "/tool/trace/limit"
            });
        }
        if (!data.result) {
            swal({
                title: "警告",
                text: msg,
                type: "warning",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            });
        }
    });

    //点击删除按钮事件
    $("#delTrace").click(function () {
        //先判断有没有选择一条数据
        var ta = $("#exampleTableEvents").bootstrapTable('getAllSelections');
        if (ta.length > 1) {
            var text = "";
            var checkId = "";
            //获取所有的url
            $.each(ta, function (i, row) {
                //赋值
                text += row.url + "\n";
                checkId += row.id + ",";
            });
            checkId = checkId.substring(0, checkId.lastIndexOf(","));
            $("#batchUrl").val(text);
            alert(checkId);
            $("#batchId").val(checkId);

            $("#batchDelModal").modal("show");
        }
        if (ta.length === 0) {
            swal({
                title: "警告",
                text: "请选择一条数据",
                type: "warning",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            });
        }
        if (ta.length == 1) {
            $.each(ta, function (i, row) {
                //赋值
                $("#delTime").val(row.createTime);
                $("#delSampleName").val(row.number);
                $("#delUrl").val(row.url);
            });
            $("#delModal").modal("show");
        }
    });
    //提交表单
    $('#commentForm').ajaxForm(function (data) {
        if (data.result) {
            $('#addTrace').modal('hide');
            $('#sampleName').val("");
            $('#url').val("");
            swal({
                title: "成功",
                text: "添加追踪信息成功",
                type: "success",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            });
            $('#exampleTableEvents').bootstrapTable("refresh", {
                url: "/tool/trace/limit"
            });
        }
        if (!data.result) {
            swal({
                title: "添加追踪信息失败",
                text: data.msg,
                type: "warning",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            });
        }
    });

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
            if (!json || json.length == 0) {
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
    //变化的时候 将number查询出来
    $("#sampleName").blur(function () {
        var sampleName = $("#sampleName").val();
        $.ajax({
            type: "post",
            url: "/tool/trace/queryNumber",
            data: {sampleName: sampleName},
            dataType: "json",
            success: function (data) {
                $("#number").val(data.number)
            }
        });
    });

    //分页处理函数
    function initTable(sampleName) {
        $("#exampleTableEvents").bootstrapTable("destroy");
        $("#exampleTableEvents").bootstrapTable({
            method: "get",  //使用get请求到服务器获取数据
            url: "/tool/trace/limit",
            editable: true,//开启编辑模式
            //search: true,//搜索框
            pagination: true,
            showRefresh: true,
            showToggle: true,
            //showColumns: true,
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
                if (sampleName !== null && sampleName !== "") {
                    param = {
                        pageNumber: params.pageNumber,
                        pageSize: params.pageSize,
                        sampleName: sampleName
                    }
                } else {
                    param = {
                        pageNumber: params.pageNumber,
                        pageSize: params.pageSize
                    }
                }
                return param;
            },
            responseHandler: function (e) {
                if (e.data && e.data.rows && e.data.rows.length > 0) {
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