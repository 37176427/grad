<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    response.setHeader("P3P","CP=CAO PSA OUR");
%>
<!doctype html>
<html>
<head>
    <link href="<%=basePath %>dtree.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath %>dtree.js"></script>
</head>
<body bgColor=#DDF0FB leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<table width="90%" border="0" cellspacing="1" cellpadding="2" align="center">
    <div class="dtree">
        <script type="text/javascript">
            d=new dTree('d');

            d.add('01','-1','科研管理系统');
            d.add('0101','01','项目管理');
            d.add('010101','0101','项目申报','${pageContext.request.contextPath}/projectAdd','','right');
            //领导具有的模块
            <c:if test="${user.permission==1}">
            d.add('010102','0101','审批管理','${pageContext.request.contextPath}/manager/approvedManager','','right');
            </c:if>
            //管理员具有的模块
            d.add('010103','0101','用户管理','${pageContext.request.contextPath}/user/findAll','','right');
            document.write(d);
        </script>
    </div>
</table>
</body>
</html>