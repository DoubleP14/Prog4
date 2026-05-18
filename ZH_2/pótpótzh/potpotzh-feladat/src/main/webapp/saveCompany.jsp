<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Új Cég Felvétele</title>
</head>
<body style="font-family: Arial; padding: 20px;">
<h2>Új Technológiai Cég Rögzítése</h2>

<form action="${pageContext.request.contextPath}/company-save" method="post">
    <label>Cég neve:</label><br>
    <input type="text" name="name" required><br><br>

    <label>Alapítási év:</label><br>
    <input type="number" name="foundationYear" required><br><br>

    <label>Ország:</label><br>
    <input type="text" name="country" required><br><br>

    <label>Ismert termék:</label><br>
    <input type="text" name="knownProduct" required><br><br>

    <button type="submit">Mentés</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/company-list">Vissza a listához</a>
</body>
</html>