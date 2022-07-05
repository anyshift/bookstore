<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书城网注册</title>
    <%@ include file="/common/head.jsp" %>
</head>
<body>
<center>
    <div style="height: 20px;"></div>

    <c:if test="${requestScope.errorInfo != null}">
        <p style="color: red;">${requestScope.errorInfo}</p>
    </c:if>

    <form action="user?method=register" method="post" id="other">
        <table cellpadding="10" border="1px solid black" cellspacing="0">
            <tr>
                <td style="background-color: beige">用&nbsp;&nbsp;户&nbsp;&nbsp;名:</td>
                <td><input type="text" name="userName"/></td>
            </tr>
            <tr>
                <td style="background-color: beige">用户密码:</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td style="background-color: beige">重复密码:</td>
                <td><input type="password" name="password_again"/></td>
            </tr>
            <tr>
                <td style="background-color: #e6ecf4"><button ><a href="index?method=getBooks" style="text-decoration: none; color: black">取消注册</a></button></td>
                <td style="background-color: #e6ecf4"><input type="submit" value="注册" style="width: 100%"/></td>
            </tr>
        </table>
    </form>
    <br><br>

</center>
</body>
</html>
