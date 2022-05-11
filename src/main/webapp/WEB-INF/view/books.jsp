<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="/WEB-INF/view/jquery/jquery-3.6.0.min.js"></script>
    <title>购书网</title>
    <style>
        a { text-decoration: none; }
    </style>
    <script type="text/javascript">
        $(function(){
            $("a").each(function(){
                this.onclick = function () {
                    alert(1);
                }
            });
        });
    </script>
</head>
    <input type="hidden" name="minPrice" value="${param.minPrice}"/>
    <input type="hidden" name="maxPrice" value="${param.maxPrice}"/>
<body>
    <center>

        <c:if test="${param.minPrice != null}">
            ${param.minPrice}
        </c:if>
        <c:if test="${param.maxPrice != null}">
            ${param.maxPrice}
            <br>
        </c:if>

        <br><br>
        <c:if test="${param.bookTitle != null}"> <%-- param是自带的，可以直接使用。用于访问URL中的参数 --%>
            已将《${param.bookTitle}》加入到购物车
            <br><br>
        </c:if>

        <c:if test="${!empty sessionScope.shoppingCart.books}">
            <%-- bookNumber是ShoppingCart对象中的getBookNumber()方法，读取bean方法，省去get。（JSP JavaBean） --%>
            当前购物车中共有 ${sessionScope.shoppingCart.bookNumber} 本书，<a href="index?method=shoppingCart">查看购物车</a>
        </c:if>
        <br><br>

        <%-- -------------------------分割线------------------------- --%>

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
                    <td>
                        <a href="index?method=getBook&bookID=${book.getBookId()}&pageNum=${requestScope.books.currentPageNum}">《${book.title}》</a>
                        <br /> &nbsp; <span style="font-size: 14px">作者: ${book.author}</span>
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

        <form action="index?method=getBooks" method="post">
            最低价：<input type="text" name="minPrice" size="4" style="text-align: center;"><br>
            最高价：<input type="text" name="maxPrice" size="4" style="text-align: center;"><br>
            <br><input type="submit" value="筛选">
        </form>
        <br>

        <%-- -------------------------分割线------------------------- --%>

        共 ${requestScope.books.getTotalPageNumber()} 页
        &nbsp;&nbsp;
        当前第 ${requestScope.books.currentPageNum} 页
        &nbsp;&nbsp;

        <c:if test="${requestScope.books.hasPrevPage}">
            <a href="index?method=getBooks">首页</a>
            &nbsp;&nbsp;
            <a href="index?method=getBooks&pageNum=${requestScope.books.prevPage}">上一页</a>
            &nbsp;&nbsp;
        </c:if>

        <c:if test="${requestScope.books.hasNextPage}">
            <a href="index?method=getBooks&pageNum=${requestScope.books.nextPage}&minPrice=${param.minPrice}">下一页</a>
            &nbsp;&nbsp;
            <a href="index?method=getBooks&pageNum=${requestScope.books.totalPageNumber}">末页</a>
            &nbsp;&nbsp;
        </c:if>

        跳转至第 <input type="text" name="toPage" size="1" style="text-align: center"/> 页

    </center>
    <br><br>

</body>
</html>
