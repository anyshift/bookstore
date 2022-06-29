<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 本页面用于给所有 a 标签添加参数minPrice和maxPrice，因所有页面都需要，所以单独抽出来 --%>

<script src="jquery/jquery-3.6.0.min.js"></script>
<script>
    $(function () {
        $("a").each(function () {
            this.onclick = function () {
                window.location.href = this.href + "&" + $(":hidden").serialize();
                return false;
            };
        });
    })
</script>

<input type="hidden" name="minPrice" value="${param.minPrice}"/>
<input type="hidden" name="maxPrice" value="${param.maxPrice}"/>
