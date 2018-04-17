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
    initTableForTool(null);
    //重置按钮
    $("#reset").click(function () {
        var userName = $("#searchUserName").val();
        if (userName !== null && userName !== "") {
            initTableForTool(null);
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
            initTableForTool(userName);
        }
    });
    //ajax验证工具是否存在
    $("#userNameAdd").blur(function(){
        var userName = $("#userNameAdd").val();
        if(userName != null && ""!=userName){
            $.ajax({
                type: "get",
                url: "/system/user/queryByName",
                data: {userName: userName},
                dataType: "json",
                success: function (data) {
                    if(!data.result){
                        $("#userNameAdd").val("");
                        swal({
                            title: "警告",
                            text: data.msg,
                            type: "warning",
                            confirmButtonColor: "#dd1320",
                            closeOnConfirm: true
                        }, function () {
                        });
                    }
                }
            });
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
                $("#userNameEdit").val(n.name);
                $("#realName1").val(n.realName);
                $("#password1").val(n.password);
                $("#permission1").val(n.permission);
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
})

