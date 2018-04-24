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
    <title>科研管理系统</title>
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i></div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <#if Session.user?exists>
                        <span style="color: #5f9ea0;font-weight: bold ">欢迎您：${Session.user.realName}</span>
                        <a class="J_menuPage" id="logout" href="javascript:void(0)">
                            <i class="fa fa-exclamation"></i>
                            <span class="nav-label">注销</span>
                        </a>
                    </#if>
                </li>
                <li>
                    <a class="J_menuPage" href="/toShouye">
                        <i class="fa fa-home"></i>
                        <span class="nav-label">首页</span>
                    </a>
                </li>
                <li>
                    <a class="J_menuPage" href="/toProject">
                        <i class="fa fa-file"></i>
                        <span class="nav-label">项目管理</span>
                    </a>
                </li>
                <li>
                    <a class="J_menuPage" href="/toMaterial">
                        <i class="fa fa-folder"></i>
                        <span class="nav-label">项目材料</span>
                    </a>
                </li>
                <li>
                    <a class="J_menuPage" href="/toStatAnalyze">
                        <i class="fa fa-pie-chart"></i>
                        <span class="nav-label">统计分析</span>
                    </a>
                </li>
                <#if Session.user?exists && Session.user.permission == 1>
                    <li>
                        <a class="J_menuPage" href="/toProjectManager">
                            <i class="fa fa-list"></i>
                            <span class="nav-label">项目审批</span>
                        </a>
                    </li>
                </#if>
                <#if Session.user?exists && Session.user.permission == 2>
                    <li>
                        <a class="J_menuPage" href="/toUserInfo">
                            <i class="fa fa-group"></i>
                            <span class="nav-label">用户管理</span>
                        </a>
                    </li>
                </#if>
            </ul>
        </div>
    </nav>

    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg">
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframeContent" width="100%" height="100%" src="/toShouye" frameborder="0" ></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">&copy; 2018 <a href="https://github.com/37176427/grad/" target="_blank">在github上寻找</a>
            </div>
        </div>
    </div>
</div>
<!-- 全局js -->
<script src="/static/js/jquery.min.js?v=2.1.4"></script>
<script src="/static/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="/static/js/plugins/layer/layer.min.js"></script>

<script type="text/javascript">
    $(document).ready(function(){
        $(".J_menuPage").click(function(){
            $(".J_iframeContent").attr("src", $(this).attr("href"));
            return false;
        });
    });
</script>


<!-- 自定义js -->
<script src="/static/js/hplus.js?v=4.1.0"></script>
<script type="text/javascript" src="/static/js/contabs.js"></script>

<!-- 第三方插件 -->
<script src="/static/js/plugins/pace/pace.min.js"></script>
</body>
</html>