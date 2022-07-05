<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"  %>
<html>
<head>
    <title>《${requestScope.book.bookName}》的详细信息</title>
    <%@ include file="/common/head.jsp"%>
    <%@ include file="/common/common.jsp" %>
    <style>
        a { text-decoration: none; }
        table {
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


        <c:if test="${param.bookTitle != null}"> <%-- param是自带的，可以直接使用。用于访问URL中的参数 --%>
            已将《${param.bookTitle}》加入到购物车
            <br><br>
        </c:if>

        <c:if test="${!empty sessionScope.shoppingCart.books}">
            <%-- bookNumber是ShoppingCart对象中的getBookNumber()方法，读取bean方法，省去get。（JSP JavaBean） --%>
            当前购物车中共有 ${sessionScope.shoppingCart.bookNumber} 本书，<a href="index?method=shoppingCart&pageNum=${param.pageNum}">查看购物车</a>
            <br>
        </c:if>


        <br>
        <table cellpadding="10" border="1px solid black" cellspacing="0"  style="text-align: center">
            <tr style="background-color: beige">
                <th style="width: 100px;">属性</th>
                <th style="width: 212px;">详情</th>
                <th style="width: 100px;">操作</th>
            </tr>
            <tr>
                <td style="background-color: beige;">书名</td>
                <td>《${requestScope.book.bookName}》</td>
                <td rowspan="7">
                    <c:choose>
                        <c:when test="${param.cartAction == 'bookInfo'}">
                        <a href="index?method=shoppingCart&cartAction=list&pageNum=${param.pageNum}" style="text-decoration: none;">返回购物车</a></td>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${empty param.keyword}">
                                    <a href="index?method=getBooks&pageNum=${param.pageNum}" style="text-decoration: none;">继续购物</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="index?method=searchBooks&pageNum=${param.pageNum}">继续购物</a>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td style="background-color: beige;">作者</td>
                <td>${requestScope.book.author}</td>
            </tr>
            <tr>
                <td style="background-color: beige;">价格</td>
                <td>${requestScope.book.price}</td>
            </tr>
            <tr>
                <td style="background-color: beige;">出版日期</td>
                <td>${requestScope.book.publishingDate}</td>
            </tr>
            <tr>
                <td style="background-color: beige;">库存量</td>
                <td>
                    <c:choose>
                        <c:when test="${requestScope.book.stock >= 100}">库存充足</c:when>
                        <c:when test="${requestScope.book.stock == 0}">库存不足</c:when>
                        <c:otherwise><font color="red">仅剩${requestScope.book.stock}本</font></c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td style="background-color: beige;">销量</td>
                <td>${requestScope.book.salesAmount}</td>
            </tr>
            <tr>
                <td style="background-color: beige;">备注</td>
                <td>${requestScope.book.info}</td>
            </tr>
        </table>
    </center>
    <br><br>

</body>
</html>
