<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="clinic" uri="http://pte.hu/petclinic" %>

<clinic:layout>

  <h3>Pet list</h3>

  <table border="1" cellpadding="5" cellspacing="0">
    <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Chip code</th>
      <th>Address</th>
      <th>Species</th>
      <th>Global Identifier</th>
      <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${pets}">
      <tr>
        <td><c:out value="${p.id}"/></td>
        <td><c:out value="${p.name}"/></td>
        <td><c:out value="${p.chipCode}"/></td>
        <td><c:out value="${p.address}"/></td>
        <td><c:out value="${p.species}"/></td>

        <td>
          <clinic:globalId pet="${p}" />
        </td>

        <td>
          <form action="${pageContext.request.contextPath}/delete" method="POST" style="margin:0;">
            <input type="hidden" name="id" value="${p.id}">
            <button type="submit">Delete</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

</clinic:layout>