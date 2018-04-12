<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<form acton="${pageContext.request.contextPath}/login/check" method="post">
    <input type="text" name="name"><br>
    <input type="text" name="password">
    <input type="submit" value="登录">
</form>
</body>
</html>
