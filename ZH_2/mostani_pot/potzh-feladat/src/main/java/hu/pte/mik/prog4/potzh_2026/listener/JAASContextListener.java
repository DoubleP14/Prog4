package hu.pte.mik.prog4.potzh_2026.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.net.URL;

@WebListener
public class JAASContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        URL url = this.getClass().getClassLoader().getResource("jaas.config");
        if (url != null) {
            System.setProperty("java.security.auth.login.config", url.getFile());
            System.out.println("=== JAAS config betöltve! ===");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.clearProperty("java.security.auth.login.config");
    }
}