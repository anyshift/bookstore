<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书城网登陆</title>
    <%@ include file="/common/head.jsp"%>
</head>
<body>
<center>
    <div style="height: 20px;"></div>

    <c:if test="${requestScope.errorInfo != null}">
        <p style="color: red;">${requestScope.errorInfo}</p>
    </c:if>

    <c:choose>
        <c:when test="${not empty param.cartAction}">
            <form action="user?method=login&cartAction=${param.cartAction}" method="post">
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
                        <td style="background-color: #e6ecf4"><button><a href="index?method=getBooks" style="text-decoration: none; color: black">取消登陆</a></button></td>
                        <td style="background-color: #e6ecf4"><input type="submit" value="登陆以支付" style="width: 100%"/></td>
                    </tr>
                </table>
            </form>
        </c:when>
        <c:otherwise>
            <form action="user?method=login" method="post">
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
                        <td style="background-color: #e6ecf4"><button><a href="index?method=getBooks" style="text-decoration: none; color: black">取消登陆</a></button></td>
                        <td style="background-color: #e6ecf4"><input type="submit" value="登陆" style="width: 100%"/></td>
                    </tr>
                </table>
            </form>
        </c:otherwise>
    </c:choose>
</center>
</body>
</html>