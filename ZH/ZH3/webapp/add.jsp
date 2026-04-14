<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="clinic" uri="http://pte.hu/petclinic" %>

<clinic:layout>

  <h3>Add new pet</h3>

  <form action="${pageContext.request.contextPath}/add" method="POST">
    <table cellpadding="3">
      <tr>
        <td><label for="name">Name:</label></td>
        <td><input type="text" id="name" name="name" required></td>
      </tr>
      <tr>
        <td><label for="chipCode">Chip code:</label></td>
        <td><input type="text" id="chipCode" name="chipCode" required></td>
      </tr>
      <tr>
        <td><label for="address">Address:</label></td>
        <td><input type="text" id="address" name="address" required></td>
      </tr>
      <tr>
        <td><label for="species">Species:</label></td>
        <td><input type="text" id="species" name="species" required></td>
      </tr>
    </table>
    <br>
    <button type="submit">Save</button>
    <button type="button" onclick="window.location.href='list'">Back</button>
  </form>

</clinic:layout>