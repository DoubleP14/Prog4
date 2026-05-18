<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<t:page>
    <style type="text/css">
        td {
            border: 1px solid black;
        }
    </style>
    <div>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Típus</th>
                <th>Model</th>
                <th>Gyártási év</th>
                <th>Lista ár</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cars}" var="car">
                <tr>
                    <td><c:out value="${car.id}"/></td>
                    <td><c:out value="${car.type}"/></td>
                    <td><c:out value="${car.model}"/></td>
                    <td><c:out value="${car.productionYear}"/></td>
                    <td><c:out value="${car.listPrice}"/></td>
                    <td>
                        <button type="button" onclick="loadExtraData('${car.id}')">Egyéb adatok</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <script type="text/javascript">
        function loadExtraData(carId) {
            // Meghívjuk a REST endpointunkat (az /api az alapértelmezett Jersey mapping)
            fetch('${pageContext.request.contextPath}/api/cars/' + carId + '/extra')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Hiba történt!');
                    }
                    return response.json();
                })
                .then(data => {
                    // Sikeres hívás esetén string formátumban jelenítjük meg a JSON-t az ablakban
                    alert(JSON.stringify(data));
                })
                .catch(error => {
                    // Bármilyen hiba (pl. leállt a 8081-es szerver) esetén ezt dobja
                    alert('Hiba történt!');
                });
        }
    </script>
</t:page>
