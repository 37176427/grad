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
    initTable(null);
    //重置按钮
    $("#reset").click(function () {
        initTable(null);
        $("#searchProjectName").val("");
    });
    //搜索按钮
    $("#search").click(function () {
        var projectName = $("#searchProjectName").val();
        if (projectName.length === 0) {
            swal({
                title: "警告",
                text: "请输入名称进行查询",
                type: "warning",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            });
        }else {
            initTable(projectName);
        }
    });


    //添加标签的界面优化
    $("input#name1.form-control").attr("style", "width:291px");
    $("button.btn.btn-white.dropdown-toggle").attr("style", "left:-400px");

    //提交添加表单
    $('#commentForm').ajaxForm(function (data) {
        var msg = data.msg;
        if (data.result) {
            $('#uploadMaterial').modal('hide');
            $('#name').val("");
            $('#createUser').val("");
            $('#ctFile').val("");
            swal({
                title: "成功",
                text: "资料上传成功",
                type: "success",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            });
            //重新加载表格数据
            $('#exampleTableEvents').bootstrapTable("refresh", {
                url: "/material/initPaging"
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
    var name = $("#name").val();
    $("#name").bsSuggest({
        indexId: 1, //每组数据的第几个数据，作为input输入框的 data-id，设为 -1 且 idField 为空则不设置此值
        indexKey: 0, //data.value 的第几个数据，作为input输入框的内容
        allowNoKeyword: false, //是否允许无关键字时请求数据
        multiWord: false, //以分隔符号分割的多关键字支持
        separator: ",", //多关键字支持时的分隔符，默认为空格
        getDataMethod: "url", //获取数据的方式，总是从 URL 获取
        effectiveFields: ["name"],
        effectiveFieldsAlias: {name: "项目名称"},
        showHeader: true,
        url: '/material/query?name=' + name,
        processData: function (json) {
            // url 获取数据时，对数据的处理，作为 getData 的回调函数
            var i, len, data = {value: []};
            if (!json || json.length === 0) {
                return false;
            }
            len = json.length;
            for (i = 0; i < len; i++) {
                data.value.push({
                    "name": json[i]
                });
            }
            return data;
        }
    });
    //完成输入后 查询创建人
    $("#name").blur(function () {
        var name = $("#name").val();
        $.ajax({
            type: "post",
            method: "get",
            url: "/material/queryByName",
            data: {name: name},
            dataType: "json",
            success: function (data) {
                $('#createUser').val(data.data.data);
            }
        });
    });

    //初始化表格数据
    function initTable(projectName) {
        //初始化表格数据
        $("#exampleTableEvents").bootstrapTable("destroy");
        $("#exampleTableEvents").bootstrapTable({
            method: "get",  //使用get请求到服务器获取数据
            url: "/material/initPaging",
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
                if (projectName !== null && projectName.length !== 0) {
                    param = {
                        pageNumber: params.pageNumber,
                        pageSize: params.pageSize,
                        projectName: projectName
                    };
                } else {
                    param = {
                        pageNumber: params.pageNumber,
                        pageSize: params.pageSize,
                    };
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
    $("#addMaterial").click(function () {
        var checked = $("#exampleTableEvents").bootstrapTable('getAllSelections');
        if(checked.length != 1){
            swal({
                title: "警告",
                text: "请选择一条数据",
                type: "warning",
                confirmButtonColor: "#dd1320",
                closeOnConfirm: true
            }, function () {
            });
        } else {
            //获取选中行的值
            $.each(checked, function (i, n) {
                $("#name1").val(n.name);
                $("#createUser1").val(n.createUser);
            });
            //弹出页面
            $("#uploadMaterial").modal('show');
        }
    });
});