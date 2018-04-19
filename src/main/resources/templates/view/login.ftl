<#--<!DOCTYPE html>
<html>
<head>
    <title>用户登录页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div>用户登录</div>
<form name="form" action="/login" method="post">
    name: <input type="text" name="name"><br/>
    pass: <input type="password" name="password"><br/>
    <input type="submit" value="登录">
    <input type="reset" value="重置">
</form>
</body>
</html>-->
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>科研管理系统 -- 登录</title>
    <link href="/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/static/css/font-awesome.css" rel="stylesheet">
    <link href="/static/css/animate.css" rel="stylesheet">
    <link href="/static/css/style.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script src="/static/js/jquery.min.js?v=2.1.4"></script>
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>
            <h1 class="logo-name">YH</h1>
        </div>
        <h3>欢迎使用科研管理系统</h3>

        <form class="m-t" role="form" action="/login" method="post">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="用户名" required="" name="name">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="密码" required="" name="password">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
            <button type="reset" class="btn btn-warning block full-width m-b">重 置</button>
        </form>
    </div>
</div>
</body>
</html>
