<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
    <style>
        a {
            text-decoration: none;
        }
        table {
            text-align: center;
        }
    </style>
</head>
<body>
    <center>
        <br><br>

        <c:choose>
            <c:when test="${empty sessionScope.shoppingCart.books}">
                购物车已清空，<a href="index?method=getBooks&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">继续购物</a>
            </c:when>
            <c:otherwise>当前购物车共有${sessionScope.shoppingCart.bookNumber}本书
                <br><br>
                <table cellpadding="10" border="1px solid black" cellspacing="0">
                    <tr style="background-color: beige">
                        <th>书名</th>
                        <th>数量</th>
                        <th>价格</th>
                        <th>操作</th>
                    </tr>


                    <c:if test="${!empty sessionScope.shoppingCart.bookNumber}">
                        <c:forEach items="${sessionScope.shoppingCart.items}" var="item">
                            <tr>
                                <td><a href="index?method=shoppingCart&cartAction=bookInfo&bookID=${item.book.bookId}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">${item.book.title}</a></td>
                                <td style="padding: unset">
                                    <input type="text" size="2" value="${item.quantity}" style="text-align: center"/>
                                </td>
                                <td>${item.itemMoney}</td>
                                <td><a href="index?method=shoppingCart&cartAction=remove&bookID=${item.book.bookId}">移除</a></td>
                            </tr>
                        </c:forEach>
                    </c:if>

                    <tr>
                        <td colspan="4" style="background-color: #e6ecf4">总金额：¥ ${sessionScope.shoppingCart.totalMoney}</td>
                    </tr>

                </table>
                <br><br>

                <a href="index?method=getBooks&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">继续购物</a> &nbsp;&nbsp;
                <a href="index?method=shoppingCart&cartAction=clear">清空购物车</a> &nbsp;&nbsp;
                <a href="index?method=shoppingCart&cartAction=toPay">支付</a>
            </c:otherwise>
        </c:choose>
        <br><br>

    </center>
</body>
</html>
