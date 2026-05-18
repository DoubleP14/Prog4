<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
  <title>Technológiai Cégek</title>
</head>

<body style="font-family: Arial, sans-serif; padding: 20px;">
<div style="margin-bottom: 20px;">
  <a href="${pageContext.request.contextPath}/saveCompany.jsp" style="margin-right: 20px;">Új cég rögzítése</a>
  <a href="${pageContext.request.contextPath}/logout" style="color: red;">Kijelentkezés</a>
</div>
<h2>Technológiai Cégek Listája</h2>

<table border="1" cellpadding="8" cellspacing="0">
  <tr style="background-color: #f2f2f2;">
    <th>ID</th>
    <th>Név</th>
    <th>Alapítási év</th>
    <th>Ismert termék</th>
    <th>Művelet</th>
  </tr>
  <c:forEach var="company" items="${companies}">
    <tr>
      <td>${company.id}</td>
      <td>${company.name}</td>
      <td>${company.foundationYear}</td>
      <td>${company.knownProduct}</td>
      <td>
          <%-- Ez a gomb újratölti az oldalt a kiválasztott ID-val --%>
        <a href="${pageContext.request.contextPath}/company-list?xmlId=${company.id}">
          <button>XML mutatása</button>
        </a>
      </td>
    </tr>
  </c:forEach>
</table>

<%-- Itt hívjuk meg a Tag Library-t, HA a felhasználó rákattintott egy gombra --%>
<c:if test="${not empty param.xmlId}">
  <my:companyXml companyId="${param.xmlId}" />
</c:if>

</body>
</html>