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
    <script>
        // JAVÍTÁS: A függvény most már a rendszámot várja paraméterként
        function getKM(licensePlate) {
            // 1. Példányosítjuk a kérést
            var request = new XMLHttpRequest();

            // 2. POST kérésre állítjuk az 5. feladat miatt
            request.open('POST', '${pageContext.request.contextPath}/api/getcarkm');
            request.setRequestHeader('Content-Type', 'application/json');

            request.onloadend = function () {
                if (request.status === 200) {
                    // 3. Szabályosan feldolgozzuk a kapott JSON-t
                    var responseData = JSON.parse(request.responseText);
                    window.alert("The sum KM: " + responseData.km);
                } else {
                    // 4. Hiba esetén a PDF-ben kért szöveg jelenik meg
                    window.alert('Some error occurred!');
                }
            }

            // 5. Elküldjük a rendszámot JSON formátumban a szervernek
            request.send(JSON.stringify({
                "licensePlate": licensePlate
            }));
        }
    </script>
    <div>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Manufacturer</th>
                <th>Type</th>
                <th>License plate</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cars}" var="car">
                <tr>
                    <td><c:out value="${car.id}"/></td>
                    <td><c:out value="${car.manufacturer}"/></td>
                    <td><c:out value="${car.type}"/></td>
                    <td><c:out value="${car.licensePlate}"/></td>
                    <td>
                        <button type="button"
                                onclick="getKM('${car.licensePlate}')">
                            KM
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</t:page>