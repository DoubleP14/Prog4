<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<my:page>
  <h1>Elérhető filmek</h1>

  <table border="1" cellpadding="8" cellspacing="0">
    <tr style="background-color: #e0e0e0;">
      <th>ID</th>
      <th>Cím</th>
      <th>Rendező</th>
      <th>Megjelenés éve</th>
      <th>Műfaj</th>
      <th>IMDb Értékelés</th>
    </tr>
    <c:forEach var="movie" items="${movies}">
      <tr>
        <td>${movie.id}</td>
        <td>${movie.title}</td>
        <td>${movie.directorName}</td>
        <td>${movie.releaseYear}</td>
        <td>${movie.genre}</td>
          <%-- Itt hívjuk a mi BIZTONSÁGOS rating tagünket! --%>
        <td><my:rating movieId="${movie.id}" /></td>
      </tr>
    </c:forEach>
  </table>
</my:page>