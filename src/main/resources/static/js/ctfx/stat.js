/**
 * 描述:
 *
 * @author Jin Huatao
 */

$(function(){
    var toolPieChart = echarts.init(document.getElementById("tool-source-chart"));
    var toolpieoption = {
        title : {
            text: '工具来源统计',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:['网站主页','应用商店','社交平台','论坛','搜索引擎']
        },
        calculable : true,
        series : [
            {
                name:'访问来源',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:1790, name:'网站主页'},
                    {value:686, name:'应用商店'},
                    {value:1943, name:'社交平台'},
                    {value:135, name:'论坛'},
                    {value:82, name:'搜索引擎'}
                ]
            }
        ]
    };
    toolPieChart.showLoading({ text:'正在加载数据...' });
    $.get("./stat/finder/source",function(data){
        toolpieoption.series[0].data= JSON.parse(data);
        toolPieChart.setOption(toolpieoption);
        toolPieChart.hideLoading();
        $(window).resize(toolPieChart.resize);
    });


    var toolBarChart = echarts.init(document.getElementById("tool-audit-bar"));
    var toolbaroption = {
        color: ['#2ec7c9'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['未审核', '审核有效(新工具/资源)', '审核无效(已存在)', '其他(推荐等)'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'页面数量',
                type:'bar',
                barWidth: '60%',
                data:[]
            }
        ]
    };

    toolBarChart.showLoading({ text:'正在加载数据...' });
    $.get("./stat/finder/audit",function(data){
        toolbaroption.series[0].data= JSON.parse(data);
        toolBarChart.setOption(toolbaroption);
        toolBarChart.hideLoading();
        $(window).resize(toolBarChart.resize);
    });

    var toolStatChart = echarts.init(document.getElementById("tool-stat-chart"));
    var toolstatoption = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['系统已有工具', '新发现工具', '新发现资源'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'工具数量',
                type:'bar',
                barWidth: '60%',
                data:[]
            }
        ]
    };

    toolStatChart.showLoading({ text:'正在加载数据...' });
    $.get("./stat/tool",function(data){
        toolstatoption.series[0].data= JSON.parse(data);
        toolStatChart.setOption(toolstatoption);
        toolStatChart.hideLoading();
        $(window).resize(toolStatChart.resize);
    });

    var toolTraceChart = echarts.init(document.getElementById("tool-trace-chart"), 'shine');
    var tooltraceoption = {
        color: ['#c4ebad'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['跟踪数量', '有效数量', '正在跟踪数量'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'页面数量',
                type:'bar',
                barWidth: '60%',
                data:[]
            }
        ]
    };

    toolTraceChart.showLoading({ text:'正在加载数据...' });
    $.get("./stat/trace",function(data){
        tooltraceoption.series[0].data= JSON.parse(data);
        toolTraceChart.setOption(tooltraceoption);
        toolTraceChart.hideLoading();
        $(window).resize(toolTraceChart.resize);
    });

    var toolSoftwareChart = echarts.init(document.getElementById("tool-software-chart"));
    var toolsoftwareoption = {
        color: ['#6f5553'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['样本数量', '有效数量',"无效数量"],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'样本数量',
                type:'bar',
                barWidth: '60%',
                data:[]
            }
        ]
    };

    toolSoftwareChart.showLoading({ text:'正在加载数据...' });
    $.get("./stat/software",function(data){
        toolsoftwareoption.series[0].data= JSON.parse(data);
        toolSoftwareChart.setOption(toolsoftwareoption);
        toolSoftwareChart.hideLoading();
        $(window).resize(toolSoftwareChart.resize);
    });


});