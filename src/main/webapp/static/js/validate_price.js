$(function () {
    $("#minPrice").change(function () {
        let value = $(this).val();
        value = $.trim(value);

        let minPrice = -1;
        let flag = false;
        let reg = /[^\d.]/g;

        if (reg.test(value)) {
            minPrice = parseInt(value);
            if (minPrice > 0) {
                minPrice = true;
            }
        }

        if (minPrice) {
            $(this).val($(this).attr(minPrice));
        } else {
            alert("价格输入有误，请重新输入");
            $(this).val($(this).attr(""));
        }
    });

    $("#maxPrice").change(function () {
        let value = $(this).val();
        value = $.trim(value);

        let maxPrice = -1;
        let flag = false;
        let reg = /[^\d.]/g;

        if (reg.test(value)) {
            maxPrice = parseInt(value);
            if (maxPrice > 0) {
                maxPrice = true;
            }
        }

        if (maxPrice) {
            $(this).val($(this).attr(maxPrice));
        } else {
            alert("价格输入有误，请重新输入");
            $(this).val($(this).attr(""));
        }
    });
})