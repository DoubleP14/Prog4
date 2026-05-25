<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Új termék rögzítése">
  <h3>Új termék rögzítése</h3>
  <form action="${pageContext.request.contextPath}/product/create" method="post">
    <label>Név:</label><br>
    <input type="text" name="name" required><br><br>

    <label>Ár:</label><br>
    <input type="text" name="price" required><br><br>

    <label>Leírás:</label><br>
    <textarea name="description" required minlength="10" placeholder="Minimum 10 karakter..."></textarea><br><br>

    <button type="submit">Küldés</button>
  </form>
</t:layout>