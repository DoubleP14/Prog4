<%@ tag description="Page Layout Template" pageEncoding="UTF-8" %>
<%@ taglib uri="http://hu.pte.mik/tags" prefix="my" %>
<%@ attribute name="pageTitle" required="true" %>

<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<h2>Welcome here!</h2>

<a href="list">List</a><br>
<a href="add.jsp">New</a>

<hr>

<%-- Ide kerül az adott oldal (pl. lista vagy űrlap) tartalma --%>
<jsp:doBody/>

<br>
<%-- A kedvenc tag a tartalom alatt: --%>
<my:favorite />

</body>
</html>