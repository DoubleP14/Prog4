package hu.pte.mik.gyakzh1.tag;

import hu.pte.mik.gyakzh1.model.Phone;
import hu.pte.mik.gyakzh1.service.PhoneService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class FavoriteTag extends SimpleTagSupport {

    private final PhoneService phoneService = new PhoneService();

    @Override
    public void doTag() throws JspException, IOException {
        // A tanár által adott segítség a Request megszerzéséhez:
        PageContext context = (PageContext) this.getJspContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();

        String favoriteId = null;

        // Megkeressük a sütik között a "favoritePhoneId" nevűt
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if ("favoritePhoneId".equals(c.getName())) {
                    favoriteId = c.getValue();
                    break;
                }
            }
        }

        // Ha találtunk sütit, kiírjuk a telefont, különben az alap szöveget
        if (favoriteId != null && !favoriteId.isEmpty()) {
            Phone phone = phoneService.findById(Long.parseLong(favoriteId));
            if (phone != null) {
                context.getOut().print("Your favorite phone is: " + phone.getManufacturer() +
                        " " + phone.getType() + " (" + phone.getImei() + ")");
                return; // Kilépünk, hogy ne írja ki a másik szöveget is
            }
        }

        context.getOut().print("You do not have a favorite phone yet.");
    }
}