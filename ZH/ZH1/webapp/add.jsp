<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout pageTitle="Új telefon">

  <h3>Manufacturer:</h3>
  <form action="add" method="POST">
    <input type="hidden" name="id" value="${phone != null ? phone.id : ''}">
    <input type="text" name="manufacturer" value="${phone != null ? phone.manufacturer : ''}" required><br>

    <h3>Type:</h3>
    <input type="text" name="type" value="${phone != null ? phone.type : ''}" required><br>

    <h3>IMEI:</h3>
    <input type="text" name="imei" value="${phone != null ? phone.imei : ''}" required><br><br>

    <button type="submit">${phone != null ? 'Update phone' : 'Create phone'}</button>
  </form>

</t:layout>