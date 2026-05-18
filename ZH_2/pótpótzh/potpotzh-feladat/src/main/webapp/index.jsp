<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Technológiai Cégek - Kezdőlap</title>
</head>
<body style="font-family: Arial, sans-serif; text-align: center; margin-top: 50px;">

<h1>Üdvözöljük a Technológiai Cégek Rendszerében!</h1>

<p style="font-size: 18px; color: #555;">
    A funkciók eléréséhez és a listázáshoz be kell jelentkeznie.
</p>

<br>

<%-- Ez a gomb átvisz a védett oldalra, ami azonnal kiváltja a JAAS bejelentkezést --%>
<a href="${pageContext.request.contextPath}/company-list">
    <button style="padding: 10px 20px; font-size: 16px; cursor: pointer;">
        Tovább a bejelentkezéshez
    </button>
</a>

</body>
</html>