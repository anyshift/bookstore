<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<html>
<head>
    <title>购书网</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="jquery/jquery-3.6.0.min.js"></script>
    <script src="js/validate-price.js"></script>
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

                window.location.href = "index?method=searchBooks&pageNum=" + pageNumber + "&" + $(":hidden").serialize();
            });

            $(".cancel-filter").click(function () {
                $("input[name=minPrice]").val("");
                $("input[name=maxPrice]").val("");
            });

            $(".cancel-search").click(function () {
                $("input[name=keyword]").val("");
            })
        });
    </script>
</head>
<body>
<input type="hidden" name="keyword" value="${param.keyword}"/>
<center>
    <br><br>
    <form action="index?method=searchBooks" method="post">
        <input size="20" type="text" name="keyword" value="${param.keyword}" placeholder="不太靠谱的搜一搜">
        <c:choose>
            <c:when test="${empty param.keyword}">
                <input type="submit" value="搜索">
            </c:when>
            <c:otherwise>
                <input type="submit" class="cancel-search" value="取消搜索">
            </c:otherwise>
        </c:choose>
    </form>

    <c:if test="${param.bookTitle != null}"> <%-- param是自带的，可以直接使用。用于访问URL中的参数 --%>
        已将《${param.bookTitle}》加入到购物车
        <br/><br/>
    </c:if>

    <c:if test="${!empty sessionScope.shoppingCart.books}">
        <%-- bookNumber是ShoppingCart对象中的getBookNumber()方法，读取bean方法，省去get。（JSP JavaBean） --%>
        当前购物车中共有 ${sessionScope.shoppingCart.bookNumber} 本书，<a href="index?method=shoppingCart&pageNum=${param.pageNum}">查看购物车</a>
        <br/><br/>
    </c:if>

    <%-- -------------------------分割线------------------------- --%>
    <c:choose>
        <c:when test="${empty requestScope.books.bookList}">未搜索到相关书籍</c:when>
        <c:otherwise><br>
            <table cellpadding="10" border="1px solid black" cellspacing="0">
                    <%-- books是Page对象， 迭代获取到的book是Book对象。因为Page<Book>。 --%>
                <tr style="background-color: beige">
                    <th>书籍</th>
                    <th>销量</th>
                    <th>库存</th>
                    <th>价格</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${requestScope.books.bookList}" var="book">
                    <tr>
                        <td style="width: 190px;">
                            <a href="index?method=getBook&bookID=${book.getBookId()}&pageNum=${requestScope.books.currentPageNum}">《${book.title}》</a>
                            <br/> &nbsp; <span style="font-size: 14px">作者: ${book.author}</span>
                        </td>
                        <td style="text-align: center">${book.salesAmount}</td>
                        <td style="text-align: center">${book.storeNumber}</td>
                        <td style="text-align: center">${book.price}</td>
                        <td style="text-align: center">
                            <c:choose>
                                <c:when test="${book.storeNumber == 0}">库存不足</c:when>
                                <c:otherwise>
                                    <a href="index?method=shoppingCart&cartAction=add&pageNum=${requestScope.books.currentPageNum}&bookID=${book.getBookId()}&bookTitle=${book.title}">加入购物车</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br>

            <%-- -------------------------分割线------------------------- --%>

            <form action="index?method=searchBooks&keyword=${param.keyword}" method="post">
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
                <a href="index?method=searchBooks">首页</a>
                &nbsp;&nbsp;
                <a href="index?method=searchBooks&pageNum=${requestScope.books.prevPage}">上一页</a>
                &nbsp;&nbsp;
            </c:if>

            <c:if test="${requestScope.books.hasNextPage}">
                <a href="index?method=searchBooks&pageNum=${requestScope.books.nextPage}">下一页</a>
                &nbsp;&nbsp;
                <a href="index?method=searchBooks&pageNum=${requestScope.books.totalPageNumber}">末页</a>
                &nbsp;&nbsp;
            </c:if>

            <c:if test="${requestScope.books.currentPageNum != 0}">
                跳转至第 <input type="text" size="1" class="pageNumber" style="text-align: center"/> 页&nbsp;&nbsp;
            </c:if>
        </c:otherwise>
    </c:choose>
</center>
</body>
</html>