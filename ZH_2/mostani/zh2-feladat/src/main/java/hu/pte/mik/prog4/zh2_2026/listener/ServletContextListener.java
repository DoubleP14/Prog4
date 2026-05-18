package hu.pte.mik.prog4.zh2_2026.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;
import java.net.URL;

@WebListener
public class ServletContextListener implements jakarta.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // Megkeressük a jaas.config fájlt a resources mappából
            URL jaasConfigUrl = getClass().getClassLoader().getResource("jaas.config");
            if (jaasConfigUrl != null) {
                System.setProperty("java.security.auth.login.config", jaasConfigUrl.toExternalForm());
                System.out.println("=== JAAS CONFIG SIKERESEN BETÖLTVE ===");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}