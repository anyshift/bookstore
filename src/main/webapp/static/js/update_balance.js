$(function () {
    $(":text:not(.serialNumber,.search,.userName, #state)").change(function () {
        let balanceValue = $.trim(this.value);
        let flag1 = false;
        let reg = /^\d+$/g;
        let balance = -1;

        if(reg.test(balanceValue)) {
            balance = parseInt(balanceValue);
            if (balance >= 0) {
                flag1 = true;
            }
        }

        if(!flag1){
            alert("输入有误，请重新输入");
            $(this).val($(this).attr("class"));
            return;
        }

        let userName = $("#username-span").text();

        let flag2 = confirm("确定要修改" + userName + "的余额吗?");
        if(!flag2){
            $(this).val($(this).attr("class")); //如果选择取消，则复原原有balance
            return
        }

        /* http://localhost:8080/user?method=updateBalanceWithAJAX */
        let url = "user";
        let args = {"method":"updateBalanceWithAJAX", "userName":userName, "balance":balanceValue, "time":new Date()};

        /* JQuery中的post方法介绍：https://blog.csdn.net/qq_42176520/article/details/86079231 */
        $.post(url, args, function(data){
            let balance = data.balance;
            $("#balance").val(balance);
        },"JSON");

        alert("修改成功");
    });

    $(".cancel-search-user").click(function () {
        $("input[name=userName]").val("");
    });
})