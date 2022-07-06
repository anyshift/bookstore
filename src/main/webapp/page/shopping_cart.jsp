<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>购物车</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/common/head.jsp" %>
    <script src="/static/js/validate_cart_quantity.js"></script>
    <%@ include file="/common/common.jsp" %>
    <style>
        a {
            text-decoration: none;
        }

        table {
            text-align: center;
            table-layout: fixed; /*列宽由表格宽度和列宽度设定*/
        }

        td {
            word-break: break-word; /*允许在单词内换行。*/
        }
    </style>
</head>
<body>
<input type="hidden" name="keyword" value="${param.keyword}"/>
<div style="height: 20px;"></div>
<center>
    <form action="index?method=searchBooks" method="post">
        <input size="20" type="text" name="keyword" value="${param.keyword}" placeholder="不太靠谱的搜一搜" style="padding: 2px;" class="search">
        <c:choose>
            <c:when test="${empty param.keyword}">
                <input type="submit" value="搜索" style="padding: 0 6px;">
            </c:when>
            <c:otherwise>
                <input type="submit" class="cancel-search" value="取消搜索" style="padding: 0 6px;">
            </c:otherwise>
        </c:choose>
        <c:if test="${empty sessionScope.user.username}">
            <c:if test="${not empty param.keyword}">
                <span style="margin: 0 86.5px"></span>
            </c:if>
            <c:if test="${empty param.keyword}">
                <span style="margin: 0 100px"></span>
            </c:if>
            <a target="_self" href="user?method=login" class="login">登陆</a>&nbsp;
            <a target="_self" href="user?method=register" class="signup">注册</a>
        </c:if>
        <c:if test="${not empty sessionScope.user.username}">
            <c:if test="${not empty param.keyword}">
                <span style="margin: 0 21.5px"></span>
            </c:if>
            <c:if test="${empty param.keyword}">
                <span style="margin: 0 35px"></span>
            </c:if>
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


    <c:choose>
        <c:when test="${empty sessionScope.shoppingCart.books}">
            购物车已清空，<a href="index?method=getBooks&pageNum=${param.pageNum}">继续购物</a>
        </c:when>
        <c:otherwise>
            <div id="bookNumber">当前购物车共有 ${sessionScope.shoppingCart.bookNumber} 本书</div>
            <br>
            <table cellpadding="10" border="1px solid black" cellspacing="0">
                <tr style="background-color: beige">
                    <th style="width: 239px;">书名</th>
                    <th style="width: 50px;">数量</th>
                    <th style="width: 50px;">单价</th>
                    <th style="width: 50px;">操作</th>
                </tr>

                <c:if test="${!empty sessionScope.shoppingCart.bookNumber}">
                    <c:forEach items="${sessionScope.shoppingCart.items}" var="item">
                        <tr>
                            <td>
                                <a href="index?method=shoppingCart&cartAction=bookInfo&bookID=${item.book.id}&pageNum=${param.pageNum}">${item.book.bookName}</a>
                            </td>
                            <td style="padding: unset">
                                <input type="text" class="${item.quantity}" size="2" name="${item.book.id}"
                                       value="${item.quantity}" style="text-align: center"/>
                            </td>
                            <td>${item.itemMoney}</td>
                            <td><a href="index?method=shoppingCart&cartAction=remove&bookID=${item.book.id}"
                                   class="delete">移除</a></td>
                        </tr>
                    </c:forEach>
                </c:if>

                <tr>
                    <td colspan="4" style="background-color: #e6ecf4" id="totalMoney">
                        当前购物车总价：¥ ${sessionScope.shoppingCart.totalMoney}<br>
                        <c:if test="${not empty requestScope.account}">
                            <p style="font-size: 13px; color: midnightblue; margin-top: 5px;">
                                （账户余额：¥ ${requestScope.account.balance}）
                            </p>
                        </c:if>
                    </td>
                </tr>

            </table>
            <br><br>

            <c:choose>
                <c:when test="${empty param.keyword}">
                    <a href="index?method=getBooks&pageNum=${param.pageNum}">继续购物</a> &nbsp;&nbsp;
                </c:when>
                <c:otherwise>
                    <a href="index?method=searchBooks&pageNum=${param.pageNum}">继续购物</a> &nbsp;&nbsp;
                </c:otherwise>
            </c:choose>
            <a href="index?method=shoppingCart&cartAction=clear" class="clear">清空购物车</a> &nbsp;&nbsp;
            <c:if test="${empty sessionScope.user}">
                <a href="user?method=login&cartAction=toPay&pageNum=${param.pageNum}">登陆并支付</a>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                <a href="index?method=shoppingCart&cartAction=toPay&pageNum=${param.pageNum}">下单并支付</a>
            </c:if>
        </c:otherwise>
    </c:choose>
    <br><br>

</center>
</body>
</html>
