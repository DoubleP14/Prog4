<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="companyId" required="true" type="java.lang.Long" %>

<%-- Java kód a tag-ben: lekérjük a céget az ID alapján --%>
<%
    hu.pte.mik.prog4.potpotzh.service.CompanyService service = new hu.pte.mik.prog4.potpotzh.service.CompanyService();
    hu.pte.mik.prog4.potpotzh.entity.CompanyEntity company = service.findById(companyId);
    request.setAttribute("xmlCompany", company);
%>

<%-- Ha találtunk céget, kiírjuk az adatait XML formátumban --%>
<%-- (A < és > jeleket &lt; és &gt; -re cseréljük, hogy a böngésző ne HTML tag-nek higgye őket!) --%>
<div style="background-color: #2b2b2b; color: #a9b7c6; padding: 15px; border-radius: 5px; margin-top: 20px;">
    <h4>Generált XML adat:</h4>
    <pre>
&lt;company&gt;
    &lt;id&gt;${xmlCompany.id}&lt;/id&gt;
    &lt;name&gt;${xmlCompany.name}&lt;/name&gt;
    &lt;foundationYear&gt;${xmlCompany.foundationYear}&lt;/foundationYear&gt;
    &lt;country&gt;${xmlCompany.country}&lt;/country&gt;
    &lt;knownProduct&gt;${xmlCompany.knownProduct}&lt;/knownProduct&gt;
&lt;/company&gt;
</pre>
</div>