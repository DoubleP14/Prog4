package hu.pte.mik.prog4.potzh.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;
// FIGYELJ: Töröltük a jakarta.servlet.ServletContextListener importot!

@WebListener
public class ServletContextListener implements jakarta.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String jaasPath = sce.getServletContext().getRealPath("/WEB-INF/classes/jaas.config");
        System.setProperty("java.security.auth.login.config", jaasPath);
        System.out.println("=== JAAS config beállítva innen: " + jaasPath + " ===");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.clearProperty("java.security.auth.login.config");
    }
}