<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<my:page>
  <div>
    <h2>Új Film Rögzítése</h2>
    <form action="save-movie" method="post">
      <table cellpadding="5">
        <tr>
          <td>Cím:</td>
          <td><input name="title" required/></td>
        </tr>
        <tr>
          <td>Rendező:</td>
          <td><input name="directorName" required/></td>
        </tr>
        <tr>
          <td>Kiadás éve:</td>
          <td><input type="number" name="releaseYear" required/></td>
        </tr>
        <tr>
          <td>Műfaj:</td>
          <td><input name="genre" required/></td>
        </tr>
        <tr>
          <td><input type="submit" value="Mentés"/></td>
          <td>
            <button type="button" onclick="window.history.back()">Vissza</button>
          </td>
        </tr>
      </table>
    </form>
  </div>
</my:page>