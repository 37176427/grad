$(function () {
    //添加标签的界面优化
    //批量上传操作
    $("#batchAddSoft").click(function () {
        $("#batchModal").modal('show');
        for (var i = 1; i < 6; i++) {
            $("input#sampleName" + i + ".form-control").attr("style", "width:85%");
            $("#btn" + i).attr("style", "left:-33px");
            $("#ver" + i).hide();
        }
    });
    /*第一个软件*/
    var sampleName = $("#sampleName1").val();
    $("#sampleName1").bsSuggest({
        indexId: 1,
        indexKey: 0,
        getDataMethod: "url",
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
    /*第二个软件*/
    var sampleName2 = $("#sampleName2").val();
    $("#sampleName2").bsSuggest({
        indexId: 1,
        indexKey: 0,
        getDataMethod: "url",
        effectiveFields: ["sampleName"],
        effectiveFieldsAlias: {sampleName: "工具名称"},
        showHeader: true,
        url: '/software/location/query?sampleName=' + sampleName2,
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
    /*第三个软件*/
    var sampleName3 = $("#sampleName3").val();
    $("#sampleName3").bsSuggest({
        indexId: 1,
        indexKey: 0,
        getDataMethod: "url",
        effectiveFields: ["sampleName"],
        effectiveFieldsAlias: {sampleName: "工具名称"},
        showHeader: true,
        url: '/software/location/query?sampleName=' + sampleName3,
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
    /*第四个软件*/
    var sampleName4 = $("#sampleName4").val();
    $("#sampleName4").bsSuggest({
        indexId: 1,
        indexKey: 0,
        getDataMethod: "url",
        effectiveFields: ["sampleName"],
        effectiveFieldsAlias: {sampleName: "工具名称"},
        showHeader: true,
        url: '/software/location/query?sampleName=' + sampleName4,
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
    /*第五个软件*/
    var sampleName5 = $("#sampleName5").val();
    $("#sampleName5").bsSuggest({
        indexId: 1,
        indexKey: 0,
        getDataMethod: "url",
        effectiveFields: ["sampleName"],
        effectiveFieldsAlias: {sampleName: "工具名称"},
        showHeader: true,
        url: '/software/location/query?sampleName=' + sampleName5,
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

    /*输入完成后 初始化查询版本*/
    function initQuery(sampleNameId) {
        var sampleName = $("#" + sampleNameId).val();
        var number = sampleNameId.substring(sampleNameId.length - 1, sampleNameId.length);
        $.ajax({
            type: "post",
            url: "/software/location/queryVersion",
            data: {sampleName: sampleName},
            dataType: "json",
            success: function (data) {
                $("#ver" + number).empty();
                var option = "<option value='0'>请选择版本</option>";
                if (data.result) {
                    $("#sel" + number).empty();
                    var json = data.data.versions;
                    if (json.length > 0) {
                        $.each(json, function (i, tool) {
                            option += "<option value='" + tool.version + "'>" + tool.version + "</option>"
                        });
                        $("#ver" + number).hide();
                        $("#sel" + number).show();
                    }
                    if (json.length === 0) {
                        $("#sel" + number).hide();
                        $("#ver" + number).show();
                    }
                    $("#sel" + number).html(option);
                }
            }
        });
    }

    /*工具名称输入完成后-查询版本信息--开始*/
    $("#sampleName1").blur(function () {
        var sampleNameId = "sampleName1";
        initQuery(sampleNameId);
    });
    $("#sampleName2").blur(function () {
        var sampleNameId = "sampleName2";
        initQuery(sampleNameId);
    });
    $("#sampleName3").blur(function () {
        var sampleNameId = "sampleName3";
        initQuery(sampleNameId);
    });
    $("#sampleName4").blur(function () {
        var sampleNameId = "sampleName4";
        initQuery(sampleNameId);
    });
    $("#sampleName5").blur(function () {
        var sampleNameId = "sampleName5";
        initQuery(sampleNameId);
    });
    /*工具名称输入完成后-查询版本信息--结束*/

    /*输入或者选择完版本信息后 将版本信息复制给隐藏域--开始*/
    /*文本输入框*/
    $("#ver1").blur(function () {
        var verId = "#ver1";
        var addVerId = "#addVer1";
        initInputVersion(verId, addVerId);
    });
    $("#ver2").blur(function () {
        var verId = "#ver2";
        var addVerId = "#addVer2";
        initInputVersion(verId, addVerId);
    });
    $("#ver3").blur(function () {
        var verId = "#ver3";
        var addVerId = "#addVer3";
        initInputVersion(verId, addVerId);
    });
    $("#ver4").blur(function () {
        var verId = "#ver4";
        var addVerId = "#addVer4";
        initInputVersion(verId, addVerId);
    });
    $("#ver5").blur(function () {
        var verId = "#ver5";
        var addVerId = "#addVer5";
        initInputVersion(verId, addVerId);
    });
    /*下拉框*/
    $("#sel1").blur(function () {
        var selId = "#sel1";
        var addVerId = "#addVer1";
        initSelectVersion(selId, addVerId);
    });
    $("#sel2").blur(function () {
        var selId = "#sel2";
        var addVerId = "#addVer2";
        initSelectVersion(selId, addVerId);
    });
    $("#sel3").blur(function () {
        var selId = "#sel3";
        var addVerId = "#addVer3";
        initSelectVersion(selId, addVerId);
    });
    $("#sel4").blur(function () {
        var selId = "#sel4";
        var addVerId = "#addVer4";
        initSelectVersion(selId, addVerId);
    });
    $("#sel5").blur(function () {
        var selId = "#sel5";
        var addVerId = "#addVer5";
        initSelectVersion(selId, addVerId);
    });
    /*输入或者选择完版本信息后 将版本信息复制给隐藏域--结束*/

    /*下拉列表的版本信息*/
    function initSelectVersion(selId, addVerId) {
        var version = $(selId).val();
        $(addVerId).val(version);
    }

    //输入框的版本
    function initInputVersion(verId, adVerId) {
        var version = $(verId).val();
        $(adVerId).val(version);
    }

    // 提交前参数配置
    var options = {
        //resetForm: true // 成功提交后，重置所有的表单元素的值.
        //timeout: 3000
        beforeSubmit: verifyRequest, // 提交前
        success: showResponse,// 提交后,
        clearForm: true// 成功提交后，清除所有的表单元素的值.
    };
    $('#batchForm').ajaxForm(options);

    //校验输入的数据
    function verifyRequest(formData, jqForm, options) {
        var flag = true;
        //formData是数组对象,在这里，我们使用$.param()方法把他转化为字符串.
        //var queryString = $.param(formData);
        for (var i = 0; i < formData.length; i++) {
            if (i === 1 || i === 4 || i === 7 || i === 10 || i === 13) {
                var value = formData[i].value;
                if (value.length === 0 || value === null || value === '0') {
                    swal({
                        title: "警告",
                        text: "请输入或者选择版本信息",
                        type: "warning",
                        confirmButtonColor: "#DD6B55",
                        closeOnConfirm: true
                    }, function () {
                    });
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            for (var u = 0; u < formData.length; u++) {
                if (u === 2 || u === 5 || u === 8 || u === 11 || u === 14) {
                    console.log(formData[u].value);
                    var val = formData[u].value;
                     if (val.length === 0 || val === null) {
                         swal({
                             title: "警告",
                             text: "请选择软件后，再次点击上传",
                             type: "warning",
                             confirmButtonColor: "#DD6B55",
                             closeOnConfirm: true
                         }, function () {
                         });
                         flag = false;
                         break;
                     }
                }
            }
        }
        return flag;
    }

    // 提交后
    function showResponse(responseText, statusText) {
        console.log(statusText);
        var msg = responseText.msg = "";
        if (responseText.result) {
            swal({
                title: "成功",
                text: "工具上传成功",
                type: "success",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
                window.location.href = "/softwareInfo";
            });

        }
        if (!responseText.result) {
            swal({
                title: "",
                text: msg,
                type: "warning",
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: true
            }, function () {
            });
        }
    }
});