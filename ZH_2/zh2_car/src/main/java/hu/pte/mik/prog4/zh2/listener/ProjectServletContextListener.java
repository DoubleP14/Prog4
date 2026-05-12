package hu.pte.mik.prog4.zh2.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectServletContextListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServletContextListener.class);
    private static final String JAVA_SECURITY_PROPERTY = "java.security.auth.login.config";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Source: " + sce.getSource());
        LOGGER.info("Path: " + sce.getServletContext().getContextPath());

        // JAVÍTÁS: Megmondjuk a Tomcatnek, hol keresse a JAAS konfigurációt!
        String jaasPath = sce.getServletContext().getRealPath("/WEB-INF/classes/jaas.config");
        System.setProperty(JAVA_SECURITY_PROPERTY, jaasPath);
    }

}
