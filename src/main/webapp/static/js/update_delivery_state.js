$(function () {
    $(":text:not(.serialNumber,.search,.userName, #balance)").change(function () {
        let deliveryStateValue = $.trim(this.value);
        let state_1 = "已下单";
        let state_2 = "已发货";
        let state_3 = "已完成";

        if (deliveryStateValue !== '已下单' && deliveryStateValue !== '已发货' && deliveryStateValue !== '已完成') {
            alert("只能为：已下单、已发货、已完成");
            $(this).val($(this).attr("class"));
            return;
        }

        let serialNumber = $("#serial-span").text();

        let flag = confirm("确定要修改订单编号为（" + serialNumber + "）的订单状态吗?");
        if(!flag){
            $(this).val($(this).attr("class")); //如果选择取消，则复原原有balance
            return;
        }

        /* http://localhost:8080/user?method=updateDeleveryStateWithAJAX */
        let url = "user";
        let args = {"method":"updateDeleveryStateWithAJAX", "serialNumber":serialNumber, "deliveryState":deliveryStateValue, "time":new Date()};

        /* JQuery中的post方法介绍：https://blog.csdn.net/qq_42176520/article/details/86079231 */
        $.post(url, args, function(data){
            let state = data.deliveryState;
            $("#state").val(state);
        },"JSON");

        alert("修改成功");
    });

    $(".cancel-search-order").click(function () {
        $("input[name=orderSerialNumber]").val("");
    });
})