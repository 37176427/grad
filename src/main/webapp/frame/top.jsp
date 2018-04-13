<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    response.setHeader("P3P","CP=CAO PSA OUR");
%>
<!doctype html>
<html>
<head>
    <style type="text/css">
        .div1 { margin-top:50px; margin-left:600px; font-size:14px; color:white}
    </style>
</head>
<body bgcolor="#0099FF">
    <div class="div1">
           欢迎您：${user.realName}
        <a href="#" onclick="logout()" >注销</a>

</body>
<script>
    function logout(){
        if(confirm("确认注销？")){
            ${pageContext.session.invalidate()};
            top.location.href="/tologin";
        }

    }
</script>
</html>
