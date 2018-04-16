//时间格式化处理
function dateFtt(fmt,date){
    date= new Date(Date.parse(date));
    var o = {
        "M+" : date.getMonth()+1,                 //月份
        "d+" : date.getDate(),                    //日
        "h+" : date.getHours(),                   //小时
        "m+" : date.getMinutes(),                 //分
        "s+" : date.getSeconds(),                 //秒
        "q+" : Math.floor((date.getMonth()+3)/3), //季度
        "S"  : date.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}
//初始化多选表单(version编辑)
function chosenInit() {
    var config = {
        '.chosen-select': {},
        '.chosen-select-deselect': {
            allow_single_deselect: true
        },
        '.chosen-select-no-single': {
            disable_search_threshold: 10
        },
        '.chosen-select-no-results': {
            no_results_text: '找不到数据!'
        },
        '.chosen-select-width': {
            width: "95%"
        }
    };
    for (var selector in config) {
        $(selector).chosen(config[selector]);
    }
    //给多选框赋值
    $(".chosen-container-multi").attr("style", "width:548.67px");
}
//初始化多选表单(version添加)
function chosenAddInit() {
    var config = {
        '.chosenAdd-select': {},
        '.chosenAdd-select-deselect': {
            allow_single_deselect: true
        },
        '.chosenAdd-select-no-single': {
            disable_search_threshold: 10
        },
        '.chosenAdd-select-no-results': {
            no_results_text: '找不到数据!'
        },
        '.chosenAdd-select-width': {
            width: "95%"
        }
    };
    for (var selector in config) {
        $(selector).chosen(config[selector]);
    }
    //给多选框赋值
    $(".chosen-container-multi").attr("style", "width:548.67px");
}
//version编辑表中Options操作
function addOptions (platform) {
    //设置option选中
    if(platform != null && platform !== ""){
        platform = platform.toString().toLowerCase()
        if(platform.indexOf("android") >-1){
            $("#Android").attr("selected","selected");
        }
        if(platform.indexOf("windows") >-1){
            $("#Windows").attr("selected","selected");
        }
        if(platform.indexOf("ios") >-1){
            $("#iOS").attr("selected","selected");
        }
        if(platform.indexOf("linux") >-1){
            $("#Linux").attr("selected","selected");
        }
        if(platform.indexOf("firefox") >-1){
            $("#Firefox").attr("selected","selected");
        }
        if(platform.indexOf("路由器") >-1){
            $("#luyouqi").attr("selected","selected");
        }
        if(platform.indexOf("chrome") >-1){
            $("#Chrome").attr("selected","selected");
        }
        if(platform.indexOf("电视机") >-1){
            $("#tv").attr("selected","selected");
        }
        if(platform.indexOf("游戏机") >-1){
            $("#youxiji").attr("selected","selected");
        }
    }
}
//初始化工具表格数据
function initTableForTool(userName) {
    //初始化表格数据
    $("#exampleTableEvents").bootstrapTable("destroy");
    $("#exampleTableEvents").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: "/system/user/initPaging",
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
            if (userName !== null && userName.length !== 0 ) {
                param = {
                    pageNumber: params.pageNumber,
                    pageSize: params.pageSize,
                    userName: userName,
                };
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
//初始化版本表格数据
function initTableForVersion(sampleName) {
    //初始化表格数据
    $("#exampleTableEvents").bootstrapTable("destroy");
    $("#exampleTableEvents").bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: "/system/versionInfo/initPaging",
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
            if (sampleName !== null && sampleName.length !== 0 ) {
                param = {
                    pageNumber: params.pageNumber,
                    pageSize: params.pageSize,
                    sampleName: sampleName,
                };
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
                var rows=e.data.rows;
                for (var i=0;i<rows.length;i++){
                    var postTime =rows[i].postTime;
                    rows[i].postTime=dateFtt("yyyy-MM-dd hh:mm:ss",postTime)
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