package hu.pte.mik.zhgyak3.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;

public class LayoutTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        // 1. A HTML keret és a fejléc kiírása
        out.println("<html>");
        out.println("<head><title>Pet Clinic</title></head>");
        out.println("<body>");

        out.println("<h2>Welcome here!</h2>");
        out.println("<h3>Menu</h3>");
        out.println("<ul>");
        // A linkek a context path-ra mutatnak majd (ezeket később bekötjük a Servletekre)
        out.println("<li><a href='add.jsp'>Add new pet</a></li>");
        out.println("<li><a href='list'>Pet list</a></li>");
        out.println("</ul>");
        out.println("<hr>"); // Egy elválasztó vonal

        // 2. A BEÁGYAZOTT TARTALOM MEGHÍVÁSA
        // Ez a sor mondja meg, hogy ami a JSP-ben a nyitó és záró tag között van, azt ide írja be!
        if (getJspBody() != null) {
            getJspBody().invoke(out);
        }

        // 3. A HTML lezárása
        out.println("</body>");
        out.println("</html>");
    }
}