<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书城网 - 管理中心</title>
    <%@ include file="/common/head.jsp"%>
    <script src="/static/js/update_balance.js"></script>
    <script src="/static/js/update_delivery_state.js"></script>
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
        <input size="20" type="text" name="keyword" class="search"
               value="${param.keyword}" placeholder="在当前页面已禁用"
               style="padding: 2px;" disabled="disabled">
        <c:choose>
            <c:when test="${empty param.keyword}">
                <input type="submit" value="搜索" style="padding: 0 6px;" disabled="disabled">
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
            <a href="user?method=myOrder" class="order">我的订单</a>&nbsp;
            <a href="user?method=logout">退出登陆</a>
        </c:if>
    </form>

    <c:if test="${!empty sessionScope.shoppingCart.books}">
        <%-- bookNumber是ShoppingCart对象中的getBookNumber()方法，读取bean方法，省去get。（JSP JavaBean） --%>
        当前购物车中共有 ${sessionScope.shoppingCart.bookNumber} 本书，<a href="index?method=shoppingCart&pageNum=${param.pageNum}">查看购物车</a>
        <br>
    </c:if>

    <br>
    <form action="user?method=mySpace" method="post">
        <span style="font-size: 18px;">管理用户：</span>
        <input type="text" name="userName" style="height: 25px;" placeholder="用户名" class="userName" value="${param.userName}">
        <c:choose>
            <c:when test="${empty param.userName}">
                <input type="submit" value="搜索">
            </c:when>
            <c:otherwise>
                <input type="submit" value="取消搜索" class="cancel-search-user" >
            </c:otherwise>
        </c:choose>
    </form>
    <c:if test="${not empty requestScope.managedUser}">
        <table cellpadding="10" border="1px solid black" cellspacing="0">
            <tr style="background-color: beige">
                <th style="width: 60px;">用户名</th>
                <th style="width: 80px;">用户余额</th>
            </tr>
            <tr>
                <td><span id="username-span">${requestScope.managedUser.username}</span></td>
                <td>
                    <c:if test="${not empty requestScope.account}">
                        <input type="text" value="${requestScope.account.balance}" style="width: 60px; height: 30px; text-align: center;" class="${requestScope.account.balance}" id="balance">
                    </c:if>
                </td>
            </tr>
        </table>
    </c:if>
    <br>

    <form action="user?method=mySpace" method="post">
        <span style="font-size: 18px;">管理订单：</span>
        <input type="text" name="orderSerialNumber" style="height: 25px;" value="${param.orderSerialNumber}" placeholder="订单编号" class="serialNumber">
        <c:choose>
            <c:when test="${empty param.orderSerialNumber}">
                <input type="submit" value="搜索">
            </c:when>
            <c:otherwise>
                <input type="submit" value="取消搜索" class="cancel-search-order">
            </c:otherwise>
        </c:choose>
    </form>
    <c:if test="${not empty requestScope.managedOrder}">
        <table cellpadding="10" border="1px solid black" cellspacing="0">
            <tr style="background-color: beige">
                <th style="width: 158px;">书籍</th>
                <th style="width: 32px;">数量</th>
                <th style="width: 136px;">购买时间</th>
                <th style="width: 64px;">订单状态</th>
            </tr>
            <tr>
                <td>
                    <span>${requestScope.managedOrder.bookName}</span><br>
                    <span style="font-size: 12px;color: midnightblue" id="serial-span">${requestScope.managedOrder.orderSerialNumber}</span>
                </td>
                <td>${requestScope.managedOrder.quantity}</td>
                <td>${requestScope.managedOrder.tradeTime}</td>
                <td>
                    <input type="text" value="${requestScope.managedOrder.deliveryState}" class="${requestScope.managedOrder.deliveryState}" id="state" style="width: 60px; height: 30px; text-align: center;">
                </td>
            </tr>
        </table>
    </c:if>
    <br>

</center>
</body>
</html>
