<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="product" required="false" type="hu.pte.mik.prog4.zh1_2026.model.Product" %>

<div style="padding: 10px; background-color: #f8f9fa; border: 1px solid #ddd; margin: 10px 0;">
    <c:choose>
        <c:when test="${not empty product}">
            <strong>Aktuális kedvenc terméked:</strong> ${product.name} (${product.price})
        </c:when>
        <c:otherwise>
            Nincs kiválasztott kedvenc terméked.
        </c:otherwise>
    </c:choose>
</div>