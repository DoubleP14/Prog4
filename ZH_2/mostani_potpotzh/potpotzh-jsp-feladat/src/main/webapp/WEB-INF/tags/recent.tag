<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="hallgatoLista" type="java.util.List" required="true" %>
<%@ tag import="hu.pte.mik.prog4.potpotzh_2026.ws.client.Hallgato" %>
<%
    // Kikeressük a listából a legjobb átlagot egy kis scriptlet segítségével
    Hallgato best = null;
    if (hallgatoLista != null && !hallgatoLista.isEmpty()) {
        for (Object obj : hallgatoLista) {
            Hallgato h = (Hallgato) obj;
            if (best == null || h.getEgyetemiAtlag() > best.getEgyetemiAtlag()) {
                best = h;
            }
        }
    }
    if (best != null) {
        out.print("<b>" + best.getHallgatoNev() + "</b> (" + best.getSzak() + ") - Átlag: " + best.getEgyetemiAtlag());
    } else {
        out.print("Nincs adat.");
    }
%>