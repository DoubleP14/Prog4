package hu.pte.mik.prog4.potpotzh_2026.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.net.URL;

@WebListener
public class JAASContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Megkeressük a jaas.config fájlt, amit a src/main/resources mappába tettél
        URL resource = Thread.currentThread().getContextClassLoader().getResource("jaas.config");

        if (resource != null) {
            // Beállítjuk a rendszer szintű property-t, hogy a JAAS ezt használja
            System.setProperty("java.security.auth.login.config", resource.toExternalForm());
            System.out.println("JAAS config sikeresen betöltve: " + resource.toExternalForm());
        } else {
            System.err.println("Kritikus hiba: Nem található a jaas.config fájl!");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Kikapcsoláskor töröljük a beállítást
        System.clearProperty("java.security.auth.login.config");
    }
}
