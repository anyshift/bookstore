<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<html>
<head>
    <title>购物车</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/common/param.jsp" %>
    <script src="jquery/jquery-3.6.0.min.js"></script>
    <style>
        a {
            text-decoration: none;
        }
        table {
            text-align: center;
        }
    </style>
    <script type="text/javascript">
        $(function () {

            /* 在移除购物车某本书前，弹出确认框，只有点击确定才会执行后续操作 */
            $(".delete").click(function () {
                let $tr = $(this).parent().parent();
                let title = $.trim($tr.find("td:first").text());
                return confirm("确定要移除《" + title + "》吗?");
            })

            /* 在清空购物车前，弹出确认框，只有点击确定才会执行后续操作 */
            $(".clear").click(function () {
                return confirm("确定要清空购物车吗?");
            })

            /* 监听input值的变化情况，当输入值与原有值不一致时，弹出确认框。点击确定后使用Ajax异步修改。 */
            $(":text").change(function () {
                let quantityValue = $.trim(this.value);
                let flag1 = false;
                let reg = /^\d+$/g;
                let quantity = -1;

                if(reg.test(quantityValue)){
                    quantity = parseInt(quantityValue);
                    if(quantity >= 1){
                        flag1 = true;
                    }
                }

                if(!flag1){
                    alert("数量输入有误，请重新输入");
                    $(this).val($(this).attr("class"));
                    return;
                }

                let $tr = $(this).parent().parent();
                let title = $.trim($tr.find("td:first").text());

                let flag2 = confirm("确定要修改《" + title + "》的数量吗?");
                if(!flag2){
                    $(this).val($(this).attr("class")); //如果选择取消，则复原原有quantity
                    return;
                }

                /* http://localhost:8080/index?method=getBooks */
                let url = "index";
                let bookIdValue = $.trim(this.name); //input中的name值即为bookID
                let args = {"method":"updateQuantityWithAjax", "bookID":bookIdValue, "quantity":quantityValue, "time":new Date()};

                /* JQuery中的post方法介绍：https://blog.csdn.net/qq_42176520/article/details/86079231 */
                $.post(url, args, function(data){
                    let bookNumber = data.bookNumber;
                    let totalMoney = data.totalMoney;

                    $("#bookNumber").text("当前购物车共有 " + bookNumber + " 本书");
                    $("#totalMoney").text("总金额：¥ " + totalMoney.toFixed(1)); //保留一位小数
                },"JSON");
            })
        })
    </script>
</head>
<body>
    <center>
        <br><br>
        <c:choose>
            <c:when test="${empty sessionScope.shoppingCart.books}">
                购物车已清空，<a href="index?method=getBooks">继续购物</a>
            </c:when>
            <c:otherwise>
                <div id="bookNumber">当前购物车共有 ${sessionScope.shoppingCart.bookNumber} 本书</div>
                <br><br>
                <table cellpadding="10" border="1px solid black" cellspacing="0">
                    <tr style="background-color: beige">
                        <th>书名</th>
                        <th>数量</th>
                        <th>单价</th>
                        <th>操作</th>
                    </tr>

                    <c:if test="${!empty sessionScope.shoppingCart.bookNumber}">
                        <c:forEach items="${sessionScope.shoppingCart.items}" var="item">
                            <tr>
                                <td><a href="index?method=shoppingCart&cartAction=bookInfo&bookID=${item.book.bookId}">${item.book.title}</a></td>
                                <td style="padding: unset">
                                    <input type="text" class="${item.quantity}" size="2" name="${item.book.bookId}" value="${item.quantity}" style="text-align: center"/>
                                </td>
                                <td>${item.itemMoney}</td>
                                <td><a href="index?method=shoppingCart&cartAction=remove&bookID=${item.book.bookId}" class="delete">移除</a></td>
                            </tr>
                        </c:forEach>
                    </c:if>

                    <tr>
                        <td colspan="4" style="background-color: #e6ecf4" id="totalMoney">总金额：¥ ${sessionScope.shoppingCart.totalMoney}</td>
                    </tr>

                </table>
                <br><br>

                <a href="index?method=getBooks">继续购物</a> &nbsp;&nbsp;
                <a href="index?method=shoppingCart&cartAction=clear" class="clear">清空购物车</a> &nbsp;&nbsp;
                <a href="index?method=shoppingCart&cartAction=toPay">支付</a>
            </c:otherwise>
        </c:choose>
        <br><br>

    </center>
</body>
</html>
