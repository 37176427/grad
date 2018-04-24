/**
 * 描述: 统计页面js
 */

$(function(){
    var pieChart = echarts.init(document.getElementById("material-chart"));
    var pieOption = {
        title : {
            text: '项目材料统计',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:['暂无材料','已有材料']
        },
        calculable : true,
        series : [
            {
                name:'材料',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:2, name:'暂无材料'},
                    {value:10, name:'已有材料'}
                ]
            }
        ]
    };
    pieChart.showLoading({ text:'正在加载数据...' });
    $.get("./stat/material",function(data){
        pieOption.series[0].data= JSON.parse(data);
        pieChart.setOption(pieOption);
        pieChart.hideLoading();
        $(window).resize(pieChart.resize);
    });


    var checkChart = echarts.init(document.getElementById("checked-chart"));
    var checkOption = {
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
                data : ['未审核', '审核通过', '审核不通过'],
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
                name:'审核状态',
                type:'bar',
                barWidth: '60%',
                data:[]
            }
        ]
    };

    checkChart.showLoading({ text:'正在加载数据...' });
    $.get("./stat/checked",function(data){
        checkOption.series[0].data= JSON.parse(data);
        checkChart.setOption(checkOption);
        checkChart.hideLoading();
        $(window).resize(checkChart.resize);
    });
});