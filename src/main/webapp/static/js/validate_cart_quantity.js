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
    $(":text:not(.search)").change(function () {
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