package in.hiresense.dbutils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.sql.Connection;
import java.sql.SQLException;


public class AppInitializer implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public AppInitializer() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized (when the Web application is deployed). */
        ServletContext context = sce.getServletContext();
        String dbUrl = context.getInitParameter("dbUrl");
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");
        DBConnection.openConnection(dbUrl, username, password);
        String appName = context.getInitParameter("appName");
        context.setAttribute("appName", appName);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        DBConnection.closeConnection();
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* The Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
 
