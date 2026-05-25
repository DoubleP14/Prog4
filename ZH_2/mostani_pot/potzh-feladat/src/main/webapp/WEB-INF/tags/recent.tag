<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="pet" required="true" type="hu.pte.mik.prog4.potzh_2026.entity.PetEntity" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<c:if test="${not empty pet}">
    <div style="background-color: #e6f2ff; padding: 15px; border: 1px solid #0066cc; margin-bottom: 20px;">
        <h3>Legutóbb lekérdezett állat adatai:</h3>
        <p><strong>Azonosító:</strong> ${pet.id}</p>
        <p><strong>Név:</strong> ${pet.petName}</p>
        <p><strong>Fajta:</strong> ${pet.species}</p>
        <p><strong>Kor:</strong> ${pet.age} év</p>
        <p><strong>Gazda:</strong> ${pet.ownerName}</p>
    </div>
</c:if>