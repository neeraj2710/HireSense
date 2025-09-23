package in.hiresense.utils;

import javax.mail.Session;
import java.util.Properties;

public class MailConfig {

    private static final String USER_NAME = "elevarehire@gmail.com";
    private static final String PASSWORD = "erqk bgts ufgz qrjl";

    public static Session getSession(){

        Properties prop = new Properties();
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.socketFactory.port","465");
        prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

        MyAuthenticator auth = new MyAuthenticator(MailConfig.USER_NAME,MailConfig.PASSWORD);
        return Session.getInstance(prop,auth);

    }
}
