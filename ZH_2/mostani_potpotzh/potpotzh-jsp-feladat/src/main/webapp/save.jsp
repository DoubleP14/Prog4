<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
  <title>Új hallgató rögzítése</title>
</head>
<body>
<h2>Új hallgató rögzítése</h2>

<form action="SaveServlet" method="POST">
  Név: <input type="text" name="nev" required><br><br>
  Szak: <input type="text" name="szak" required><br><br>
  Félév: <input type="number" name="felev" required><br><br>
  Átlag: <input type="number" step="0.01" name="atlag" required><br><br>
  <button type="submit">Mentés</button>
</form>

<t:page />
</body>
</html>