package hu.pte.mik.gyakzh1.tag;

import hu.pte.mik.gyakzh1.model.Food;
import hu.pte.mik.gyakzh1.service.FoodService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Stream;

public class FavouriteTag extends SimpleTagSupport {
    private final FoodService foodService = new FoodService();

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        var context = (PageContext) getJspContext();
        var request = (HttpServletRequest) context.getRequest();

        // Haladó Stream API a Süti (Cookie) megkeresésére
        var favourite = Optional.ofNullable(request.getCookies())
                .map(Stream::of)
                .orElse(Stream.empty())
                .filter(cookie -> cookie.getName().equals("favouriteid"))
                .findFirst()
                .map(cookie -> URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8))
                .orElse(null);

        // Ha van süti, megkeressük az ételt és kiírjuk a nevét
        if (favourite != null) {
            Long longId = Long.parseLong(favourite);
            Food favouriteFood = foodService.findById(longId);
            if (favouriteFood != null) {
                out.println("A kedvenc ételed a következő: " + favouriteFood.getFoodName() + " (" + favouriteFood.getRestaurantName() + ")");
            } else {
                out.println("A kedvenc ételed sajnos már nem elérhető.");
            }
        }
        else{
            out.println("Még nincs kedvenc ételed!");
        }
    }
}
