<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Index</title>
</head>
<body>
    <%
        response.sendRedirect(request.getContextPath() + "/index?method=getBooks");
    %>
</body>
</html>