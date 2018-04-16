$(function () {
    chosenAddInit();
    //提交表单
    $('#commentForm').ajaxForm(function (data) {
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
    //ajax验证工具是否存在
    $("#sampleNameAdd").blur(function () {
        var sampleName = $("#sampleNameAdd").val();
        if (sampleName != null && sampleName != "") {
            $.ajax({
                type: "get",
                url: "/system/sample/queryByNameForVersion",
                data: {sampleName: sampleName},
                dataType: "json",
                success: function (data) {
                    if (!data.result) {
                        $("#sampleNameAdd").val("");
                        swal({
                            title: "警告",
                            text: "该工具不存在",
                            type: "warning",
                            confirmButtonColor: "#dd1320",
                            closeOnConfirm: true
                        }, function () {
                        });
                    } else {
                        $("#numberAdd").val(data.msg);
                    }
                }
            });
        }
    });
    //自动提示
    var sampleName = $("#sampleNameAdd").val();
    $("#sampleNameAdd").bsSuggest({
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
    //进行初始化表格数据
    initTableForVersion(null);
    //重置按钮
    $("#reset").click(function () {
        var sampleName = $("#searchSampleName").val();
        if (sampleName !== null && sampleName !== "") {
            initTableForVersion(null);
        }
        $("#searchSampleName").val("");
    });
    //搜索按钮
    $("#search").click(function () {
        var sampleName = $("#searchSampleName").val();
        if (sampleName.length === 0) {
            swal({
                title: "警告",
                text: "请输入名称进行查询",
                type: "warning",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            });
        }else {
            initTableForVersion(sampleName);
        }
    });


    //更新JS
    //提交表单
    $('#commentForm2').ajaxForm(function (data) {
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
        if (checked.length != 1) {
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
                $("#idEdit").val(n.id);
                $("#sampleNameEdit").val(n.number);
                $("#versionEdit").val(n.version);
                $("#developerEdit").val(n.developer);
                $("#descriptionEdit").val(n.description);
                addOptions(n.platform);
                //时间转格式
                $("#postTimeEdit").val(dateFtt("yyyy-MM-dd hh:mm:ss",n.postTime));
                chosenInit();
            });
            //ajax获取number
            var sampleName = $("#sampleNameEdit").val();
            if (sampleName != null && sampleName != "") {
                $.ajax({
                    type: "get",
                    url: "/system/sample/queryByNameForVersion",
                    data: {sampleName: sampleName},
                    dataType: "json",
                    success: function (data) {
                        if (!data.result) {
                            $("#sampleNameAdd").val("");
                            swal({
                                title: "警告",
                                text: "该工具不存在",
                                type: "warning",
                                confirmButtonColor: "#dd1320",
                                closeOnConfirm: true
                            }, function () {
                            });
                        } else {
                            $("#numberEdit").val(data.msg);
                        }
                    }
                });
            }
            //弹出页面
            $("#myModal6").modal('show');
        }
    });
});