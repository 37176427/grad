<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login/check" method="post">
    ${msg}<br>
    <input type="text" name="name"><br>
    <input type="password" name="password">
    <input type="submit" value="登录">
    <input type="reset" value="重置">
</form>
</body>
</html>
