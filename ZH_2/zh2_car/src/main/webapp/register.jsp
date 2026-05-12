<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:page>
  <h2>Regisztráció</h2>

  <c:if test="${not empty error}">
    <p style="color: red;"><b>${error}</b></p>
  </c:if>

  <form method="post" action="${pageContext.request.contextPath}/register">
    <p>
      <span>Felhasználónév:</span><br/>
      <input type="text" name="username" required/>
    </p>
    <p>
      <span>Jelszó:</span><br/>
      <input type="password" name="password" required/>
    </p>
    <p>
      <input type="submit" value="Regisztrálok!"/>
    </p>
  </form>

  <br/>
  <p><a href="${pageContext.request.contextPath}/login.jsp">Mégis inkább bejelentkezek</a></p>
</t:page>