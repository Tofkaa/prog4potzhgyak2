package hu.pte.mik.prog4.potpotzh.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ProjectServletContextListener implements ServletContextListener {

    private static final String JAVA_SECURITY_PROPERTY =
            "java.security.auth.login.config";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if(System.getProperty(JAVA_SECURITY_PROPERTY) == null) {
            try{
                String jaasConfigFile = this.getClass().getClassLoader()
                        .getResource("jaas.config").getFile();

                System.setProperty(JAVA_SECURITY_PROPERTY, jaasConfigFile);

            } catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
    }
}
