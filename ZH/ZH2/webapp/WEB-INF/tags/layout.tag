<%@ tag description="Oldal keret" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" %>
<%@ taglib prefix="my" uri="http://pte.hu/customtags" %>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<h1>Sziasztok!</h1>

<%-- Menü --%>
<a href="${pageContext.request.contextPath}/Food">Étel lista</a> |
<a href="${pageContext.request.contextPath}/AddFood">Új étel</a>
<hr>

<%-- Ide fog behelyettesítődni az adott oldal tartalma --%>
<jsp:doBody/>

<hr>
<%-- A feladat (6. pont) kifejezetten kérte: a kedvenc tag a tartalom alatt legyen! --%>
<my:favouriteFood />

</body>
</html>