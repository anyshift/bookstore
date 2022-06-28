<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="/WEB-INF/view/jquery/jquery-3.6.0.min.js"></script>
    <title>购书网</title>
    <style>
        a { text-decoration: none; }
        table {
            border-collapse: collapse; /*为表格设置合并边框模型*/
            table-layout: fixed; /*列宽由表格宽度和列宽度设定*/
        }
        td {
            word-break: break-word; /*允许在单词内换行。*/
        }
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
        <br><br>
        <c:if test="${param.bookTitle != null}"> <%-- param是自带的，可以直接使用。用于访问URL中的参数 --%>
            已将《${param.bookTitle}》加入到购物车
            <br><br>
        </c:if>

        <c:if test="${!empty sessionScope.shoppingCart.books}">
            <%-- bookNumber是ShoppingCart对象中的getBookNumber()方法，读取bean方法，省去get。（JSP JavaBean） --%>
            当前购物车中共有 ${sessionScope.shoppingCart.bookNumber} 本书，<a href="index?method=shoppingCart&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">查看购物车</a>
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
                    <td style="width: 150px;">
                        <a href="index?method=getBook&bookID=${book.getBookId()}&pageNum=${requestScope.books.currentPageNum}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">《${book.title}》</a>
                        <br /> &nbsp; <span style="font-size: 14px">作者: ${book.author}</span>
                    </td>
                    <td style="text-align: center">${book.salesAmount}</td>
                    <td style="text-align: center">${book.storeNumber}</td>
                    <td style="text-align: center">${book.price}</td>
                    <td style="text-align: center">
                        <c:choose>
                            <c:when test="${book.storeNumber == 0}">库存不足</c:when>
                            <c:otherwise>
                                <a href="index?method=shoppingCart&cartAction=add&pageNum=${requestScope.books.currentPageNum}&bookID=${book.getBookId()}&bookTitle=${book.title}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">加入购物车</a>
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
                        最低价：<input type="text" name="minPrice" size="4" value="${param.minPrice}" style="text-align: center;">
                    </td>
                    <td rowspan="2">
                        <input type="submit" value="筛选" style="margin-left: 10px; padding: 12px 5px;">
                        <button style="margin-left: 10px; padding: 12px 5px;"><a href="index?method=shoppingCart">取消筛选</a></button>
                    </td>
                </tr>
                <tr>
                    <td>
                        最高价：<input type="text" name="maxPrice" size="4" value="${param.maxPrice}" style="text-align: center;">
                    </td>
                </tr>
            </table>
        </form>

        <%-- -------------------------分割线------------------------- --%>

        共 ${requestScope.books.getTotalPageNumber()} 页
        &nbsp;&nbsp;
        当前第 ${requestScope.books.currentPageNum} 页
        &nbsp;&nbsp;

        <c:if test="${requestScope.books.hasPrevPage}">
            <a href="index?method=getBooks&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">首页</a>
            &nbsp;&nbsp;
            <a href="index?method=getBooks&pageNum=${requestScope.books.prevPage}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">上一页</a>
            &nbsp;&nbsp;
        </c:if>

        <c:if test="${requestScope.books.hasNextPage}">
            <a href="index?method=getBooks&pageNum=${requestScope.books.nextPage}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">下一页</a>
            &nbsp;&nbsp;
            <a href="index?method=getBooks&pageNum=${requestScope.books.totalPageNumber}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">末页</a>
            &nbsp;&nbsp;
        </c:if>

        跳转至第 <input type="text" name="toPage" size="1" style="text-align: center"/> 页

    </center>
    <br><br>

</body>
</html>
