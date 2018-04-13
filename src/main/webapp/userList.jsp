<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="0" width="900px">
    <tr>
        <td align="center" style="font-size:24px; color:#666"> 用户管理</td>
    </tr>
    <tr>
        <td align="right" > <a href="${pageContext.request.contextPath}/user/add">添加用户</a></td>
    </tr>
</table>
    <br/>
    <table cellspacing="0" border="1" class="table1">
        <thead>
        <tr>
            <th width="300">ID</th>
            <th width="300">登录名</th>
            <th  width="300">密码</th>
            <th width="300">姓名</th>
            <th width="300">级别</th>
            <th width="300">编辑</th>
            <th  width="300">删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="user">
            <tr>
                <td align="center">${user.id}</td>
                <td align="center">${user.name}</td>
                <td align="center">${user.password}</td>
                <td align="center">${user.realName}</td>
                <td align="center">${user.permission}</td>
                <td align="center"><a href="${pageContext.request.contextPath}/user/edit?id=${user.id}/>"><img src="<%=basePath %>images/编辑.png"></a></td>
                <td align="center"><a href="javascript:void(0)" onclick="deleteById(${user.id})"/><img src="<%=basePath %>images/trash.gif"></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
<script language="JavaScript">
    function deleteById(id){
        if(confirm("确认删除此用户吗？")){
            window.location.href="${pageContext.request.contextPath}/user/delete("+id+")";
        }
    }
</script>
</html>
