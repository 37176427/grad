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
                $("#nameEn").val(n.nameEn);
                $("#password1").val(n.password);
                $("#permission1").val(n.permission);
            });
            //弹出页面
            $("#myModal6").modal('show');
        }
    });
    $("#delete").click(function () {
        var checked = new Array();
        checked = document.getElementsByName("btSelectedItem");
        if(confirm("确认删除选中的用户吗?")){
            //执行删除操作
            for (var i=0; i<checked.length;i++){
                $.ajax({
                    type: "get",
                    url: "/system/user/delete",
                    data: {userName: checked[i]},
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
        }

    })
});