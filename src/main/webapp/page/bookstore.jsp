<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"  %>
<html>
<head>
    <title>购书网</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <%@ include file="/common/head.jsp"%>
    <script src="${pageContext.request.contextPath}/static/js/validate_price.js"></script>
    <%@ include file="/common/common.jsp" %>
    <style>
        a { text-decoration: none; }
        table {
            table-layout: fixed; /*列宽由表格宽度和列宽度设定*/
        }
        td { word-break: break-word; /*允许在单词内换行。*/ }
    </style>
    <script>
        $(function () {
            $(".pageNumber").change(function () {
                let value = $(this).val();
                value = $.trim(value);

                let flag = false;
                let reg = /^\d+$/g;
                let pageNumber = 0;

                if (reg.test(value)) {
                    pageNumber = parseInt(value);
                    if (pageNumber >= 1 && pageNumber <= parseInt("${requestScope.books.getTotalPageNumber()})")) {
                        flag = true;
                    }
                }

                if (!flag) {
                    alert("页码输入错误，请重新输入");
                    $(this).val(""); //将错误页码置空
                    return; //停止后续程序执行
                }

                window.location.href = "index?method=getBooks&pageNum=" + pageNumber + "&" + $(":hidden").serialize();
            });

            $(".cancel-filter").click(function () {
                $("input[name=minPrice]").val("");
                $("input[name=maxPrice]").val("");
            });

            $(".cancel-search").click(function () {
                $("input[name=keyword]").val("");
            });

        });
    </script>
</head>
<body>
<div style="height: 20px;"></div>
<center>
    <c:choose>
        <c:when test="${empty requestScope.books.bookList}">书城无书</c:when>
        <c:otherwise>
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

            <%-- -------------------------分割线------------------------- --%>
            <br>
            <table cellpadding="10" border="1px solid black" cellspacing="0">
                    <%-- books是Page对象， 迭代获取到的book是Book对象。因为Page<Book>。 --%>
                <tr style="background-color: beige">
                    <th style="width: 192px;">书籍</th>
                    <th style="width: 32px;">销量</th>
                    <th style="width: 32px;">库存</th>
                    <th style="width: 32px;">价格</th>
                    <th style="width: 80px;">操作</th>
                </tr>
                <c:forEach items="${requestScope.books.bookList}" var="book">
                    <tr>
                        <td>
                            <a href="index?method=getBook&bookID=${book.id}&pageNum=${requestScope.books.currentPageNum}">《${book.bookName}》</a>
                            <br/> &nbsp; <span style="font-size: 14px">作者: ${book.author}</span>
                        </td>
                        <td style="text-align: center">${book.getSalesAmount()}</td>
                        <td style="text-align: center">${book.stock}</td>
                        <td style="text-align: center">${book.price}</td>
                        <td style="text-align: center">
                            <c:choose>
                                <c:when test="${book.stock == 0}">库存不足</c:when>
                                <c:otherwise>
                                    <a href="index?method=shoppingCart&cartAction=add&pageNum=${requestScope.books.currentPageNum}&bookID=${book.id}&bookTitle=${book.bookName}">加入购物车</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br>

            <%-- -------------------------分割线------------------------- --%>

            <form action="index?method=getBooks" method="post">
                <table>
                    <tr>
                        <td>
                            最低价：<input type="text" name="minPrice" size="4" value="${param.minPrice}"
                                       style="text-align: center;" id="minPrice">
                        </td>
                        <td rowspan="2">
                            <c:choose>
                                <c:when test="${empty param.minPrice && empty param.maxPrice}">
                                    <input type="submit" value="筛选" style="margin-left: 10px; padding: 12px 5px;">
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" class="cancel-filter" value="取消筛选" style="margin-left: 10px; padding: 12px 5px;">
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            最高价：<input type="text" name="maxPrice" size="4" value="${param.maxPrice}"
                                       style="text-align: center;" id="maxPrice">
                        </td>
                    </tr>
                </table>
            </form>

            <%-- -------------------------分割线------------------------- --%>

            &nbsp;&nbsp;
            共 ${requestScope.books.getTotalPageNumber()} 页
            &nbsp;&nbsp;
            <c:if test="${requestScope.books.currentPageNum != 0}">
                当前第 ${requestScope.books.currentPageNum} 页
                &nbsp;&nbsp;
            </c:if>

            <c:if test="${requestScope.books.hasPrevPage}">
                <a href="index?method=getBooks">首页</a>
                &nbsp;&nbsp;
                <a href="index?method=getBooks&pageNum=${requestScope.books.prevPage}">上一页</a>
                &nbsp;&nbsp;
            </c:if>

            <c:if test="${requestScope.books.hasNextPage}">
                <a href="index?method=getBooks&pageNum=${requestScope.books.nextPage}">下一页</a>
                &nbsp;&nbsp;
                <a href="index?method=getBooks&pageNum=${requestScope.books.totalPageNumber}">末页</a>
                &nbsp;&nbsp;
            </c:if>

            <c:if test="${requestScope.books.currentPageNum != 0}">
                跳转至第 <input type="text" size="1" class="pageNumber" style="text-align: center"/> 页&nbsp;&nbsp;
            </c:if>
        </c:otherwise>
    </c:choose>
</center>
<br><br>
</body>
</html>