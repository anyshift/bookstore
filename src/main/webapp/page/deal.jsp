<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>交易前台</title>
    <%@ include file="/common/head.jsp"%>
    <%@ include file="/common/common.jsp" %>
</head>
<body>

    <div style="height: 20px;"></div>
    <center>

        <c:if test="${!empty sessionScope.shoppingCart.bookNumber}">
            一共添加 ${sessionScope.shoppingCart.bookNumber} 本书到购物车 <br><br>
            应付 ¥ ${sessionScope.shoppingCart.totalMoney} <br><br>

            <c:if test="${requestScope.errorInfo != null}">
                <p style="color: red;">${requestScope.errorInfo}</p>
                <br>
            </c:if>


            <form action="index?method=shoppingCart&cartAction=pay" method="post">

                <table cellpadding="10" border="1px solid black" cellspacing="0">
                    <tr>
                        <td style="background-color: beige">用&nbsp;&nbsp;户&nbsp;&nbsp;名:</td>
                        <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                <td><input type="text" name="userName" disabled="disabled" placeholder="已登陆, 无需输入" style="text-align: center"/></td>
                            </c:when>
                            <c:otherwise>
                                <td><input type="text" name="userName"/></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <td style="background-color: beige">用户密码:</td>
                        <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                <td><input type="text" name="password" disabled="disabled" placeholder="已登陆, 无需输入" style="text-align: center"/></td>
                            </c:when>
                            <c:otherwise>
                                <td><input type="text" name="password"/></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <td style="background-color: #e6ecf4"><button><a href="index?method=shoppingCart&pageNum=${param.pageNum}" style="text-decoration: none; color: black">取消支付</a></button></td>
                        <td style="background-color: #e6ecf4"><input type="submit" value="支付" style="width: 100%"/></td>
                    </tr>
                </table>

            </form>
        </c:if>

    </center>

</body>
</html>
