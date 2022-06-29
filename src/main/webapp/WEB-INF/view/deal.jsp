<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>交易前台</title>
    <script src="jquery/jquery-3.6.0.min.js"></script>
    <%@ include file="/common/param.jsp" %>
</head>
<body>

    <br><br>
    <center>

        <c:if test="${!empty sessionScope.shoppingCart.bookNumber}">
            一共添加 ${sessionScope.shoppingCart.bookNumber} 本书到购物车 <br><br>
            应付 ¥ ${sessionScope.shoppingCart.totalMoney} <br><br>

            <c:if test="${requestScope.errorInfo != null}">
                <p style="color: red;">${requestScope.errorInfo}</p>
                <br>
            </c:if>


            <form action="index?method=shoppingCart&cartAction=pay" method="post">

                <table cellpadding="10" border="1px solid black" cellspacing="0">
                    <tr>
                        <td style="background-color: beige">信用卡姓名:</td>
                        <td><input type="text" name="userName"/></td>
                    </tr>
                    <tr>
                        <td style="background-color: beige">信用卡ID:</td>
                        <td><input type="text" name="accountID"/></td>
                    </tr>
                    <tr>
                        <td style="background-color: #e6ecf4"><button style="width: 90%; margin-left: 0.3em;"><a href="index?method=shoppingCart" style="text-decoration: none; color: black">取消支付</a></button></td>
                        <td style="background-color: #e6ecf4"><input type="submit" value="支付" style="width: 100%"/></td>
                    </tr>
                </table>

            </form>
        </c:if>

    </center>

</body>
</html>
