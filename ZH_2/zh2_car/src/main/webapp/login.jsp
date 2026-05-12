<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:page>
    <form method="post" action="j_security_check">
        <p>
            <span>Username:</span><br/><input type="text" name="j_username"/>
        </p>
        <p>
            <span>Password:</span><br/><input type="password" name="j_password"/>
        </p>
        <p>
            <input type="submit" value="Login"/>
        </p>
    </form>

    <br/>
    <p>
        <a href="${pageContext.request.contextPath}/register.jsp">Nincs még fiókod? Regisztrálj itt!</a>
    </p>
</t:page>