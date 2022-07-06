<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书城网 - 订单中心</title>
    <%@ include file="/common/head.jsp" %>
    <%@ include file="/common/common.jsp" %>
    <style>
        a { text-decoration: none; }
        table {
            text-align: center;
            table-layout: fixed; /*列宽由表格宽度和列宽度设定*/
        }
        td { word-break: break-word; /*允许在单词内换行。*/ }
    </style>
</head>
<body>
<input type="hidden" name="keyword" value="${param.keyword}"/>
<div style="height: 20px;"></div>
<center>

    <form action="index?method=searchBooks" method="post">
        <input size="20" type="text" name="keyword" value="${param.keyword}" placeholder="不太靠谱的搜一搜" style="padding: 2px;">
        <c:choose>
            <c:when test="${empty param.keyword}">
                <input type="submit" value="搜索" style="padding: 0 6px;">
            </c:when>
            <c:otherwise>
                <input type="submit" class="cancel-search" value="取消搜索" style="padding: 0 6px;">
            </c:otherwise>
        </c:choose>
        <c:if test="${empty sessionScope.user.username}">
            <span style="margin: 0 100px"></span>
            <a target="_self" href="user?method=login" class="login">登陆</a>&nbsp;
            <a target="_self" href="user?method=register" class="signup">注册</a>
        </c:if>
        <c:if test="${not empty sessionScope.user.username}">
            <span style="margin: 0 35px"></span>
            <c:choose>
                <c:when test="${sessionScope.user.isAdmin == 1}">
                    <a href="user?method=mySpace" class="admin_space">管理中心</a>&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="user?method=mySpace" class="user_space">个人中心</a>&nbsp;
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${param.method == 'myOrder'}">
                    <a href="index?method=getBooks" class="order">返回书城</a>&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="user?method=myOrder" class="order">我的订单</a>&nbsp;
                </c:otherwise>
            </c:choose>
            <a href="user?method=logout">退出登陆</a>
        </c:if>
    </form>

    <c:if test="${!empty sessionScope.shoppingCart.books}">
        <%-- bookNumber是ShoppingCart对象中的getBookNumber()方法，读取bean方法，省去get。（JSP JavaBean） --%>
        当前购物车中共有 ${sessionScope.shoppingCart.bookNumber} 本书，<a href="index?method=shoppingCart&pageNum=${param.pageNum}">查看购物车</a>
        <br>
    </c:if>

    <br>
    <c:choose>
        <c:when test="${empty requestScope.orders}">无订单记录</c:when>
        <c:otherwise>
            <table cellpadding="10" border="1px solid black" cellspacing="0">
                <tr style="background-color: beige">
                    <th style="width: 158px;">书籍</th>
                    <th style="width: 32px;">数量</th>
                    <th style="width: 136px;">购买时间</th>
                    <th style="width: 64px;">订单状态</th>
                </tr>
                <c:forEach items="${requestScope.orders}" var="order">
                    <tr>
                        <td>
                            ${order.bookName}<br>
                            <span style="font-size: 12px;color: midnightblue">订单编号: ${order.orderSerialNumber}</span>
                        </td>
                        <td>${order.quantity}</td>
                        <td>${order.tradeTime}</td>
                        <td>${order.deliveryState}</td>
                    </tr>
                </c:forEach>
            </table>
            <p style="font-size: 13px; color: midnightblue; margin-top: 5px;">注：只显示最近十条订单记录</p>
        </c:otherwise>
    </c:choose>

</center>
</body>
</html>
