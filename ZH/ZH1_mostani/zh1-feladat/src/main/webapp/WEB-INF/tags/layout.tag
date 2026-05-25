<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>
<%@ attribute name="favorite" required="false" type="hu.pte.mik.prog4.zh1_2026.model.Product" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
</head>
<body>
<h2>Hello Gábor!</h2> <%-- Itt jön a konkrét oldal tartalma (lista vagy form) --%>
<jsp:doBody/>

<%-- Ide szúrjuk be a kedvenc taget a tartalom és a menü közé --%>
<t:favorite product="${favorite}" />

<hr>
<%-- Menü a tartalom alatt --%>
<nav>
    <a href="${pageContext.request.contextPath}/products">Termékek listázása</a> |
    <a href="${pageContext.request.contextPath}/product/create">Új termék rögzítése</a>
</nav>
</body>
</html>