<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍详情</title>
    <script src="jquery/jquery-3.6.0.min.js"></script>
    <%@ include file="/common/common.jsp" %>
</head>
<body>

    <br><br><br><br>
    <center>
        <table cellpadding="10" border="1px solid black" cellspacing="0" style="text-align: center">
            <tr style="background-color: beige">
                <th>属性</th>
                <th>详情</th>
                <th>操作</th>
            </tr>
            <tr>
                <td style="background-color: beige">书名</td>
                <td>《${requestScope.book.title}》</td>
                <td rowspan="7"><a href="index?method=shoppingCart&cartAction=list&pageNum=${param.pageNum}" style="text-decoration: none;">返回购物车</a></td>
            </tr>
            <tr>
                <td style="background-color: beige">作者</td>
                <td>${requestScope.book.author}</td>
            </tr>
            <tr>
                <td style="background-color: beige">价格</td>
                <td>${requestScope.book.price}</td>
            </tr>
            <tr>
                <td style="background-color: beige">出版日期</td>
                <td>${requestScope.book.publishingDate}</td>
            </tr>
            <tr>
                <td style="background-color: beige">库存量</td>
                <td>
                    <c:choose>
                        <c:when test="${requestScope.book.storeNumber >= 100}">库存充足</c:when>
                        <c:otherwise><font color="red">仅剩${requestScope.book.storeNumber}本</font></c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td style="background-color: beige">销量</td>
                <td>${requestScope.book.salesAmount}</td>
            </tr>
            <tr>
                <td style="background-color: beige">备注</td>
                <td>${requestScope.book.remark}</td>
            </tr>
        </table>
    </center>
    <br><br>

</body>
</html>
