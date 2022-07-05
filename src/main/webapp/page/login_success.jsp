<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>
<head>
    <title>登陆成功</title>
    <%@ include file="/common/head.jsp"%>
</head>
<body>
    <div style="height: 20px;"></div>
    <center>
        ${sessionScope.user.username}，欢迎回来，正在为您返回首页... <a href="index?method=getBooks">返回首页</a>
    </center>
</body>
</html>
