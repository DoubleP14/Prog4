<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout pageTitle="Phone List">

    <h3>Rendszerben tárolt telefonok</h3>

    <table border="1" cellpadding="5" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th>Manufacturer</th>
            <th>Type</th>
            <th>IMEI</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="phone" items="${phones}">
            <%-- Sárga háttér, ha az ID megegyezik a sütiben tárolttal, amúgy fehér --%>
            <tr style="background-color: ${cookie.favoritePhoneId.value == phone.id ? 'yellow' : 'white'};">
                <td>${phone.id}</td>
                <td>${phone.manufacturer}</td>
                <td>${phone.type}</td>
                <td>${phone.imei}</td>
                <td>
                    <a href="delete?id=${phone.id}">Delete</a> |
                    <a href="favorite?id=${phone.id}">Favorite</a> |
                    <a href="update?id=${phone.id}">Update</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</t:layout>