<%@ tag language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Kisállat Nyilvántartó</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .nav { background-color: #eee; padding: 10px; margin-bottom: 20px; border-radius: 5px; }
        .nav a { margin-right: 15px; text-decoration: none; color: #333; font-weight: bold; }
        .nav a:hover { color: #0066cc; }
        .content { padding: 10px; }
    </style>
</head>
<body>
<div class="nav">
    <a href="${pageContext.request.contextPath}/index.jsp">Kezdőlap</a>
    <a href="${pageContext.request.contextPath}/save.jsp">Új Állat Rögzítése</a>
    <a href="${pageContext.request.contextPath}/search.jsp">Keresés</a>
    <a href="${pageContext.request.contextPath}/logout">Kijelentkezés</a>
</div>

<div class="content">
    <%-- Ide kerül majd a többi JSP oldal tartalma --%>
    <jsp:doBody/>
</div>
</body>
</html>