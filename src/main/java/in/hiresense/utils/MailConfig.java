package in.hiresense.utils;

import javax.mail.Session;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailConfig {

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = MailConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input != null) {
                props.load(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static Session getSession(){
        Properties config = loadProperties();
        String username = config.getProperty("mail.username");
        String password = config.getProperty("mail.password");

        Properties prop = new Properties();
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.socketFactory.port","465");
        prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

        MyAuthenticator auth = new MyAuthenticator(username,password);
        return Session.getInstance(prop,auth);

    }



}
