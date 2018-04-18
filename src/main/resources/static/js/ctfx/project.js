$(function () {
    //提交表单
    $('#commentForm2').ajaxForm(function (data) {
        var msg = data.msg;
        if (data.result) {
            $('#myModal5').modal('hide');
            swal({
                title: "成功",
                text: msg,
                type: "success",
                confirmButtonColor: "#24dd27",
                closeOnConfirm: true
            }, function () {
            });
            //自动刷新
            /*$('#exampleTableEvents').bootstrapTable("refresh",{
                url: "/system/sample/findAll"});*/
            window.location.reload();
        }
        if (!data.result) {
            swal({
                title: "失败",
                text: msg,
                type: "warning",
                confirmButtonColor: "#dd1320",
                closeOnConfirm: true
            }, function () {
            });
        }
    });
    //进行初始化表格数据
    initTable(null);
    //重置按钮
    $("#reset").click(function () {
        var userName = $("#searchUserName").val();
        if (userName !== null && userName !== "") {
            initTable(null);
        }
        $("#userName").val("");
    });
    //搜索按钮
    $("#search").click(function () {
        var userName = $("#searchUserName").val();
        if (userName.length === 0) {
            swal({
                title: "警告",
                text: "请输入名称进行查询",
                type: "warning",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            });
        }else {
            initTable(userName);
        }
    });
    //提交表单
    $('#commentForm').ajaxForm(function (data) {
        var msg = data.msg;
        if (data.result) {
            $('#myModal6').modal('hide');
            swal({
                title: "成功",
                text: msg,
                type: "success",
                confirmButtonColor: "#24dd27",
                closeOnConfirm: true
            }, function () {
            });
            //自动刷新
            /*$('#exampleTableEvents').bootstrapTable("refresh",{
                url: "/system/user/findAll"});*/
            window.location.reload();
        }
        if (!data.result) {
            swal({
                title: "失败",
                text: msg,
                type: "warning",
                confirmButtonColor: "#dd1320",
                closeOnConfirm: true
            }, function () {
            });
        }
    });
    $("#edit").click(function () {
        //获取选择个数
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
        }else {
            //获取选中行的值
            $.each(checked,function (i,n) {
                $("#number1").val(n.number);
                $("#name1").val(n.name);
                $("#manager1").val(n.manager);
                $("#member1").val(n.member);
                $("#nature1").val(n.nature);
                $("#desc1").val(n.desc);
                $("#awards1").val(n.awards);
                $("#createUser1").val(n.createUser);
                $("#status1").val(n.status);
            });
            //弹出页面
            $("#myModal6").modal('show');
        }
    });

    //点击删除按钮事件
    $("#delUser").click(function () {
        //先判断有没有选择一条数据
        var ta = $("#exampleTableEvents").bootstrapTable('getAllSelections');
        if (ta.length > 1) {
            var text = "";
            var checkId = "";
            //获取所有的url
            $.each(ta, function (i, row) {
                //赋值
                text += row.realName + "\n";
                checkId += row.id + ",";
            });
            checkId = checkId.substring(0, checkId.lastIndexOf(","));
            $("#batchRealName").val(text);
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
                $("#delRealName").val(row.realName);
                $("#delName").val(row.name);
            });
            $("#delModal").modal("show");
        }
    });
    // 提交批量删除表单
    $("#batchDelForm").ajaxForm(function (data) {
        var msg = data.msg;
        if (data.result) {
            swal({
                title: "成功",
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
                url: "/system/user/initPaging"
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
                title: "成功",
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
                url: "/system/user/initPaging"
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

    function initTable(number) {
        $("#exampleTableEvents").bootstrapTable("destroy");
        $("#exampleTableEvents").bootstrapTable({
            method: "get",  //使用get请求到服务器获取数据
            url: "/project/pro/initPaging",
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
                if (number !== null && number !== "") {
                    param = {
                        pageNumber: params.pageNumber,
                        pageSize: params.pageSize,
                        number: number
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
})

