<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<t:page>
    <h2>Új állat rögzítése</h2>

    <c:if test="${not empty message}">
        <p style="color: green; font-weight: bold;">${message}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/save-pet" method="post">
        Név: <input type="text" name="petName" required><br><br>
        Fajta (species): <input type="text" name="species" required><br><br>
        Kor: <input type="number" name="age" required><br><br>
        Gazda neve: <input type="text" name="ownerName" required><br><br>
        <button type="submit">Mentés</button>
    </form>
</t:page>