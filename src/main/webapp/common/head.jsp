<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String basePath = request.getScheme()
          + "://"
          + request.getServerName()
          + ":"
          + request.getServerPort()
          + request.getContextPath()
          + "/";

  pageContext.setAttribute("basePath",basePath);
%>

<base href="<%=basePath%>">
<script src="static/jquery/jquery-3.6.0.min.js"></script>
