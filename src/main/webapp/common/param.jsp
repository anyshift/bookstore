<script type="text/javascript">
    $(function(){
        $("a:not(.delete)").each(function(){
            this.onclick = function(){
                let serializeVal = $(":hidden").serialize();
                window.location.href = this.href + "&" + serializeVal;
                return false;
            };
        });
    });
</script>

<input type="hidden" name="minPrice" value="${param.minPrice}"/>
<input type="hidden" name="maxPrice" value="${param.maxPrice}"/>
