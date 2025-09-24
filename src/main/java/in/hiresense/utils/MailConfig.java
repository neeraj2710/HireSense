package in.hiresense.utils;

import javax.mail.Session;
import java.util.Properties;

public class MailConfig {

    public static Session getSession(){

        String username = AppConfig.getProperty("mail.username");
        String password = AppConfig.getProperty("mail.password");

        Properties prop = new Properties();
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port","465");
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.socketFactory.port","465");
        prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

        MyAuthenticator auth = new MyAuthenticator(username,password);
        return Session.getInstance(prop,auth);

    }



}
