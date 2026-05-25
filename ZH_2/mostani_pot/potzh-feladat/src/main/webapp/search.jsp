<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<t:page>
    <h2>Állat Keresése</h2>

    <c:if test="${not empty recentPet}">
        <t:recent pet="${recentPet}" />
    </c:if>

    <c:if test="${not empty error}">
        <p style="color: red; font-weight: bold;">${error}</p>
    </c:if>
    <c:if test="${not empty message}">
        <p style="color: blue; font-weight: bold;">${message}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/search-pet" method="get" style="margin-bottom: 20px;">
        Azonosító (ID): <input type="text" name="id">
        <button type="submit">Lekérdezés</button>
    </form>

    <form action="${pageContext.request.contextPath}/recent-pet" method="get">
        <button type="submit">Legutóbb lekérdezett</button>
    </form>

    <c:if test="${not empty pet}">
        <hr>
        <h3>Keresési eredmény:</h3>
        <table border="1" cellpadding="5">
            <tr>
                <th>ID</th><th>Név</th><th>Fajta</th><th>Kor</th><th>Gazda</th>
            </tr>
            <tr>
                <td>${pet.id}</td>
                <td>${pet.petName}</td>
                <td>${pet.species}</td>
                <td>${pet.age}</td>
                <td>${pet.ownerName}</td>
            </tr>
        </table>
    </c:if>
</t:page>