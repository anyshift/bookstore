<script type="text/javascript">
    $(function () {
        $("a:not(.delete,.clear,.cancel-1,.cancel-2)").each(function () {
            this.onclick = function () {
                window.location.href = this.href + "&" + $(":hidden").serialize();
                return false;
            };
        });
    })
</script>

<input type="hidden" name="minPrice" value="${param.minPrice}"/>
<input type="hidden" name="maxPrice" value="${param.maxPrice}"/>

