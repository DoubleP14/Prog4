<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Termékek listája" favorite="${favoriteProduct}">
  <h3>Termékek listája</h3>
  <table border="1" cellpadding="5">
    <tr>
      <th>Azonosító</th><th>Név</th><th>Ár</th><th>Leírás</th><th colspan="3">Műveletek</th>
    </tr>
    <c:forEach var="p" items="${products}">
      <tr>
        <td>${p.id}</td>
        <td>${p.name}</td>
        <td>${p.price}</td>
        <td>${p.description}</td>
        <td>
          <form action="${pageContext.request.contextPath}/product/action" method="post" style="display:inline;">
            <input type="hidden" name="id" value="${p.id}">
            <c:choose>
              <c:when test="${not empty favoriteProduct and favoriteProduct.id == p.id}">
                <button type="submit" name="action" value="removeFavorite">Kedvenc eltávolítása</button>
              </c:when>
              <c:otherwise>
                <button type="submit" name="action" value="markFavorite">Kedvencnek jelölés</button>
              </c:otherwise>
            </c:choose>
          </form>
        </td>
        <td>
          <c:if test="${empty favoriteProduct or favoriteProduct.id != p.id}">
            <form action="${pageContext.request.contextPath}/product/action" method="post" style="display:inline;">
              <input type="hidden" name="id" value="${p.id}">
              <button type="submit" name="action" value="delete">Törlés</button>
            </form>
          </c:if>
        </td>
        <td>
          <form action="${pageContext.request.contextPath}/product/action" method="post" style="display:inline;">
            <input type="hidden" name="id" value="${p.id}">
            <button type="submit" name="action" value="exportXml">XML Export</button>
          </form>
        </td>
      </tr>
    </c:forEach>
  </table>
</t:layout>