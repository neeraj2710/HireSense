package in.hiresense.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

    public static void sendTextEmail(String recipientEmailAddresses, String subject, String body) throws MessagingException {
        Message message = new MimeMessage(MailConfig.getSession());
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recipientEmailAddresses));
        message.setSubject(subject);
        message.setText(body);
        Transport.send(message);
        System.out.println("mail Sent successfully");
    }

}
