<%@ tag language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>PotZH Filmek</title>
</head>
<body>
<div style="background-color: #f0f0f0; padding: 15px; margin-bottom: 20px;">
    <h3>Navigáció</h3>
    <a href="${pageContext.request.contextPath}/index.jsp" style="margin-right: 15px;">Kezdőlap</a>
    <a href="${pageContext.request.contextPath}/movie-list" style="margin-right: 15px;">Film Lista</a>
    <a href="${pageContext.request.contextPath}/save-movie" style="margin-right: 15px;">Új Film</a>
    <a href="${pageContext.request.contextPath}/logout">Kijelentkezés</a>
</div>

<%-- Ide fog bekerülni az adott oldal érdemi része! --%>
<div style="padding: 10px;">
    <jsp:doBody/>
</div>
</body>
</html>