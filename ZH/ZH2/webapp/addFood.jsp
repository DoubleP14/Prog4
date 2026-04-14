<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Új étel rögzítése">

    <h2>Új étel felvitele</h2>

    <form action="${pageContext.request.contextPath}/AddFood" method="POST">
        <label>Étterem neve:</label><br>
        <input type="text" name="restaurant" required><br><br>

        <label>Étel neve:</label><br>
        <input type="text" name="name" required><br><br>

        <label>Ár (Ft):</label><br>
        <input type="number" name="price" required><br><br>

        <button type="submit">Mentés</button>
    </form>

</t:layout>