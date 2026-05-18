package hu.pte.mik.prog4.potpotzh.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServletContextListener implements jakarta.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Webalkalmazás és a REST API sikeresen elindult!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Webalkalmazás leállt!");
    }
}