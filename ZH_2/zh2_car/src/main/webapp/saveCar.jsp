<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<t:page>
    <div>
        <form action="save-car" method="post">
            <table>
                <tr>
                    <td>Manufacturer:</td>
                    <td><input name="manufacturer"/></td>
                </tr>
                <tr>
                    <td>Type:</td>
                    <td><input name="type"/></td>
                </tr>
                <tr>
                    <td>License plate:</td>
                    <td><input name="licensePlate"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Save"/></td>
                    <td>
                        <button type="button" onclick="window.history.back()">Back</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</t:page>
