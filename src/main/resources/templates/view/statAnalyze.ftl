<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <link rel="shortcut icon" type="image/x-icon" href="/static/img/logo.ico">
    <link href="/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="/static/css/animate.css" rel="stylesheet">
    <link href="/static/css/style.css?v=4.1.0" rel="stylesheet">
    <title>CT工具统计分析</title>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-6">
            <div class="ibox float-e-margins">
                <!--浮动框标题-->
                <div class="ibox-title">
                    <h5>项目材料统计</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <!--浮动框内容-->
                <div class="ibox-content">
                    <div class="echarts" id="material-chart"></div>
                </div>
            </div>
        </div>

        <div class="col-sm-6">
            <div class="ibox float-e-margins">
                <!--浮动框标题-->
                <div class="ibox-title">
                    <h5>项目审核统计</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <!--浮动框内容-->
                <div class="ibox-content">
                    <div class="echarts" id="checked-chart"></div>
                </div>
            </div>
        </div>
    </div>
</div>



<!-- 全局js -->
<script src="/static/js/jquery.min.js?v=2.1.4"></script>
<script src="/static/js/bootstrap.min.js?v=3.3.6"></script>

<!-- ECharts -->
<script src="/static/js/plugins/echarts/echarts.min.js"></script>
<script src="/static/js/plugins/echarts/shine.js"></script>

<!-- 自定义js -->
<script src="/static/js/content.js?v=1.0.0"></script>

<!-- ECharts demo data -->
<script src="/static/js/myjs/stat.js"></script>
</body>
</html>