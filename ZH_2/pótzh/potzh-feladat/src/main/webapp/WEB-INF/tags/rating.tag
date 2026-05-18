<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="movieId" required="true" type="java.lang.Long" %>
<%@ tag import="hu.pte.mik.prog4.potzh.service.MovieService" %>
<%
    // Példányosítjuk a Service-t és lekérjük az értékelést
    MovieService service = new MovieService();
    Double rating = service.getImdbRatingForMovie(movieId);

    // Kiírjuk a weboldalra
    if (rating != null) {
        out.print(rating);
    } else {
        out.print("N/A");
    }
%>