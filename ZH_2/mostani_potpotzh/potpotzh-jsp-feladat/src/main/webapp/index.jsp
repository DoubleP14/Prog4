<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
  <title>Hallgatók Listája</title>
</head>
<body>
<h2>Hallgatók Listája</h2>

<table border="1">
  <tr>
    <th>ID</th>
    <th>Név</th>
    <th>Szak</th>
    <th>Félév</th>
    <th>Átlag</th>
    <th>Műveletek</th>
  </tr>
  <c:forEach var="h" items="${hallgatok}">
    <tr>
      <td>${h.id}</td>
      <td>${h.hallgatoNev}</td>
      <td>${h.szak}</td>
      <td>${h.felev}</td>
      <td>${h.egyetemiAtlag}</td>
      <td>
        <form action="XmlServlet" method="POST" style="display:inline;">
          <input type="hidden" name="id" value="${h.id}">
          <button type="submit">XML</button>
        </form>

        <form action="DeleteServlet" method="POST" style="display:inline;">
          <input type="hidden" name="id" value="${h.id}">
          <button type="submit" onclick="return confirm('Biztosan törlöd?');">Törlés</button>
        </form>
      </td>
    </tr>
  </c:forEach>
</table>

<br>
<h3>Legjobb átlaggal rendelkező hallgató:</h3>
<t:recent hallgatoLista="${hallgatok}" />

<t:page />
</body>
</html>