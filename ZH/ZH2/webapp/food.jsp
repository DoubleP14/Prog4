<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%-- Itt hívjuk meg a keretet és adjuk meg a fül nevét (4. feladat) --%>
<t:layout title="Ételek Listája">

    <h2>Rendszerben tárolt ételek</h2>

    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Étterem</th>
            <th>Étel neve</th>
            <th>Ár (Ft)</th>
            <th>Műveletek</th>
        </tr>

        <c:forEach var="f" items="${model}">
            <tr>
                <td><c:out value="${f.id}"/></td>
                <td><c:out value="${f.restaurantName}"/></td>
                <td><c:out value="${f.foodName}"/></td>
                <td><c:out value="${f.price}"/></td>
                <td>
                        <%-- Itt van a Törlés, az XML és a Kedvenc gomb is! --%>
                    <a href="${pageContext.request.contextPath}/Food?deleteid=${f.id}">Törlés</a> |
                    <a href="${pageContext.request.contextPath}/Food?xmlid=${f.id}">XML log</a> |

                    <form action="${pageContext.request.contextPath}/Food" method="POST" style="display:inline;">
                        <input type="hidden" name="favouriteid" value="${f.id}">
                        <button type="submit" style="background:none; border:none; color:blue; text-decoration:underline; cursor:pointer;">Kedvenc</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

</t:layout>