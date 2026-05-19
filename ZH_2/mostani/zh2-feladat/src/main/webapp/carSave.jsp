<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<t:page>
    <div>
        <form action="car-save" method="post">
            <table>
                <tr>
                    <td>Típus:</td>
                    <td><input name="type"/></td>
                </tr>
                <tr>
                    <td>Model:</td>
                    <td><input name="model"/></td>
                </tr>
                <tr>
                    <td>Gyártási év:</td>
                    <td><input name="productionYear"/></td>
                </tr>
                <tr>
                    <td>Lista ár:</td>
                    <td><input name="listPrice"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Mentés"/></td>
                    <td>
                        <button type="button" onclick="window.history.back()">Back</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</t:page>
